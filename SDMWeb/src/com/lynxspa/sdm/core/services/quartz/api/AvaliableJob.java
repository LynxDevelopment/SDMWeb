package com.lynxspa.sdm.core.services.quartz.api;

import java.util.ArrayList;
import java.util.List;

import org.quartz.StatefulJob;

import com.lynxspa.exception.FPMRuntimeException;
import com.lynxspa.sdm.core.services.quartz.jobs.maintenance.RemoveOldFilesJob;
import com.lynxspa.sdm.core.services.quartz.jobs.notifications.ClearSentNotifications;
import com.lynxspa.sdm.core.services.quartz.jobs.notifications.SendNotifications;

public enum AvaliableJob {
	REMOVE_OLD_FILES_JOB(RemoveOldFilesJob.class, "MAINTENBATCHES", RemoveOldFilesJob.getPropertyBeans()),
	
	SEND_PUSH_NOTIFICATIONS(SendNotifications.class, "NOTIFICATIONS", SendNotifications.getPropertyBeans()),
	CLEAR_SENT_NOTIFICATIONS(ClearSentNotifications.class, "NOTIFICATIONS", ClearSentNotifications.getPropertyBeans());
	
	private Class<? extends StatefulJob>	implementorClass	= null;
	private String							jobClass			= null;
	private PropertyBean[]					properties			= null;

	AvaliableJob(final Class<? extends StatefulJob> _implementorClass, final String _jobClass) {
		this(_implementorClass, _jobClass, new PropertyBean[0]);
	}

	AvaliableJob(final Class<? extends StatefulJob> _implementorClass, final String _jobClass, final PropertyBean[] _properties) {
		this.implementorClass = _implementorClass;
		this.jobClass = _jobClass;
		this.properties = _properties;
	}

	public Class<? extends StatefulJob> getImplementorClass() {
		return implementorClass;
	}

	public String getName() {
		return this.name();
	}

	public String getJobClass() {
		return jobClass;
	}

	public PropertyBean[] getProperties() {
		return properties;
	}

	@SuppressWarnings("unchecked")
	public static AvaliableJob findAvaliableJobByImplementorClass(final String _implementorClass) {
		AvaliableJob reply = null;
		try {
			final Class<? extends StatefulJob> implementorClassCandidate = (Class<? extends StatefulJob>) Class.forName(_implementorClass);
			for (AvaliableJob job : AvaliableJob.values()) {
				if (job.implementorClass.equals(implementorClassCandidate)) {
					reply = job;
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			throw new FPMRuntimeException(e);
		}
		return reply;
	}

	public static final List<AvaliableJob> findAvaliableJobByClass(final String _jobClass) {
		final List<AvaliableJob> reply = new ArrayList<AvaliableJob>();
		for (AvaliableJob job : AvaliableJob.values()) {
			if (job.getJobClass().equals(_jobClass))
				reply.add(job);
		}
		return reply;
	}
}
