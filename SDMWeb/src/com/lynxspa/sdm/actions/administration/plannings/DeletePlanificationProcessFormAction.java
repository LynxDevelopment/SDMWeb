package com.lynxspa.sdm.actions.administration.plannings;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.plannings.SPProcess;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.sdm.logs.WebLogWarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class DeletePlanificationProcessFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String,Object> _parameters,List<ValidationException> _errors) throws FPMException{
		
		ValidationUtils.validateField("planificationProcessDeletable",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		//Existence validation in DB
		if(_errors.size()==0){
			Long planificationId=Long.valueOf((String)_parameters.get("planificationProcessDeletable"));
			final Session session=getCurrentSession();
			if(HibernateUtils.getEntity(session,SPProcess.class,planificationId)==null)
				_errors.add(new ValidationException(WebLogWarningDict.UNKNOWN_PLANIFICATIONPROCESS.getMessageKey(),"planificationProcessDeletable",(String)_parameters.get("planificationDeletable")));
		}
	}

	@Override
	public void performAction(HttpServletRequest _request,Session _session, LoggedUser _user,Map<String, Object> _parameters) throws FPMException {
		
		try {
			//Recover parameters
			final Long planificationProcessId=Long.parseLong((String)_parameters.get("planificationProcessDeletable"));
			final SPProcess process=(SPProcess)_session.load(SPProcess.class,planificationProcessId);
			//Update planning processes
			final Query query=HibernateUtils.createQuery(_session,"update SPPlanningProcess as planification set planification.process = null, planification.auditor.deleted =:deleted where planification.process.id=:processId");
			query.setBoolean("deleted", true);
			query.setLong("processId", planificationProcessId);
			final int updatedPlanificationProcesses=query.executeUpdate();
			//Delete processes
			_session.delete(process);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.SECURITYPLANIFICATIONPROCESSDELETED,new Object[]{planificationProcessId,updatedPlanificationProcesses},null,false);
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

