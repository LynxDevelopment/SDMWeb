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
import com.lynxspa.entities.plannings.SPProcess;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class InsertUpdatePlanificationsFormAction extends CoacEnhancedBasicAction {

	
	
	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
	
		ValidationUtils.validateField("operationType", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("cron", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("name", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		//ValidationUtils.validateField("planificationProcessId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		//ValidationUtils.validateField("planificationActive", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		
	}
	
	@Override
	public void performAction(HttpServletRequest _request, Session _session,
			LoggedUser _user, Map<String, Object> _parameters)
			throws FPMException {
		String cron=null;
		String name=null;
		String operationType=null;
		String template = null;
		String active=null;
		String planificationProcessId = null;
		SPProcess process=null;
		SPPlanningProcess planification = null;
		String id=null;
		
		try{
			template = _parameters.get("template")!=null?(String)_parameters.get("template"):"";
			cron = (String)_parameters.get("cron");
			name = (String)_parameters.get("name");
			operationType=(String)_parameters.get("operationType");
			planificationProcessId=_parameters.get("planificationProcessId")!=null?(String)_parameters.get("planificationProcessId"):"";
			
			process  = (SPProcess) _session.load(SPProcess.class, Long.parseLong(planificationProcessId));
			
			if ("UPDATE".equals(operationType)){
				active = (String)_parameters.get("planificationActive");
				id=(String)_parameters.get("id");
				planification = (SPPlanningProcess) _session.load(SPPlanningProcess.class, Long.parseLong(id));
				planification.setCron(cron);
				planification.setName(name);
				planification.setTemplate(template.getBytes());
				planification.getAuditor().setDeleted(!"active".equals(active));
				planification.setProcess(process);
				
			}else{
				planification = new SPPlanningProcess (_user.getId(),name, cron,template.getBytes(),process);
				HibernateUtils.customSave(_session, planification, _user.getId());
			}
		}catch(FPMException e){
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),e,true);
			throw e;
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