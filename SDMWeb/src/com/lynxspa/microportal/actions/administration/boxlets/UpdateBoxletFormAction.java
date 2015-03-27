package com.lynxspa.microportal.actions.administration.boxlets;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.xweb.boxlet.BoxletManager;
import com.lynxit.xweb.boxlet.hibernateentities.Boxlet;
import com.lynxit.xweb.hibernateentities.containers.ContainerKey;
import com.lynxit.xweb.hibernateentities.containers.ContainerSchedule;
import com.lynxit.xweb.hibernateentities.groups.Group;
import com.lynxit.xweb.hibernateentities.messages.Html;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.microportal.actions.MicroportalBasicAction;
import com.lynxspa.microportal.dictionaries.WebLogAuditDict;
import com.lynxspa.microportal.dictionaries.WebLogWarningDict;

public class UpdateBoxletFormAction extends MicroportalBasicAction {

	public static final String	BOXLET_NAME_PARAM			= "BOXLET_NAME";
	public static final String	CONFIGURATION_NAME_PARAM	= "CONFIGURATION_NAME";
	public static final String	URL_PARAM					= "URL";
	public static final String	PUBLICATION_TIME_PARAM		= "PUBLICATION_TIME";
	public static final String	EXPIRATION_TIME_PARAM		= "EXPIRATION_TIME";
	public static final String	BOXLET_CONFIG_PARAM			= "EXPIRATION_TIME";
	public static final String	POSITION_PARAM				= "POSITION";
	public static final String	ENABLED_PARAM				= "ENABLED";
	public static final String	BODY_PARAM					= "BODY";
	public static final String	CONTENT_TYPE				= "BOXLET";

	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String boxletName = null;
		String configName = null;
		String url = null;
		String body = null;
		String pos = null;
		String enabled = null;
		String[] locale = null;
		Date publicationDate = null;
		Date expirationDate = null;
		ContainerKey id = null;
		Boxlet boxlet = null;
		ContainerSchedule configuration = null;
		String contentType = null;
		Html html = null;

		try {
			boxletName = _request.getParameter(BOXLET_NAME_PARAM);
			configName = _request.getParameter(CONFIGURATION_NAME_PARAM);
			url = _request.getParameter(URL_PARAM);
			body = _request.getParameter(BODY_PARAM);
			pos = _request.getParameter(POSITION_PARAM);// se valorizzato
														// identifica l'azione
														// di aggiornamento
														// della boxlet
			enabled = _request.getParameter(ENABLED_PARAM);
			locale = (String[]) getParameter("locale");
			publicationDate = (Date) getParameter(PUBLICATION_TIME_PARAM);
			expirationDate = (Date) getParameter(EXPIRATION_TIME_PARAM);

			// Verify boxlets groups
			List<String> listGroupsBoxlet = getGroupListFromString((String) _parameters.get("listGroups"));
			List<Group> listValidationBoxlet = _session.createQuery("from Group where id in(:listGroups)").setParameterList("listGroups", listGroupsBoxlet).list();
			if ((listValidationBoxlet == null) || (listValidationBoxlet.size() != listGroupsBoxlet.size())) {
				throw new FPMException(WebLogWarningDict.ANY_GROUP_NOT_EXIST);
			}
			String[] groups = new String[listValidationBoxlet.size()];
			for (int countArray = 0; countArray < listValidationBoxlet.size(); countArray++) {
				groups[countArray] = listValidationBoxlet.get(countArray).getId();
			}

			id = new ContainerKey(boxletName, Boxlet.BOXLET_TYPE);
			if ((boxlet = (Boxlet) _session.get(Boxlet.class, id)) == null)
				throw new FPMException(WebLogWarningDict.BOXLET_NOT_EXIST, new Object[] { String.valueOf(boxletName) });
			if ((configuration = (ContainerSchedule) boxlet.getConfigurations().get(Integer.parseInt(pos))) == null)
				throw new FPMException(WebLogWarningDict.BOXLET_CONFIGURATION_NOT_EXIST, new Object[] { String.valueOf(configName), String.valueOf(pos), String.valueOf(configName) });
			configuration.setName(configName);
			configuration.setPublishDate(publicationDate);
			configuration.setExpirationDate(expirationDate);
			configuration.setEnabled(Boolean.valueOf(enabled).booleanValue());
			contentType = ((String[]) getParameter("content_type"))[0];
			if ("url".equals(contentType)) {
				configuration.setUrl(url);
				configuration.setHtml(null);
			} else {
				if ((html = configuration.getHtml()) == null)
					throw new FPMException(WebLogWarningDict.BOXLET_CONFIGURATION_CONTENT_NOT_EXIST, new Object[] { String.valueOf(configName), String.valueOf(pos), String.valueOf(configName) });
				html.removeAllPermission();
				html.addGroupsPermission(groups);
				html.setAuthor(_user.getId());
				html.setBody(body);
				if (locale[0] != null || locale[0].trim().length() != 0) {
					html.setLanguage(locale[0]);
				} else {
					String languageLoggedUser = ((XWebUser) getRequest().getSession().getAttribute("LOGGED_USER")).getLocale().getLanguage();
					html.setLanguage(languageLoggedUser);
				}
				configuration.setHtml(html);
				configuration.setUrl(null);
				_session.save(html);
			}
			_session.update(configuration);
			_session.update(boxlet);
			super.appendParamToUrl(BOXLET_NAME_PARAM, boxletName);
			BoxletManager.getInstance().clearCache(boxletName);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.getInstance().getBundleName(), _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.BOXLET_UPDATED, new Object[] { boxletName }, null);
		} catch (FPMException e) {
			throw e;
		} catch (HibernateException e) {
			FPMException newException = new FPMException(BasicErrorDict.DATABASE_SAVE_ERROR, new Object[] { String.valueOf(_parameters.get("BOXLET_NAME_PARAM")), Boxlet.class.getName(), String.valueOf(_session) }, e);
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
