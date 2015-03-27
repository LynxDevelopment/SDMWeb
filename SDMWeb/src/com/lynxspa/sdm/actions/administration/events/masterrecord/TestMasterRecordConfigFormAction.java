package com.lynxspa.sdm.actions.administration.events.masterrecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.sdm.entities.events.configuration.CAEventConfig;
import com.lynxspa.sdm.entities.events.configuration.CAEventMasterRecordConfig;
import com.lynxspa.sdm.entities.events.providers.CAEventProvider;
import com.lynxspa.sdm.entities.events.types.CAEventType;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.processors.masterrecord.MRProcessorAdapter;
import com.lynxspa.sdm.processors.masterrecord.rules.results.RulesResultBean;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class TestMasterRecordConfigFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("primaryMasterRecord",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("onNoPreferredMasterRecordFound",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("onNoCustodianAndManualFound",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("testGroupId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		ValidationUtils.validateField("eventType",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		CAEventGroup testEventGroup=null;
		Object[] masterRecordRules=null;
		List<CAEventMasterRecordConfig> rules=null;
		MRProcessorAdapter processor=null;
		RulesResultBean testResult=null;
		CAEventConfig eventConfig=null;

		try{
			//Clean up previous executions
			_request.getSession().removeAttribute("varMasterRecordRulesResultInstance");
			_request.getSession().removeAttribute("varMasterRecordRulesResultExceptionInstance");
			//Recover eventGroup to test
			testEventGroup=(CAEventGroup)_session.get(CAEventGroup.class,Long.parseLong((String)_parameters.get("testGroupId")));
			//Prepare test
			masterRecordRules=(Object[])_request.getSession().getAttribute("varMasterRecordRulesConfigInstance");
			rules=new ArrayList<CAEventMasterRecordConfig>(masterRecordRules.length);
			for(Object rule:masterRecordRules){
				rules.add((CAEventMasterRecordConfig)rule);
			}
			eventConfig=new CAEventConfig("MRTESTER",(CAEventType)_session.get(CAEventType.class,(String)_parameters.get("eventType")));
			eventConfig.setPreferentProvider((CAEventProvider)_session.get(CAEventProvider.class,((String[])_parameters.get("primaryMasterRecord"))[0]));
			eventConfig.setOnPrimaryAndSecondaryNotFound(((String[])_parameters.get("onNoPreferredMasterRecordFound"))[0]);
			eventConfig.setOnCustodianAndManualNotFound(((String[])_parameters.get("onNoCustodianAndManualFound"))[0]);
			if((_parameters.get("secondaryMasterRecord")!=null)&&(((String[])_parameters.get("secondaryMasterRecord")).length>0)){
				eventConfig.setSecondPreferentProvider((CAEventProvider)_session.get(CAEventProvider.class,((String[])_parameters.get("secondaryMasterRecord"))[0]));
			}
			eventConfig.setMasterRecordConfigs(rules);
			//Launch test
			processor=(MRProcessorAdapter)SDMConfigManager.getInstance().getProcessor(_session, CAConfiguration.MRPROCESSOR);
			testResult=(RulesResultBean)processor.test(_session,eventConfig,testEventGroup);
			_request.getSession().setAttribute("varMasterRecordRulesResultInstance", testResult);
		}catch (FPMException e) {
			_request.getSession().setAttribute("varMasterRecordRulesResultExceptionInstance",e);
		}catch(Throwable e){
			_request.getSession().setAttribute("varMasterRecordRulesResultExceptionInstance", new FPMException(e));
		}
	}
}
