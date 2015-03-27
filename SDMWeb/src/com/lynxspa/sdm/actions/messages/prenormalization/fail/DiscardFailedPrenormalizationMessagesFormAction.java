package com.lynxspa.sdm.actions.messages.prenormalization.fail;

import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.actions.messages.state.MessageStateChangeFormAction;

public class DiscardFailedPrenormalizationMessagesFormAction extends MessageStateChangeFormAction{

	@Override
	protected CAStatesEVENTMESSAGEFlow getToState(final CAEventMessage _eventMessage) {
		return CAStatesEVENTMESSAGEFlow.MSDS;
	}

	protected String getChangedMessageStatesCountLabelStart(){
		return "message.discard.countLabel.start";
	}

	protected String getChangedMessageStatesCountLabelEnd(){
		return "message.discard.countLabel.end";
	}
}
