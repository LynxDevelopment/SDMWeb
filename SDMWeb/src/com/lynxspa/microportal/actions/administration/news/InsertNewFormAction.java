package com.lynxspa.microportal.actions.administration.news;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.hibernateentities.messages.NewsMessage;
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
import com.lynxspa.microportal.utils.MicroportalUtils;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class InsertNewFormAction extends MicroportalBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		ValidationUtils.validateField("title", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("subtitle", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("body", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("publishDate", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISDATE);
		ValidationUtils.validateField("expirationDate", _parameters, _errors, ValidationsDict.ISDATE);
		ValidationUtils.validateField("listGroups", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		NewsMessage message = null;
		String title = null;
		String subtitle = null;
		String body = null;
		Date publishDate = null;
		Date expirationDate = null;

		try {
			// Recover parameters
			title = (String) _parameters.get("title");
			subtitle = (String) _parameters.get("subtitle");
			body = (String) _parameters.get("body");
			publishDate = getNowOrFuturePublishDate((Date) _parameters.get("publishDate"));
			if ((_parameters.get("expirationDate")) != null) {
				expirationDate = getNowOrFuturePublishDate((Date) _parameters.get("expirationDate"));
			}
			List<String> listGroups = getGroupListFromString((String) _parameters.get("listGroups"));
			// ValidateExistent new
			if (isAlreadyExisentNew(_session, title))
				throw new FPMException(WebLogWarningDict.NEW_ALREADY_EXIST);
			for (String language : MicroportalUtils.getConfiguredLanguages(_session)) {
				// new generation
				message = new NewsMessage();
				message.setTitle(title);
				message.setSubtitle(subtitle);
				message.setBody(body);
				message.setPublishDate(publishDate);
				message.setExpirationDate(expirationDate);
				message.setAuthor(_user.getId());
				message.setLanguage(language);
				for (String groupId : listGroups) {
					message.addGroupPermission(groupId);
				}
				// user save
				HibernateUtils.customSave(_session, message, _user.getId());
			}
			_session.flush();
			_request.getSession().setAttribute("edit_user_varEditingNewId", message.getId());
			// Log Change
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.NEW_INSERTED, new Object[] { message.getTitle() }, null, false);
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

	private boolean isAlreadyExisentNew(Session _session, String _title) {

		boolean reply = false;
		Query query = null;
		long newsFound = 0l;

		query = _session.createQuery("select count(news.id) from NewsMessage news where news.title=:title");
		query.setString("title", _title);
		query.setReadOnly(true);
		query.setFetchSize(1);
		newsFound = (Long) query.uniqueResult();
		if (newsFound > 0)
			reply = true;

		return reply;
	}

	protected List<String> getGroupListFromString(String groupParameter) {

		List<String> listGroup = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(groupParameter, ",");

		while (tokenizer.hasMoreTokens()) {
			listGroup.add(tokenizer.nextToken());
		}

		return listGroup;
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
