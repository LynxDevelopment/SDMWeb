package com.lynxspa.sdm.actions;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.exception.FPMException;
import com.lynxspa.xweb.actions.EnhancedBasicAction;

public abstract class CoacEnhancedBasicAction extends EnhancedBasicAction{

	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException{}
	
	protected final void clearCache(final Session _session,final String _user) throws FPMException{
		final SDMConfigManager manager=SDMConfigManager.getInstance();
		manager.initialize();
		manager.setConfiguration(_session,CAConfiguration.CLEARCACHE,Boolean.TRUE.toString(),_user,false);
	}
}
