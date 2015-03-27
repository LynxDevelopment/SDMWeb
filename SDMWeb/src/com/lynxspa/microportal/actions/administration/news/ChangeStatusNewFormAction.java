package com.lynxspa.microportal.actions.administration.news;

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
import com.lynxit.xweb.hibernateentities.messages.WFStatus;
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

public class ChangeStatusNewFormAction extends MicroportalBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		ValidationUtils.validateField("newId", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISINTEGER, ValidationsDict.ISNOTNEGATIVENUMBER);
		ValidationUtils.validateField("status", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		NewsMessage message = null;
		Query query = null;
		List<NewsMessage> newsList = null;
		int id = 0;
		String statusDescription = null;
		String oldStatus = null;
		WFStatus status = null;

		try {
			// Recover parameters
			id = Integer.parseInt((String) _parameters.get("newId"));
			statusDescription = (String) _parameters.get("status");
			// Recover entities
			message = (NewsMessage) _session.get(NewsMessage.class, id);
			if (message == null)
				throw new FPMException(WebLogWarningDict.NEW_NOT_EXIST, new Object[] { (String) _parameters.get("newId") });
			query = _session.createQuery("from WFStatus where description=:description");
			query.setString("description", statusDescription);
			query.setReadOnly(true);
			query.setMaxResults(1);
			query.setFetchSize(1);
			status = (WFStatus) query.uniqueResult();
			if (status == null)
				throw new FPMException(WebLogWarningDict.NEW_STATUS_NOT_EXIST, new Object[] { (String) _parameters.get("status"), (String) _parameters.get("newId") });
			query = _session.createQuery("from NewsMessage news where news.title=:title");
			query.setString("title", message.getTitle());
			newsList = query.list();
			// update news
			oldStatus = message.getStatus().getDescription();
			Date actualDate = Calendar.getInstance().getTime();
			for (NewsMessage news : newsList) {
				news.setStatus(status);
				if ("DRAFT".equals(status.getDescription())) {
					news.setPublishDate(null);
					news.setExpirationDate(null);
				} else if ("PUBLISHED".equals(status.getDescription())) {
					news.setPublishDate(actualDate);
					news.setExpirationDate(null);
				} else if ("EXPIRED".equals(status.getDescription())) {
					news.setExpirationDate(actualDate);
				}
				HibernateUtils.customUpdate(_session, news, _user.getId());
			}
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.NEW_STATUS_UPDATED, new Object[] { message.getTitle(), oldStatus, status.getDescription() }, null, false);
			_session.flush();
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
