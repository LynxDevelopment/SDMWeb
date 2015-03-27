package com.lynxspa.microportal;

import com.lynxspa.managers.ConfigManager;

public class MicroportalManager extends ConfigManager {

	public static final String			APPLICATIONNAME	= "MICROPORTAL";
	public static final String			BUNDLENAME		= "microportal";

	public static MicroportalManager	instance		= null;

	@SuppressWarnings("unchecked")
	protected MicroportalManager() {

		super();
		initialize();
	}

	public static synchronized MicroportalManager getInstance() {

		MicroportalManager reply = null;

		if (MicroportalManager.instance == null) {
			MicroportalManager.instance = new MicroportalManager();
		}
		reply = MicroportalManager.instance;

		return reply;
	}

	@Override
	public void initialize() {
		super.initialize();
	}

	@Override
	public String getApplicationName() {
		return MicroportalManager.APPLICATIONNAME;
	}

	@Override
	public String getBundleName() {
		return MicroportalManager.BUNDLENAME;
	}
}
