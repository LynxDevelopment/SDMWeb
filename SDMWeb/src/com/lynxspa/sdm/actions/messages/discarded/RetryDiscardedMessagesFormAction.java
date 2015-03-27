package com.lynxspa.sdm.actions.messages.discarded;

import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.actions.messages.state.MessageStateChangeFormAction;

public class RetryDiscardedMessagesFormAction extends MessageStateChangeFormAction {

	private static final String EVENTMESSAGE_CURRENT_STATE_PREFIX  = "eventMessageCurrentState_";
	
	@Override
	protected CAStatesEVENTMESSAGEFlow getToState(final CAEventMessage _eventMessage) throws Exception{

		String eventMessageCurrentState = null;

		try{
			eventMessageCurrentState = (String)(super.requestParams.get(EVENTMESSAGE_CURRENT_STATE_PREFIX + _eventMessage.getId()));
		}
		catch(Exception e){
			throw new Exception("Could not retrieve request scope parameter:\"" + (EVENTMESSAGE_CURRENT_STATE_PREFIX + _eventMessage.getId()) + "\". ");
		}

		if(CAStatesEVENTMESSAGEFlow.DISC.getId().equals(eventMessageCurrentState)){
			return CAStatesEVENTMESSAGEFlow.PNRM;
		}else if(CAStatesEVENTMESSAGEFlow.MSDS.getId().equals(eventMessageCurrentState)){
			return CAStatesEVENTMESSAGEFlow.PRSD;
		}else{
			throw new Exception("Could not handle EventMessage current state id code (" + (EVENTMESSAGE_CURRENT_STATE_PREFIX + _eventMessage.getId()) + " = " + eventMessageCurrentState + "). ");
		}
	}

	protected String getChangedMessageStatesCountLabelStart(){
		return "message.discarded.retry.success.start";
	}

	protected String getChangedMessageStatesCountLabelEnd(){
		return "message.discarded.retry.success.end";
	}
}
