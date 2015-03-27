package com.lynxspa.sdm.core.model.dictionaries;

import com.lynxspa.entities.application.flow.adapters.FlowsDictAdapter;

public enum WorkflowDict implements FlowsDictAdapter {
	TASKEXECUTION("workflow.task.execution", "Task execution workflow for execution control"),
	TASK("workflow.task", "Task workflow for schedule task control"), ;

	private String	name		= null;
	private String	description	= null;

	WorkflowDict(String _name, String _description) {
		this.name = _name;
		this.description = _description;
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
}
