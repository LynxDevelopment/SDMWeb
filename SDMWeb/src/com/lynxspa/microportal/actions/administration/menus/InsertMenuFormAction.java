package com.lynxspa.microportal.actions.administration.menus;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.boxlet.hibernateentities.Boxlet;
import com.lynxit.xweb.hibernateentities.containers.ContainerKey;
import com.lynxit.xweb.hibernateentities.containers.ContainerSchedule;
import com.lynxit.xweb.hibernateentities.groups.Group;
import com.lynxit.xweb.hibernateentities.messages.Html;
import com.lynxit.xweb.i18n.BundlesMap;
import com.lynxit.xweb.i18n.I18nSystem;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxit.xweb.xmenu.entities.BoxletLinkMenuItem;
import com.lynxit.xweb.xmenu.entities.SimpleMenuItem;
import com.lynxit.xweb.xmenu.exception.InvalidMenuItemException;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.microportal.actions.MicroportalBasicAction;
import com.lynxspa.microportal.configuration.MicroportalConfig;
import com.lynxspa.microportal.dictionaries.WebLogAuditDict;
import com.lynxspa.microportal.dictionaries.WebLogWarningDict;
import com.lynxspa.validation.utils.ValidationAdapter;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class InsertMenuFormAction extends MicroportalBasicAction {

	protected enum MenuValidations implements ValidationAdapter {

		CLEARID("error.menu.clear.id") {
			@Override
			public boolean validateInternal(Object _value) {

				boolean reply = true;

				for (char ch : ((String) _value).toCharArray()) {
					reply &= (((((1 << Character.UPPERCASE_LETTER) | (1 << Character.LOWERCASE_LETTER) | (1 << Character.DECIMAL_DIGIT_NUMBER)) >> Character.getType(ch)) & 1) != 0);
				}

				return reply;
			}
		},
		MENUTYPE("error.menu.type") {
			@Override
			public boolean validateInternal(Object _value) {
				return (("LINK".equals(_value)) || ("BOXLET".equals(_value)) || ("LINK_POPUP".equals(_value)) || ("FILE".equals(_value)));
			}
		};

		// Object definition
		private String	errorKey	= null;

		MenuValidations(String _errorKey) {
			this.errorKey = _errorKey;
		}

		@Override
		public String getErrorKey() {
			return errorKey;
		}

		public void setErrorKey(String errorKey) {
			this.errorKey = errorKey;
		}

		@Override
		public boolean validate(final Object[] _value) {

			if (_value != null) {
				for (Object valueToValidate : _value) {
					if (!validateInternal(valueToValidate))
						return false;
				}
			}

			return true;
		}

		protected abstract boolean validateInternal(Object _value);
	}

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {

		String fullId = null;

		try {
			ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING, MenuValidations.CLEARID);
			ValidationUtils.validateField("parentId", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
			fullId = ((String) _parameters.get("parentId")) + "/" + ((String) _parameters.get("id"));
			if (getCurrentSession().get(SimpleMenuItem.class, fullId) != null)
				_errors.add(new ValidationException("error.menu.id.already.exist", "id", String.valueOf(_parameters.get("id"))));
			for (String language : ((String) MicroportalManager.getInstance().getConfiguration(getCurrentSession(), MicroportalConfig.CONFIGUREDLANGUAGES)).split(",")) {
				ValidationUtils.validateField("title_" + language, _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
			}

			ValidationUtils.validateField("status", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISBOOLEAN);
			ValidationUtils.validateField("itemType", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING, MenuValidations.MENUTYPE);
			final String classeMenu = (_parameters.get("classeMenu") != null) ? ((String[]) _parameters.get("classeMenu"))[0] : null;
			if ("DATOS".equals(classeMenu)) {
				final String itemType = (_parameters.get("itemType") != null) ? ((String[]) _parameters.get("itemType"))[0] : null;
				if (("LINK".equals(itemType)) || ("LINK_POPUP".equals(itemType)) || ("FILE".equals(itemType))) {
					ValidationUtils.validateField("menuUrl", _parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
				} else if (("BOXLET".equals(itemType))) {
					ValidationUtils.validateField("numbersBody", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
					ValidationUtils.validateField("listTemplates", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
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
		String fullId = null;
		String parent = null;
		boolean status = false;
		String itemType = null;
		String url = null;
		String boxletName = null;
		String classeMenu = null;
		SimpleMenuItem menuItem = null;
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
			classeMenu = ((String[]) _parameters.get("classeMenu"))[0];
			// En el caso que el menu se del tipo jerarquizado, su url debe ser
			// nula.
			if ("NIVEL".equals(classeMenu)) {
				url = null;
			}
			// Build fullId
			fullId = parent + "/" + id;
			// Verify groups
			List<String> listGroups = getGroupListFromString((String) _parameters.get("listGroups"));
			List<Group> listValidation = _session.createQuery("from Group where id in(:listGroups)").setParameterList("listGroups", listGroups).list();
			if ((listValidation == null) || (listValidation.size() != listGroups.size())) {
				throw new FPMException(WebLogWarningDict.ANY_GROUP_NOT_EXIST);
			}
			// Create MenuItem
			menuItem = ((itemType.equals("BOXLET")) ? new BoxletLinkMenuItem() : new SimpleMenuItem());
			menuItem.rename(fullId);
			menuItem.setTitle(id);
			menuItem.setEnabled(status);
			menuItem.setMarked(false);
			menuItem.setLanguageId("es");
			menuItem.setIcon1(null);
			menuItem.setIcon2(null);
			menuItem.setExternalLink(false);
			// Add groups.
			Set permissions = new HashSet();
			for (Group group : listValidation) {
				permissions.add(group);
			}
			menuItem.setPermissions(permissions);

			if (itemType.equals("LINK")) {
				if (url != null) {
					menuItem.setUrl("/fpm/microportal/common/link_display.xwb?title=" + id + "&urlEmbeded=" + URLEncoder.encode(url, "UTF-8"));
				} else {
					menuItem.setUrl(null);
				}
				insertSimpleXMenu(menuItem, parent, _session);
			} else if (itemType.equals("LINK_POPUP")) {
				menuItem.setUrl(url);
				menuItem.setTargetLink(true);
				menuItem.setExternalLink(true);
				insertSimpleXMenu(menuItem, parent, _session);
			} else if (itemType.equals("BOXLET")) {
				numbersBody = Integer.parseInt((String) _parameters.get("numbersBody"));
				templateFileName = ((String[]) _parameters.get("listTemplates"))[0];
				for (int count = 1; count <= numbersBody; count++) {
					listBody.add((String) _parameters.get(new StringBuilder("body").append(count).toString()));
				}
				menuItem.setUrl(new StringBuilder("/fpm/microportal/common/templates/").append(templateFileName).append("?title=").append(id).toString());
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
					boxletName = new StringBuilder(fullId).append("/body").append(count).toString();
					createBoxlet(_session, id, boxletName, listBody.get(count - 1), groups, _user);
				}

				insertBoxletLinkXMenu((BoxletLinkMenuItem) menuItem, parent, boxletName, _session);
			} else if (itemType.equals("FILE")) {
				menuItem.setUrl(url);
				menuItem.setTargetLink(true);
				menuItem.setExternalLink(true);
				insertSimpleXMenu(menuItem, parent, _session);
			}

			// Create menu Internationalization
			createInternationalization(_session, id, _parameters);

			// Save
			_session.flush();
			_request.getSession().setAttribute("edit_user_varEditingMenuId", fullId);
			_request.getSession().setAttribute("edit_user_varEditingParentId", fullId.substring(0, fullId.length() - id.length() + 1));
			// Log Change
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), WebLogAuditDict.MENU_INSERTED, new Object[] { _parameters.get("id") }, null, false);
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
	protected Boxlet createBoxlet(Session _session, String _internalId, String _boxletName, String _defaultContent, String[] groups, LoggedUser _user) throws FPMException {

		Boxlet reply = null;
		Html htmlContent = null;
		ContainerSchedule configuration;

		if (_session.get(Boxlet.class, new ContainerKey(_boxletName, Boxlet.BOXLET_TYPE)) != null)
			throw new FPMException(WebLogWarningDict.BOXLET_OR_MENU_ALREADY_EXIST, new Object[] { _internalId });
		reply = new Boxlet();
		reply.setId(new ContainerKey(_boxletName, Boxlet.BOXLET_TYPE));
		for (String language : ((String) MicroportalManager.getInstance().getConfiguration(_session, MicroportalConfig.CONFIGUREDLANGUAGES)).split(",")) {
			htmlContent = new Html("BOXLET");
			htmlContent.addGroupsPermission(groups);
			htmlContent.setAuthor(_user.getId());
			htmlContent.setBody(_defaultContent);
			htmlContent.setLanguage(language);
			HibernateUtils.customSave(_session, htmlContent, _user.getId());
			configuration = new ContainerSchedule();
			configuration.setName(_boxletName + "Config");
			configuration.setPublishDate(Calendar.getInstance().getTime());
			configuration.setExpirationDate(null);
			configuration.setEnabled(true);
			configuration.setHtml(htmlContent);
			configuration.setUrl(null);
			configuration.setContainer(reply);
			HibernateUtils.customSave(_session, configuration, _user.getId());
			reply.getConfigurations().add(configuration);
		}
		HibernateUtils.customSave(_session, reply, _user.getId());

		return reply;
	}

	protected void createInternationalization(Session _session, String _internalTitle, Map<String, Object> _parameters) throws FPMException {

		String title = null;
		BundlesMap bundle = null;

		for (String language : ((String) MicroportalManager.getInstance().getConfiguration(_session, MicroportalConfig.CONFIGUREDLANGUAGES)).split(",")) {
			title = (String) _parameters.get("title_" + language);
			bundle = I18nSystem.getInstance().getBundlesMaps().get("menus");
			bundle.setProperty(_internalTitle, new Locale(language), title);
			bundle.save();
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

	protected boolean insertSimpleXMenu(SimpleMenuItem menuItem, String parentId, Session session_) throws InvalidMenuItemException {
		// Get the parent:
		SimpleMenuItem parentItem = (SimpleMenuItem) session_.load(SimpleMenuItem.class, parentId);

		long checkId = ((Number) session_.createQuery("select count(item.id) from HibernateMappedMenuItem item where item.id=?").setString(0, menuItem.getId()).uniqueResult()).longValue();
		if (checkId > 0)
			return false;

		// Append the new menuItem to the parent:
		parentItem.addChild(menuItem);

		return true;
	}

	protected boolean insertBoxletLinkXMenu(BoxletLinkMenuItem menuItem, String parentId, String boxletName, Session session_) throws InvalidMenuItemException {
		ContainerKey id = new ContainerKey(boxletName, Boxlet.BOXLET_TYPE);

		Boxlet boxlet = (Boxlet) session_.get(Boxlet.class, id);
		if (boxlet == null)
			return false;

		// Get the parent:
		SimpleMenuItem parentItem = (SimpleMenuItem) session_.load(SimpleMenuItem.class, parentId);

		long checkId = ((Number) session_.createQuery("select count(item.id) from HibernateMappedMenuItem item where item.id=?").setString(0, menuItem.getId()).uniqueResult()).longValue();
		if (checkId > 0)
			return false;

		// Append the new menuItem to the parent:
		parentItem.addChild(menuItem);
		menuItem.setBoxlet(boxlet);
		return true;
	}
}
