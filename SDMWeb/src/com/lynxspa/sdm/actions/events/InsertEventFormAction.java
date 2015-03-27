package com.lynxspa.sdm.actions.events;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.dictionaries.providers.BasicProviders;
import com.lynxspa.sdm.entities.events.configuration.CAEventConfig;
import com.lynxspa.sdm.entities.events.providers.CAEventProvider;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.events.EventDetailBuilder;
import com.lynxspa.sdm.events.EventDetailValidator;
import com.lynxspa.sdm.events.EventInserter;
import com.lynxspa.sdm.events.EventMainValidator;
import com.lynxspa.sdm.exceptions.ErrorDict;

public class InsertEventFormAction extends CoacEnhancedBasicAction {
	
	private static final String EVENT_TYPE_PATH_ID_PREFIX = "pathId_";
	private static final int EVENT_TYPE_PATH_ID_START_POS = InsertEventFormAction.EVENT_TYPE_PATH_ID_PREFIX.length();
	private static final String EVENT_TYPE_PARAM = "eventTypeDropdown";
	private static final String SECURITY_ID_PARAM = "securityId";
	public static final String EVENT_GROUP_ID_PARAM = "eventGroupId";
	private static final String EXECUTION_DATE_PARAM = "executionDate";
	private static final String SUBSCRIPTION_DATE_PARAM = "subscriptionDate";
	private static final String EXPIRATION_DATE_PARAM = "expirationDate";
	private static final String OPERATIONAL_DATE_PARAM = "operationalDate";
	private static final String STATUS_PARAM = "insertEventStatus";
	private static final String SUCCESS = "SUCCESS";
	private static final long NOT_EVENT_TYPE_PATH_FLAG = -1L;
	private static final long NO_EVENT_GROUP_FLAG = -1L;
	private static final Logger logger = Logger.getLogger(InsertEventFormAction.class);
	private Long securityId = -1L;
	private Long eventGroupId = NO_EVENT_GROUP_FLAG;
	private String eventTypeCode = null;
	private Date executionDate = null;
	private Date subscriptionDate = null;
	private Date expirationDate = null;
	private Date operationalDate = null;
	private Map<Long, Object> validPaths = null;
	
