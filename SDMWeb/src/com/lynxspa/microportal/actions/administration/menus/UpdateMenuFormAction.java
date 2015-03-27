package com.lynxspa.microportal.actions.administration.menus;

import java.net.URLEncoder;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.boxlet.hibernateentities.Boxlet;
import com.lynxit.xweb.hibernateentities.containers.ContainerKey;
import com.lynxit.xweb.hibernateentities.containers.ContainerSchedule;
import com.lynxit.xweb.hibernateentities.groups.Group;
import com.lynxit.xweb.hibernateentities.messages.Html;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxit.xweb.xmenu.entities.BoxletLinkMenuItem;
import com.lynxit.xweb.xmenu.entities.SimpleMenuItem;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.microportal.configuration.MicroportalConfig;
import com.lynxspa.microportal.dictionaries.WebLogAuditDict;
import com.lynxspa.microportal.dictionaries.WebLogWarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class UpdateMenuFormAction extends InsertMenuFormAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		String scrictId = null;

		try {
			ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
			ValidationUtils.validateField("parentId", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
			scrictId = ((String) _parameters.get("id")).substring(((String) _parameters.get("parentId")).length() + 1);
			if (getCurrentSession().get(SimpleMenuItem.class, (String) _parameters.get("id")) == null)
				_errors.add(new ValidationException("error.menu.id.not.exist", "id", scrictId));
			for (String language : ((String) MicroportalManager.getInstance().getConfiguration(getCurrentSession(), MicroportalConfig.CONFIGUREDLANGUAGES)).split(",")) {
				ValidationUtils.validateField("title_" + language, _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
			}
			ValidationUtils.validateField("status", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISBOOLEAN);
			ValidationUtils.validateField("itemType", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING, MenuValidations.MENUTYPE);
			ValidationUtils.validateField("classeMenu", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
			final String classeMenu = (_parameters.get("classeMenu") != null) ? ((String[]) _parameters.get("classeMenu"))[0] : null;
			if ("DATOS".equals(classeMenu)) {
				final String itemType = (_parameters.get("itemType") != null) ? ((String[]) _parameters.get("itemType"))[0] : null;
				if (itemType != null) {
					if (("LINK".equals(itemType)) || ("LINK_POPUP".equals(itemType)) || ("FILE".equals(itemType))) {
						ValidationUtils.validateField("menuUrl", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
					} else if (("BOXLET".equals(itemType))) {
						ValidationUtils.validateField("numbersBody", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
						ValidationUtils.validateField("listTemplates", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
					}
				}
			}
			ValidationUtils.validateField("listGroups", _parameters.get("listGroups"), _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
		} catch (Exception e) {
			throw new FPMException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String id = null;
		String scrictId = null;
		String parent = null;
		boolean status = false;
		String itemType = null;
		String url = null;
		String boxletName = null;
		SimpleMenuItem menuItem = null;
		String classeMenu = null;
		int numbersBody;
		LinkedList<String> listBody = new LinkedList<String>();
		String templateFileName;

		try {
			// Recover parameters
			id = (String) _parameters.get("id");
			parent = (String) _parameters.get("parentId");
			status = Boolean.valueOf(((String[]) _parameters.get("status"))[0]);
			itemType = ((String[]) _parameters.get("itemType"))[0];
			url = (String) _parameters.get("menuUrl");
			scrictId = ((String) _parameters.get("id")).substring(((String) _parameters.get("parentId")).length() + 1);
			classeMenu = ((String[]) _parameters.get("classeMenu"))[0];
			// En el caso que el menu se del tipo jerarquizado, su url debe ser
			// nula...
			if ("NIVEL".equals(classeMenu)) {
				url = null;
			}

			// Verify groups
			List<String> listGroups = getGroupListFromString((String) _parameters.get("listGroups"));
			List<Group> listValidation = _session.createQuery("from Group where id in(:listGroups)").setParameterList("listGroups", listGroups).list();
			if ((listValidation == null) || (listValidation.size() != listGroups.size())) {
				throw new FPMException(WebLogWarningDict.ANY_GROUP_NOT_EXIST);
			}

			// Create MenuItem
			menuItem = (SimpleMenuItem) _session.get(SimpleMenuItem.class, id);
			if (!menuItem.getItemType().equals(itemType) && !("LINK".equals(menuItem.getItemType()) && "LINK_POPUP".equals(itemType))) {
				boxletName = id;
				if ("BOXLET".equals(itemType)) {
					// If is boxlet a new boxlet must be created before
					Query query = _session.createQuery("update SimpleMenuItem menuItem set menuItem.itemType=:itemType where menuItem.id=:id");
					query.setString("itemType", "BOXLET");
					query.setString("id", id);
					query.executeUpdate();

					numbersBody = Integer.parseInt((String) _parameters.get("numbersBody"));
					templateFileName = ((String[]) _parameters.get("listTemplates"))[0];

					for (int count = 1; count <= numbersBody; count++) {
						listBody.add((String) _parameters.get(new StringBuilder("body").append(count).toString()));
					}

					for (int count = 1; count <= numbersBody; count++) {

						// Verify boxlets groups
						List<String> listGroupsBoxlet = getGroupListFromString((String) _parameters.get("listGroups" + count));
						List<Group> listValidationBoxlet = _session.createQuery("from Group where id in(:listGroups)").setParameterList("listGroups", listGroupsBoxlet).list();
						if ((listValidationBoxlet == null) || (listValidationBoxlet.size() != listGroupsBoxlet.size())) {
							throw new FPMException(WebLogWarningDict.ANY_GROUP_NOT_EXIST);
						}
						String[] groups = new String[listValidationBoxlet.size()];
						for (int countArray = 0; countArray < listValidationBoxlet.size(); countArray++) {
							groups[countArray] = listValidationBoxlet.get(countArray).getId();
						}
						// If is boxlet a new boxlet must be created before
						boxletName = new StringBuilder(menuItem.getId()).append("/body").append(count).toString();
						createBoxlet(_session, scrictId, boxletName, listBody.get(count - 1), groups, _user);
					}

					_session.flush();

					query = _session.createQuery("update BoxletLinkMenuItem menuItem set menuItem.boxlet.id.name=:boxletName,menuItem.boxlet.id.type=:boxletType where menuItem.id=:id");
					query.setString("boxletName", new StringBuilder(boxletName.substring(0, boxletName.lastIndexOf("/body"))).append("/body1").toString());
					query.setString("boxletType", "BOXLET");
					query.setString("id", id);
					query.executeUpdate();
					menuItem.setUrl(new StringBuilder("/fpm/microportal/common/templates/").append(templateFileName).append("?title=").append(id.substring(parent.length() + 1)).toString());

				} else if ("LINK".equals(itemType) || "LINK_POPUP".equals(itemType) || "FILE".equals(itemType)) {
					// If was boxlet old boxlets should be deleted
					((BoxletLinkMenuItem) menuItem).setBoxlet(null);
					Query query = _session.createQuery("update SimpleMenuItem menuItem set menuItem.itemType=:itemType where menuItem.id=:id");
					query.setString("itemType", "LINK");
					query.setString("id", id);
					query.executeUpdate();

					String hqlQuery = new StringBuilder().append("from Boxlet where id.name like ('").append(menuItem.getId()).append("%')").toString();
					List<Boxlet> listBoxlet = _session.createQuery(hqlQuery).list();
					for (Boxlet toDelete : listBoxlet) {
						deleteBoxlet(_session, toDelete.getId().getName(), _user);
					}

					menuItem.setUrl("/fpm/microportal/common/link_display.xwb?title=" + id.substring(parent.length() + 1) + "&urlEmbeded=" + URLEncoder.encode(url, "UTF-8"));
				}
			}
			menuItem.setTitle(id.substring(parent.length() + 1));
			menuItem.setEnabled(status);
			menuItem.setMarked(false);
			menuItem.setLanguageId("es");
			menuItem.setIcon1(null);
			menuItem.setIcon2(null);
			menuItem.setExternalLink(false);
			menuItem.setTargetLink(false);

			// update groups
			Set permissions = new HashSet();
			for (Group group : listValidation) {
				permissions.add(group);
			}
			menuItem.setPermissions(permissions);

			if ("LINK".equals(itemType)) {
				if (url != null) {
					menuItem.setUrl("/fpm/microportal/common/link_display.xwb?title=" + id.substring(parent.length() + 1) + "&urlEmbeded=" + URLEncoder.encode(url, "UTF-8"));
				} else {
					menuItem.setUrl(null);
				}
			} else if ("LINK_POPUP".equals(itemType)) {
				menuItem.setUrl(url);
				menuItem.setTargetLink(true);
				menuItem.setExternalLink(true);
			} else if ("FILE".equals(itemType)) {
				menuItem.setUrl(url);
				menuItem.setTargetLink(true);
				menuItem.setExternalLink(true);
			}

			// Save
			HibernateUtils.customUpdate(_session, menuItem, _user.getId());

			/*
			 * //Update Menu parent URL for popup link
			 * MicroportalUtils.updateMenuParentURL(parent, _session);
			 * 
			 * //Verificar si todos los hijos estan deshabilitados. En tal caso
			 * deshabilitar al padre. if(!menuItem.isEnabled()){ SimpleMenuItem
			 * parentItem = (SimpleMenuItem)menuItem.getParent(); int
			 * numberOfChildren = parentItem.getChildren().size()-1;
			 * HibernateXMenuDAO menuDAO=new HibernateXMenuDAO(_session);
			 * menuDAO
			 * .moveXMenu(menuItem.getId(),menuItem.getParent().getId(),Integer
			 * .toString(numberOfChildren));
			 * 
			 * boolean isOneChildEnabled = false; for(Object childObject :
			 * parentItem.getChildren()){ SimpleMenuItem child =
			 * (SimpleMenuItem)childObject; if(child.isEnabled()){
			 * isOneChildEnabled = true; } } if(!isOneChildEnabled){
			 * parentItem.setEnabled(false); _session.update(parentItem); }
			 * 
			 * }
			 * 
			 * //Si activamos el hijo lo colocamos en primera posicion.
			 * if((oldStatus == false) && (status == true)){
			 * if(menuItem.getPosition()>1){ HibernateXMenuDAO menuDAO=new
			 * HibernateXMenuDAO(_session); SimpleMenuItem firstChild =
			 * (SimpleMenuItem)menuItem.getParent().getChildren().get(0);
			 * menuDAO
			 * .moveXMenu(menuItem.getId(),menuItem.getParent().getId(),firstChild
			 * .getId()); } }
			 */
			_session.flush();
			_request.getSession().setAttribute("edit_user_varEditingMenuId", id);
			_request.getSession().setAttribute("edit_user_varEditingParentId", id.substring(0, id.length() - scrictId.length() + 1));
			// Create menu Internationalization
			createInternationalization(_session, scrictId, _parameters);
			// Log Change
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.MENU_UPDATED, new Object[] { _parameters.get("id") }, null, false);
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
