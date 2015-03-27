package com.lynxspa.sdm.actions.administration.domains;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.domains.Domain;
import com.lynxspa.entities.application.domains.DomainCluster;
import com.lynxspa.entities.application.domains.DomainNorm;
import com.lynxspa.entities.application.domains.DomainValue;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.exceptions.ErrorDict;
import com.lynxspa.utils.ReflectionUtils;
import com.lynxspa.utils.StringUtils;
import com.lynxspa.xweb.actions.DownloadBasicAction;


public class ExportDomainsFormAction extends DownloadBasicAction {

	private static final Logger logger = Logger.getLogger(ExportDomainsFormAction.class);
    
	private static final String NAME_REPLACER="\\$\\{enumNameHere\\}";
	private static final String CONTENT_REPLACER="${contentHere}";
	private static final String TEMPLATE_DOMAIN="com/lynxspa/sdm/actions/administration/domains/templates/DomainDictAdapter.tmpl";
	private static final String TEMPLATE_DOMAIN_NORMALS="com/lynxspa/sdm/actions/administration/domains/templates/DomainNormalDictAdapter.tmpl";
	private static final String TEMPLATE_DOMAIN_CLUSTERS="com/lynxspa/sdm/actions/administration/domains/templates/DomainClusterDictAdapter.tmpl";
	private static final String TEMPLATE_DOMAIN_VALUES="com/lynxspa/sdm/actions/administration/domains/templates/DomainValueDictAdapter.tmpl";
	private static final String DOMAIN_FILE_PATH="com/lynxspa/sdm/dictionaries/domains/";
	private static final String NORMALS_FILE_PATH="com/lynxspa/sdm/dictionaries/domains/normals/";
	private static final String CLUSTERS_FILE_PATH="com/lynxspa/sdm/dictionaries/domains/clusters/";
	private static final String VALUES_FILE_PATH="com/lynxspa/sdm/dictionaries/domains/values/";
	
	
	@Override
	public void validate(Map<String, Object> _parameters, List<ValidationException> _errors) throws FPMException {
	}
	
