package com.lynxspa.sdm.actions.administration.securities;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.entities.securities.SPVirtualSecurity;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class SolveVirtualSecurityFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
	
		ValidationUtils.validateField("securityId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		ValidationUtils.validateField("virtualSecurityId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
	}
	
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		SPSecurity security = null;
		SPVirtualSecurity virtualSecurity = null;
		long securityId=0l;
		long virtualSecurityId=0l;
		Query query=null;
		int affectedRows=0;

		try {
			//Recover parameter values
			securityId=Long.parseLong((String)_parameters.get("securityId"));
			virtualSecurityId=Long.parseLong((String)_parameters.get("virtualSecurityId"));
			//Recover original securities to validate id
			security=(SPSecurity)_session.get(SPSecurity.class, securityId);
			virtualSecurity=(SPVirtualSecurity)_session.get(SPVirtualSecurity.class, virtualSecurityId);
			//Update associated messages
			query=_session.createQuery("update CAEventMessage set normalizedSecurity.id=:securityId,operationStatus.state.id.code=:newState where normalizedSecurity.id=:virtualSecurityId");
			query.setLong("securityId", security.getId());
			query.setString("newState", CAStatesEVENTMESSAGEFlow.PRSD.getId());
			query.setLong("virtualSecurityId", virtualSecurity.getId());
			affectedRows=query.executeUpdate();
			//delete virtual security
			HibernateUtils.customDelete(_session, virtualSecurity, _user.getId());
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.VIRTUAL_SECURITY_REPLACED,new Object[]{security.getId(),virtualSecurity.getId(),affectedRows},null,false);
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
