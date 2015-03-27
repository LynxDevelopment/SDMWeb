package com.lynxspa.microportal.utils;

import org.hibernate.Session;

import com.lynxit.xweb.xmenu.entities.SimpleMenuItem;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.microportal.configuration.MicroportalConfig;

public class MicroportalUtils {

	public static final String getDefaultLanguage(Session _session) throws Exception {
		return (String) MicroportalManager.getInstance().getConfiguration(_session, MicroportalConfig.DEFAULTLANGUAGE);
	}

	public static final String[] getConfiguredLanguages(Session _session) throws Exception {
		return ((String) MicroportalManager.getInstance().getConfiguration(_session, MicroportalConfig.CONFIGUREDLANGUAGES)).split(",");
	}

	public static final Object getConfigValue(String _config, Session _session) throws Exception {
		return MicroportalManager.getInstance().getConfiguration(_session, MicroportalConfig.valueOf(_config));
	}

	public static void updateMenuParentURL(String _parent, Session _session) throws Exception {
		SimpleMenuItem parentItem = (SimpleMenuItem) _session.get(SimpleMenuItem.class, _parent);
		for (Object childObject : parentItem.getChildren()) {
			SimpleMenuItem child = (SimpleMenuItem) childObject;
			if (child.isEnabled() && !child.isExternalLink() && !child.isTargetLink()) {
				parentItem.setUrl(null);
				_session.update(parentItem);
				break;
			} else if (child.isEnabled() && child.isExternalLink() && child.isTargetLink()) {
				parentItem.setUrl("/fpm/microportal/common/blank.xwb");
				_session.update(parentItem);
				break;
			}
		}
	}
}
