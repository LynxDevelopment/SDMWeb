package com.lynxspa.aml.taglibs.jstl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lynxit.xweb.initialization.initializers.DataSourcesManager;
import com.lynxit.xweb.initialization.initializers.DataSourcesManager.LocalInstance;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxspa.entities.application.Application;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.FPMRuntimeException;
import com.lynxspa.sdm.core.managers.configuration.XwebConfigurationClass;
import com.lynxspa.sdm.core.model.tasks.ProcessProperty;
import com.lynxspa.sdm.core.model.tasks.UserProcess;
import com.lynxspa.sdm.core.model.tasks.UserTask;
import com.lynxspa.sdm.core.services.quartz.QuartzService;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.microportal.MicroportalManager;

public class NameBookFunctions {

	public static final XWebUser getCustomer(final PageContext _pageContext) throws NamingException {
		return (XWebUser) _pageContext.getSession().getAttribute("LOGGED_USER");
	}

	public static final Object getConfigValue(final String _dataSource, final String _configName) throws NamingException, FPMException {

		final Session session = DataSourcesManager.getLocalInstance().getHibernateSession(_dataSource);
		return MicroportalManager.getInstance().getConfiguration(session, XwebConfigurationClass.valueOf(_configName));
	}

	public static final Application getApplication(final String _dataSource) throws NamingException {

		final LocalInstance localInstance = DataSourcesManager.getLocalInstance();
		final Session session = localInstance.getHibernateSession(_dataSource);
		return MicroportalManager.getInstance().getApplication(session);
	}

	public static final int getIntFromBigDecimal(final BigDecimal bigDecimal) {

		int reply = 0;

		if (bigDecimal != null) {
			reply = bigDecimal.intValue();
		}

		return reply;
	}

	public static final String autogeneratePassword() {

		return RandomStringUtils.random(10, true, true);
	}

	public static final int getTriggerStatus(final String idTask, final String dataSource, final ServletContext application) {

		int reply = 5; // //--> unknown status

		try {
			final Session session = DataSourcesManager.getLocalInstance().getHibernateSession(dataSource);
			final Long id = Long.parseLong(idTask);
			final UserTask task = HibernateUtils.getEntity(session, UserTask.class, id);
			ApplicationContext spring = WebApplicationContextUtils.getWebApplicationContext(application);
			QuartzService quartzService = spring.getBean(QuartzService.class);
			reply = quartzService.getTriggerStatus(session, task);
		} catch (Exception e) {
			// if error the set status to unknown
			reply = 5;
		}

		return reply;
	}
	
	public static final Map<String, String> getProcessProperties(final UserProcess process) {

		Map<String, String> reply = new HashMap<String, String>();

		for (ProcessProperty processProperty : process.getProcessProperties()) {
			reply.put(processProperty.getCode(), processProperty.getValue());
		}

		return reply;
	}

	public static String getNotificationGroups(final UserProcess process, final String _dataSource) {
		return "";
	}

	public static final String getTextWithHtmlNewLineBreaks(String textWithStringNewLineBreaks) {
		String reply = null;
		try {
			reply = textWithStringNewLineBreaks.replaceAll("\r\n", "<br/>");
		} catch (Exception e) {
			throw new FPMRuntimeException(e);
		}
		return reply;
	}

}