package com.lynxspa.sdm.actions.administration.reports.questions;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.events.answers.CAQuestions;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;

public class DeleteQuestionDetailFormAction extends CoacEnhancedBasicAction {

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters)
			throws FPMException {
		CAQuestions question = null;
		Iterator<CAQuestions> questions=null;
		Query query=null;
		
		try{
			if (_parameters.get("answerId")!=null && !_parameters.get("answerId").equals("")){
				question=(CAQuestions)_session.get(CAQuestions.class, Long.valueOf(String.valueOf(_parameters.get("answerId"))));
				if (question.isHeader()){
					query =_session.createQuery("from CAQuestions where headerGroup=:questionGroup");
					query.setLong("questionGroup",question.getId());
					questions = query.iterate();
					while(questions.hasNext()){
						HibernateUtils.customDelete(_session,questions.next(),_user.getId());
					}
				}
				HibernateUtils.customDelete(_session,question,_user.getId());
			}
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
