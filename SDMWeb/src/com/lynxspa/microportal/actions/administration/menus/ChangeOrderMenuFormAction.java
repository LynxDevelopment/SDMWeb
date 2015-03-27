package com.lynxspa.microportal.actions.administration.menus;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxit.xweb.xmenu.entities.HibernateXMenuDAO;
import com.lynxit.xweb.xmenu.entities.SimpleMenuItem;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.microportal.actions.MicroportalBasicAction;
import com.lynxspa.microportal.dictionaries.WebLogAuditDict;
import com.lynxspa.microportal.dictionaries.WebLogWarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class ChangeOrderMenuFormAction extends MicroportalBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("goUp", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISBOOLEAN);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		SimpleMenuItem menuItem = null;
		String id = null;
		boolean goUp = false;
		int oldPosition = 0;
		int newPosition = 0;
		String nextId = null;
		HibernateXMenuDAO menuDAO = null;
		List brothers = null;

		try {
			menuDAO = new HibernateXMenuDAO(_session);
			// Recover parameters
			id = (String) _parameters.get("id");
			goUp = Boolean.parseBoolean((String) _parameters.get("goUp"));
			// Recover entities
			menuItem = (SimpleMenuItem) _session.get(SimpleMenuItem.class, id);
			if (menuItem == null)
				throw new FPMException(WebLogWarningDict.MENU_NOT_EXIST, new Object[] { _parameters.get("id") });
			brothers = menuItem.getParent().getChildren();
			for (int ic1 = 0; ic1 < brothers.size(); ic1++) {
				SimpleMenuItem item = (SimpleMenuItem) brothers.get(ic1);
				if (item.getId().equals(menuItem.getId())) {
					if ((!goUp) && (ic1 > 0)) {
						nextId = ((SimpleMenuItem) brothers.get(ic1 - 1)).getId();
					} else if ((goUp) && (ic1 < brothers.size() - 2)) {
						nextId = ((SimpleMenuItem) brothers.get(ic1 + 2)).getId();
					}
					break;
				}
			}
			oldPosition = menuItem.getPosition();
			menuDAO.moveXMenu(menuItem.getId(), menuItem.getParent().getId(), nextId);
			newPosition = menuItem.getPosition();
			// update menu
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.MENU_POSITION_UPDATED, new Object[] { menuItem.getTitle(), oldPosition, newPosition }, null, false);

			// Update Menu parent URL for popup link
			// String _parent = menuItem.getParent().getId();
			// if(!"/private".equals(_parent)){
			// MicroportalUtils.updateMenuParentURL(_parent, _session);
			// }

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
