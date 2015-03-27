package com.lynxspa.sdm.actions.administration.events.masterrecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.configuration.CAEventConfig;
import com.lynxspa.sdm.entities.events.configuration.CAEventMasterRecordConfig;
import com.lynxspa.sdm.entities.events.providers.CAEventProvider;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class SaveMasterRecordConfigFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		
		ValidationUtils.validateField("primaryMasterRecord",_parameters.get("primaryMasterRecord"), _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("onNoPreferredMasterRecordFound",_parameters.get("onNoPreferredMasterRecordFound"), _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("onNoCustodianAndManualFound",_parameters.get("onNoCustodianAndManualFound"), _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("eventType",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		Object[] masterRecordRules=null;
		Map<Integer,CAEventMasterRecordConfig> newMasterRecordRules=null;
		CAEventMasterRecordConfig masterRecordRule=null;
		CAEventConfig eventConfig=null;
		Query query=null;
		CAEventProvider eventProvider=null;
		
		try {
			//Store preferred master records
			query=_session.createQuery("from CAEventConfig where eventType.id=:eventTypeId");
			query.setString("eventTypeId",(String)_parameters.get("eventType"));
			eventConfig=(CAEventConfig)query.uniqueResult();
			eventProvider=(CAEventProvider)_session.load(CAEventProvider.class,((String[])_parameters.get("primaryMasterRecord"))[0]);
			eventConfig.setPreferentProvider(eventProvider);
			eventConfig.setOnPrimaryAndSecondaryNotFound(((String[])_parameters.get("onNoPreferredMasterRecordFound"))[0]);
			eventConfig.setOnCustodianAndManualNotFound(((String[])_parameters.get("onNoCustodianAndManualFound"))[0]);
			if((_parameters.get("secondaryMasterRecord")!=null)&&(((String[])_parameters.get("secondaryMasterRecord")).length>0)){
				eventProvider=(CAEventProvider)_session.load(CAEventProvider.class,((String[])_parameters.get("secondaryMasterRecord"))[0]);
				eventConfig.setSecondPreferentProvider(eventProvider);
			}
			HibernateUtils.customUpdate(_session,eventConfig,_user.getId());
			//Store master record rules
			//	Recover new MasterRecord rules from rules instance
			masterRecordRules=(Object[])_request.getSession().getAttribute("varMasterRecordRulesConfigInstance");
			newMasterRecordRules=new HashMap<Integer, CAEventMasterRecordConfig>(masterRecordRules.length);
			for(Object rule:masterRecordRules){
				masterRecordRule=(CAEventMasterRecordConfig)rule;
				newMasterRecordRules.put(masterRecordRule.getRuleOrder(), masterRecordRule);
			}
			//	Delete old MasterRecord rules
			query=_session.createQuery("delete CAEventMasterRecordConfig where eventConfig.id in (select id from CAEventConfig where eventType.id=:eventTypeId)");
			query.setString("eventTypeId",(String)_parameters.get("eventType"));
			query.executeUpdate();
			//	Add new MasterRecord rules
			for(Integer ruleOrder:newMasterRecordRules.keySet()){
				masterRecordRule=newMasterRecordRules.get(ruleOrder);
				HibernateUtils.customSave(_session,masterRecordRule,_user.getId());
			}
			//	Clean rules instance
			_request.getSession().removeAttribute("varMasterRecordRulesConfigInstance");
			//Log Change
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MASTERRECORD_CONFIG_CHANGED,new Object[]{eventConfig.getEventType().getId()},null,false);
			clearCache(_session,_user.getId());
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
