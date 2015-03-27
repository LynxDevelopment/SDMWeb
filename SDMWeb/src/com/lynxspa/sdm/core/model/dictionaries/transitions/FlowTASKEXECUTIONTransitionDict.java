package com.lynxspa.sdm.core.model.dictionaries.transitions;

import com.lynxspa.entities.application.flow.adapters.StatesDictAdapter;
import com.lynxspa.entities.application.flow.adapters.TransitionsDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.sdm.core.model.dictionaries.states.FlowTASKEXECUTIONStateDict;

public enum FlowTASKEXECUTIONTransitionDict implements TransitionsDictAdapter {

	WAIT_EXEC("Start execution", "Process starts it's execution.", FlowTASKEXECUTIONStateDict.WAIT, FlowTASKEXECUTIONStateDict.EXEC, null, null),
	WAIT_CANC("Cancel wait execution", "Cancel wait execution.", FlowTASKEXECUTIONStateDict.WAIT, FlowTASKEXECUTIONStateDict.CANC, null, null),
	EXEC_EXER("Stop by error", "Process execution generate error.", FlowTASKEXECUTIONStateDict.EXEC, FlowTASKEXECUTIONStateDict.EXER, null, null),
	EXER_EXEC("Retry execution", "Process continue it's execution from previous error position.", FlowTASKEXECUTIONStateDict.EXER, FlowTASKEXECUTIONStateDict.EXEC, null, null),
	EXER_EXER("Retry error", "Process continues running with errors", FlowTASKEXECUTIONStateDict.EXER, FlowTASKEXECUTIONStateDict.EXER, null, null),
	EXEC_NDOK("End execution", "Process has been finalized it's execution.", FlowTASKEXECUTIONStateDict.EXEC, FlowTASKEXECUTIONStateDict.NDOK, null, null),
	EXER_NDER("End execution with errors", "Process has been finalized with errors.", FlowTASKEXECUTIONStateDict.EXER, FlowTASKEXECUTIONStateDict.NDER, null, null),
	EXEC_NDER("End execution with errors", "Process has been finalized with errors.", FlowTASKEXECUTIONStateDict.EXEC, FlowTASKEXECUTIONStateDict.NDER, null, null),
	EXEC_NODT("End without processed data", "Process has been finalized without data processed.", FlowTASKEXECUTIONStateDict.EXEC, FlowTASKEXECUTIONStateDict.NODT, null, null),
	EXER_FAIL("End execution failed", "Process has been failed.", FlowTASKEXECUTIONStateDict.EXER, FlowTASKEXECUTIONStateDict.FAIL, null, null),
	EXEC_FAIL("End execution failed", "Process has been failed.", FlowTASKEXECUTIONStateDict.EXEC, FlowTASKEXECUTIONStateDict.FAIL, null, null),
	EXEC_CANC("Cancel running execution", "Cancel running execution.", FlowTASKEXECUTIONStateDict.EXEC, FlowTASKEXECUTIONStateDict.CANC, null, null),
	EXER_CANC("Cancel execution with errors", "Cancel execution with errors.", FlowTASKEXECUTIONStateDict.EXER, FlowTASKEXECUTIONStateDict.CANC, null, null), ;

	private String				name					= null;
	private String				description				= null;
	private StatesDictAdapter	initialState			= null;
	private StatesDictAdapter	endState				= null;
	private String				transitionExecutorClass	= null;
	private LogDictAdapter		logMessage				= null;

	FlowTASKEXECUTIONTransitionDict(String _name, String _description, StatesDictAdapter _initialState, StatesDictAdapter _endState, String _transitionExecutorClass, LogDictAdapter _logMessage) {
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
