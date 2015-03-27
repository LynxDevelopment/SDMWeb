package com.lynxspa.sdm.core.services.quartz.api;

import com.lynxspa.monitor.ProcessMonitorAdapter;

public class TaskExecutionMonitor implements ProcessMonitorAdapter {

	private long				rate				= 2000l;
	private long				start				= 0l;
	private long				end					= 0l;
	private long				currentProgress		= 0l;
	private double				conversionRate		= 0.0d;
	private TaskExecutionBean	taskExecutionBean	= null;

	public TaskExecutionMonitor(final TaskExecutionBean _taskExecutionBean, final long _rate) {
		this.rate = (_rate > 0l) ? _rate : 1l;
		this.start = _taskExecutionBean.getActualReferenceProgress();
		this.taskExecutionBean = _taskExecutionBean;
	}

	public void incrementProgress(final long _progress) {
		this.currentProgress += _progress;
		final long newValue = (this.end > this.currentProgress) ? ((long) (this.currentProgress * conversionRate)) : this.rate;
		this.taskExecutionBean.setActualReferenceProgress(this.start + newValue);
	}

	public void setTotal(final long _total) {
		this.end = (_total > 0l) ? _total : this.rate;
		this.conversionRate = (double) this.rate / (double) this.end;
	}

	public void end() {
		this.currentProgress = this.end;
		this.taskExecutionBean.setActualReferenceProgress(this.start + this.rate);
	}
}