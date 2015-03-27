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
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class DeleteManagersFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String,Object> _parameters,List<ValidationException> _errors) throws FPMException{
		ValidationUtils.validateField("managerDeletable",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
	}

	@Override
	public void performAction(HttpServletRequest _request,Session _session, LoggedUser _user,Map<String, Object> _parameters) throws FPMException {

		try {
			//Recover parameters
			final String managerId=(String)_parameters.get("managerDeletable");
			//Get manager group
			final SPManager manager=HibernateUtils.getEntity(_session,SPManager.class, Long.parseLong(managerId));
			//Delete manager group
			HibernateUtils.customDelete(_session, manager, _user.getId());
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MANAGER_DELETED,new Object[]{managerId},null,false);
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
