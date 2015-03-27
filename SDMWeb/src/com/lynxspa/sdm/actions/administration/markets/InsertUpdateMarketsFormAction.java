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

public class InsertUpdateMarketsFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String,Object> _parameters,List<ValidationException> _errors) throws FPMException{
		
		ValidationUtils.validateField("operationType",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		//ValidationUtils.validateField("mic",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		if("UPDATE".equals(_parameters.get("operationType"))){
			ValidationUtils.validateField("id",_parameters,_errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		}
	}
	
	@Override
	public void performAction(HttpServletRequest _request,Session _session, LoggedUser _user,Map<String, Object> _parameters) throws FPMException {

		try {
			//Recover parameters
			final String operationType=(String)_parameters.get("operationType");
			final String mic=_parameters.get("mic")!=null && !((String)_parameters.get("mic")).equals("")?(String)_parameters.get("mic"):" ";
			final String name=(String)_parameters.get("name");
			final String country=((String[])_parameters.get("country"))[0];
			final String city=(String)_parameters.get("city");
			final String custId=(String)_parameters.get("custId");
			final String ticker=(String)_parameters.get("ticker");
			//Market generation

			final SPMarket market=new SPMarket(_user.getId(),mic,name,country,city,custId);
			market.setTicker(ticker);
			if(operationType.equalsIgnoreCase("UPDATE")){
				final Long id=Long.valueOf((String)_parameters.get("id"));
				market.setId(id);
				HibernateUtils.customUpdate(_session,market,_user.getId());
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MARKET_CHANGED,new Object[]{id},null,false);
			}else{
				HibernateUtils.customSave(_session,market,_user.getId());
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.MARKET_INSERTED,new Object[]{market.getId()},null,false);
			}
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
