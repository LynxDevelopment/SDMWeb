package com.lynxspa.sdm.core.model.dictionaries.states;

import com.lynxspa.entities.application.flow.adapters.StatesDictAdapter;

public enum FlowTASKStateDict implements StatesDictAdapter {

	NORMAL("task.state.normal", "Indicates that the Trigger is in the normal state", true, false),
	PAUSED("task.state.paused", "Indicates that the Trigger is in the paused state", false, false);

	private String	name		= null;
	private String	description	= null;
	private boolean	initial		= false;
	private boolean	end			= false;

	FlowTASKStateDict(String _name, String _description, boolean _initial, boolean _end) {
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
