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
import com.lynxspa.entities.application.domains.DomainCluster;
import com.lynxspa.entities.application.domains.DomainClusterId;
import com.lynxspa.entities.application.domains.DomainId;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class UpdateEditDomainClusterFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		 
		ValidationUtils.validateField("applicationId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainClusterId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainClusterAction",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainClusterName",_parameters, _errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		//Clean other page forms
		clearOtherPageFormErrors();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String applicationId=null;
		String domainId=null;
		String clusterId=null;
		DomainCluster cluster=null;
		Query query=null;
		String action=null;
		int affectedRows=0;
		int affectedFormats=0;
		Application application=null;
		
		try{
			applicationId=(String)_parameters.get("applicationId");
			domainId=(String)_parameters.get("domainId");
			clusterId=(String)_parameters.get("domainClusterId");
			action=(String)_parameters.get("domainClusterAction");
			application=HibernateUtils.getEntity(_session, Application.class,applicationId);
			cluster=HibernateUtils.getEntity(_session,DomainCluster.class, new DomainClusterId((Domain)_session.load(Domain.class,new DomainId(application,domainId)),clusterId));
			if("DELETE".equals(action)){
				query=_session.createQuery("delete DomainValue where id.cluster.id.domain.id.application.id=:clusterDomainApplicationId and id.cluster.id.domain.id.code=:clusterDomainCode and id.cluster.id.code=:clusterCode");
				query.setString("clusterDomainApplicationId", cluster.getId().getDomain().getId().getApplication().getId());
				query.setString("clusterDomainCode", cluster.getId().getDomain().getId().getCode());
				query.setString("clusterCode", cluster.getId().getCode());
				affectedRows=query.executeUpdate();
				query=_session.createQuery("delete CADomainClusterFormat where id.domainCluster.id.domain.id.application.id=:clusterDomainApplicationId and id.domainCluster.id.domain.id.code=:clusterDomainCode and id.domainCluster.id.code=:clusterCode");
				query.setString("clusterDomainApplicationId", cluster.getId().getDomain().getId().getApplication().getId());
				query.setString("clusterDomainCode", cluster.getId().getDomain().getId().getCode());
				query.setString("clusterCode", cluster.getId().getCode());
				affectedFormats=query.executeUpdate();
				_session.delete(cluster);
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.DOMAIN_CLUSTER_DELETED,new Object[]{clusterId,domainId,affectedRows,affectedFormats},null,false);
			}else{
				if(cluster==null){
					cluster=new DomainCluster(_user.getId(),(Domain)_session.load(Domain.class,new DomainId(application,domainId)),clusterId,(String)_parameters.get("domainClusterName"));
					HibernateUtils.customSave(_session, cluster, _user.getId());
					LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.DOMAIN_CLUSTER_INSERTED,new Object[]{clusterId,domainId},null,false);
				}else{
					cluster.setName((String)_parameters.get("domainClusterName"));
					HibernateUtils.customUpdate(_session, cluster, _user.getId());
					LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.DOMAIN_CLUSTER_UPDATED,new Object[]{clusterId,domainId},null,false);
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
		try{((FormStateBean)manager.getStateBean("editDomainNormalForm")).clearErrors();}catch (Throwable e) {e.printStackTrace();}
		try{((FormStateBean)manager.getStateBean("editDomainValuesForm")).clearErrors();}catch (Throwable e) {e.printStackTrace();}
	}
}
