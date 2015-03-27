package com.lynxspa.sdm.actions.administration.messageformats.messagefields;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFieldConfig;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFieldConfigId;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFormat;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageType;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageTypeId;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class RemoveMessageTypeDetailsFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		 
		ValidationUtils.validateField("messageFormatId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageTypeId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageTypeDetailPath",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String messageFormatId=null;
		String messageTypeId=null;
		String messageFieldPath=null;
		CAMessageFieldConfig fieldConfig=null;
		CAMessageFormat messageFormat=null;
		CAMessageType messageType=null;
	
		try{
			messageFormatId=(String)_parameters.get("messageFormatId");
			messageTypeId=(String)_parameters.get("messageTypeId");
			messageFieldPath=(String)_parameters.get("messageTypeDetailPath");
			messageFormat=(CAMessageFormat)_session.get(CAMessageFormat.class, messageFormatId);
			messageType=(CAMessageType)_session.get(CAMessageType.class,new CAMessageTypeId(messageFormat,messageTypeId));
			fieldConfig=(CAMessageFieldConfig)_session.get(CAMessageFieldConfig.class,new CAMessageFieldConfigId(messageType,messageFieldPath));
			//delete field config
			_session.delete(fieldConfig);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MESSAGEFIELDCONFIG_DELETED,new Object[]{messageFieldPath,messageTypeId,messageFormatId},null,false);
			clearCache(_session,_user.getId());
		}catch(HibernateException e) {
			FPMException newException=new FPMException(ErrorDict.HIBERNATE_ERROR,e);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),newException,true);
			throw newException;
		}catch(Throwable e) {
			FPMException newException=new FPMException(e);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),newException,true);
			throw newException;
		}
	}
}
