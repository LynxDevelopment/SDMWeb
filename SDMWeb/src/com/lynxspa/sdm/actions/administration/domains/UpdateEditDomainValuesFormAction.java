package com.lynxspa.sdm.actions.administration.domains;

import java.util.HashSet;
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
import com.lynxspa.entities.application.domains.DomainCluster;
import com.lynxspa.entities.application.domains.DomainClusterId;
import com.lynxspa.entities.application.domains.DomainId;
import com.lynxspa.entities.application.domains.DomainNorm;
import com.lynxspa.entities.application.domains.DomainNormId;
import com.lynxspa.entities.application.domains.DomainValue;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class UpdateEditDomainValuesFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		 
		ValidationUtils.validateField("applicationId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainClusterId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainNormalId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("codifiedDomainValue",_parameters, _errors,ValidationsDict.ISSTRING);
		//Clean other page forms
		clearOtherPageFormErrors();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String applicationId=null;
		String domainId=null;
		String clusterId=null;
		String normId=null;
		DomainNorm norm=null;
		DomainCluster cluster=null;
		DomainValue value=null;
		Query query=null;
		List<DomainValue> values=null;
		String originalCodifiedDomainValues="";
		String destinyCodifiedDomainValues=null;
		HashSet<String> newDomainValues=null;
		Application application=null;
		
		try{
			applicationId=(String)_parameters.get("applicationId");
			domainId=(String)_parameters.get("domainId");
			clusterId=(String)_parameters.get("domainClusterId");
			normId=(String)_parameters.get("domainNormalId");
			application=HibernateUtils.getEntity(_session, Application.class,applicationId);
			cluster=(DomainCluster) _session.load(DomainCluster.class, new DomainClusterId((Domain)_session.load(Domain.class,new DomainId(application,domainId)),clusterId));
			norm=(DomainNorm) _session.load(DomainNorm.class, new DomainNormId((Domain)_session.load(Domain.class,new DomainId(application,domainId)),normId));
			//Reorder domain values
			destinyCodifiedDomainValues=(String)_parameters.get("codifiedDomainValue");
			newDomainValues=new HashSet<String>();
			if((destinyCodifiedDomainValues!=null)&&(destinyCodifiedDomainValues.length()>0)){
				for(String domainValue:destinyCodifiedDomainValues.split("\\|")){
					newDomainValues.add(domainValue);
				}
			}
			//Recover original values
			query=_session.createQuery("from DomainValue where id.cluster.id.domain.id.application.id=:clusterDomainApplicationId and id.cluster.id.domain.id.code=:clusterDomainCode and id.cluster.id.code=:clusterCode and id.norm.id.domain.id.application.id=:normDomainApplicationId and id.norm.id.domain.id.code=:normDomainCode and id.norm.id.code=:normCode");
			query.setString("clusterDomainApplicationId", application.getId());
			query.setString("clusterDomainCode", domainId);
			query.setString("clusterCode",clusterId);
			query.setString("normDomainApplicationId", application.getId());
			query.setString("normDomainCode", domainId);
			query.setString("normCode", normId);
			values=query.list();
			//delete original values that not exist in new values
			for(DomainValue originalValue:values){
				if(originalCodifiedDomainValues.length()>0)
					originalCodifiedDomainValues+='|';
				originalCodifiedDomainValues+=originalValue.getId().getValue();
				if(newDomainValues.contains(originalValue.getId().getValue())){
					newDomainValues.remove(originalValue.getId().getValue());
				}else{
					_session.delete(originalValue);
				}
			}
			//add new values
			for(String newValue:newDomainValues){
				value=new DomainValue(_user.getId(),norm,cluster,newValue);				
				HibernateUtils.customSave(_session, value, _user.getId());
			}
			//Log Change
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.DOMAIN_VALUES_UPDATED,new Object[]{domainId,clusterId,normId,originalCodifiedDomainValues,destinyCodifiedDomainValues},null,false);
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
		try{((FormStateBean)manager.getStateBean("editDomainNormalForm")).clearErrors();}catch (Throwable e) {e.printStackTrace();}
	}
}
