package com.lynxspa.microportal.actions.administration.menus;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.boxlet.hibernateentities.Boxlet;
import com.lynxit.xweb.hibernateentities.containers.ContainerKey;
import com.lynxit.xweb.hibernateentities.containers.ContainerSchedule;
import com.lynxit.xweb.hibernateentities.messages.Html;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxit.xweb.xmenu.entities.HibernateXMenuDAO;
import com.lynxit.xweb.xmenu.entities.SimpleMenuItem;
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

public class DeleteMenuFormAction extends MicroportalBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		SimpleMenuItem menuItem = null;
		HibernateXMenuDAO menuDAO = null;

		try {
			menuDAO = new HibernateXMenuDAO(_session);
			// Recover news
			menuItem = (SimpleMenuItem) _session.get(SimpleMenuItem.class, (String) _parameters.get("id"));
			if (menuItem == null)
				throw new FPMException(WebLogWarningDict.MENU_NOT_EXIST, new Object[] { _parameters.get("id") });
			// Validate children
			if (!menuItem.getChildren().isEmpty())
				throw new FPMException(WebLogWarningDict.MENU_HAVE_ALREADY_CHILDS, new Object[] { _parameters.get("id") });
			// menu delete
			if (menuItem.getItemType().equals("BOXLET")) {
				String hqlQuery = new StringBuilder().append("from Boxlet where id.name like ('").append(menuItem.getId()).append("%')").toString();
				List<Boxlet> listBoxlet = _session.createQuery(hqlQuery).list();
				for (Boxlet toDelete : listBoxlet) {
					deleteBoxlet(_session, toDelete.getId().getName(), _user);
				}
			}
			menuDAO.deleteXMenu(menuItem.getId());

			// Log Change
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.MENU_DELETED, new Object[] { _parameters.get("id") }, null, false);
		} catch (FPMException e) {
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), e, true);
			throw e;
		} catch (HibernateException e) {
			FPMException newException = new FPMException(BasicErrorDict.DATABASE_SAVE_ERROR, new Object[] { _parameters.get("id"), XWebUser.class.getName(), String.valueOf(_session) }, e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException, true);
			throw newException;
		} catch (Throwable e) {
			FPMException newException = new FPMException(e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException, true);
			throw newException;
		}
	}

	@SuppressWarnings("unchecked")
	protected void deleteBoxlet(Session _session, String _boxletName, LoggedUser _user) throws FPMException {

		Boxlet boxlet = null;
		Html htmlContent = null;

		if ((boxlet = (Boxlet) _session.get(Boxlet.class, new ContainerKey(_boxletName, Boxlet.BOXLET_TYPE))) != null) {
			for (Object configuration : boxlet.getConfigurations()) {
				if (configuration instanceof ContainerSchedule) {
					htmlContent = ((ContainerSchedule) configuration).getHtml();
				}
				HibernateUtils.customDelete(_session, configuration, _user.getId());
				if (configuration instanceof ContainerSchedule) {
					HibernateUtils.customDelete(_session, htmlContent, _user.getId());
				}
			}
			HibernateUtils.customDelete(_session, boxlet, _user.getId());
		}
	}
}