	protected StringBuffer processDomain(Domain _domain) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append(ReflectionUtils.ensureJavaComplianceName(_domain.getId().getCode()).toUpperCase());
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_domain.getId().getCode()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral((_domain.getName()!=null)? _domain.getName() : null));
			reply.append(',');
			reply.append(_domain.isDeletable());
			reply.append(',');
			reply.append(_domain.isUpdatable());
			reply.append(',');
			reply.append(_domain.isCacheable());
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.DOMAIN_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	protected StringBuffer processDomainNormal(DomainNorm _domainNorm,int _ic1) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append("NORMAL");
			reply.append(_ic1);
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_domainNorm.getId().getCode()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral((_domainNorm.getDescription()!=null)? _domainNorm.getDescription() : null));
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.DOMAIN_NORMAL_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	protected StringBuffer processDomainCluster(DomainCluster _domainCluster) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append(ReflectionUtils.ensureJavaComplianceName(_domainCluster.getId().getCode()).toUpperCase());
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_domainCluster.getId().getCode()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_domainCluster.getName()));
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.DOMAIN_CLUSTER_EXPORT_ERROR,e);
		}
		
		return reply;
	}

	protected StringBuffer processDomainValue(DomainValue _domainValue,int _ic1) throws Exception{

		StringBuffer reply=null;
		
		try{
			reply=new StringBuffer();
			reply.append('	');
			reply.append("VALUE");
			reply.append(_ic1);
			reply.append('(');
			reply.append(StringUtils.stringToLiteral(_domainValue.getId().getNorm().getId().getCode()));
			reply.append(',');
			reply.append(StringUtils.stringToLiteral(_domainValue.getValue()));
			reply.append(')');
		}catch (Exception e) {
			throw new FPMException(ErrorDict.DOMAIN_VALUE_EXPORT_ERROR,e);
		}
		
		return reply;
	}
	

	protected String generateNewFile(String _template,String _enumName,StringBuffer _content){
		
		String reply=null;
		int it1=0;
		
		reply=_template.replaceAll(ExportDomainsFormAction.NAME_REPLACER,_enumName);
		it1=reply.indexOf(ExportDomainsFormAction.CONTENT_REPLACER);
		reply=reply.substring(0,it1)+_content.toString()+reply.substring(it1+ExportDomainsFormAction.CONTENT_REPLACER.length());

		return reply;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> downloadFiles(HttpServletRequest _request,Session _session, LoggedUser _user, Map<String, Object> _parameters) throws FPMException {
		
		Map<String,String> reply=null;
		String domainTemplate=null;
		String domainNormalTemplate=null;
		String domainClusterTemplate=null;
		String domainValueTemplate=null;
		StringBuffer domainContent=null;
		StringBuffer clusterContent=null;
		StringBuffer normalContent=null;
		StringBuffer valueContent=null;
		Domain domain=null;
		Iterator<Domain> domains=null;
		List<DomainNorm> normals=null;
		List<DomainCluster> clusters=null;
		List<DomainValue> values=null;
		String newFile=null;
		String enumName=null;
		Query query=null;
		int domainNormalCounter=0;
		String applicationId=null;

		try{
			reply=new HashMap<String, String>();
			applicationId=StringUtils.checkNotExist((String)_parameters.get("applicationId"),SDMConfigManager.getInstance().getApplicationName());
			//Recover template
			domainTemplate=getTemplate(ExportDomainsFormAction.TEMPLATE_DOMAIN);
			logger.debug("Template:\n"+domainTemplate);
			domainNormalTemplate=getTemplate(ExportDomainsFormAction.TEMPLATE_DOMAIN_NORMALS);
			logger.debug("Template:\n"+domainNormalTemplate);
			domainClusterTemplate=getTemplate(ExportDomainsFormAction.TEMPLATE_DOMAIN_CLUSTERS);
			logger.debug("Template:\n"+domainClusterTemplate);
			domainValueTemplate=getTemplate(ExportDomainsFormAction.TEMPLATE_DOMAIN_VALUES);
			logger.debug("Template:\n"+domainValueTemplate);
			//Create domains file
			query=_session.createQuery("select domain from Domain domain where id.application.id=:applicationId");
			query.setString("applicationId",applicationId);
			domains=query.iterate();
			while(domains.hasNext()){
				if(domainContent!=null){
					domainContent.append(',');
					domainContent.append('\n');
				}else{
					domainContent=new StringBuffer();
				}
				domain=domains.next();
				domainContent.append(processDomain(domain));
				//Create normals file
				normals=domain.getNormals();
				if((normals!=null)&&(normals.size()>0)){
					normalContent=null;
					domainNormalCounter=0;
					for(DomainNorm normal:normals){
						domainNormalCounter++;
						if(normalContent!=null){
							normalContent.append(',');
							normalContent.append('\n');
						}else{
							normalContent=new StringBuffer();
						}
						normalContent.append(processDomainNormal(normal,domainNormalCounter));
					}
					normalContent.append(';');
					enumName="CADomain"+domain.getId().getCode()+"Norm";
					newFile=generateNewFile(domainNormalTemplate,enumName,normalContent);
					logger.debug("Final file ["+ExportDomainsFormAction.NORMALS_FILE_PATH+enumName+"]:\n"+newFile);
					reply.put(ExportDomainsFormAction.NORMALS_FILE_PATH+enumName+".java",newFile);
					//Create Clusters file
					clusters=domain.getClusters();
					if((clusters!=null)&&(clusters.size()>0)){
						clusterContent=null;
						for(DomainCluster cluster:clusters){
							if(clusterContent!=null){
								clusterContent.append(',');
								clusterContent.append('\n');
							}else{
								clusterContent=new StringBuffer();
							}
							clusterContent.append(processDomainCluster(cluster));
							//Create Values file
							values=cluster.getValues();
							if((values!=null)&&(values.size()>0)){
								valueContent=null;
								int ic1=0;
								for(DomainValue value:values){
									ic1++;
									if(valueContent!=null){
										valueContent.append(',');
										valueContent.append('\n');
									}else{
										valueContent=new StringBuffer();
									}
									valueContent.append(processDomainValue(value,ic1));
								}
								valueContent.append(';');
								enumName="CADomain"+domain.getId().getCode()+"Cluster"+cluster.getId().getCode()+"Value";
								newFile=generateNewFile(domainValueTemplate,enumName,valueContent);
								logger.debug("Final file ["+ExportDomainsFormAction.VALUES_FILE_PATH+enumName+"]:\n"+newFile);
								reply.put(ExportDomainsFormAction.VALUES_FILE_PATH+enumName+".java",newFile);
							}
						}
						clusterContent.append(';');
						enumName="CADomain"+domain.getId().getCode()+"Cluster";
						newFile=generateNewFile(domainClusterTemplate,enumName,clusterContent);
						logger.debug("Final file ["+ExportDomainsFormAction.CLUSTERS_FILE_PATH+enumName+"]:\n"+newFile);
						reply.put(ExportDomainsFormAction.CLUSTERS_FILE_PATH+enumName+".java",newFile);
					}
				}
			}
			if(domainContent!=null){
				domainContent.append(';');
				enumName="CADomain";
				newFile=generateNewFile(domainTemplate,enumName,domainContent);
				logger.debug("Final file ["+ExportDomainsFormAction.DOMAIN_FILE_PATH+enumName+"]:\n"+newFile);
				reply.put(ExportDomainsFormAction.DOMAIN_FILE_PATH+enumName+".java",newFile);
			}
		}catch (FPMException e) {
			throw e;
		}catch (Throwable e) {
			throw new FPMException(ErrorDict.DOMAIN_DOWNLOAD_ERROR,e);
		}
		
		return reply;
	}
}
