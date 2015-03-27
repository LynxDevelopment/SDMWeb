package com.lynxspa.microportal.taglibs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.tag.common.core.ImportSupport;
import org.hibernate.Session;

import com.lynxit.xweb.boxlet.BoxletManager;
import com.lynxit.xweb.boxlet.hibernateentities.Boxlet;
import com.lynxit.xweb.hibernateentities.containers.ContainerSchedule;
import com.lynxit.xweb.hibernateentities.messages.Permission;
import com.lynxit.xweb.servlets.HttpSessionParams;
import com.lynxit.xweb.users.User;
import com.lynxit.xweb.users.UsersHelper;
import com.lynxit.xweb.usersimulation.SimulatedSessionManager;

/**
 * @author albert.farre Print boxlet with multilanguage support, it means that
 *         search from all existent configurations until find one with the
 *         correspondent language
 */
public class I18NBoxletTag extends ImportSupport {

	private static final long	serialVersionUID	= 2847757119468061717L;
	private static Logger		logger_				= Logger.getLogger(I18NBoxletTag.class);
	private static final String	MODULE_NAME			= "BOXLET";
	private static final String	ADMIN_FUNCTION_NAME	= "UPDATE";
	public static final String	SHOW_IN_PAGES		= "SHOW_BOXLETS";

	private ContainerSchedule	configuration		= null;										;
	private String				url					= null;
	private String				name				= null;
	private String				publicationTime		= null;
	private String				expirationTime		= null;
	private boolean				enabled				= true;
	private String				datePattern			= "dd/MM/yyyy HH:mm";
	private boolean				showBoxlets			= true;
	private boolean				i18n				= false;

	/**
	 * @param _name
	 */
	public void setName(String _name) {
		this.name = _name;
	}

	/**
	 * @param _datePattern
	 */
	public void setDatePattern(String _datePattern) {
		this.datePattern = _datePattern;
	}

	/**
	 * @param _expirationTime
	 */
	public void setExpirationTime(String _expirationTime) {
		this.expirationTime = _expirationTime;
	}

	/**
	 * @param _publicationTime
	 */
	public void setPublicationTime(String _publicationTime) {
		this.publicationTime = _publicationTime;
	}

	/**
	 * @param _url
	 */
	public void setUrl(String _url) {
		this.url = _url;
	}

	/**
	 * @param Boolean
	 */
	public void setEnabled(Boolean _enabled) {
		this.enabled = _enabled;
	}

	/**
	 * @param Boolean
	 */
	public void setI18n(Boolean _i18n) {
		this.i18n = _i18n;
	}

