package com.lynxspa.sdm.actions.matching;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTCOLLECTEDFlow;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.flow.Flow;
import com.lynxspa.entities.application.flow.State;
import com.lynxspa.entities.application.flow.StateId;
import com.lynxspa.entities.application.flow.operations.OperationStatus;
import com.lynxspa.entities.application.flow.utils.WorkflowUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.events.EventMainValidator;
import com.lynxspa.sdm.events.EventValidator;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.xweb.actions.EnhancedBasicAction;

public class MatchEventsFormAction extends EnhancedBasicAction {
	private static final String EVENT_GROUP_FLOW_CODE = "EVENTGROUP";
	private static final CAStatesEVENTGROUPFlow NEW_EVENT_GROUP_FLOW = CAStatesEVENTGROUPFlow.PMRS;
	private static final String STATUS_PARAM = "matchEventStatus";
	private static final String SUCCESS_FLAG = "EVENT_MATCHED";
	private static final String IS_NEW_GROUP_KEY = "isNewGroup";
	private static final String NEW_GROUP_EXECUTION_DATE_KEY = "newGroupExecutionDate";
	private static final String NEW_GROUP_OPERATIONAL_DATE_KEY = "newGroupOperationalDate";
	private static final String NEW_GROUP_SUBSCRIPTION_DATE_KEY = "newGroupSubscriptionDate";
	private static final String NEW_GROUP_EXPIRATION_DATE_KEY = "newGroupExpirationDate";
	private static final String SELECTED_EVENTGROUP_KEY = "selectedGroup";
	private static final String CHECKED_EVENTS_KEY = "checkedEvents";
	private static final Logger LOGGER = Logger.getLogger(MatchEventsFormAction.class);
	private Long groupId = null;
	private String[] eventIds = null;
	private Date newGroupExecutionDate = null;
	private Date newGroupOperationalDate = null;
	private Date newGroupSubscriptionDate = null;
	private Date newGroupExpirationDate = null;
	
	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		StringBuffer logBuffer = null; 
		String logDelimitter = "";
		CAEventGroup eventGroup = null;
		List<CAEventCollected> eventList = null;
		