	public void validate(Map<String, Object> params, List<ValidationException> errorList) throws FPMException{
		EventDetailValidator eventDetailValidator = null;
		EventMainValidator eventValidator = null;
		this.validPaths = new HashMap<Long, Object>();
		Session session= null;
		this.executionDate = null;
		this.subscriptionDate = null;
		this.expirationDate = null;
		this.operationalDate = null;
		this.eventTypeCode = null;
		
		try{
			session=getCurrentSession();
			
			eventDetailValidator = new EventDetailValidator(session);
			eventValidator = new EventMainValidator(session);

			if(eventValidator.isBlank(params.get(InsertEventFormAction.EVENT_TYPE_PARAM))){
				errorList.add(new ValidationException("event.error.blank_eventType", InsertEventFormAction.EVENT_TYPE_PARAM, ""));
			}
			else{
				this.eventTypeCode = ((String[])(params.get(InsertEventFormAction.EVENT_TYPE_PARAM)))[0];
				
				if(!eventValidator.isValidEventTypeCode(this.eventTypeCode)){
					errorList.add(new ValidationException("event.error.invalid_eventType_code", InsertEventFormAction.EVENT_TYPE_PARAM, String.valueOf(this.eventTypeCode)));
				}
			}
			
			if(eventValidator.isBlank(params.get(InsertEventFormAction.SECURITY_ID_PARAM))){
				errorList.add(new ValidationException("event.error.blank_security_id", InsertEventFormAction.SECURITY_ID_PARAM, ""));
			}
			else{
				if(eventValidator.isValidLong(params.get(InsertEventFormAction.SECURITY_ID_PARAM))){
					this.securityId = Long.parseLong((String)params.get(InsertEventFormAction.SECURITY_ID_PARAM));

					if(!eventValidator.isValidSecurityId(this.securityId)){
						errorList.add(new ValidationException("event.error.nonexistent_security_id", InsertEventFormAction.SECURITY_ID_PARAM, String.valueOf(this.securityId)));
					}
				}
				else{
					errorList.add(new ValidationException("event.error.invalid_security_id", InsertEventFormAction.SECURITY_ID_PARAM, String.valueOf(this.securityId)));
				}
			}

			/*
			 * Validates optional eventGroup param. Skips if field not present
			 */
			Object rawEventGroupId = params.get(InsertEventFormAction.EVENT_GROUP_ID_PARAM);
			if(!eventValidator.isBlank(rawEventGroupId)){
				if(eventValidator.isValidLong(rawEventGroupId)){
					this.eventGroupId = Long.parseLong((String)rawEventGroupId);
					if(!eventValidator.isValidEventGroupId(this.eventGroupId)){
						errorList.add(new ValidationException("event.error.nonexistent_eventGroup_id", InsertEventFormAction.EVENT_GROUP_ID_PARAM, String.valueOf(rawEventGroupId)));
					}
				}
				else{
					errorList.add(new ValidationException("event.error.invalid_eventGroup_id", InsertEventFormAction.EVENT_GROUP_ID_PARAM, String.valueOf(rawEventGroupId)));
				}
			}

			if(eventValidator.isBlank(params.get(InsertEventFormAction.EXECUTION_DATE_PARAM))){
				errorList.add(new ValidationException("error.blank_date", InsertEventFormAction.EXECUTION_DATE_PARAM, ""));
			}
			else{
				Object executionDateVal = params.get(InsertEventFormAction.EXECUTION_DATE_PARAM);

				if(eventValidator.isValidTimestamp(executionDateVal)){
					this.executionDate = eventValidator.toDate(executionDateVal);
				}
				else{
					errorList.add(new ValidationException("error.invalid_date", InsertEventFormAction.EXECUTION_DATE_PARAM, String.valueOf(executionDateVal)));
				}
			}
			
			Object dateVal = null;
			dateVal = params.get(InsertEventFormAction.SUBSCRIPTION_DATE_PARAM);

			if(!eventValidator.isBlank(dateVal)){
				if(eventValidator.isValidTimestamp(dateVal)){
					this.subscriptionDate = eventValidator.toDate(dateVal);
				}
				else{
					errorList.add(new ValidationException("error.invalid_date", InsertEventFormAction.SUBSCRIPTION_DATE_PARAM, String.valueOf(this.subscriptionDate)));
				}
			}
			
			dateVal = params.get(InsertEventFormAction.EXPIRATION_DATE_PARAM);
			if(!eventValidator.isBlank(dateVal)){
				if(eventValidator.isValidTimestamp(dateVal)){
					this.expirationDate = eventValidator.toDate(dateVal);
				}
				else{
					errorList.add(new ValidationException("error.invalid_date", InsertEventFormAction.EXPIRATION_DATE_PARAM, String.valueOf(this.expirationDate)));
				}
			}

			dateVal = params.get(InsertEventFormAction.OPERATIONAL_DATE_PARAM);
			if(!eventValidator.isBlank(dateVal)){
				if(eventValidator.isValidTimestamp(dateVal)){
					this.operationalDate = eventValidator.toDate(dateVal);
				}
				else{
					errorList.add(new ValidationException("error.invalid_date", InsertEventFormAction.OPERATIONAL_DATE_PARAM, String.valueOf(this.operationalDate)));
				}
			}

			Query query = null;
			CAEventConfig eventConfig=null;
			
			query = session.createQuery("from CAEventConfig eventConfig where eventConfig.eventType.id=:eventTypeId");
			query.setParameter("eventTypeId", this.eventTypeCode);
			eventConfig = (CAEventConfig)query.uniqueResult();
			
			for(Map.Entry<String, Object> param : params.entrySet()){
				String key = null;
				Object value = null;
				key = param.getKey();
				value = param.getValue();
				Long typeId = getEventTypeDetailId(key);
				if(typeId != InsertEventFormAction.NOT_EVENT_TYPE_PATH_FLAG){
					if(null != value && !eventDetailValidator.isBlank(value)){											// Ignoring nulls and blanks
						if (value instanceof String[]){
							value = ((String[])value)[0];
						}
						if(eventDetailValidator.isValidField(typeId, value)){
							value = eventDetailValidator.convertFieldValue(typeId, value);
							this.validPaths.put(typeId, value);
						}
						else{
							errorList.add(new ValidationException("error.bad_field", key, value.toString()));			// TODO localised message
						}
					}else if(null != value && eventDetailValidator.isBlank(value) && eventDetailValidator.isRequiredField(session, eventConfig, typeId) ){
						errorList.add(new ValidationException("error.mandatory.field", key, ""));
					}
				}
			}
		}
		catch(Exception e){
			logger.error("Error during validation routine. ", e);
			throw new FPMException(e);
		}
	}
	
	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		EventDetailBuilder eventDetailBuilder = null;
		EventInserter eventInserter = null;
		CAEventProvider eventProvider = null;
		
