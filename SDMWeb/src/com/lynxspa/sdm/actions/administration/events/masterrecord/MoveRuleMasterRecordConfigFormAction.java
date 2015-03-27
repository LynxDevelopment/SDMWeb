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


public class MoveRuleMasterRecordConfigFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		
		ValidationUtils.validateField("selectedRule",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISINTEGER);
		ValidationUtils.validateField("moveDirectionRule",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		Object[] masterRecordRules=null;
		List newMasterRecordRules=null;
		CAEventMasterRecordConfig masterRecordRule=null;
		CAEventMasterRecordConfig tempMasterRecordRule=null;
		int selectedRule=0;
		char moveDirection='U';
	
		try{
			selectedRule=Integer.parseInt((String)_parameters.get("selectedRule"));
			moveDirection=((String)_parameters.get("moveDirectionRule")).charAt(0);
			masterRecordRules=(Object[])_request.getSession().getAttribute("varMasterRecordRulesConfigInstance");
			newMasterRecordRules=new ArrayList(masterRecordRules.length);
			for(Object rule:masterRecordRules){
				masterRecordRule=(CAEventMasterRecordConfig)rule;
				if(((selectedRule>1)&&(selectedRule<masterRecordRules.length))||((selectedRule==1)&&(moveDirection=='D'))||((selectedRule==masterRecordRules.length)&&(moveDirection=='U'))){
					switch(moveDirection){
						case 'U':
							this.getForm().getComponent("selectedRule").setProperty("value", new String[]{""+(selectedRule-1)});
							if(masterRecordRule.getRuleOrder()==selectedRule-1){
								masterRecordRule.setRuleOrder(selectedRule);
							}else{
								if(masterRecordRule.getRuleOrder()==selectedRule){
									masterRecordRule.setRuleOrder(selectedRule-1);
									tempMasterRecordRule=(CAEventMasterRecordConfig)newMasterRecordRules.remove(newMasterRecordRules.size()-1);
									newMasterRecordRules.add(masterRecordRule);
									masterRecordRule=tempMasterRecordRule;
								}
							}
							break;
						case 'D':
							this.getForm().getComponent("selectedRule").setProperty("value", new String[]{""+(selectedRule+1)});
							if(masterRecordRule.getRuleOrder()==selectedRule){
								masterRecordRule.setRuleOrder(selectedRule+1);
							}else{
								if(masterRecordRule.getRuleOrder()==selectedRule+1){
									masterRecordRule.setRuleOrder(selectedRule);
									tempMasterRecordRule=(CAEventMasterRecordConfig)newMasterRecordRules.remove(newMasterRecordRules.size()-1);
									newMasterRecordRules.add(masterRecordRule);
									masterRecordRule=tempMasterRecordRule;
								}
							}
							break;
					}
				}
				newMasterRecordRules.add(masterRecordRule);
			}
			_request.getSession().removeAttribute("varMasterRecordRulesConfigInstance");
			_request.getSession().setAttribute("varMasterRecordRulesConfigInstance", newMasterRecordRules.toArray());
		}catch (ComponentStateBeanNotFoundException e) {
			throw new FPMException(e);
		}catch (ComponentStateBeanPropertyNotFoundException e) {
			throw new FPMException(e);
		}catch (InvocationTargetException e) {
			throw new FPMException(e);
		}
	}
}