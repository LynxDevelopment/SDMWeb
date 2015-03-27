package com.lynxspa.sdm.core.model.dictionaries.states;

import com.lynxspa.entities.application.flow.adapters.StatesDictAdapter;

public enum FlowTASKEXECUTIONStateDict implements StatesDictAdapter {

	WAIT("task.waiting.execution", "Process has been created and wait for execution", true, false),
	EXEC("task.executing", "Process is executing", false, false),
	EXER("task.execution.error", "Process has errors during execution.{0}", false, false),
	FAIL("task.execution.failed", "Process has been failed.", false, true),
	NDER("task.execution.ends.error", "Process has been finalized with errors.", false, true),
	NDOK("task.execution.ends", "Process has been finalized", false, true),
	NODT("task.execution.ends.without.processed", "Process has been finalized without data processed", false, true),
	CANC("task.execution.ends.cancel", "Process has been canceled because was running before restart server application", false, true), ;

	private String	name		= null;
	private String	description	= null;
	private boolean	initial		= false;
	private boolean	end			= false;

	FlowTASKEXECUTIONStateDict(String _name, String _description, boolean _initial, boolean _end) {
		this.name = _name;
		this.description = _description;
		this.initial = _initial;
		this.end = _end;
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

	public boolean isInitial() {
		return initial;
	}

	public boolean isEnd() {
		return end;
	}
}
