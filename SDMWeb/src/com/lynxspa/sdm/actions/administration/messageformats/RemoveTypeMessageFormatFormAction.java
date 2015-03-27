package com.lynxspa.sdm.actions.administration.messageformats;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFormat;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageType;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageTypeId;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.exceptions.WarningDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class RemoveTypeMessageFormatFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		 
		ValidationUtils.validateField("formatId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("removeTypeId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String formatId=null;
		String typeCode=null;
		CAMessageFormat messageFormat=null;
		CAMessageType messageType=null;
		Query query=null;
		long existentMessages=0l;
	
		try{
			formatId=(String)_parameters.get("formatId");
			typeCode=(String)_parameters.get("removeTypeId");
			messageFormat=(CAMessageFormat)_session.get(CAMessageFormat.class, formatId);
			messageType=(CAMessageType)_session.get(CAMessageType.class,new CAMessageTypeId(messageFormat,typeCode));
			//Validate existent messages associated to this type
			query=_session.createQuery("select count(*) from CAEventMessage where messageType=:messageType");
			query.setEntity("messageType",messageType);
			existentMessages+=(Long)query.uniqueResult();
			query=_session.createQuery("select count(*) from CAEventMessageHistoric where messageType=:messageType");
			query.setEntity("messageType",messageType);
			existentMessages+=(Long)query.uniqueResult();
			if(existentMessages>0)
				throw new FPMException(WarningDict.EXISTING_MESSAGES_RELATED_TO_THIS_MESSAGETYPE,new Object[]{existentMessages});
			//Delete
			//	Deleting type details
			query=_session.createQuery("delete CAMessageFieldConfig where id.type=:messageType");
			query.setEntity("messageType",messageType);
			query.executeUpdate();
			//	Delete type
			_session.delete(messageType);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MESSAGEFORMAT_REMOVE_MESSAGETYPE,new Object[]{typeCode,formatId},null,false);
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
