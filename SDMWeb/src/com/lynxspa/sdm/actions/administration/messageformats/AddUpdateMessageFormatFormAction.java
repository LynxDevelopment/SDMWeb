package com.lynxspa.sdm.actions.administration.messageformats;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFormat;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class AddUpdateMessageFormatFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		 
		ValidationUtils.validateField("messageFormatAction",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("formatId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("formatName",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("formatPattern",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("formatPath",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String action=null;
		String messageFormatId=null;
		String messageFormatName=null;
		String messageFormatPattern=null;
		String messageFormatPath=null;
		CAMessageFormat messageFormat=null;
	
		try{
			action=(String)_parameters.get("messageFormatAction");
			messageFormatId=(String)_parameters.get("formatId");
			messageFormatName=(String)_parameters.get("formatName");
			messageFormatPattern=(String)_parameters.get("formatPattern");
			messageFormatPath=(String)_parameters.get("formatPath");
			messageFormat=(CAMessageFormat)_session.get(CAMessageFormat.class, messageFormatId);
			if("INSERT".equals(action)){
				messageFormat=new CAMessageFormat(_user.getId(),messageFormatId,messageFormatName,messageFormatPath,messageFormatPattern);
				HibernateUtils.customSave(_session, messageFormat, _user.getId());
				this.getForm().getComponent("messageFormatAction").setProperty("value",new String[]{"UPDATE"});
				_request.getSession().setAttribute("edit_messageFormat_varEditingMessageFormat",messageFormatId);
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MESSAGEFORMAT_INSERTED,new Object[]{messageFormatId,messageFormatName,messageFormatPath,messageFormatPattern},null,false);
			}else{
				messageFormat.setName(messageFormatName);
				messageFormat.setPath(messageFormatPath);
				messageFormat.setPattern(messageFormatPattern);
				HibernateUtils.customUpdate(_session, messageFormat, _user.getId());
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MESSAGEFORMAT_UPDATED,new Object[]{messageFormatId,messageFormatName,messageFormatPath,messageFormatPattern},null,false);
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
