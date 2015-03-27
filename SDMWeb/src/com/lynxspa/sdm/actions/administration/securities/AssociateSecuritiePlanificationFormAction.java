package com.lynxspa.sdm.actions.administration.securities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.plannings.SPPlanningProcess;
import com.lynxspa.entities.plannings.SPSecuritiesPlannings;
import com.lynxspa.entities.securities.SPVirtualSecurity;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.utils.StringUtils;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class AssociateSecuritiePlanificationFormAction  extends CoacEnhancedBasicAction{

	@Override
	public void validate(Map<String,Object> _parameters,List<ValidationException> _errors) throws FPMException{
		
		//ValidationUtils.validateField("planificationToAssociate",_parameters,_errors,ValidationsDict.ISSTRING);
		//ValidationUtils.validateField("planification",_parameters,_errors,ValidationsDict.ISLONG);
		ValidationUtils.validateField("isin",_parameters,_errors,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("name",_parameters,_errors,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("sedol",_parameters,_errors,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("cusip",_parameters,_errors,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("secFinancialAssets",_parameters,_errors,ValidationsDict.ISSTRING);
		ValidationUtils.validateField("market",_parameters,_errors,ValidationsDict.ISLONG);
		ValidationUtils.validateField("inCustPortfolio",_parameters,_errors,ValidationsDict.ISBOOLEAN);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void performAction(HttpServletRequest _request,Session _session, LoggedUser _user,Map<String, Object> _parameters) throws FPMException {
		
		String st1=null;
		String queryString=null;
		Iterator<SPVirtualSecurity> securities=null;
		
		try {
			//Recover parameters
			final String securityClass=StringUtils.checkNotExist((String)_parameters.get("securityclass"),null);
			final String isin=StringUtils.checkNotExist((String)_parameters.get("isin"),null);
			final String name=StringUtils.checkNotExist((String)_parameters.get("name"),null);
			final String sedol=StringUtils.checkNotExist((String)_parameters.get("sedol"),null);
			final String cusip=StringUtils.checkNotExist((String)_parameters.get("cusip"),null);
			final String secFinancialAssets=StringUtils.checkNotExist((String)_parameters.get("secFinancialAssets"),null);
			final String planificationId=StringUtils.checkNotExist((String)_parameters.get("planification"),null);
			st1=StringUtils.checkNotExist((String)_parameters.get("market"),null);
			final Long market=(st1!=null)? Long.valueOf(st1) : null;
			st1=StringUtils.checkNotExist((String)_parameters.get("inCustPortfolio"),null);
			final Boolean inCustPortfolio=(st1!=null)? Boolean.TRUE.toString().equalsIgnoreCase(st1) : null;
			final Map<String,Object> parameters=new HashMap<String,Object>(15);
			
			//queryString = "from SPVirtualSecurity where auditor.deleted =:deleted";
			queryString = "select distinct(sec) from SPSecuritiesPlannings as secplan right outer join secplan.security as sec where sec.auditor.deleted=:deleted";
			if(securityClass!=null){
				queryString+=" and SECURITYCLASS=:securityClass";
				parameters.put("securityClass",securityClass);
			}
			if(isin!=null){
				queryString+=" and sec.isin=:isin";
				parameters.put("isin",isin);
			}
			if(name!=null){
				queryString+=" and name like :name";
				parameters.put("name","%"+name+"%");
			}
			if(sedol!=null){
				queryString+=" and sedol=:sedol";
				parameters.put("sedol",sedol);
			}
			if(cusip!=null){
				queryString+=" and cusip=:cusip";
				parameters.put("cusip",cusip);
			}
			if(secFinancialAssets!=null){
				queryString+=" and sec.secFinancialAssets.id=:secFinancialAssets";
				parameters.put("secFinancialAssets",secFinancialAssets);
			}
			
			List<Long> lplanningstodelete = new ArrayList<Long>();;
			
			if(planificationId!=null){
				StringTokenizer plannings = new StringTokenizer(planificationId,",");
				int i=0;
				while (plannings.hasMoreElements()){
					if (i==0){
						queryString+=" and secplan.planning.id=:planificationId_"+i;
					}else{
						queryString+=" or secplan.planning.id=:planificationId_"+i;
					}
					parameters.put("planificationId_"+i,Long.parseLong(plannings.nextElement().toString()));
					lplanningstodelete.add((Long)parameters.get("planificationId_"+i));
					i++;
				}
			}
			if(market!=null){
				queryString+=" and sec.market.id=:marketId";
				parameters.put("marketId",market);
			}
			if(inCustPortfolio!=null){
				queryString+=" and inCustPortfolio=:inCustPortfolio";
				parameters.put("inCustPortfolio",inCustPortfolio);
			}
			
			
			//Execute query
			Query query=HibernateUtils.createQuery(_session,queryString);
			query.setBoolean("deleted", false);
			for(Entry<String,Object> entry:parameters.entrySet()){
				query.setParameter(entry.getKey(),entry.getValue());
			}
			securities=(Iterator<SPVirtualSecurity>)query.iterate();
			List<SPVirtualSecurity> lsecurities = new ArrayList<SPVirtualSecurity>();

			
			SPVirtualSecurity security = null;
			while(securities.hasNext()){
				security= securities.next();
				lsecurities.add(security);
				if (lplanningstodelete.size()>0){
					for (Long plan:lplanningstodelete){
						query=_session.createQuery("delete SPSecuritiesPlannings where security.id=:securityId and planning.id=:planningId");
						query.setLong("securityId", security.getId());
						query.setLong("planningId", plan);
						query.executeUpdate();
					}
				}else{
					query=_session.createQuery("delete SPSecuritiesPlannings where security.id=:securityId");
					query.setLong("securityId", security.getId());
					query.executeUpdate();
				}
			}
			
			
			SPSecuritiesPlannings secPlanning = null;
			StringTokenizer plannings = null;
			String planificationAssociation = ((String)_parameters.get("planificationAssociation"));
			
			if (!planificationAssociation.equals("none")){
				st1=StringUtils.checkNotExist(planificationAssociation,null);
				plannings = new StringTokenizer(st1,",");
				SPPlanningProcess sp = null;
				while (plannings.hasMoreElements()){
					queryString = "from SPPlanningProcess where auditor.deleted =:deleted and id=:planificationId";
					query=HibernateUtils.createQuery(_session,queryString);
					query.setBoolean("deleted", false);
					query.setLong("planificationId", Long.parseLong(plannings.nextElement().toString()));
					sp = (SPPlanningProcess)query.uniqueResult();
	
					for (SPVirtualSecurity secs:lsecurities){
						secPlanning = new SPSecuritiesPlannings(secs,sp);
						_session.save(secPlanning);
					}
				}
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
