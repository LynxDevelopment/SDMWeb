package com.lynxspa.microportal.actions.administration.menus;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
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

public class ChangeStatusMenuFormAction extends MicroportalBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("status", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISBOOLEAN);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		SimpleMenuItem menuItem = null;
		String id = null;
		boolean status = false;
		boolean oldStatus = false;

		try {
			// Recover parameters
			id = (String) _parameters.get("id");
			status = Boolean.parseBoolean((String) _parameters.get("status"));
			// Recover entities
			menuItem = (SimpleMenuItem) _session.get(SimpleMenuItem.class, id);
			if (menuItem == null)
				throw new FPMException(WebLogWarningDict.MENU_NOT_EXIST, new Object[] { _parameters.get("id") });
			oldStatus = menuItem.isEnabled();
			menuItem.setEnabled(status);
			// update menu
			HibernateUtils.customUpdate(_session, menuItem, _user.getId());
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.MENU_STATUS_UPDATED, new Object[] { menuItem.getTitle(), ((oldStatus) ? "enabled" : "disabled"), ((status) ? "enabled" : "disabled") }, null, false);

			// Update Menu parent URL for popup link
			/*
			 * String _parent = menuItem.getParent().getId();
			 * if(!"/private".equals(_parent)){
			 * MicroportalUtils.updateMenuParentURL(_parent, _session);
			 * //Tratamiento para bug interno de XWEB, el cual no monta
			 * correctamente en los menus la URL en el caso de encontrarse con
			 * el primer hijo deshabilitado. //Para solventarlo sin parchear el
			 * XWEB, cada vez que deshabilitemos un item lo pondremos al final
			 * de la lista. SimpleMenuItem parentItem =
			 * (SimpleMenuItem)menuItem.getParent(); HibernateXMenuDAO
			 * menuDAO=new HibernateXMenuDAO(_session); if(!status){
			 * 
			 * int numberOfChildren = parentItem.getChildren().size()-1;
			 * menuDAO.
			 * moveXMenu(menuItem.getId(),menuItem.getParent().getId(),Integer
			 * .toString(numberOfChildren));
			 * 
			 * //Verificar si todos los hijos estan deshabilitados. En tal caso
			 * deshabilitar al padre. boolean isOneChildEnabled = false;
			 * for(Object childObject : parentItem.getChildren()){
			 * SimpleMenuItem child = (SimpleMenuItem)childObject;
			 * if(child.isEnabled()){ isOneChildEnabled = true; } }
			 * if(!isOneChildEnabled){ parentItem.setEnabled(false);
			 * _session.update(parentItem); }
			 * 
			 * }else{ //Si activamos el hijo lo colocamos en primera posicion.
			 * if(menuItem.getPosition()>1){ SimpleMenuItem firstChild =
			 * (SimpleMenuItem)menuItem.getParent().getChildren().get(0);
			 * menuDAO
			 * .moveXMenu(menuItem.getId(),menuItem.getParent().getId(),firstChild
			 * .getId()); } }
			 * 
			 * }
			 */
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
