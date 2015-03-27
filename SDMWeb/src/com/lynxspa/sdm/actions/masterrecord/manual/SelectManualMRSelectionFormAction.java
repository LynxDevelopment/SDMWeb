package com.lynxspa.sdm.actions.masterrecord.manual;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.flow.utils.WorkflowUtils;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class SelectManualMRSelectionFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("groupId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		ValidationUtils.validateField("selectedEventId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		CAEventGroup eventGroup=null;
		CAEventCollected eventMaster=null;
		
		try{
			eventGroup=(CAEventGroup)_session.get(CAEventGroup.class, Long.parseLong((String)_parameters.get("groupId")));
			eventMaster=(CAEventCollected)_session.get(CAEventCollected.class, Long.parseLong((String)_parameters.get("selectedEventId")));
			eventGroup.setMasterEvent(eventMaster);
			HibernateUtils.customUpdate(_session, eventGroup, _user.getId());
			WorkflowUtils.changeState(SDMConfigManager.getInstance(), SDMConfigManager.BUNDLENAME, _user.getId(), _session, eventGroup, CAStatesEVENTGROUPFlow.NAUT);
		}catch(FPMException e){
			LogUtils.createLog(_session, _user.getId(), SDMConfigManager.BUNDLENAME, _user.getLocale(), SDMConfigManager.getInstance().getApplication(_session), e);
			throw e;
		}catch (Throwable e) {
			FPMException newException= new FPMException(e);
			LogUtils.createLog(_session, _user.getId(), SDMConfigManager.BUNDLENAME, _user.getLocale(), SDMConfigManager.getInstance().getApplication(_session), newException);
			throw newException;
		}
	}
}
