package com.lynxspa.sdm.actions.administration.assets.details;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.entities.securities.assets.AssetType;
import com.lynxspa.entities.securities.assets.AssetTypeDetail;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.utils.StringUtils;
import com.lynxspa.xweb.actions.DownloadBasicAction;


public class ExportAssetDetailFormAction extends DownloadBasicAction {
	
	private static final Logger logger = Logger.getLogger(ExportAssetDetailFormAction.class);
    
	private static final String NAME_REPLACER="\\$\\{enumNameHere\\}";
	private static final String CONTENT_REPLACER="${contentHere}";
	private static final String TEMPLATE_EVENT_DETAIL="com/lynxspa/sdm/actions/administration/assets/details/templates/AssetDetailAdapter.tmpl";
	private static final String FILE_PATH="com/lynxspa/sdm/dictionaries/assets/";

	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
	}
	
	protected StringBuffer processAssetDetail(int _counter,AssetTypeDetail _assetTypeDetail) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append("DETAIL");
			reply.append(_counter+1);
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_assetTypeDetail.getFieldPath()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_assetTypeDetail.getName()));
			reply.append(',');
			reply.append("ClassType.");
			reply.append(_assetTypeDetail.getFieldType());
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_assetTypeDetail.getFormat()));
			reply.append(',');
			reply.append(_assetTypeDetail.getMaxLength());
			reply.append(',');
			reply.append(_assetTypeDetail.isDisplayInTable());
			reply.append(',');
			reply.append(_assetTypeDetail.getDisplayInTableOrder());
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_assetTypeDetail.getDisplayGroup()));
			reply.append(',');
			reply.append(_assetTypeDetail.getDisplayGroupOrder());
			reply.append(',');
			reply.append(_assetTypeDetail.getDisplayInGroupOrder());
			reply.append(',');
			reply.append(_assetTypeDetail.getDisplayRow());
			reply.append(',');
			reply.append(_assetTypeDetail.getDisplayColumn());
			reply.append(',');
			reply.append(_assetTypeDetail.getDisplayTop());
			reply.append(',');
			reply.append(_assetTypeDetail.getDisplayLeft());
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_assetTypeDetail.getDescription()));
			reply.append(',');
			reply.append(String.valueOf(_assetTypeDetail.isExtension()));
			reply.append(',');
			reply.append(String.valueOf(_assetTypeDetail.isHidden()));
			reply.append(',');
			reply.append(String.valueOf(_assetTypeDetail.isBasic()));
			//reply.append(',');
			//reply.append(String.valueOf(_assetTypeDetail.isEditableNormalization()));
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENTDETAIL_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> downloadFiles(HttpServletRequest _request, Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		Map<String,String> reply=null;
		Query query=null;
		List<AssetType> assetTypes=null;
		List<AssetTypeDetail> assetTypeDetails=null;
		String fileName=null;
		String fileContent=null;
		StringBuffer contentBuffer=null;
		String template=null;
		int it1=0;
		
		try{
			reply=new HashMap<String, String>();
			//Recover template
			template=getTemplate(ExportAssetDetailFormAction.TEMPLATE_EVENT_DETAIL);
			logger.debug("Template:\n"+template);
			//Mount files
			query=_session.createQuery("from AssetType");
			assetTypes=(List<AssetType>)query.list();
			for(AssetType assetType:assetTypes){
				query=_session.createQuery("from AssetTypeDetail where auditor.deleted=:isDeleted and assetType.id=:assetTypeId order by displayGroupOrder,displayInGroupOrder");
				query.setBoolean("isDeleted",false);
				query.setString("assetTypeId",assetType.getId());
				assetTypeDetails=(List<AssetTypeDetail>)query.list();
				if(assetTypeDetails.size()>0){
					fileName=assetType.getId()+"AssetDetail";
					contentBuffer=new StringBuffer();
					for(int ic1=0;ic1<assetTypeDetails.size();ic1++){
						if(ic1>0){
							contentBuffer.append(',');
							contentBuffer.append('\n');
						}
						AssetTypeDetail assetTypeDetail=assetTypeDetails.get(ic1);
						contentBuffer.append(processAssetDetail(ic1,assetTypeDetail));
					}
					contentBuffer.append(';');
					fileContent=template.replaceAll(ExportAssetDetailFormAction.NAME_REPLACER,fileName);
					it1=fileContent.indexOf(ExportAssetDetailFormAction.CONTENT_REPLACER);
					fileContent=fileContent.substring(0,it1)+contentBuffer.toString()+fileContent.substring(it1+ExportAssetDetailFormAction.CONTENT_REPLACER.length());
					reply.put(ExportAssetDetailFormAction.FILE_PATH+fileName+".java", fileContent);
				}
			}
		}catch (FPMException e) {
			throw e;
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENTDETAIL_DOWNLOAD_ERROR,e);
		}
		
		return reply;
	}
}
