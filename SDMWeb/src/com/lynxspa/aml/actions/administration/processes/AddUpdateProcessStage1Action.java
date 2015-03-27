package com.lynxspa.aml.actions.administration.processes;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.webcomp.exceptions.ComponentStateBeanNotFoundException;
import com.lynxit.webcomp.exceptions.ComponentStateBeanPropertyNotFoundException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.aml.actions.administration.NameBookEnhancedBasicAction;
import com.lynxspa.exception.FPMException;
import com.lynxspa.validation.utils.ValidationAdapter;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class AddUpdateProcessStage1Action extends NameBookEnhancedBasicAction{
	
	public static final ValidationAdapter ISVALIDOPERATION=new ValidationAdapter(){
		public String getErrorKey(){return "error.unknown.operation";}
		public boolean validate(Object[] _value){
			boolean reply=false;
			if(_value!=null) for(Object valueToValidate:_value) reply=("INSERT".equals(valueToValidate))||("UPDATE".equals(valueToValidate)); 
			return reply;
		}
	};

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("processClass",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("operation",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING,AddUpdateProcessStage1Action.ISVALIDOPERATION);
	}
	
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		try {
			this.getForm().getComponent("stage").setProperty("value",new String[]{"2"});
		} catch (ComponentStateBeanPropertyNotFoundException e) {
			throw new FPMException(e);
		} catch (InvocationTargetException e) {
			throw new FPMException(e);
		} catch (ComponentStateBeanNotFoundException e) {
			throw new FPMException(e);
		}
	}
}
