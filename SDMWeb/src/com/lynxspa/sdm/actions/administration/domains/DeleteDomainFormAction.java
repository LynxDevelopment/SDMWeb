package com.lynxspa.sdm.actions.administration.domains;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

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
import com.lynxspa.sdm.logs.WebLogWarningDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class DeleteDomainFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("applicationId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("domainId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String applicationId=null;
		String domainId=null;
		Domain domain=null;
		Query query=null;
		int affectedValues=0;
		int affectedClusters=0;
		int affectedNormals=0;
		int affectedFormats=0;
		Application application=null;
	
		try{
			applicationId=(String)_parameters.get("applicationId");
			domainId=(String)_parameters.get("domainId");
			application=HibernateUtils.getEntity(_session, Application.class,applicationId);
			domain=HibernateUtils.getEntity(_session,Domain.class, new DomainId(application,domainId));
			if(domain==null)
				throw new FPMException(WebLogWarningDict.UNKNOWN_DOMAIN,new Object[]{domainId,applicationId});
			query=_session.createQuery("delete DomainValue where id.cluster.id.domain.id.application.id=:clusterDomainApplicationId and id.cluster.id.domain.id.code=:clusterDomainId and id.norm.id.domain.id.application.id=:normalDomainApplicationId and id.norm.id.domain.id.code=:normalDomainId");
			query.setString("clusterDomainApplicationId", domain.getId().getApplication().getId());
			query.setString("clusterDomainId", domain.getId().getCode());
			query.setString("normalDomainApplicationId", domain.getId().getApplication().getId());
			query.setString("normalDomainId", domain.getId().getCode());
			affectedValues=query.executeUpdate();
			query=_session.createQuery("delete CADomainClusterFormat where id.domainCluster.id.domain.id.application.id=:clusterDomainApplicationId and id.domainCluster.id.domain.id.code=:clusterDomainId");
			query.setString("clusterDomainApplicationId", domain.getId().getApplication().getId());
			query.setString("clusterDomainId", domain.getId().getCode());
			affectedFormats=query.executeUpdate();
			query=_session.createQuery("delete DomainCluster where id.domain.id.application.id=:clusterDomainApplicationId and id.domain.id.code=:clusterDomainId");
			query.setString("clusterDomainApplicationId", domain.getId().getApplication().getId());
			query.setString("clusterDomainId", domain.getId().getCode());
			affectedClusters=query.executeUpdate();
			query=_session.createQuery("delete DomainNorm where id.domain.id.application.id=:normalDomainApplicationId and id.domain.id.code=:normalDomainId");
			query.setString("normalDomainApplicationId", domain.getId().getApplication().getId());
			query.setString("normalDomainId", domain.getId().getCode());
			affectedNormals=query.executeUpdate();
			_session.delete(domain);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.DOMAIN_DELETED,new Object[]{domainId,affectedValues,affectedNormals,affectedClusters,affectedFormats},null,false);
			clearCache(_session,_user.getId());
		}catch(FPMException e) {
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
		}
	}
}
