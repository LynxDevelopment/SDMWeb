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
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.exceptions.WarningDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class RemoveMessageFormatFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		 
		ValidationUtils.validateField("messageFormatId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String messageFormatId=null;
		Query query=null;
		long existentMessages=0l;
		int fieldsDeleted=0;
		int typesDeleted=0;
		int clustersDeleted=0;
		CAMessageFormat messageFormat=null;
	
		try{
			messageFormatId=(String)_parameters.get("messageFormatId");
			messageFormat=(CAMessageFormat)_session.get(CAMessageFormat.class, messageFormatId);
			//Validate existent messages associated to this type
			query=_session.createQuery("select count(*) from CAEventMessage where messageType.id.format=:messageFormat");
			query.setEntity("messageFormat",messageFormat);
			existentMessages+=(Long)query.uniqueResult();
			query=_session.createQuery("select count(*) from CAEventMessageHistoric where messageType.id.format=:messageFormat");
			query.setEntity("messageFormat",messageFormat);
			existentMessages+=(Long)query.uniqueResult();
			if(existentMessages>0)
				throw new FPMException(WarningDict.EXISTING_MESSAGES_RELATED_TO_THIS_MESSAGEFORMAT,new Object[]{existentMessages});
			//Delete
			//	Deleting type details
			query=_session.createQuery("delete CAMessageFieldConfig where id.type.id.format=:messageFormat");
			query.setEntity("messageFormat",messageFormat);
			fieldsDeleted=query.executeUpdate();
			//	Deleting types
			query=_session.createQuery("delete CAMessageType where id.format=:messageFormat");
			query.setEntity("messageFormat",messageFormat);
			typesDeleted=query.executeUpdate();
			//	Deleting clusters
			query=_session.createQuery("delete CADomainClusterFormat where id.format=:messageFormat");
			query.setEntity("messageFormat",messageFormat);
			clustersDeleted=query.executeUpdate();
			//	Deleting association with providers
			query=_session.createQuery("delete CAFormatProvider where messageFormat=:messageFormat");
			query.setEntity("messageFormat",messageFormat);
			fieldsDeleted=query.executeUpdate();
			//	Deleting format
			_session.delete(messageFormat);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MESSAGEFORMAT_DELETED,new Object[]{messageFormatId,typesDeleted,fieldsDeleted,clustersDeleted},null,false);
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
