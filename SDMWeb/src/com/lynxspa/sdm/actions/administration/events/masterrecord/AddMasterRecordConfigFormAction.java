package com.lynxspa.sdm.actions.administration.events.masterrecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.configuration.CAEventConfig;
import com.lynxspa.sdm.entities.events.configuration.CAEventMasterRecordConfig;
import com.lynxspa.sdm.entities.events.types.CAEventType;
import com.lynxspa.sdm.entities.events.types.CAEventTypeDetail;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.processors.masterrecord.rules.conditions.Condition;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class AddMasterRecordConfigFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		 
		String ruleCondition=null;
		
		ValidationUtils.validateField("eventType",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("ruleTarget",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("ruleField",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		ValidationUtils.validateField("ruleCondition",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ruleCondition=((String[])_parameters.get("ruleCondition"))[0];
		if(Condition.valueOf(ruleCondition).getAdditionalParameters()){
			ValidationUtils.validateField("ruleAmountParam", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISDOUBLE);
			ValidationUtils.validateField("ruleUnitsParam",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		}
		ValidationUtils.validateField("ruleOnTrueResultAction",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("ruleOnFalseResultAction",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		Object[] masterRecordRules=null;
		List newMasterRecordRules=null;
		CAEventConfig config=null;
		CAEventMasterRecordConfig masterRecordRule=null;
		String ruleCondition=null;
		
		config=SDMConfigManager.getInstance().getEventConfigurations(_session, (CAEventType)_session.get(CAEventType.class,(String)_parameters.get("eventType")));
		masterRecordRules=(Object[])_request.getSession().getAttribute("varMasterRecordRulesConfigInstance");
		newMasterRecordRules=new ArrayList(masterRecordRules.length+1);
		for(Object rule:masterRecordRules){
			newMasterRecordRules.add(rule);
		}
		masterRecordRule=new CAEventMasterRecordConfig();
		masterRecordRule.setTarget(((String[])_parameters.get("ruleTarget"))[0]);
		masterRecordRule.setEventConfig(config);
		masterRecordRule.setRuleOrder(masterRecordRules.length+1);
		masterRecordRule.setDetail((CAEventTypeDetail)_session.get(CAEventTypeDetail.class,Long.parseLong(((String[])_parameters.get("ruleField"))[0])));
		ruleCondition=((String[])_parameters.get("ruleCondition"))[0];
		masterRecordRule.setCondition(ruleCondition);
		if(Condition.valueOf(ruleCondition).getAdditionalParameters()){
			masterRecordRule.setConditionParams(((String)_parameters.get("ruleAmountParam"))+"|"+((String[])_parameters.get("ruleUnitsParam"))[0]);
		}
		masterRecordRule.setOnTrueResult(((String[])_parameters.get("ruleOnTrueResultAction"))[0]);
		masterRecordRule.setOnFalseResult(((String[])_parameters.get("ruleOnFalseResultAction"))[0]);
		newMasterRecordRules.add(masterRecordRule);
		_request.getSession().removeAttribute("varMasterRecordRulesConfigInstance");
		_request.getSession().setAttribute("varMasterRecordRulesConfigInstance", newMasterRecordRules.toArray());
	}
}
