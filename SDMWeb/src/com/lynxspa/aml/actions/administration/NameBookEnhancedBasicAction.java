package com.lynxspa.aml.actions.administration;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.core.managers.configuration.XwebConfigurationClass;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.xweb.actions.EnhancedBasicAction;

public abstract class NameBookEnhancedBasicAction extends EnhancedBasicAction {

	public static final String	SESSIONAMLUSERCUSTOMER	= "NameBook.User.customer";

	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
	}

	public final Object getSpringBean(String name) {
		return WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean(name);
	}
	
	public final <T> T getSpringBean(Class<T> TClass) {
		return WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean(TClass);
	}
	
	protected File getTemporalDirectory(Session _session) throws FPMException {

		File reply = null;

		final String temporalDirectoryUrl = (String) MicroportalManager.getInstance().getConfiguration(_session, XwebConfigurationClass.TEMPORALDIRECTORY);
		reply = new File(temporalDirectoryUrl);

		return reply;
	}

	public static Locale getLocale(final HttpServletRequest _request, final Session _session, final LoggedUser _user) throws FPMException {
		return new Locale("ca");
	}
}
