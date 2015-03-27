package com.lynxspa.microportal.actions.administration.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxit.xweb.users.PasswordUtils;
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

public class InsertUserFormAction extends MicroportalBasicAction {

	private static final String	DEFAULT_HOMEPAGE	= "/fpm/microportal/private/index.xwb";
	private static final String	DEFAULT_GENDER		= "M";

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("password", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
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
		String password = null;
		String firstname = null;
		String lastname = null;
		String email = null;
		String locale = null;

		try {
			// Recover parameters
			id = (String) _parameters.get("id");
			password = (String) _parameters.get("password");
			firstname = (String) _parameters.get("firstname");
			lastname = (String) _parameters.get("lastname");
			email = (String) _parameters.get("email");
			locale = ((String[]) _parameters.get("locale"))[0];
			// Verify groups
			List<String> listGroups = getGroupListFromString((String) _parameters.get("listGroups"));
			List listValidation = _session.createQuery("from Group where id in(:listGroups)").setParameterList("listGroups", listGroups).list();
			if ((listValidation == null) || (listValidation.size() != listGroups.size())) {
				throw new FPMException(WebLogWarningDict.ANY_GROUP_NOT_EXIST);
			}
			// User generation
			xwebUser = new XWebUser();
			xwebUser.setId(id);
			xwebUser.setPassword(PasswordUtils.hashPassword(password));
			xwebUser.setHomepage(getDefaultHomePage());
			xwebUser.setFirstname(firstname);
			xwebUser.setLastname(lastname);
			xwebUser.setEmail(email);
			xwebUser.setGender(InsertUserFormAction.DEFAULT_GENDER);
			xwebUser.setLocale(new Locale(locale));
			for (String groupId : listGroups) {
				xwebUser.addGroup(groupId);
				if ("SUPERUSER".equals(groupId)) {
					xwebUser.addGroup("XWEB_ADMIN");
				}
			}
			// user save
			HibernateUtils.customSave(_session, xwebUser, _user.getId());
			_session.flush();
			_request.getSession().setAttribute("edit_user_varEditingUserId", xwebUser.getId());
			// Log Change
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.USER_INSERTED, new Object[] { (String) _parameters.get("id") }, null, false);
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

	protected List<String> getGroupListFromString(String groupParameter) {

		List<String> listGroup = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(groupParameter, ",");

		while (tokenizer.hasMoreTokens()) {
			listGroup.add(tokenizer.nextToken());
		}

		return listGroup;
	}

	protected String getDefaultHomePage() {
		return InsertUserFormAction.DEFAULT_HOMEPAGE;
	}
}
