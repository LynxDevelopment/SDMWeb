package com.lynxspa.microportal.actions.administration.news;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.lynxspa.microportal.dictionaries.WebLogAuditDict;
import com.lynxspa.microportal.dictionaries.WebLogWarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class UpdateNewFormAction extends ChangeDestinataryNewFormAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISINTEGER, ValidationsDict.ISNOTNEGATIVENUMBER);
		ValidationUtils.validateField("subtitle", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("body", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("listGroups", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("expirationDate", _parameters, _errors, ValidationsDict.ISDATE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		NewsMessage message = null;
		int id = 0;
		String subtitle = null;
		String body = null;
		Date expirationDate = null;
		Query query = null;
		List<NewsMessage> newsList = null;
		String oldDestinatary = null;

		try {
			// Recover parameters
			id = Integer.parseInt((String) _parameters.get("id"));
			subtitle = (String) _parameters.get("subtitle");
			body = (String) _parameters.get("body");
			if (_parameters.get("expirationDate") != null)
				expirationDate = getNowOrFuturePublishDate((Date) _parameters.get("expirationDate"));
			// Recover new
			message = (NewsMessage) _session.get(NewsMessage.class, id);
			if (message == null)
				throw new FPMException(WebLogWarningDict.NEW_NOT_EXIST, new Object[] { (String) _parameters.get("id") });
			message.setSubtitle(subtitle);
			message.setBody(body);
			message.setExpirationDate(expirationDate);

			// Verify groups
			List<String> listGroups = getGroupListFromString((String) _parameters.get("listGroups"));
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

				news.setExpirationDate(expirationDate);

				HibernateUtils.customUpdate(_session, news, _user.getId());
			}
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.NEW_DESTINATARY_UPDATED, new Object[] { message.getTitle(), oldDestinatary, (String) _parameters.get("listGroups") }, null, false);
			// new database update
			HibernateUtils.customUpdate(_session, message, _user.getId());
			_session.flush();
			// Log Change
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.NEW_CONTENT_UPDATED, new Object[] { message.getTitle() }, null, false);
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

	private Date getNowOrFuturePublishDate(Date dateParam) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date now = sdf.parse(sdf.format(new Date()));
		Date publish = sdf.parse(sdf.format(dateParam));

		Calendar calendar = Calendar.getInstance();
		if (publish.compareTo(now) < 0) {
			calendar.setTime(now);
			calendar.add(Calendar.MINUTE, 1);
		} else {
			calendar.setTime(publish);
		}
		return calendar.getTime();
	}
}
