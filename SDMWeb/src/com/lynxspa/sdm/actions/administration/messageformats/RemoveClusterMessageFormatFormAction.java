package com.lynxspa.sdm.actions.administration.messageformats;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.domains.CADomainClusterFormat;
import com.lynxspa.sdm.entities.domains.CADomainClusterFormatId;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFormat;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.domains.Domain;
import com.lynxspa.entities.application.domains.DomainCluster;
import com.lynxspa.entities.application.domains.DomainClusterId;
import com.lynxspa.entities.application.domains.DomainId;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class RemoveClusterMessageFormatFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		 
		ValidationUtils.validateField("formatId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("removeDomainId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("removeClusterId",_parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		String formatId=null;
		String domainId=null;
		String domainClusterId=null;
		Domain domain=null;
		DomainCluster domainCluster=null;
		CAMessageFormat messageFormat=null;
		CADomainClusterFormat clusterFormat=null;
	
		try{
			formatId=(String)_parameters.get("formatId");
			domainId=(String)_parameters.get("removeDomainId");
			domainClusterId=(String)_parameters.get("removeClusterId");
			domain=(Domain)_session.get(Domain.class,new DomainId(SDMConfigManager.getInstance().getApplication(_session),domainId));
			domainCluster=(DomainCluster)_session.get(DomainCluster.class, new DomainClusterId(domain,domainClusterId));
			messageFormat=(CAMessageFormat)_session.get(CAMessageFormat.class, formatId);
			clusterFormat=(CADomainClusterFormat)_session.get(CADomainClusterFormat.class,new CADomainClusterFormatId(domainCluster,messageFormat));
			_session.delete(clusterFormat);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MESSAGEFORMAT_REMOVE_DOMAINCLUSTER,new Object[]{domainClusterId,domainId,formatId},null,false);
			clearCache(_session,_user.getId());
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
