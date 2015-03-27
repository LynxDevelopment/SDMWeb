package com.lynxspa.microportal.actions.administration.news;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.hibernateentities.messages.NewsMessage;
import com.lynxit.xweb.hibernateentities.messages.Permission;
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

public class ChangeDestinataryNewFormAction extends MicroportalBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		ValidationUtils.validateField("newId", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISINTEGER, ValidationsDict.ISNOTNEGATIVENUMBER);
		ValidationUtils.validateField("destinatary", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		NewsMessage message = null;
		Query query = null;
		List<NewsMessage> newsList = null;
		int id = 0;
		String destinatary = null;
		String oldDestinatary = null;

		try {
			// Recover parameters
			id = Integer.parseInt((String) _parameters.get("newId"));
			destinatary = (String) _parameters.get("destinatary");
			// Recover entities
			message = (NewsMessage) _session.get(NewsMessage.class, id);
			if (message == null)
				throw new FPMException(WebLogWarningDict.NEW_NOT_EXIST, new Object[] { (String) _parameters.get("newId") });

			// Verify groups
			List<String> listGroups = getGroupListFromString(destinatary);
			List listValidation = _session.createQuery("from Group where id in(:listGroups)").setParameterList("listGroups", listGroups).list();
			if ((listValidation == null) || (listValidation.size() != listGroups.size())) {
				throw new FPMException(WebLogWarningDict.ANY_GROUP_NOT_EXIST);
			}

			query = _session.createQuery("from NewsMessage news where news.title=:title");
			query.setString("title", message.getTitle());
			newsList = query.list();
			// update news
			oldDestinatary = message.getPermissions().iterator().next().getPrincipal();
			StringBuilder sb = new StringBuilder();
			for (Permission permission : message.getPermissions()) {
				sb.append(permission.getPrincipal()).append(",");
			}
			oldDestinatary = sb.toString();
			for (NewsMessage news : newsList) {
				news.getPermissions().clear();
				for (String groupId : listGroups) {
					news.addGroupPermission(groupId);
				}
				HibernateUtils.customUpdate(_session, news, _user.getId());
			}
			_session.flush();

			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.NEW_DESTINATARY_UPDATED, new Object[] { message.getTitle(), oldDestinatary, destinatary }, null, false);
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
}
