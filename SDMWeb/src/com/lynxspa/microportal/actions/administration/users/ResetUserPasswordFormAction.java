package com.lynxspa.microportal.actions.administration.users;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Hibernate;
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
import com.lynxspa.microportal.configuration.MicroportalConfig;
import com.lynxspa.microportal.dictionaries.WebLogAuditDict;
import com.lynxspa.microportal.dictionaries.WebLogWarningDict;
import com.lynxspa.utils.UtilDate;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class ResetUserPasswordFormAction extends MicroportalBasicAction {

	public static final String	GENERATEDPASSWORD		= "newPasswordGenerated";
	public static final String	PASSWORDEXPIRATIONDATE	= "passwordExpirationDate";

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		XWebUser xwebUser = null;
		long expirationMarginInDays = 0l;
		Date expirationDate = null;
		String newPassword = null;
		UserFactory userFactory = null;
		try {
			// Recover User
			xwebUser = (XWebUser) _session.get(XWebUser.class, (String) _parameters.get("id"));
			if (xwebUser == null)
				throw new FPMException(WebLogWarningDict.USER_NOT_EXIST, new Object[] { (String) _parameters.get("id") });
			// get the correct user factory
			userFactory = AbstractUserFactory.getInstance().getDomainFactory("xweb");
			if (!(userFactory instanceof HibernateUserFactory))
				throw new FPMException(WebLogWarningDict.CANT_CHANGE_PASSWORD_FROM_DOMAIN, new Object[] { "xweb" });
			Hibernate.initialize(xwebUser);
			HibernateUserFactory domainFactory = (HibernateUserFactory) userFactory;
			// change the password
			expirationMarginInDays = (Long) MicroportalManager.getInstance().getConfiguration(_session, MicroportalConfig.PASSWORDEXPIRATIONMARGIN);
			expirationDate = UtilDate.calculateRelativeDate(Calendar.getInstance().getTime(), (int) expirationMarginInDays, 0, 0);
			newPassword = autogeneratePassword();
			domainFactory.setUserPassword(xwebUser, newPassword);
			xwebUser.setExpirationDate(expirationDate);
			_request.getSession().setAttribute(GENERATEDPASSWORD, newPassword);
			_request.getSession().setAttribute(PASSWORDEXPIRATIONDATE, (new SimpleDateFormat("dd/MM/yyyy")).format(expirationDate));
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

	private String autogeneratePassword() {

		String reply = null;

		reply = RandomStringUtils.random(10, true, true);

		return reply;
	}
}
