package com.lynxspa.sdm.actions.administration.plannings;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.plannings.SPPlanningProcess;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class ExecutePlanificationsManualFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("planificationId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}
	
	@Override
	public void performAction(HttpServletRequest _request, Session _session,
			LoggedUser _user, Map<String, Object> _parameters)
			throws FPMException {
		
		String planificationId = null;
		SPPlanningProcess planification = null;
		
		try{
			
			planificationId=_parameters.get("planificationId")!=null?(String)_parameters.get("planificationId"):"";
			planification = (SPPlanningProcess) _session.load(SPPlanningProcess.class, Long.parseLong(planificationId));
			planification.setManual(true);	
			
		}catch (Exception e) {
			FPMException newException=new FPMException(e);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),newException,true);
			throw new FPMException(e);
		}catch(Throwable e) {
			FPMException newException=new FPMException(e);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),newException,true);
			throw newException;
		}
		
	}

}
