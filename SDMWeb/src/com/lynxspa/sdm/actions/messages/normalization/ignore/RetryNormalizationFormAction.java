package com.lynxspa.sdm.actions.messages.normalization.ignore;

import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.actions.messages.state.MessageStateChangeFormAction;

public class RetryNormalizationFormAction extends MessageStateChangeFormAction {

	@Override
	protected CAStatesEVENTMESSAGEFlow getToState(final CAEventMessage _eventMessage) {
		
		CAStatesEVENTMESSAGEFlow reply=CAStatesEVENTMESSAGEFlow.PNRM;
		
		if(LogInfoDict.SECURITY_MESSAGE_NOT_IN_PRINCIPAL_MARKET.getMessageKey().equals(_eventMessage.getOperationStatus().getTransitionMessageKey())){
			reply=CAStatesEVENTMESSAGEFlow.PRSD;
		}
		
		return reply;
	}

	protected String getChangedMessageStatesCountLabelStart(){
		return "message.retryNormalization.countLabel.start";
	}

	protected String getChangedMessageStatesCountLabelEnd(){
		return "message.retryNormalization.countLabel.end";
	}
}
