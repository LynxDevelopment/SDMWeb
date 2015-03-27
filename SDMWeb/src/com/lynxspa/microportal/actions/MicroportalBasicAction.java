package com.lynxspa.microportal.actions;

import java.util.List;
import java.util.Map;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxspa.exception.FPMException;
import com.lynxspa.xweb.actions.EnhancedBasicAction;

public abstract class MicroportalBasicAction extends EnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
	}
}
