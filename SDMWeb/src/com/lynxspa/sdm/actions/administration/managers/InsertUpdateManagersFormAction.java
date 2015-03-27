package com.lynxspa.sdm.actions.administration.managers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.managers.SPManager;
import com.lynxspa.entities.securities.managers.SPManagerGroup;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class InsertUpdateManagersFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String,Object> _parameters,List<ValidationException> _errors) throws FPMException{
		
		ValidationUtils.validateField("operationType",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("name",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		if("UPDATE".equals(_parameters.get("operationType"))){
			ValidationUtils.validateField("id",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		}
	}

	@Override
	public void performAction(HttpServletRequest _request,Session _session, LoggedUser _user,Map<String, Object> _parameters) throws FPMException {

		SPManager manager = null;

		try{
			//Recover parameters
			final String operationType=(String)_parameters.get("operationType");
			final String managerGroupId=_parameters.get("managerGroup")!=null&&((String[])_parameters.get("managerGroup")).length>0?((String[])_parameters.get("managerGroup"))[0]:null;
			final String name=(String)_parameters.get("name");
			final String customerId=(String)_parameters.get("customerId");
			//Manager generation
			final SPManagerGroup managerGroup=(managerGroupId!=null)? HibernateUtils.getEntity(_session,SPManagerGroup.class,managerGroupId) : null;
			manager=new SPManager(_user.getId(),name,customerId,managerGroup);
			if(operationType.equalsIgnoreCase("UPDATE")){
				final String managerId=(String)_parameters.get("id");				
				manager.setId(Long.parseLong(managerId));
				HibernateUtils.customUpdate(_session, manager, _user.getId());
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MANAGER_CHANGED,new Object[]{managerId},null,false);
			}else{
				HibernateUtils.customSave(_session, manager, _user.getId());
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MANAGER_INSERTED,new Object[]{manager.getId()},null,false);
			}
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
