package com.lynxspa.sdm.actions.masterrecord.authorization;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;

public class InsertEntityDeadLine extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
		//ValidationUtils.validateField("eventGroupId", ActionUtils.getWebComponentCheckBoxMutipleValue("checkedEventGroups"), _errors, ValidationsDict.ISNOTEMPTY);
	}
	
	@Override
	public void performAction(HttpServletRequest _request, Session _session,
			LoggedUser _user, Map<String, Object> _parameters)
			throws FPMException {
		CAEventGroup eventGroup=null;
		Date entityOperationalDate = null;
		try{
			eventGroup=(CAEventGroup)_session.get(CAEventGroup.class,Long.parseLong((String)_parameters.get("eventGroupId")));
			if (_parameters.get("entityOperationalDate") instanceof Date){
				entityOperationalDate = (Date) _parameters.get("entityOperationalDate");
			}
			eventGroup.setEntityDeadLine(entityOperationalDate);
			HibernateUtils.customUpdate(_session, eventGroup, _user.getId());
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
