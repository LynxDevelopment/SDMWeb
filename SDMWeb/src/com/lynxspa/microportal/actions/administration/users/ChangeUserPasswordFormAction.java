package com.lynxspa.microportal.actions.administration.users;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.AbstractUserFactory;
import com.lynxit.xweb.users.HibernateUserFactory;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxit.xweb.users.UserFactory;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.microportal.actions.MicroportalBasicAction;
import com.lynxspa.microportal.dictionaries.WebLogAuditDict;
import com.lynxspa.microportal.dictionaries.WebLogWarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class ChangeUserPasswordFormAction extends MicroportalBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		ValidationUtils.validateField("oldpassword", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("newpassword", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("confirmpassword", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		if (!_parameters.get("newpassword").equals(_parameters.get("confirmpassword"))) {
			_errors.add(new ValidationException("error.password.new.confirmation.distinct", "newpassword", String.valueOf(_parameters.get("newpassword"))));
			_errors.add(new ValidationException("error.password.new.confirmation.distinct", "confirmpassword", String.valueOf(_parameters.get("confirmpassword"))));
		}
		if (_parameters.get("oldpassword").equals(_parameters.get("newpassword"))) {
			_errors.add(new ValidationException("error.old.new.password.equals", "oldpassword", String.valueOf(_parameters.get("oldpassword"))));
			_errors.add(new ValidationException("error.old.new.password.equals", "newpassword", String.valueOf(_parameters.get("newpassword"))));
		}
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String oldPsw = null;
		String newPsw = null;

		try {
			oldPsw = getRequest().getParameter("oldpassword");
			newPsw = getRequest().getParameter("newpassword");
			// get the correct user factory
			UserFactory userFactory = AbstractUserFactory.getInstance().getDomainFactory(_user.getDomain());
			if (!(userFactory instanceof HibernateUserFactory))
				throw new FPMException(WebLogWarningDict.CANT_CHANGE_PASSWORD_FROM_DOMAIN, new Object[] { _user.getDomain() });
			HibernateUserFactory domainFactory = (HibernateUserFactory) userFactory;
			// change the password
			domainFactory.changeUserPassword(_user.getId(), oldPsw, newPsw, _session);
			// Log Change
			_session.flush();
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.USER_PASSWORD_CHANGE, new Object[] { _user.getId() }, null, false);
		} catch (FPMException e) {
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), e, true);
			throw e;
		} catch (HibernateException e) {
			FPMException newException = new FPMException(BasicErrorDict.DATABASE_SAVE_ERROR, new Object[] { (String) _parameters.get("id"), XWebUser.class.getName(), String.valueOf(_session) }, e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException, true);
			throw newException;
		} catch (Throwable e) {
			FPMException newException = new FPMException(e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException, true);
			throw newException;
		}
	}
}
