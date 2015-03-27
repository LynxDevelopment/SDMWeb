package com.lynxspa.sdm.actions.administration.events.providers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.providers.CAExternalEventProvider;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class DeleteEventProviderFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("eventProviderToDelete", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		HibernateUtils.customDelete(_session, _session.load(CAExternalEventProvider.class,String.valueOf(_parameters.get("eventProviderToDelete"))), _user.getId());
		clearCache(_session,_user.getId());
	}
}
