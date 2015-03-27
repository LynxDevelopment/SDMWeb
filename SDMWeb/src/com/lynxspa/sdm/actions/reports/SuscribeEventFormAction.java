package com.lynxspa.sdm.actions.reports;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.flow.utils.WorkflowUtils;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;

public class SuscribeEventFormAction extends CoacEnhancedBasicAction {

	@Override
	public void performAction(HttpServletRequest _request, Session _session,
			LoggedUser _user, Map<String, Object> _parameters)
			throws FPMException {
		String eventGroupId=null;
		CAEventGroup eventGroup=null;
		Object obj = null;
		
		try{
			obj = _parameters.get("eventGroupId");
			if((obj!=null) && (obj instanceof String)){
				eventGroupId =(String) obj;
				eventGroup=(CAEventGroup)_session.get(CAEventGroup.class,Long.parseLong(eventGroupId));
				WorkflowUtils.changeState(SDMConfigManager.getInstance(),_user.getLocale().getLanguage(), _user.getId(), _session, eventGroup, CAStatesEVENTGROUPFlow.SUSC);
			}
			
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
