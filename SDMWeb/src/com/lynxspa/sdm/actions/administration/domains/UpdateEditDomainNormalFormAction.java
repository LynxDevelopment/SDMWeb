package com.lynxspa.sdm.actions.administration.domains;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.StateBeansManager;
import com.lynxit.webcomp.commands.form.FormStateBean;
import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.Application;
import com.lynxspa.entities.application.domains.Domain;
import com.lynxspa.entities.application.domains.DomainId;
import com.lynxspa.entities.application.domains.DomainNorm;
import com.lynxspa.entities.application.domains.DomainNormId;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class UpdateEditDomainNormalFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		 
		ValidationUtils.validateField("applicationId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainNormalId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainNormalAction",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainNormalDescription",_parameters, _errors,ValidationsDict.ISSTRING);
		//Clean other page forms
		clearOtherPageFormErrors();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String applicationId=null;
		String domainId=null;
		String normId=null;
		DomainNorm norm=null;
		Query query=null;
		String action=null;
		int affectedRows=0;
		Application application=null;
		
		try{
			applicationId=(String)_parameters.get("applicationId");
			domainId=(String)_parameters.get("domainId");
			normId=(String)_parameters.get("domainNormalId");
			action=(String)_parameters.get("domainNormalAction");
			application=HibernateUtils.getEntity(_session, Application.class,applicationId);
			norm=HibernateUtils.getEntity(_session,DomainNorm.class, new DomainNormId((Domain)_session.load(Domain.class,new DomainId(application,domainId)),normId));
			if("DELETE".equals(action)){
				query=_session.createQuery("delete DomainValue where id.norm.id.domain.id.application.id=:normDomainApplicationId and id.norm.id.domain.id.code=:normDomainCode and id.norm.id.code=:normCode");
				query.setString("normDomainApplicationId", norm.getId().getDomain().getId().getApplication().getId());
				query.setString("normDomainCode", norm.getId().getDomain().getId().getCode());
				query.setString("normCode", norm.getId().getCode());
				affectedRows=query.executeUpdate();
				_session.delete(norm);
				//Log Change
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.DOMAIN_NORM_DELETED,new Object[]{normId,domainId,affectedRows},null,false);
			}else{
				if(norm==null){
					norm=new DomainNorm(_user.getId(),(Domain)_session.load(Domain.class,new DomainId(application,domainId)),normId,(String)_parameters.get("domainNormalDescription"));
					HibernateUtils.customSave(_session, norm, _user.getId());
					//Log Change
					LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.DOMAIN_NORM_INSERTED,new Object[]{normId,domainId},null,false);
				}else{
					norm.setDescription((String)_parameters.get("domainNormalDescription"));
					HibernateUtils.customUpdate(_session, norm, _user.getId());
					//Log Change
					LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.DOMAIN_NORM_UPDATED,new Object[]{normId,domainId},null,false);
				}
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
		try{((FormStateBean)manager.getStateBean("DomainForm")).clearErrors();}catch (Throwable e) {e.printStackTrace();}
		try{((FormStateBean)manager.getStateBean("editDomainClusterForm")).clearErrors();}catch (Throwable e) {e.printStackTrace();}
		try{((FormStateBean)manager.getStateBean("editDomainValuesForm")).clearErrors();}catch (Throwable e) {e.printStackTrace();}
	}
}
