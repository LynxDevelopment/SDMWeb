package com.lynxspa.sdm.core.services.quartz;

import org.quartz.JobDetail;
import org.quartz.StatefulJob;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.lynxspa.sdm.core.services.utils.SpringContextService;

public class QuartzJobFactory extends SpringBeanJobFactory {
	@Autowired
	private SpringContextService	springContextService;

	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		JobDetail detail = bundle.getJobDetail();
		Class<?> jobClass = detail.getJobClass();

		// sólo aceptaremos "jobs" de quartz que tengan estado (-> implementa StatefulJob | extiende SDMJob)
		if (!StatefulJob.class.isAssignableFrom(jobClass)) {
			throw new IllegalArgumentException(jobClass + " for job " + detail.getName() + " is invalid. It must implement StatefulJob or extend SDMJob.");
		}

		// Nuestros jobs no están dentro del IoC de Spring, por lo que
		// inyectaremos las dependencias nosotros mismos
		StatefulJob jobToRun = (StatefulJob) jobClass.newInstance();
		springContextService.autowire(jobToRun);

		// aquí el job está preparado y con las dependencias inyectadas (los @Autowired)
		return jobToRun;
	}
}
