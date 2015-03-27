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
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class AddUpdateMessageFieldFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		 
		ValidationUtils.validateField("messageFieldAction",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageFormatId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageTypeId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageFieldPath",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageFieldName",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageFieldDescription",_parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageFieldType",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageFieldFormat",_parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageFieldLength",_parameters, _errors, ValidationsDict.ISNOTEMPTY, ValidationsDict.ISINTEGER);
		ValidationUtils.validateField("messageFieldDisplayGroup",_parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("messageFieldDisplayRow",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISINTEGER);
		ValidationUtils.validateField("messageFieldDisplayColumn",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISINTEGER);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String action=null;
		String messageFormatId=null;
		String messageTypeId=null;
		String messageFieldPath=null;
		CAMessageFieldConfig fieldConfig=null;
		CAMessageFormat messageFormat=null;
		CAMessageType messageType=null;
	
		try{
			action=(String)_parameters.get("messageFieldAction");
			messageFormatId=(String)_parameters.get("messageFormatId");
			messageTypeId=(String)_parameters.get("messageTypeId");
			messageFieldPath=(String)_parameters.get("messageFieldPath");
			messageFormat=(CAMessageFormat)_session.get(CAMessageFormat.class, messageFormatId);
			messageType=(CAMessageType)_session.get(CAMessageType.class,new CAMessageTypeId(messageFormat,messageTypeId));
			fieldConfig=(CAMessageFieldConfig)_session.get(CAMessageFieldConfig.class,new CAMessageFieldConfigId(messageType,messageFieldPath));

			final boolean messageFieldHidden=((_parameters.get("messageFieldHidden")!=null)&&(((String[])_parameters.get("messageFieldHidden")).length>0));
			final String messageFieldType=((String[])_parameters.get("messageFieldType"))[0];
			if("INSERT".equals(action)){
				fieldConfig=new CAMessageFieldConfig(_user.getId(),messageType,messageFieldPath);
				fieldConfig.setFieldName((String)_parameters.get("messageFieldName"));
				fieldConfig.setHidden(messageFieldHidden);
				fieldConfig.setDescription((String)_parameters.get("messageFieldDescription"));
				fieldConfig.setFieldType(messageFieldType);
				fieldConfig.setFieldFormat((String)_parameters.get("messageFieldFormat"));
				if(_parameters.get("messageFieldLength")!=null)
					fieldConfig.setFieldLength(Integer.parseInt((String)_parameters.get("messageFieldLength")));
				fieldConfig.setDisplayGroup((String)_parameters.get("messageFieldDisplayGroup"));
				fieldConfig.setDisplayRow(Integer.parseInt((String)_parameters.get("messageFieldDisplayRow")));
				fieldConfig.setDisplayColumn(Integer.parseInt((String)_parameters.get("messageFieldDisplayColumn")));
				HibernateUtils.customSave(_session, fieldConfig, _user.getId());
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MESSAGEFIELDCONFIG_INSERTED,new Object[]{messageFieldPath,messageTypeId,messageFormatId},null,false);
			}else{
				fieldConfig.setFieldName((String)_parameters.get("messageFieldName"));
				fieldConfig.setHidden(messageFieldHidden);
				fieldConfig.setDescription((String)_parameters.get("messageFieldDescription"));
				fieldConfig.setFieldType(messageFieldType);
				fieldConfig.setFieldFormat((String)_parameters.get("messageFieldFormat"));
				if(_parameters.get("messageFieldLength")!=null)
					fieldConfig.setFieldLength(Integer.parseInt((String)_parameters.get("messageFieldLength")));
				fieldConfig.setDisplayGroup((String)_parameters.get("messageFieldDisplayGroup"));
				fieldConfig.setDisplayRow(Integer.parseInt((String)_parameters.get("messageFieldDisplayRow")));
				fieldConfig.setDisplayColumn(Integer.parseInt((String)_parameters.get("messageFieldDisplayColumn")));
				HibernateUtils.customUpdate(_session, fieldConfig, _user.getId());
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MESSAGEFIELDCONFIG_UPDATED,new Object[]{messageFieldPath,messageTypeId,messageFormatId},null,false);
			}
			clearCache(_session,_user.getId());
		}catch(FPMException e){
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),e,true);
			throw e;
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
