package com.lynxspa.sdm.actions.messages.prenormalization.fail;

import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.actions.messages.state.MessageStateChangeFormAction;

public class RetryPrenormalizationFormAction extends MessageStateChangeFormAction{

	@Override
	protected CAStatesEVENTMESSAGEFlow getToState(final CAEventMessage _eventMessage) {
		return CAStatesEVENTMESSAGEFlow.PRSD;
	}

	protected String getChangedMessageStatesCountLabelStart(){
		return "message.retryPrenormalization.countLabel.start";
	}

	protected String getChangedMessageStatesCountLabelEnd(){
		return "message.retryPrenormalization.countLabel.end";
	}
}
