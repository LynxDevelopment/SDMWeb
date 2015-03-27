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
import com.lynxspa.entities.plannings.SPPlanningProcess;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.sdm.logs.WebLogWarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

/**
 * This class delete the planification selected 
 * and securities with this planification will be updated with none planification
 * @author joseluis.llorente
 *
 */
public class DeletePlanificationsFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String,Object> _parameters,List<ValidationException> _errors) throws FPMException{
		
		ValidationUtils.validateField("planificationDeletable",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		//Existence validation in DB
		if(_errors.size()==0){
			Long planificationId=Long.valueOf((String)_parameters.get("planificationDeletable"));
			final Session session=getCurrentSession();
			if(HibernateUtils.getEntity(session,SPPlanningProcess.class,planificationId)==null)
				_errors.add(new ValidationException(WebLogWarningDict.UNKNOWN_PLANIFICATION.getMessageKey(),"planificationDeletable",(String)_parameters.get("planificationDeletable")));
		}
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user,Map<String, Object> _parameters) throws FPMException {
		
		try {
			final Long planificationId=Long.valueOf((String)_parameters.get("planificationDeletable"));
			final SPPlanningProcess planification = (SPPlanningProcess) _session.load(SPPlanningProcess.class,planificationId);
			//Securities Updated
			final String queryString = "update SPVirtualSecurity set planification=null where auditor.deleted =:deleted and planification.id=:planificationId";
			final Query query=HibernateUtils.createQuery(_session, queryString);
			query.setBoolean("deleted", false);
			query.setLong("planificationId", planificationId);
			final int securitiesUpdated = query.executeUpdate();
			//Planification deleted
			_session.delete(planification);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.SECURITYPLANIFICATIONDELETED,new Object[]{planificationId,securitiesUpdated},null,false);
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
