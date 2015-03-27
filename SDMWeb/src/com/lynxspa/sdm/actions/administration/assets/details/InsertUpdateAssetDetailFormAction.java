package com.lynxspa.sdm.actions.administration.assets.details;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.dictionaries.ClassType;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.assets.AssetType;
import com.lynxspa.entities.securities.assets.AssetTypeDetail;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.sdm.logs.WebLogInfoDict;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class InsertUpdateAssetDetailFormAction extends CoacEnhancedBasicAction {

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
		
		ClassType classType=null;
		
		ValidationUtils.validateField("operationType", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
		ValidationUtils.validateField("assetTypeId", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
		if("UPDATE".equals(_parameters.get("operationType"))){
			ValidationUtils.validateField("assetTypeDetailId", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISLONG);
		}
		ValidationUtils.validateField("name", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
		if(((_parameters.get("isExtension")==null)||(((String[])_parameters.get("isExtension")).length==0))){
			ValidationUtils.validateField("extensionName", _parameters.get("extensionName"), _errors, ValidationsDict.ISNOTEMPTY);
			ValidationUtils.validateField("fieldType", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
			ValidationUtils.validateField("tablePosition",_parameters.get("tablePosition"), _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISINTEGER);
			classType=ClassType.valueOf(((String[])_parameters.get("fieldType"))[0]);
			if(ClassType.TIMESTAMP.getCode().equals(classType.getCode())){
				ValidationUtils.validateField("format", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISDATEFORMAT);
			}
			if(ClassType.DOUBLE.getCode().equals(classType.getCode())){
				ValidationUtils.validateField("format", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISNUMBERFORMAT);
			}
			if(classType.getMaxLength()>0){
				ValidationUtils.validateField("maxlength", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISINTEGER);
				if(Integer.parseInt((String)_parameters.get("maxlength"))<0)
					_errors.add(new ValidationException("value.must.be.positive.and.greater.than.zero","maxlength",String.valueOf(_parameters.get("maxlength"))));
				if(Integer.parseInt((String)_parameters.get("maxlength"))>classType.getMaxLength())
					_errors.add(new ValidationException("value.out.of.bounds","maxlength",String.valueOf(_parameters.get("maxlength"))));
			}
			ValidationUtils.validateField("inTableOrder", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISINTEGER);
			if(((_parameters.get("isHidden")==null)||(((String[])_parameters.get("isHidden")).length==0))){
				ValidationUtils.validateField("displayGroup", _parameters, _errors, ValidationsDict.ISNOTEMPTY);
				ValidationUtils.validateField("displayGroupOrder", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISINTEGER);
				ValidationUtils.validateField("displayInGroupOrder", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISINTEGER);
			}
		}else{
			ValidationUtils.validateField("extensionCode", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
		}
	}

	@Override
	public void performAction(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		AssetType assetType=null;
		AssetTypeDetail assetTypeDetail=null;

		try{
			assetType=(AssetType)_session.load(AssetType.class, String.valueOf(_parameters.get("assetTypeId")));
			if("UPDATE".equals(_parameters.get("operationType"))){
				assetTypeDetail=(AssetTypeDetail)_session.load(AssetTypeDetail.class, new Long((String)_parameters.get("assetTypeDetailId")));
			}else{
				assetTypeDetail=new AssetTypeDetail();
				assetTypeDetail.setAssetType(assetType);
				assetTypeDetail.setBasic(false);
			}
			assetTypeDetail.setHidden(((_parameters.get("isHidden")!=null)&&(((String[])_parameters.get("isHidden")).length>0)));
			if(!assetTypeDetail.isBasic()){
				if(((_parameters.get("isExtension")==null)||(((String[])_parameters.get("isExtension")).length==0))){
					assetTypeDetail.setExtension(false);
					assetTypeDetail.setName(String.valueOf(_parameters.get("name")));
					assetTypeDetail.setDescription(String.valueOf(_parameters.get("description")));
					assetTypeDetail.setFieldType(((String[])_parameters.get("fieldType"))[0]);
					assetTypeDetail.setFieldPath(String.valueOf(((String[])_parameters.get("extensionName"))[0])+':'+((String[])_parameters.get("fieldType"))[0]+':'+((String[])_parameters.get("tablePosition"))[0]);
					assetTypeDetail.setFormat(String.valueOf(_parameters.get("format")));
					assetTypeDetail.setMaxLength(Integer.parseInt((String)_parameters.get("maxlength")));
					assetTypeDetail.setDisplayInTable(((_parameters.get("isDisplayInTable")!=null)&&(((String[])_parameters.get("isDisplayInTable")).length>0)));
					assetTypeDetail.setDisplayInTableOrder(Integer.parseInt((String)_parameters.get("inTableOrder")));
					assetTypeDetail.setDisplayGroup(String.valueOf(_parameters.get("displayGroup")));
					assetTypeDetail.setDisplayGroupOrder(Integer.parseInt((String)_parameters.get("displayGroupOrder")));
					assetTypeDetail.setDisplayInGroupOrder(Integer.parseInt((String)_parameters.get("displayInGroupOrder")));
					//assetTypeDetail.setEditableNormalization(true);
				}else{
					assetTypeDetail.setExtension(true);
					assetTypeDetail.setName(String.valueOf(_parameters.get("name")));
					assetTypeDetail.setDescription(String.valueOf(_parameters.get("description")));
					assetTypeDetail.setFieldPath(String.valueOf(_parameters.get("extensionCode")).replaceAll(":","_"));
					assetTypeDetail.setFieldType("EXTENSION");
					assetTypeDetail.setMaxLength(0);
					assetTypeDetail.setDisplayInTable(false);
					assetTypeDetail.setDisplayInTableOrder(0);
					assetTypeDetail.setDisplayGroup("EXTENSIONS");
					assetTypeDetail.setDisplayGroupOrder(-1);
					assetTypeDetail.setDisplayInGroupOrder(0);
					assetTypeDetail.setHidden(true);
					//assetTypeDetail.setEditableNormalization(true);
				}
			}else{
				assetTypeDetail.setFormat(String.valueOf(_parameters.get("format")));
				assetTypeDetail.setMaxLength(Integer.parseInt((String)_parameters.get("maxlength")));
				assetTypeDetail.setDisplayInTable(((_parameters.get("isDisplayInTable")!=null)&&(((String[])_parameters.get("isDisplayInTable")).length>0)));
				assetTypeDetail.setDisplayInTableOrder(Integer.parseInt((String)_parameters.get("inTableOrder")));
				assetTypeDetail.setDisplayGroup(String.valueOf(_parameters.get("displayGroup")));
				assetTypeDetail.setDisplayGroupOrder(Integer.parseInt((String)_parameters.get("displayGroupOrder")));
				assetTypeDetail.setDisplayInGroupOrder(Integer.parseInt((String)_parameters.get("displayInGroupOrder")));
			}
			//Log Change
			if("UPDATE".equals(_parameters.get("operationType"))){
				HibernateUtils.customUpdate(_session, assetTypeDetail, _user.getId());
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.EVENTDETAIL_CONFIG_CHANGED,new Object[]{_parameters.get("name"),assetType.getName()},null,false);
			}else{
				HibernateUtils.customSave(_session, assetTypeDetail, _user.getId());
				LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),WebLogInfoDict.EVENTDETAIL_CONFIG_ADDED,new Object[]{_parameters.get("name"),assetType.getName()},null,false);
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
		}
	}
}
