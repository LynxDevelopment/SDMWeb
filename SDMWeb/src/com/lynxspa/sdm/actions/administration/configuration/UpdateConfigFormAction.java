package com.lynxspa.sdm.actions.administration.configuration;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.dictionaries.ClassType;
import com.lynxspa.entities.application.configurations.Config;
import com.lynxspa.entities.application.configurations.adapter.ConfigDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogConfigDict;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.sdm.logs.WebLogWarningDict;
import com.lynxspa.utils.StringUtils;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;
import com.lynxspa.xweb.actions.EnhancedBasicAction;


public class UpdateConfigFormAction extends EnhancedBasicAction {
	
	public static final String CUSTOMER_CONFIG="customer.configurations";

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		
		ClassType configType=null;
		
		ValidationUtils.validateField("applicationId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("configId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		configType=ClassType.valueOf((String)_parameters.get("configType"));
		if(ClassType.BOOLEAN.equals(configType)){
			ValidationUtils.validateField("configValue", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISBOOLEAN);
		}else if (ClassType.TIMESTAMP.equals(configType)){
			ValidationUtils.validateField("configValue", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISTIMESTAMP);
		}else if (ClassType.LONG.equals(configType)){
			ValidationUtils.validateField("configValue", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		}else if (ClassType.DOUBLE.equals(configType)){
			ValidationUtils.validateField("configValue", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISDOUBLE);
		}else{
			ValidationUtils.validateField("configValue", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		}
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String stringValue=null;
		String applicationId=null;
		String configId=null;
		String actualValue=null;
		ConfigDictAdapter configuration=null;

		try{
			applicationId=(String)_parameters.get("applicationId");
			configId=(String)_parameters.get("configId");
			stringValue=((_parameters.get("configValue") instanceof String[]))? ((String[])_parameters.get("configValue"))[0] : (String)_parameters.get("configValue");
			if(SDMConfigManager.getInstance().getApplication(_session).getId().equals(applicationId)){
				configuration=findConfigAdapter(configId);
				actualValue=String.valueOf(SDMConfigManager.getInstance().getConfiguration(_session, configuration));
				SDMConfigManager.getInstance().setConfiguration(_session, configuration, stringValue, _user.getId(), false);
			}else{
				throw new FPMException(WebLogWarningDict.UNKNOWN_APPLICATION,new Object[]{applicationId});
			}
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.CONFIGURATION_CHANGED,new Object[]{configId,actualValue,stringValue},null,false);
		}catch(FPMException e){
			FPMException newException=new FPMException(LogConfigDict.CONFIGURATION_STORE_FAIL,new Object[]{String.valueOf(_parameters.get("configId")),_user.getId(),String.valueOf(_parameters.get("configValue"))},e);;
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),newException,true);
			throw e;
		}catch(HibernateException e) {
			FPMException newException=new FPMException(BasicErrorDict.DATABASE_SAVE_ERROR,new Object[]{String.valueOf(_parameters.get("applicationId"))+"|"+String.valueOf(_parameters.get("configId")),Config.class.getName(),String.valueOf(_session)},e);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),newException,true);
			throw newException;
		}catch(Throwable e) {
			FPMException newException=new FPMException(e);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),newException,true);
			throw newException;
		}
	}
	
	@SuppressWarnings("unchecked")
	private ConfigDictAdapter findConfigAdapter(String _code) throws ClassNotFoundException{
		
		ConfigDictAdapter reply=null;
		String customerConfigClassName=null;
		Class<? extends ConfigDictAdapter> customerConfigClass=null;
		
		if((reply=findConfigAdapter(CAConfiguration.class,_code))==null){
			customerConfigClassName=this.getServletContext().getInitParameter(UpdateConfigFormAction.CUSTOMER_CONFIG);
			if((customerConfigClassName=StringUtils.checkNotExist(customerConfigClassName,null))!=null){
				if((customerConfigClassName=StringUtils.checkNotExist(customerConfigClassName.trim(),null))!=null){
					customerConfigClass=(Class<? extends ConfigDictAdapter>)Class.forName(customerConfigClassName);
					reply=findConfigAdapter(customerConfigClass,_code);
				}
			}
		}
		
		return reply;
	}
	private ConfigDictAdapter findConfigAdapter(Class<? extends ConfigDictAdapter> _configAdapterClass,String _code){
		
		ConfigDictAdapter reply=null;
		
		for(ConfigDictAdapter configDict:_configAdapterClass.getEnumConstants()){
			if(configDict.getCode().equals(_code))
				reply=configDict;
		}	
		
		return reply;
	}
}
