package com.lynxspa.microportal.configuration;

import com.lynxspa.dictionaries.ClassType;
import com.lynxspa.entities.application.configurations.adapter.ConfigDictAdapter;

public enum MicroportalConfig implements ConfigDictAdapter {

	PASSWORDEXPIRATIONMARGIN("microportal.pwdResetExpMargin", "Password expiration margin in days", ClassType.LONG, 10l, true, true),
	DEFAULTLANGUAGE("microportal.DefaultLanguage", "Default language", ClassType.SHORTSTRING, "en", false, false),
	CONFIGUREDLANGUAGES("microportal.configured.languages", "Configured languages", ClassType.MIDDLESTRING, "es,ca,en,fr", false, false),
	UPLOADFILESPATH("microportal.uploadfiles.path", "Raiz del path a partir de la cual se pueden guardar ficheros", ClassType.LONGSTRING, "/upload/boxlet", true, true),
	TEMPORALDIRECTORY("temporal.directory", "Temporal directory (should be into webapp deployment)", ClassType.VERYLONGSTRING, "userfiles/temp", true, true);

	private String		code			= null;
	private String		description		= null;
	private ClassType	type			= null;
	private Object		defaultValue	= null;
	private boolean		updatable		= false;
	private boolean		editable		= false;

	MicroportalConfig(String _code, String _description, ClassType _type, Object _defaultValue, boolean _updatable, boolean _editable) {
		this.code = _code;
		this.description = _description;
		this.type = _type;
		this.defaultValue = _defaultValue;
		this.editable = _editable;
		this.updatable = _updatable;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public ClassType getType() {
		return type;
	}

	@Override
	public Object getDefaultValue() {
		return defaultValue;
	}

	@Override
	public boolean isEditable() {
		return editable;
	}

	@Override
	public boolean isUpdatable() {
		return updatable;
	}
}
