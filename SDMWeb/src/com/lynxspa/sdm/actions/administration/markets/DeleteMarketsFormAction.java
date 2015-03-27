package com.lynxspa.sdm.actions.administration.markets;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.markets.SPMarket;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class DeleteMarketsFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String,Object> _parameters,List<ValidationException> _errors) throws FPMException{
		ValidationUtils.validateField("marketDeletable",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
	}

	@Override
	public void performAction(HttpServletRequest _request,Session _session, LoggedUser _user,Map<String, Object> _parameters) throws FPMException {

		try {
			//Recover parameters
			final Long marketId=Long.valueOf((String)_parameters.get("marketDeletable"));
			//Get Market
			final SPMarket market=HibernateUtils.getEntity(_session,SPMarket.class,marketId);
			//Delete market
			HibernateUtils.customDelete(_session, market,_user.getId());
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MARKET_DELETED,new Object[]{marketId},null,false);
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
