package com.lynxspa.sdm.actions.messages.state;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.flow.utils.WorkflowUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.actions.messages.prenormalization.fail.RetryPrenormalizationFormAction;

public abstract class MessageStateChangeFormAction extends CoacEnhancedBasicAction{

	protected static final String PROPAGATE_ERROR_FLAG = "PROPAGATE_ERROR_FLAG";

	private static final String CHECKED_MESSAGES_PARAM = "checkedMessages";
	private static final String CHANGED_MESSAGE_STATES_COUNT = "CHANGED_MESSAGE_STATES_COUNT";
	private static final String CHANGED_MESSAGE_STATES_COUNT_LABEL_START = "CHANGED_MESSAGE_STATES_COUNT_LABEL_START";
	private static final String CHANGED_MESSAGE_STATES_COUNT_LABEL_END = "CHANGED_MESSAGE_STATES_COUNT_LABEL_END";
	private static final Logger logger = Logger.getLogger(RetryPrenormalizationFormAction.class);
	protected Map<String, Object> requestParams = null;
	
	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		this.requestParams = _parameters;
		
		int messageStatesChanged=0;
		CAEventMessage eventMessage=null;
		String[] eventMessageIds=null;
		Object checkedMessages=null;
		Session session=null;
		
		try{
			checkedMessages=_parameters.get(CHECKED_MESSAGES_PARAM);
			if(checkedMessages instanceof java.lang.String[]){
				eventMessageIds=(String[])(checkedMessages);
			}else{
				eventMessageIds=new String[]{(String)checkedMessages};
			}
			session=_session.getSessionFactory().openSession();
			for(int ic1=0;ic1<eventMessageIds.length;ic1++){
				HibernateUtils.beguinTransaction(session);
				try {
					Long eventMessageId = Long.parseLong(eventMessageIds[ic1]);
					eventMessage=(CAEventMessage)session.get(CAEventMessage.class, eventMessageId);
					if(_request.getAttribute(MessageStateChangeFormAction.PROPAGATE_ERROR_FLAG)!=null){
			//			WorkflowUtils.changeState(SDMConfigManager.getInstance(), _user.getLocale().getLanguage(), _user.getId(), session, eventMessage, getToState(eventMessageId),null,new ExceptionStateClone(eventMessage.getOperationStatus()));
					}else{
						WorkflowUtils.changeState(SDMConfigManager.getInstance(), _user.getLocale().getLanguage(), _user.getId(), session, eventMessage, getToState(eventMessage));
					}
					HibernateUtils.commitTransaction(session);
					++messageStatesChanged;
				}catch(Exception e){	
					logger.warn(e);
					HibernateUtils.rollbackTransaction(session);
				}
			}
			session.close();
			if(messageStatesChanged > 0){
				_request.getSession().setAttribute(CHANGED_MESSAGE_STATES_COUNT, String.valueOf(messageStatesChanged));
				_request.getSession().setAttribute(CHANGED_MESSAGE_STATES_COUNT_LABEL_START, getChangedMessageStatesCountLabelStart());
				_request.getSession().setAttribute(CHANGED_MESSAGE_STATES_COUNT_LABEL_END, getChangedMessageStatesCountLabelEnd());
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new FPMException(e);
		}
	}

	protected String getChangedMessageStatesCountLabelStart(){
		return " ";
	}

	protected String getChangedMessageStatesCountLabelEnd(){
		return " message states were successfully changed. ";
	}
	
	protected abstract CAStatesEVENTMESSAGEFlow getToState(final CAEventMessage _eventMessage) throws Exception;
}
