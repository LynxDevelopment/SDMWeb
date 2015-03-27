package com.lynxspa.sdm.actions.administration.events.providers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFormat;
import com.lynxspa.sdm.entities.events.providers.CAExternalEventProvider;
import com.lynxspa.sdm.entities.events.providers.CAFormatProvider;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class InsertEventProviderFormatFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		
		ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageFormat",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("idAtMessage", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		CAExternalEventProvider provider=null;
		CAMessageFormat format=null;
		CAFormatProvider formatProvider=null;
		
		format=(CAMessageFormat)_session.load(CAMessageFormat.class, ((String[])_parameters.get("messageFormat"))[0]);
		provider=(CAExternalEventProvider)_session.load(CAExternalEventProvider.class, String.valueOf(_parameters.get("id")));
		formatProvider=new CAFormatProvider(_user.getId(),format,String.valueOf(_parameters.get("idAtMessage")),provider);
		HibernateUtils.customSave(_session, formatProvider, _user.getId());
		clearCache(_session,_user.getId());
	}
}