	protected void setUpConfiguration() throws ParseException {

		this.configuration = new ContainerSchedule();
		this.configuration.setName(this.name);
		this.configuration.setEnabled(this.enabled);
		this.configuration.setUrl(this.url);
		String datePattern = this.datePattern;
		if (this.datePattern == null) {
			datePattern = "dd/MM/yyyy HH:mm";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		if (this.publicationTime != null) {
			this.configuration.setPublishDate(sdf.parse(this.publicationTime));
		}
		if (this.expirationTime != null) {
			this.configuration.setExpirationDate(sdf.parse(this.expirationTime));
		}
	}

	protected ContainerSchedule getConfiguratioToUse(final Boxlet _boxlet, final Locale _locale) {

		ContainerSchedule reply = null;
		ContainerSchedule candidate = null;
		Locale currentLocale = null;

		reply = _boxlet.getEnabledConfiguration();
		if (this.i18n) {
			for (Object configuration : _boxlet.getConfigurations()) {
				candidate = (ContainerSchedule) configuration;
				currentLocale = new Locale(candidate.getHtml().getLanguage());
				if ((candidate.getHtml() != null) && (currentLocale.getLanguage().equals(_locale.getLanguage()))) {
					reply = candidate;
					break;
				}
			}
		}

		return reply;
	}

	protected int performTagActions() throws IOException, JspException {

		int reply = SKIP_BODY;

		showBoxlets = false;
		SimulatedSessionManager simulatedSessionManager = new SimulatedSessionManager(pageContext.getSession());
		Object showBoxletsAttr = simulatedSessionManager.getRealSessionAttribute(SHOW_IN_PAGES);
		Locale locale = (Locale) Config.get(pageContext.getSession(), Config.FMT_LOCALE);
		Boxlet boxlet = BoxletManager.getInstance().getBoxlet(this.name);

		if (showBoxletsAttr != null) {
			showBoxlets = ((Boolean) showBoxletsAttr).booleanValue();
		}
		// print the fieldset tag to identify boxlet if "showBoxlet" flag is
		// true
		if (showBoxlets) {
			pageContext.getOut().print("<div id=\"" + this.name + "\" class=\"boxlet_item");
			if (boxlet == null)
				pageContext.getOut().print(" not_existent");
			pageContext.getOut().print("\">");
			pageContext.getOut().print("<fieldset><legend>Boxlet: " + this.name + "</legend>");
		}
		// search configuration
		boolean configurationFound = false;
		if (boxlet == null) {
			logger_.debug("No configuration found for boxlet '" + this.name + "'");
		} else {
			ContainerSchedule configuration = getConfiguratioToUse(boxlet, locale);
			if (configuration != null) {
				// Use configured settings
				logger_.debug("Using " + configuration.getName() + " configuration");
				this.configuration = configuration;
				if (this.configuration.isValid()) {
					if ((this.configuration.getUrl() != null) && (this.configuration.getUrl().length() != 0)) {
						super.url = this.configuration.getUrl();
						reply = super.doStartTag();
					} else if ((this.configuration.getHtml() != null) && (this.configuration.getHtml().getBody().length() != 0)) {

						// Busqueda de permisos...
						User user = (User) pageContext.getSession().getAttribute(HttpSessionParams.LOGGED_USER);
						Set<String> userGroups = user.getAssociatedGroups();
						Set<Permission> groupsPermission = this.configuration.getHtml().getGroupPermissions();
						boolean groupFound = false;
						for (String userGroup : userGroups) {
							for (Permission permission : groupsPermission) {
								if (userGroup.equals(permission.getPrincipal())) {
									groupFound = true;
									break;
								}
							}
							if (groupFound) {
								break;
							}
						}

						JspWriter out = pageContext.getOut();
						// Si el grupo del usuario casa con el del boxlet,
						// printamos el contenido...
						if (groupFound) {
							out.print(this.configuration.getHtml().getBody());
						} else {
							out.print("&nbsp");
						}
						reply = SKIP_BODY;
					}
					configurationFound = true;
				}
			}
		}

		if (!configurationFound) {
			// Use tag attributes
			if (this.configuration.isValid()) {
				if (this.configuration.getUrl() != null) {
					super.url = this.configuration.getUrl();
					reply = super.doStartTag();
				} else {
					reply = EVAL_BODY_INCLUDE;
				}
			}
		}

		return reply;
	}

	/**
	 * @param _user
	 * @param _session
	 * @return
	 */
	public static boolean hasAdminFunction(User _user, Session _session) {
		return UsersHelper.hasFunction(_user, I18NBoxletTag.MODULE_NAME, I18NBoxletTag.ADMIN_FUNCTION_NAME, _session);
	}

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {

		int reply = SKIP_BODY;

		try {
			setUpConfiguration();
			reply = performTagActions();

		} catch (Exception e) {
			logger_.error("Error printing boxlet " + name, e);
		}
		return reply;
	}

	@Override
	public int doEndTag() throws JspException {

		int reply = EVAL_PAGE;

		if ((this.configuration.getUrl() != null) && (this.configuration.getUrl().length() != 0) && (this.configuration.isValid()))
			reply = super.doEndTag();
		if (showBoxlets) {
			try {
				pageContext.getOut().print("</fieldset>");
				pageContext.getOut().print("</div>");
			} catch (IOException e) {
				logger_.error("Error printing html to highlight boxlet " + this.name, e);
			}
		}

		return reply;
	}
}