		try{
			if(null != this.groupId){
				eventGroup = (CAEventGroup)(this.getCurrentSession().get(CAEventGroup.class, groupId));
			}
			else{
				eventGroup = new CAEventGroup(_user.getId());
			}
			
			eventList = new ArrayList<CAEventCollected>();
			for(int i=0; i<this.eventIds.length; i++){
				eventList.add((CAEventCollected)(this.getCurrentSession().get(CAEventCollected.class, Long.parseLong(eventIds[i]))));
			}

			if(null == this.groupId){
				eventGroup.setEventType(eventList.get(0).getEventType());
				eventGroup.setExecutionDate(this.newGroupExecutionDate);			
				eventGroup.setOperationStatus(getOperationStatus(_session));
				if(null != this.newGroupOperationalDate)eventGroup.setOperationalDate(this.newGroupOperationalDate);
				if(null != this.newGroupSubscriptionDate)eventGroup.setSubscriptionDate(this.newGroupSubscriptionDate);
				if(null != this.newGroupExpirationDate)eventGroup.setExpirationDate(this.newGroupExpirationDate);
				eventGroup.setSecurity(eventList.get(0).getSecurity());
				HibernateUtils.customSave(_session, eventGroup, _user.getId());
				LOGGER.info("Event group " + eventGroup.getId() + " was successfully created. ");
			}
		
			logBuffer = new StringBuffer();
			for(Iterator<CAEventCollected> it = eventList.iterator(); it.hasNext();){
				CAEventCollected event = it.next();
				event.setEventGroup(eventGroup);
				logBuffer.append(logDelimitter).append(event.getId());
				WorkflowUtils.changeState(SDMConfigManager.getInstance(), _user.getLocale().getLanguage(), _user.getId(), _session, event, CAStatesEVENTCOLLECTEDFlow.MTCH);
				HibernateUtils.customSave(_session, event, _user.getId());
				logDelimitter = ", ";
			}
		}
		catch(Exception e){
			LOGGER.error("Error saving matched events.", e);
			throw new FPMException(ErrorDict.MATCH_EVENTS_SAVE_ERROR, e);
		}
		_request.getSession().setAttribute(MatchEventsFormAction.STATUS_PARAM, MatchEventsFormAction.SUCCESS_FLAG);
		LOGGER.info("Event group " + eventGroup.getId() + " was successfully updated. The following event(s) were added: " + logBuffer + ". ");
	}

	@Override
	public void validate(Map<String, Object> paramMap, List<ValidationException> errorList) throws FPMException {
		EventValidator eventValidator = new EventMainValidator(this.getCurrentSession());
		this.groupId = null;
		this.eventIds = null;
		String groupIdStr = null;
		boolean isNewGroup = false;
		this.newGroupExecutionDate = null;
		this.newGroupOperationalDate = null;
		this.newGroupSubscriptionDate = null;
		this.newGroupExpirationDate = null;

		this.clearErrors();
		
		try{
			eventIds = (String[])paramMap.get(MatchEventsFormAction.CHECKED_EVENTS_KEY);
			if(null == eventIds || eventIds.length == 0){
				errorList.add(new ValidationException("events.matching.error.no_events_selected", MatchEventsFormAction.CHECKED_EVENTS_KEY, ""));		
			}
		}
		catch(Exception e){
			LOGGER.error("Form submission error. Could not find selected events checkbox array.", e);
			throw new FPMException(ErrorDict.NO_EVENTS_CHECKBOX_ARRAY_ERROR, new Object[]{MatchEventsFormAction.CHECKED_EVENTS_KEY}, e);
		}

		try{
			isNewGroup = Boolean.parseBoolean((String)paramMap.get(MatchEventsFormAction.IS_NEW_GROUP_KEY)); 
		}
		catch(Exception e){
			throw new FPMException(ErrorDict.NO_MATCH_EVENT_NEW_GROUP_CHECKBOX_ERROR, new Object[]{MatchEventsFormAction.IS_NEW_GROUP_KEY});
		}

		if(!isNewGroup){
			try{
				groupIdStr = (String)paramMap.get(MatchEventsFormAction.SELECTED_EVENTGROUP_KEY);
				if(null == groupIdStr || 0 == groupIdStr.trim().length()){
					errorList.add(new ValidationException("events.matching.error.no_eventGroup_selected", MatchEventsFormAction.SELECTED_EVENTGROUP_KEY, "value = " + String.valueOf(groupIdStr)));		
				}
			}
			catch(Exception e){
				LOGGER.error("Form submission error. Could not find selected event group field.", e);
				throw new FPMException(ErrorDict.NO_EVENTGROUP_FIELD_ERROR, new Object[]{MatchEventsFormAction.SELECTED_EVENTGROUP_KEY}, e);
			}
					
			try{
				this.groupId = Long.parseLong(groupIdStr);
			}
			catch(Exception e){
				errorList.add(new ValidationException("events.matching.error.no_eventGroup_found", MatchEventsFormAction.SELECTED_EVENTGROUP_KEY, "value = " + String.valueOf(groupIdStr)));		
			}
		}
		else{
			if(eventValidator.isBlank(paramMap.get(MatchEventsFormAction.NEW_GROUP_EXECUTION_DATE_KEY))){									// exec date is required
				errorList.add(new ValidationException("error.blank_date", MatchEventsFormAction.NEW_GROUP_EXECUTION_DATE_KEY, ""));
			}
			else{
				Object newGroupExecutionDateVal = paramMap.get(MatchEventsFormAction.NEW_GROUP_EXECUTION_DATE_KEY);
				if(eventValidator.isValidTimestamp(newGroupExecutionDateVal)){
					this.newGroupExecutionDate = eventValidator.toDate(newGroupExecutionDateVal);
				}
				else{
					errorList.add(new ValidationException("error.invalid_date", MatchEventsFormAction.NEW_GROUP_EXECUTION_DATE_KEY, String.valueOf(newGroupExecutionDateVal)));
				}
			}

			if(!eventValidator.isBlank(paramMap.get(MatchEventsFormAction.NEW_GROUP_OPERATIONAL_DATE_KEY))){								// operational date is optional
				Object newGroupOperationalDateVal = paramMap.get(MatchEventsFormAction.NEW_GROUP_OPERATIONAL_DATE_KEY);
				if(eventValidator.isValidTimestamp(newGroupOperationalDateVal)){
					this.newGroupOperationalDate = eventValidator.toDate(newGroupOperationalDateVal);
				}
				else{
					errorList.add(new ValidationException("error.invalid_date", MatchEventsFormAction.NEW_GROUP_OPERATIONAL_DATE_KEY, String.valueOf(newGroupOperationalDateVal)));
				}
			}

			if(!eventValidator.isBlank(paramMap.get(MatchEventsFormAction.NEW_GROUP_SUBSCRIPTION_DATE_KEY))){								// subscription date is optional
				Object newGroupSubscriptionDateVal = paramMap.get(MatchEventsFormAction.NEW_GROUP_SUBSCRIPTION_DATE_KEY);
				if(eventValidator.isValidTimestamp(newGroupSubscriptionDateVal)){
					this.newGroupSubscriptionDate = eventValidator.toDate(newGroupSubscriptionDateVal);
				}
				else{
					errorList.add(new ValidationException("error.invalid_date", MatchEventsFormAction.NEW_GROUP_SUBSCRIPTION_DATE_KEY, String.valueOf(newGroupSubscriptionDateVal)));
				}
			}

			if(!eventValidator.isBlank(paramMap.get(MatchEventsFormAction.NEW_GROUP_EXPIRATION_DATE_KEY))){									// expiration date is optional
				Object newGroupExpirationDateVal = paramMap.get(MatchEventsFormAction.NEW_GROUP_EXPIRATION_DATE_KEY);
				if(eventValidator.isValidTimestamp(newGroupExpirationDateVal)){
					this.newGroupExpirationDate = eventValidator.toDate(newGroupExpirationDateVal);
				}
				else{
					errorList.add(new ValidationException("error.invalid_date", MatchEventsFormAction.NEW_GROUP_EXPIRATION_DATE_KEY, String.valueOf(newGroupExpirationDateVal)));
				}
			}
		}
	}

	private OperationStatus getOperationStatus(Session session) throws FPMException{
		OperationStatus operationStatus = null;
		try{
			Flow flow = (Flow)session.get(Flow.class, MatchEventsFormAction.EVENT_GROUP_FLOW_CODE);
			String code = MatchEventsFormAction.NEW_EVENT_GROUP_FLOW.getId();
			StateId stateId = new StateId(flow, code);
			operationStatus = new OperationStatus((State)session.get(State.class, stateId));
		}
		catch(Exception e){
			throw new FPMException(e);
		}
		return operationStatus;
	}

	@SuppressWarnings("unused")
	private Date getGroupExecutionDate(List<CAEventCollected> eventList){
		Date groupExecutionDate = null;
		Map<Date, Integer> dateMap = new HashMap<Date,Integer>();
		for(Iterator<CAEventCollected> it = eventList.iterator(); it.hasNext();){
			Date date = it.next().getExecutionDate();
			if(dateMap.containsKey(date)){
				dateMap.put(date, dateMap.get(date) + 1 );
			}
			else{
				dateMap.put(date, new Integer(1));
			}
		}
		Integer curMax = 0;
		for(Entry<Date, Integer> e: dateMap.entrySet()){
			if(e.getValue() > curMax){
				groupExecutionDate = e.getKey();
				curMax = e.getValue();
			}
		}
		return groupExecutionDate;
	}
}
