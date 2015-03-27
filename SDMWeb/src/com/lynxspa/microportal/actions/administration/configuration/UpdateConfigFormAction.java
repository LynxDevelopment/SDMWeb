package com.lynxspa.microportal.actions.administration.configuration;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.dictionaries.ClassType;
import com.lynxspa.entities.application.configurations.Config;
import com.lynxspa.entities.application.configurations.adapter.ConfigDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.microportal.dictionaries.WebLogWarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;
import com.lynxspa.xweb.actions.EnhancedBasicAction;

public class UpdateConfigFormAction extends EnhancedBasicAction {

	private static final String	CONFIGURARTIONCLASSES_CONTEXT_PARAMETER	= "ConfigurationClasses";

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		ClassType configType = null;

		ValidationUtils.validateField("applicationId", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("configId", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		configType = ClassType.valueOf((String) _parameters.get("configType"));
		if (ClassType.BOOLEAN.equals(configType)) {
			ValidationUtils.validateField("configValue", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISTIMESTAMP);
		} else if (ClassType.TIMESTAMP.equals(configType)) {
			ValidationUtils.validateField("configValue", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISTIMESTAMP);
		} else if (ClassType.LONG.equals(configType)) {
			ValidationUtils.validateField("configValue", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISLONG);
		} else if (ClassType.DOUBLE.equals(configType)) {
			ValidationUtils.validateField("configValue", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISDOUBLE);
		} else {
			ValidationUtils.validateField("configValue", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		}
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String stringValue = null;
		String applicationId = null;
		String configId = null;
		ConfigDictAdapter configuration = null;

		try {
			applicationId = (String) _parameters.get("applicationId");
			configId = (String) _parameters.get("configId");
			stringValue = (String) _parameters.get("configValue");
			if (MicroportalManager.getInstance().getApplication(_session).getId().equals(applicationId)) {
				configuration = findConfigAdapter(_request.getSession().getServletContext().getInitParameter(CONFIGURARTIONCLASSES_CONTEXT_PARAMETER), configId);
				MicroportalManager.getInstance().setConfiguration(_session, configuration, stringValue, _user.getId(), false);
			} else {
				throw new FPMException(WebLogWarningDict.UNKNOWN_APPLICATION, new Object[] { applicationId });
			}
		} catch (FPMException e) {
			throw e;
		} catch (HibernateException e) {
			FPMException newException = new FPMException(BasicErrorDict.DATABASE_SAVE_ERROR, new Object[] { String.valueOf(_parameters.get("applicationId")) + "|" + String.valueOf(_parameters.get("configId")), Config.class.getName(), String.valueOf(_session) }, e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException, true);
			throw newException;
		} catch (Throwable e) {
			FPMException newException = new FPMException(e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException, true);
			throw newException;
		}
	}

	private ConfigDictAdapter findConfigAdapter(String configsAdapters, String _code) throws ClassNotFoundException {

		ConfigDictAdapter reply = null;

		for (String configAdapter : configsAdapters.split(",")) {
			for (ConfigDictAdapter configDict : (ConfigDictAdapter[]) Class.forName(configAdapter).getEnumConstants()) {
				if (configDict.getCode().equals(_code))
					reply = configDict;
			}
		}
		return reply;
	}
}