		try{
			eventDetailBuilder = new EventDetailBuilder(_session, _user.getId());
			eventDetailBuilder.setExecutionDate(this.executionDate);
			eventDetailBuilder.setSubscriptionDate(this.subscriptionDate);
			eventDetailBuilder.setExpirationDate(this.expirationDate);
			eventDetailBuilder.setOperationalDate(this.operationalDate);
			for(Entry<Long, Object> e: this.validPaths.entrySet()){
				eventDetailBuilder.addField(e.getKey(), e.getValue());
			}
			eventProvider = (CAEventProvider)(_session.get(CAEventProvider.class, BasicProviders.MANUAL.getCode()));	
			eventInserter = new EventInserter(_session, _user.getId(), eventProvider, this.eventTypeCode, this.securityId, this.executionDate, eventDetailBuilder);
			eventInserter.setExecutionDate(this.executionDate);
			eventInserter.setSubscriptionDate(this.subscriptionDate);
			eventInserter.setExpirationDate(this.expirationDate);
			eventInserter.setOperationalDate(this.operationalDate);
			if(this.eventGroupId > NO_EVENT_GROUP_FLAG){
				eventInserter.setEventGroup(this.eventGroupId);
			}
			
			try{
				eventDetailBuilder.save();
				logger.info("Event detail created at " + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())) + ", ID = " + eventDetailBuilder.getCaEventDetail().getId() + ". ");
			}
			catch(Exception e){
				logger.error("Error inserting event. ", e);
				throw new FPMException(ErrorDict.EVENT_DETAIL_SAVE_ERROR , e);
			}

			if(eventInserter.isComplete()){
				try{
					eventInserter.save();
					logger.info("Event inserted at " + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())) + ", ID = " + eventInserter.getEvent().getId() + ". ");
					_request.getSession().setAttribute(InsertEventFormAction.STATUS_PARAM, InsertEventFormAction.SUCCESS);
					this.clearForm();
				}
				catch(Exception e){
					logger.error("Error inserting event. ", e);
					throw new FPMException(ErrorDict.INSERTED_EVENT_NOT_SAVED_ERROR, e);
				}
			}
			else{
				logger.error("Error inserting event. (Event not complete)");
				throw new FPMException(ErrorDict.EVENT_NOT_COMPLETE_ERROR);
			}
		}
		catch(Exception e){
			logger.error("Error inserting event. ", e);
			throw new FPMException(ErrorDict.INSERT_EVENT_ERROR, e);
		}
	}

	private Long getEventTypeDetailId(String rawParam){
		Long id = InsertEventFormAction.NOT_EVENT_TYPE_PATH_FLAG;
		if(rawParam.startsWith(InsertEventFormAction.EVENT_TYPE_PATH_ID_PREFIX)){
			id = Long.parseLong(rawParam.substring(InsertEventFormAction.EVENT_TYPE_PATH_ID_START_POS)); 
		}
		return id;
	}
}
