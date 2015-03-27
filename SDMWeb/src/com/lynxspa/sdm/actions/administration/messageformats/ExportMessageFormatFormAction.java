package com.lynxspa.sdm.actions.administration.messageformats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.domains.CADomainClusterFormat;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFieldConfig;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageFormat;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageType;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.utils.ReflectionUtils;
import com.lynxspa.utils.StringUtils;
import com.lynxspa.xweb.actions.DownloadBasicAction;


public class ExportMessageFormatFormAction extends DownloadBasicAction {

	private static final Logger logger = Logger.getLogger(ExportMessageFormatFormAction.class);
    
	private static final String NAME_REPLACER="\\$\\{enumNameHere\\}";
	private static final String PACKAGE_REPLACER="\\$\\{enumPackageHere\\}";
	private static final String CONTENT_REPLACER="${contentHere}";
	private static final String FORMAT_TEMPLATE="com/lynxspa/sdm/actions/administration/messageformats/templates/CAFormatDictAdapter.tmpl";
	private static final String TYPEFORMAT_TEMPLATE="com/lynxspa/sdm/actions/administration/messageformats/templates/CAFormatMessageTypeDictAdapter.tmpl";
	private static final String TYPEFIELDFORMAT_TEMPLATE="com/lynxspa/sdm/actions/administration/messageformats/templates/CAMessageTypeFieldsDictAdapter.tmpl";
	private static final String CLUSTERFORMAT_TEMPLATE="com/lynxspa/sdm/actions/administration/messageformats/templates/CAFormatClusterDictAdapter.tmpl";
	private static final String FORMAT_NAME="CAFormat";
	private static final String TYPEFORMAT_NAME="CAMessageType";
	private static final String TYPEFIELDFORMAT_NAME="CAMessageType{1}Field";
	private static final String CLUSTERFORMAT_NAME="CADomainCluster";
	private static final String FORMAT_PACKAGE="com.lynxspa.sdm.dictionaries.formats";
	private static final String TYPEFORMAT_PACKAGE="com.lynxspa.sdm.dictionaries.formats.{0}";
	private static final String TYPEFIELDFORMAT_PACKAGE="com.lynxspa.sdm.dictionaries.formats.{0}.messages";
	private static final String CLUSTERFORMAT_PACKAGE="com.lynxspa.sdm.dictionaries.formats.{0}";

	
	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
	}
	
	protected StringBuffer processEnumContent(int _counter,CAMessageFormat _messageFormat) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append(_messageFormat.getId().toUpperCase());
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_messageFormat.getId()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_messageFormat.getName()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_messageFormat.getPath()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_messageFormat.getPattern()));
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENTDETAIL_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	protected StringBuffer processEnumContent(int _counter,CAMessageType _messageType) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append("TYPE");
			reply.append(ReflectionUtils.ensureJavaComplianceName(_messageType.getId().getCode()).toUpperCase());
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_messageType.getId().getCode()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_messageType.getName()));
			reply.append(',');
			reply.append("CAFormat.");
			reply.append(_messageType.getId().getFormat().getId().toUpperCase());
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENTDETAIL_EXPORT_ERROR,e);
		}
		
		return reply;
	}
	
	protected StringBuffer processEnumContent(int _counter,CAMessageFieldConfig _messageFieldConfig) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append("FIELD");
			reply.append(_counter);
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_messageFieldConfig.getId().getPath()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_messageFieldConfig.getFieldName()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_messageFieldConfig.getFieldType()));
			reply.append(',');
			reply.append(_messageFieldConfig.getFieldLength());
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_messageFieldConfig.getFieldFormat()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_messageFieldConfig.getDisplayGroup()));
			reply.append(',');
			reply.append(_messageFieldConfig.getDisplayRow());
			reply.append(',');
			reply.append(_messageFieldConfig.getDisplayColumn());
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_messageFieldConfig.getDescription()));
			reply.append(',');
			reply.append(_messageFieldConfig.isHidden());
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENTDETAIL_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	protected StringBuffer processEnumContent(int _counter,CADomainClusterFormat _domainCluster) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append(_domainCluster.getId().getDomainCluster().getId().getDomain().getId().getCode().toUpperCase());
			reply.append('_');
			reply.append(_domainCluster.getId().getDomainCluster().getId().getCode().toUpperCase());
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_domainCluster.getId().getDomainCluster().getId().getDomain().getId().getCode()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_domainCluster.getId().getDomainCluster().getId().getCode()));
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENTDETAIL_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	
	protected void addFile(Map<String,String> _files,final String _template,final String _package,final String _name,final StringBuffer _content,String... _parameters){

		String fileContent=null;
		String filePackage=null;
		String fileName=null;
		String filePath=null;
		int it1=0;
	
		fileName=StringUtils.formatMessage(_name,(Object[])_parameters);
		fileName=ReflectionUtils.ensureJavaComplianceName(fileName);
		filePackage=StringUtils.formatMessage(_package,(Object[])_parameters);
		filePackage=ReflectionUtils.ensureJavaComplianceName(filePackage);
		fileContent=_template.replaceAll(ExportMessageFormatFormAction.NAME_REPLACER,fileName);
		fileContent=fileContent.replaceAll(ExportMessageFormatFormAction.PACKAGE_REPLACER,filePackage);
		it1=fileContent.indexOf(ExportMessageFormatFormAction.CONTENT_REPLACER);
		fileContent=fileContent.substring(0,it1)+_content.toString()+';'+fileContent.substring(it1+ExportMessageFormatFormAction.CONTENT_REPLACER.length());
		filePath=filePackage.replaceAll("\\.", "\\/");
		_files.put(filePath+'/'+fileName+".java", fileContent);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> downloadFiles(HttpServletRequest _request,Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		Map<String,String> reply=null;
		Query query=null;
		StringBuffer typeFieldBuffer=null;
		StringBuffer formatContent=null;
		StringBuffer typeFormatContent=null;
		StringBuffer clusterFormatContent=null;
		String formatTemplate=null;
		String typeFormatTemplate=null;
		String typeFieldFormatTemplate=null;
		String clusterFormatTemplate=null;
		List<CAMessageFormat> formats=null;
		int ic1=0,ic2=0,ic3=0;
		
		try{
			reply=new HashMap<String, String>();
			//Recover templates
			formatTemplate=getTemplate(ExportMessageFormatFormAction.FORMAT_TEMPLATE);
			logger.debug("FormatTemplate:\n"+formatTemplate);
			typeFormatTemplate=getTemplate(ExportMessageFormatFormAction.TYPEFORMAT_TEMPLATE);
			logger.debug("FormatTemplate:\n"+typeFormatTemplate);
			typeFieldFormatTemplate=getTemplate(ExportMessageFormatFormAction.TYPEFIELDFORMAT_TEMPLATE);
			logger.debug("FormatTemplate:\n"+typeFieldFormatTemplate);
			clusterFormatTemplate=getTemplate(ExportMessageFormatFormAction.CLUSTERFORMAT_TEMPLATE);
			logger.debug("FormatTemplate:\n"+clusterFormatTemplate);
			//Mount files
			query=_session.createQuery("from CAMessageFormat");
			formats=query.list();
			for(CAMessageFormat messageFormat:formats){
				ic1++;
				//Mount messageTypes
				ic2=0;
				typeFormatContent=null;
				for(CAMessageType messageType:messageFormat.getMessageTypes()){
					ic2++;
					//Mount messageTypeDetails
					ic3=0;
					typeFieldBuffer=null;
					for(CAMessageFieldConfig messageFieldConfig:messageType.getMessageFieldConfigs()){
						ic3++;
						if(typeFieldBuffer==null){
							typeFieldBuffer=new StringBuffer();
						}else{
							typeFieldBuffer.append(',');
							typeFieldBuffer.append('\n');
						}
						typeFieldBuffer.append(processEnumContent(ic3,messageFieldConfig));
					}
					addFile(reply,typeFieldFormatTemplate,ExportMessageFormatFormAction.TYPEFIELDFORMAT_PACKAGE,ExportMessageFormatFormAction.TYPEFIELDFORMAT_NAME,typeFieldBuffer,messageFormat.getId().toLowerCase(),messageType.getId().getCode().toUpperCase());
					if(typeFormatContent==null){
						typeFormatContent=new StringBuffer();
					}else{
						typeFormatContent.append(',');
						typeFormatContent.append('\n');
					}
					typeFormatContent.append(processEnumContent(ic2,messageType));
				}
				addFile(reply,typeFormatTemplate,ExportMessageFormatFormAction.TYPEFORMAT_PACKAGE,ExportMessageFormatFormAction.TYPEFORMAT_NAME,typeFormatContent,messageFormat.getId().toLowerCase());
				//Mount messageTypes
				ic2=0;
				clusterFormatContent=null;
				for(CADomainClusterFormat domainClusterFormat:messageFormat.getDomainClusters()){
					ic2++;
					if(clusterFormatContent==null){
						clusterFormatContent=new StringBuffer();
					}else{
						clusterFormatContent.append(',');
						clusterFormatContent.append('\n');
					}
					clusterFormatContent.append(processEnumContent(ic2,domainClusterFormat));
				}
				addFile(reply,clusterFormatTemplate,ExportMessageFormatFormAction.CLUSTERFORMAT_PACKAGE,ExportMessageFormatFormAction.CLUSTERFORMAT_NAME,clusterFormatContent,messageFormat.getId().toLowerCase());
				//Mount messageFormat
				if(formatContent==null){
					formatContent=new StringBuffer();
				}else{
					formatContent.append(',');
					formatContent.append('\n');
				}
				formatContent.append(processEnumContent(ic1,messageFormat));
			}
			addFile(reply,formatTemplate,ExportMessageFormatFormAction.FORMAT_PACKAGE,ExportMessageFormatFormAction.FORMAT_NAME,formatContent);
		}catch (FPMException e) {
			throw e;
		}catch (Exception e) {
			throw new FPMException(ErrorDict.EVENTDETAIL_DOWNLOAD_ERROR,e);
		}

		return reply;
	}
}
