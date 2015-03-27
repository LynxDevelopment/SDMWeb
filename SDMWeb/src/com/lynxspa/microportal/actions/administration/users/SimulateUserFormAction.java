package com.lynxspa.microportal.actions.administration.users;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.microportal.actions.MicroportalBasicAction;
import com.lynxspa.microportal.dictionaries.WebLogAuditDict;
import com.lynxspa.microportal.dictionaries.WebLogWarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class SimulateUserFormAction extends MicroportalBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		XWebUser xwebUser = null;

		try {
			// Recover User
			xwebUser = (XWebUser) _session.get(XWebUser.class, (String) _parameters.get("id"));
			if (xwebUser == null)
				throw new FPMException(WebLogWarningDict.USER_NOT_EXIST, new Object[] { (String) _parameters.get("id") });
			// User delete
			HibernateUtils.customDelete(_session, xwebUser, _user.getId());
			// Log Change
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.USER_DELETED, new Object[] { (String) _parameters.get("id") }, null, false);
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
