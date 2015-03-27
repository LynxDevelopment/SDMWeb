package com.lynxspa.sdm.core.model.dictionaries.transitions;

import com.lynxspa.entities.application.flow.adapters.StatesDictAdapter;
import com.lynxspa.entities.application.flow.adapters.TransitionsDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.sdm.core.model.dictionaries.states.FlowTASKStateDict;

public enum FlowTASKTransitionDict implements TransitionsDictAdapter {

	NORMAL_PAUSED("Normal to Paused", "Task change state from NORMAL to PAUSED", FlowTASKStateDict.NORMAL, FlowTASKStateDict.PAUSED, null, null),
	PAUSED_NORMAL("Paused to Normal", "Task chage state from PAUSED to NORMAL", FlowTASKStateDict.PAUSED, FlowTASKStateDict.NORMAL, null, null);

	private String				name					= null;
	private String				description				= null;
	private StatesDictAdapter	initialState			= null;
	private StatesDictAdapter	endState				= null;
	private String				transitionExecutorClass	= null;
	private LogDictAdapter		logMessage				= null;

	FlowTASKTransitionDict(String _name, String _description, StatesDictAdapter _initialState, StatesDictAdapter _endState, String _transitionExecutorClass, LogDictAdapter _logMessage) {
		this.name = _name;
		this.description = _description;
		this.initialState = _initialState;
		this.endState = _endState;
		this.transitionExecutorClass = _transitionExecutorClass;
		this.logMessage = _logMessage;
	}

	public String getId() {
		return this.name();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public StatesDictAdapter getInitialState() {
		return initialState;
	}

	public StatesDictAdapter getEndState() {
		return endState;
	}

	public String getTransitionExecutorClass() {
		return transitionExecutorClass;
	}

	public LogDictAdapter getMessageLog() {
		return logMessage;
	}
}
