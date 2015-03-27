package com.lynxspa.sdm.actions.administration.assets.details;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.assets.AssetTypeDetail;
import com.lynxspa.entities.securities.assets.AssetTypeDetailBlocked;
import com.lynxspa.entities.securities.assets.providers.Provider;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class InsertAssetDetailBlockedFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		ValidationUtils.validateField("assetProvider", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
		ValidationUtils.validateField("assetTypeDetailId", _parameters, _errors, ValidationsDict.ISNOTEMPTY);		
		ValidationUtils.validateField("startDate", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
		ValidationUtils.validateField("endDate", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		AssetTypeDetailBlocked blocked = null;
		
		try{
			// Get Provider
			String providerId = ((String[])_parameters.get("assetProvider"))[0];			
			
			Provider provider = (Provider)_session.createQuery("from Provider where id=:id")
													.setParameter("id", new Long(providerId))
													.uniqueResult();
			// Get AssetTypeDetail			
			String assetTypeDetailId = (String)_parameters.get("assetTypeDetailId");
			AssetTypeDetail assetTypeDetail = (AssetTypeDetail)_session.createQuery("from AssetTypeDetail where id=:id")
													.setParameter("id", new Long(assetTypeDetailId))
													.uniqueResult();
			
			// Look for update first
			//Criteria criteria = new Criteria();
			blocked = (AssetTypeDetailBlocked)_session.createQuery("from AssetTypeDetailBlocked as blocked where blocked.provider.id=:providerid and blocked.assetTypeDetail.id=:assettypedetailid")
													.setParameter("providerid", new Long(providerId))
													.setParameter("assettypedetailid", new Long(assetTypeDetailId))
													.uniqueResult();
			
			if(blocked == null ){
				blocked = new AssetTypeDetailBlocked();
			
				blocked.setProvider(provider);
				blocked.setAssetTypeDetail(assetTypeDetail);
			}
			
			// Set Date values
			// These dates are pased from datepicked as Date Java Class
			blocked.setBeginBlockedDate((Date)_parameters.get("startDate"));
			blocked.setEndBlockedDate((Date)_parameters.get("endDate"));
			
			HibernateUtils.customSave(_session, blocked, _user.getId());
			
			LogUtils.createLog(	_session,_user.getId()
								,SDMConfigManager.BUNDLENAME
								,_user.getLocale()
								,SDMConfigManager.getInstance().getApplication(_session)
								,WebLogInfoDict.EVENTDETAIL_CONFIG_ADDED
								,new Object[]{}
								,null
								,false);
			clearCache(_session,_user.getId());

		}catch(FPMException e){
			LogUtils.createLog( _session
								,_user.getId()
								,SDMConfigManager.BUNDLENAME
								,_user.getLocale()
								,SDMConfigManager.getInstance().getApplication(_session)
								,e
								,true);
			throw e;
		}catch(HibernateException e) {
			FPMException newException=new FPMException(ErrorDict.HIBERNATE_ERROR,e);
			LogUtils.createLog( _session
								,_user.getId()
								,SDMConfigManager.BUNDLENAME
								,_user.getLocale()
								,SDMConfigManager.getInstance().getApplication(_session)
								,newException
								,true);
			throw newException;
		}catch(Throwable e) {
			FPMException newException=new FPMException(e);
			LogUtils.createLog(	_session
								,_user.getId()
								,SDMConfigManager.BUNDLENAME
								,_user.getLocale()
								,SDMConfigManager.getInstance().getApplication(_session)
								,newException
								,true);
			throw newException;
		}
	}
}
