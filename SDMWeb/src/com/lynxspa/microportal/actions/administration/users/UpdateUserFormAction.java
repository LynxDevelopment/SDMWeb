package com.lynxspa.microportal.actions.administration.users;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.hibernateentities.groups.Group;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.microportal.dictionaries.WebLogAuditDict;
import com.lynxspa.microportal.dictionaries.WebLogWarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class UpdateUserFormAction extends InsertUserFormAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("firstname", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("lastname", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("email", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("locale", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("listGroups", _parameters.get("listGroups"), _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		XWebUser xwebUser = null;
		String id = null;
		String firstname = null;
		String lastname = null;
		String email = null;
		String locale = null;

		try {
			// Recover parameters
			id = (String) _parameters.get("id");
			firstname = (String) _parameters.get("firstname");
			lastname = (String) _parameters.get("lastname");
			email = (String) _parameters.get("email");
			locale = ((String[]) _parameters.get("locale"))[0];
			// Recover user
			xwebUser = (XWebUser) _session.get(XWebUser.class, id);
			if (xwebUser == null)
				throw new FPMException(WebLogWarningDict.USER_NOT_EXIST, new Object[] { (String) _parameters.get("id") });

			// Verify group
			List<String> listGroups = getGroupListFromString((String) _parameters.get("listGroups"));
			List listValidation = _session.createQuery("from Group where id in(:listGroups)").setParameterList("listGroups", listGroups).list();
			if ((listValidation == null) || (listValidation.size() != listGroups.size())) {
				throw new FPMException(WebLogWarningDict.ANY_GROUP_NOT_EXIST);
			}
			// User update
			xwebUser.setFirstname(firstname);
			xwebUser.setLastname(lastname);
			xwebUser.setEmail(email);
			xwebUser.setLocale(new Locale(locale));
			xwebUser.getStaticGroups().clear();
			for (Object associatedGroup : xwebUser.getStaticGroups()) {
				xwebUser.removeGroup(((Group) associatedGroup).getId());
			}
			for (String groupId : listGroups) {
				xwebUser.addGroup(groupId);
				if ("SUPERUSER".equals(groupId)) {
					xwebUser.addGroup("XWEB_ADMIN");
				}
			}
			// user database update
			HibernateUtils.customUpdate(_session, xwebUser, _user.getId());
			_session.flush();
			// Log Change
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.USER_UPDATED, new Object[] { (String) _parameters.get("id") }, null, false);
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
