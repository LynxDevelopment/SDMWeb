package com.lynxspa.sdm.actions.administration.assets.details;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.assets.AssetTypeDetail;
import com.lynxspa.entities.securities.assets.AssetTypeDetailBlocked;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;


public class DeleteAssetDetailBlockedFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("assetTypeDetailId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		ValidationUtils.validateField("providerId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		Query query=null;
		
		try{
			Long assetTypeDetailId 	= new Long((String)_parameters.get("assetTypeDetailId"));
			Long providerId			= new Long((String)_parameters.get("providerId"));
			
			System.out.println("AssetType->"+assetTypeDetailId+". Provider->"+providerId);
			query=HibernateUtils.createQuery(_session, "delete AssetTypeDetailBlocked where assetTypeDetail.id=:assetTypeDetailId and provider.id=:providerId");
			query.setLong("assetTypeDetailId",assetTypeDetailId);
			query.setLong("providerId",providerId);
			query.executeUpdate();
			
			//Log Change
			LogUtils.createLog(	_session
								,_user.getId()
								,SDMConfigManager.BUNDLENAME
								,_user.getLocale()
								,SDMConfigManager.getInstance().getApplication(_session)
								,WebLogInfoDict.EVENTDETAIL_CONFIG_DELETED
								,new Object[]{assetTypeDetailId,providerId},null,false);
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
		}
	}
}
