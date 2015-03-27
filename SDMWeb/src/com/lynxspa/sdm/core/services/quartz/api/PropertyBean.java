package com.lynxspa.sdm.core.services.quartz.api;

import java.io.Serializable;

import com.lynxspa.validation.utils.ValidationAdapter;
import com.lynxspa.validation.utils.ValidationsDict;

public class PropertyBean implements Serializable {

	private static final long	serialVersionUID	= 6971234719612779454L;

	private String				name				= null;
	private ValidationAdapter[]	validations			= null;

	public PropertyBean(final String _name) {
		this(_name, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISSTRING);
	}

	public PropertyBean(final String _name, final ValidationAdapter... _validations) {
		this.name = _name;
		this.validations = (_validations != null) ? _validations : new ValidationAdapter[0];
	}

	public String getName() {
		return name;
	}

	public ValidationAdapter[] getValidations() {
		return validations;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
