package com.lynxspa.sdm.actions.administration.events.masterrecord;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.webcomp.exceptions.ComponentStateBeanNotFoundException;
import com.lynxit.webcomp.exceptions.ComponentStateBeanPropertyNotFoundException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.configuration.CAEventMasterRecordConfig;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class RemoveMasterRecordConfigFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		
		ValidationUtils.validateField("selectedRule",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISINTEGER);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		Object[] masterRecordRules=null;
		List newMasterRecordRules=null;
		CAEventMasterRecordConfig masterRecordRule=null;
		int selectedRule=0;
		
		
		try {
			selectedRule=Integer.parseInt((String)_parameters.get("selectedRule"));
			masterRecordRules=(Object[])_request.getSession().getAttribute("varMasterRecordRulesConfigInstance");
			newMasterRecordRules=new ArrayList(masterRecordRules.length-1);
			for(Object rule:masterRecordRules){
				masterRecordRule=(CAEventMasterRecordConfig)rule;
				if(masterRecordRule.getRuleOrder()<selectedRule){
					newMasterRecordRules.add(rule);
				}else{
					if(masterRecordRule.getRuleOrder()>selectedRule){
						masterRecordRule.setRuleOrder(masterRecordRule.getRuleOrder()-1);
						newMasterRecordRules.add(rule);
					}
				}
			}
			_request.getSession().removeAttribute("varMasterRecordRulesConfigInstance");
			if(newMasterRecordRules.size()==0){
				_request.getSession().setAttribute("varMasterRecordRulesConfigInstance",new Object[]{});
			}else{
				_request.getSession().setAttribute("varMasterRecordRulesConfigInstance",newMasterRecordRules.toArray());
			}
			this.getForm().getComponent("selectedRule").setProperty("value", new String[]{""});
		} catch (ComponentStateBeanPropertyNotFoundException e) {
			throw new FPMException(e);
		} catch (InvocationTargetException e) {
			throw new FPMException(e);
		} catch (ComponentStateBeanNotFoundException e) {
			throw new FPMException(e);
		}
	}
}
