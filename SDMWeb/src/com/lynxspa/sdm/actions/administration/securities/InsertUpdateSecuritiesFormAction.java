package com.lynxspa.sdm.actions.administration.securities;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.plannings.SPPlanningProcess;
import com.lynxspa.entities.plannings.SPSecuritiesPlannings;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.entities.securities.SPVirtualSecurity;
import com.lynxspa.entities.securities.financialassets.SPSecurityFinancialAssets;
import com.lynxspa.entities.securities.markets.SPMarket;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.utils.StringUtils;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class InsertUpdateSecuritiesFormAction extends CoacEnhancedBasicAction {
	
	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		
		Query query=null;
		SPSecurity security = null;
		
		ValidationUtils.validateField("operationType", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("name", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("marketId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("isin", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("cusip", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("sedol", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("customerId", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("country",_parameters, _errors, ValidationsDict.ISNOTEMPTY);
		ValidationUtils.validateField("currency", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("relIndex", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("securityType", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("indSector", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("indGroup", _parameters, _errors, ValidationsDict.ISSTRING);
		ValidationUtils.validateField("indSubGroup", _parameters, _errors, ValidationsDict.ISSTRING);
		if("UPDATE".equals(_parameters.get("operationType"))){
			ValidationUtils.validateField("id", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		}else{
			ValidationUtils.validateField("ticker", _parameters, _errors,ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
			//Validate if security exist
			Session session=getCurrentSession();
			query= session.createQuery("from SPSecurity where isin=:securityIsin and isDeleted=:deleted");
			query.setString("securityIsin",String.valueOf(_parameters.get("isin")));
			query.setBoolean("deleted", false);
			security=(SPSecurity)query.uniqueResult();
			if(security!=null){
				_errors.add(new ValidationException("security.exist","isin",String.valueOf(_parameters.get("isin"))));
			}
		}
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		SPMarket market=null;
		SPSecurity security = null;
		SPVirtualSecurity virtualSecurity = null;
		String operationType=null;
		long securityId=0l;
		String securityFieldAssignement=null;
		Query query=null;
		int affectedRows=0;
		SPSecurityFinancialAssets financialAsset = null;

		try {
			//Recover parameter values
			operationType=(String)_parameters.get("operationType");
			if(StringUtils.checkNotExist((String)_parameters.get("id"), null)!=null)
				securityId=Long.parseLong((String)_parameters.get("id"));
			//Insert/Update action
			if("UPDATE".equalsIgnoreCase(operationType)){
				security=(SPSecurity)_session.get(SPSecurity.class,securityId);
			}else{
				security = new SPSecurity(_user.getId());
				security.setTicker(String.valueOf(_parameters.get("ticker")));
			}
			security.setName(String.valueOf(_parameters.get("name")));
			security.setIsin(String.valueOf(_parameters.get("isin")));
			security.setCusip(String.valueOf(_parameters.get("cusip")));
			security.setSedol(String.valueOf(_parameters.get("sedol")));
			if((securityFieldAssignement=(String)SDMConfigManager.getInstance().getConfiguration(_session,CAConfiguration.SECURITYFIELDASSIGNMENTPROVIDER))!=null){
				String[] assignements=securityFieldAssignement.split(",");
				for(String assignement:assignements){
					switch (assignement.charAt(assignement.length()-1)) {
						case '1':security.setInfoProviderId1(String.valueOf(_parameters.get("infoProviderId1")));
						case '2':security.setInfoProviderId2(String.valueOf(_parameters.get("infoProviderId2")));
						case '3':security.setInfoProviderId3(String.valueOf(_parameters.get("infoProviderId3")));
					}
				}
			}
			query=_session.createQuery("from SPMarket where mic=:marketMic");
			query.setString("marketMic",(String)_parameters.get("marketId"));
			market=(SPMarket)query.uniqueResult();
			security.setMarket(market);
			security.setCountry(((String[])_parameters.get("country"))[0]);
			security.setSecurityType(String.valueOf(_parameters.get("securityType")));
			security.setIndSector(String.valueOf(_parameters.get("indSector")));
			security.setIndGroup(String.valueOf(_parameters.get("indGroup")));
			security.setIndSubGroup(String.valueOf(_parameters.get("indSubGroup")));
			security.setRelIndex(String.valueOf(_parameters.get("relIndex")));
			security.setCustomerId(String.valueOf(_parameters.get("customerId")));
			security.setCurrency(String.valueOf(_parameters.get("currency")));
			security.setExpirationDate((Date)_parameters.get("expirationDate"));
			
			
			if((_parameters.get("financialAssetId")!=null)&&(((String[])_parameters.get("financialAssetId")).length>0)){
				final String financialAssetId=(((String[])_parameters.get("financialAssetId"))[0]);
				if (!"0".equals(financialAssetId)){
					query=_session.createQuery("from SPSecurityFinancialAssets where id=:financialAssetId");
					query.setString("financialAssetId",financialAssetId);
					financialAsset=query.uniqueResult()!=null?(SPSecurityFinancialAssets)query.uniqueResult():null;
					security.setSecFinancialAssets(financialAsset);
				}
			}
			if("UPDATE".equalsIgnoreCase(operationType)){
				HibernateUtils.customUpdate(_session, security, _user.getId());
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.SECURITY_CHANGED,new Object[]{security.getId()},null,false);
				
				query=_session.createQuery("delete SPSecuritiesPlannings where security.id=:securityId");
				query.setLong("securityId", security.getId());
				query.executeUpdate();
				
			}else{
				HibernateUtils.customSave(_session, security, _user.getId());
				_session.flush();
				if(securityId!=0l){
					//This is because security must be created as copy of existent virtual security and must override it
					virtualSecurity=(SPVirtualSecurity)_session.get(SPVirtualSecurity.class, securityId);
					//Update associated messages
					query=_session.createQuery("update CAEventMessage set normalizedSecurity.id=:securityId,operationStatus.state.id.code=:newState where normalizedSecurity.id=:virtualSecurityId");
					query.setLong("securityId", security.getId());
					query.setString("newState", CAStatesEVENTMESSAGEFlow.PRSD.getId());
					query.setLong("virtualSecurityId", virtualSecurity.getId());
					affectedRows=query.executeUpdate();
					//delete virtual security
					HibernateUtils.customDelete(_session, virtualSecurity, _user.getId());
					LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.VIRTUAL_SECURITY_INSERTED,new Object[]{security.getId(),virtualSecurity.getId(),affectedRows},null,false);
				}else{
					LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.SECURITY_INSERTED,new Object[]{security.getId()},null,false);
				}
			}
			
			associatePlanifications(_parameters, _session, security, _user);
			
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
	
	private void associatePlanifications (Map<String, Object> _parameters, Session _session, SPSecurity _security, LoggedUser _user) throws FPMException{
		String planificationAssociation = null;
		SPSecuritiesPlannings secPlanning = null;
		String st1 = null;
		String queryString = null;
		StringTokenizer plannings = null;
		Query query=null;
		
		try{
			planificationAssociation =((String)_parameters.get("planificationAssociation"));
			if (!planificationAssociation.equals("none")){
				st1=StringUtils.checkNotExist(planificationAssociation,null);
				plannings = new StringTokenizer(st1,",");
				SPPlanningProcess sp = null;
				while (plannings.hasMoreElements()){
					queryString = "from SPPlanningProcess where id=:planificationId";
					query=HibernateUtils.createQuery(_session,queryString);
					query.setLong("planificationId", Long.parseLong(plannings.nextElement().toString()));
					sp = (SPPlanningProcess)query.uniqueResult();
				
					secPlanning = new SPSecuritiesPlannings(_security,sp);
					HibernateUtils.customSave(_session, secPlanning, _user.getId());
				}
				_session.flush();
			}
		}catch(FPMException e){
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),e,true);
			throw e;
		}
	}
}
