package com.lynxspa.sdm.actions.administration.domains;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.StateBeansManager;
import com.lynxit.webcomp.commands.form.FormStateBean;
import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.Application;
import com.lynxspa.entities.application.domains.Domain;
import com.lynxspa.entities.application.domains.DomainId;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class AddUpdateDomainFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		 
		ValidationUtils.validateField("applicationId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainName",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainAction",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		//Clean other page forms
		clearOtherPageFormErrors();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String applicationId=null;
		String domainId=null;
		Domain domain=null;
		String action=null;
		String domainName=null;
		Application application=null;
		boolean cacheable=true;
		
		try{
			applicationId=((String[])_parameters.get("applicationId"))[0];
			domainId=(String)_parameters.get("domainId");
			domainName=(String)_parameters.get("domainName");
			action=(String)_parameters.get("domainAction");
			cacheable=((_parameters.get("cacheable")!=null)&&((String[])_parameters.get("cacheable")).length>0);
			application=HibernateUtils.getEntity(_session, Application.class,applicationId);
			domain=HibernateUtils.getEntity(_session,Domain.class, new DomainId(application,domainId));
			if("INSERT".equals(action)){
				domain=new Domain(_user.getId(),application,domainId,domainName);
				domain.setCacheable(cacheable);
				HibernateUtils.customSave(_session, domain, _user.getId());
				this.getForm().getComponent("domainAction").setProperty("value",new String[]{"UPDATE"});
				_request.getSession().setAttribute("edit_domain_varEditingDomain",domainId);
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.DOMAIN_INSERTED,new Object[]{domainId,domainName},null,false);
			}else{
				domain.setName(domainName);
				domain.setCacheable(cacheable);
				HibernateUtils.customUpdate(_session, domain, _user.getId());
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.DOMAIN_UPDATED,new Object[]{domainId,domainName},null,false);
			}
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
		}finally{
			//Clean other page forms
			clearOtherPageFormErrors();
		}
	}

	protected void clearOtherPageFormErrors(){
		
		StateBeansManager manager=null;
		
		manager=StateBeansManager.getInstance(this.getRequest().getSession());
		try{((FormStateBean)manager.getStateBean("editDomainClusterForm")).clearErrors();}catch (Throwable e) {}
		try{((FormStateBean)manager.getStateBean("editDomainNormalForm")).clearErrors();}catch (Throwable e) {}
		try{((FormStateBean)manager.getStateBean("editDomainValuesForm")).clearErrors();}catch (Throwable e) {}
	}
}
