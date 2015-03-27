package com.lynxspa.sdm.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lynxspa.entities.jobs.SDMEnterprise;
import com.lynxspa.entities.jobs.SDMValue;
import com.lynxspa.entities.securities.assets.AssetType;
import com.lynxspa.entities.securities.assets.AssetTypeDetail;
import com.lynxspa.entities.securities.assets.providers.Provider;
import com.lynxspa.sdm.core.services.PersistenceService;
import com.lynxspa.sdm.entities.statics.configuration.StaticFieldConfig;
import com.lynxspa.sdm.entities.statics.configuration.StaticFieldMessageConfig;


@Controller
@RequestMapping("/admin/normalization/")
public class NormalizationAdminController {

	private final String NO_SELECT = "NONE";
	private final String NULL_ACTION = "";
	private final String SAVE_ACTION = "SAVE";
	private @Autowired
	PersistenceService		persistenceService;
	
	@RequestMapping("load.sdo")
	public String normalizationFiltersLoad(HttpServletRequest request, ModelMap model) {
		NormalizationBean normalBean = new NormalizationBean();
		
		model.put("filterNormalization",normalBean);
		return  "admin.normalization.load";
	}
	
	@RequestMapping("selectNormalizateProcessorConfigAction.sdo")
	public String normalizationConfiguration(HttpServletRequest request, ModelMap model) {
		System.out.println("Filtro enviado");
		return "";
	}
	
	@RequestMapping("filter.sdo")
	public String normalizationFiltersSubmit(@ModelAttribute("filterNormalization") NormalizationBean norm, HttpServletRequest request, ModelMap model) {
		System.out.println("Filtro enviado"+norm.getDetailSelected());
		
		norm.setlSDMValues(getStaticMessages(norm.getEnterprises(),norm.getProviders(),norm.getAssetTypes()));
		norm.setCurrentRow(1);
		System.out.println("currentStaticFieldRowId: "+norm.getCurrentStaticFieldRowId());
		if (norm.getVarAssetFieldConfig()==null){
			norm.setVarAssetFieldConfig(getEventFieldMessageConfig(norm.getProviders(),norm.getEnterprises(),norm.getAssetTypes()));
		}
		
		Iterator<Map<String,Object>> itte = norm.getVarAssetFieldConfig().iterator();
		int pos = 0;
		while(itte.hasNext()){
			if (pos==norm.getCurrentStaticFieldRowId()){
				System.out.println(pos);
				AssetTypeDetail detail = (AssetTypeDetail)itte.next().get("assetTypeDetail");
				String normalizationScript="";
				if (norm.getAction().equalsIgnoreCase(this.SAVE_ACTION)){
					norm.setAction(this.NULL_ACTION);
					normalizationScript = norm.getNormalizationScript();
					saveNormaizationScript(normalizationScript, detail, norm.getProviders(),norm.getEnterprises(),norm.getAssetTypes() );
				}else{
					normalizationScript=getNormalizationScriptForField(norm.getCurrentStaticFieldRowId(), detail, norm.getProviders(),norm.getEnterprises(),norm.getAssetTypes() );
				}
				norm.setDetailSelected(detail);
				norm.setNormalizationScript(normalizationScript);
				break;
			}
			itte.next();
			pos++;
		}
		norm.setNormalizationConfigured((norm.getVarAssetFieldConfig().size()>0));
		norm.setCurrentStaticFieldRow(norm.getCurrentStaticFieldRowId());
		
		model.put("filterNormalization",norm);
		
		return  "admin.normalization.load";
	}
	
	
	@RequestMapping("selectStaticFieldConfig.sdo")
	public String selectStaticFieldConfig(@ModelAttribute("filterNormalization") NormalizationBean norm, HttpServletRequest request, ModelMap model) {
		System.out.println("Seleccion enviada");
		
		System.out.println("val:"+norm.getSelectedNormalizationProcessorConfig());

		return  "admin.normalization.load";
	}
	
	@ModelAttribute("assetTypeList")
	public Map<String,String> populateAssetyTypeList() {
		Map<String,String> assetTypes = new LinkedHashMap<String,String>();
		List<AssetType> lAssetTypes = (List<AssetType>) persistenceService.executeQueryForList("from AssetType");

		for (AssetType assetType:lAssetTypes){
			assetTypes.put(assetType.getId(), assetType.getName());
		}
		
		return assetTypes;
	}
	
	@ModelAttribute("enterpriseList")
	public Map<Long,String> populateEnterpriseList() {
		Map<Long,String> enterprises = new LinkedHashMap<Long,String>();
		List<SDMEnterprise> lEnterprises = (List<SDMEnterprise>) persistenceService.executeQueryForList("from SDMEnterprise");

		for (SDMEnterprise enterprise:lEnterprises){
			enterprises.put(enterprise.getId(), enterprise.getName());
		}
		
		return enterprises;
	}
	
	@ModelAttribute("providerList")
	public Map<Long,String> populateProviderList() {
		Map<Long,String> providers = new LinkedHashMap<Long,String>();
		List<Provider> lProviders = (List<Provider>) persistenceService.executeQueryForList("from Provider");

		for (Provider provider:lProviders){
			providers.put(provider.getId(), provider.getName());
		}
		
		return providers;
	}
	
	
	private List<SDMValue> getStaticMessages (String enterpriseId, String providerId, String assetTypeId){
		List<SDMValue> reply= new ArrayList<SDMValue>();
		String query="from SDMValue as value where value.auditor.deleted=0 and value.row.operationStatus.state.id.code='PRSD' and value.row.class='security'";
		reply = (List<SDMValue>) persistenceService.executeQueryForList(query);

		return reply;
	}
	
	
	@SuppressWarnings("unchecked")
	private final List<Map<String,Object>> getEventFieldMessageConfig (String providerId, String enterpriseId, String assetTypeId) {
		System.out.println("Obteniendo datos...");
		List<Map<String,Object>> reply=null;
		Map<String,Object> replyMap=null;
		List<AssetTypeDetail> details=null;
		int numElements=2;
		reply=new ArrayList<Map<String,Object>>();
		String query = "from AssetTypeDetail as detail where detail.nature='S' and auditor.deleted=%s and detail.editableNormalization=%s";
		if (!assetTypeId.equals(NO_SELECT)){
			query += " and detail.assetType.id='%s'";
			numElements++;
		}
		if (!enterpriseId.equals(NO_SELECT)){
			query += " and detail.enterprise.id=%s";
			numElements++;
		}

		Object [] conditionValues = new Object[numElements];
		conditionValues[0]=false;
		conditionValues[1]=true;
		numElements=2;
		if (!assetTypeId.equals(NO_SELECT)){
			conditionValues[numElements]=assetTypeId;
			numElements++;
		}
		if (!enterpriseId.equals(NO_SELECT)){
			conditionValues[numElements]=enterpriseId;
			numElements++;
		}

		details = (List<AssetTypeDetail>)persistenceService.executeQueryForList(query, conditionValues);
		
		
		int pos=0;
		for(AssetTypeDetail detail:details){
			replyMap=new HashMap<String,Object>();
			replyMap.put("assetTypeDetail",detail);
			replyMap.put("position",pos);
					
			reply.add(replyMap);
			pos++;
		}
				
		return reply;
	}
	
	private String getNormalizationScriptForField (int currentStaticFieldRowId, AssetTypeDetail detail, String providerId , String enterpriseId, String assetTypeId){
		String reply="";
		Object [] conditionValues = null;
		String query = "";
		
		conditionValues = new Object[4];
		query="select staticFieldConfigSel.normalizationScript from StaticFieldMessageConfig as staticFieldConfigSel "+
				"where staticFieldConfig.assetTypeDetail.id=%s and staticFieldConfigSel.assetType.id='%s' "+
				"and staticFieldConfigSel.provider.id='%s' and staticFieldConfigSel.enterprise.id='%s'";
		
		conditionValues[0]=detail.getId();
		conditionValues[1]=assetTypeId;
		conditionValues[2]=providerId;
		conditionValues[3]=enterpriseId;
		
		reply=(String)persistenceService.executeQueryForObject(query, conditionValues);
		
		return reply;
	}
	
	private void saveNormaizationScript (String script, AssetTypeDetail detail, String providerId , String enterpriseId, String assetTypeId){
		Object [] conditionValues = null;
		
		String query="from StaticFieldMessageConfig as staticFieldConfigSel "+
				"where staticFieldConfig.assetTypeDetail.id=%s and staticFieldConfigSel.assetType.id='%s' "+
				"and staticFieldConfigSel.provider.id='%s' and staticFieldConfigSel.enterprise.id='%s'";
		
		conditionValues = new Object[4];
		conditionValues[0]=detail.getId();
		conditionValues[1]=assetTypeId;
		conditionValues[2]=providerId;
		conditionValues[3]=enterpriseId;
				
		StaticFieldMessageConfig sfmc = (StaticFieldMessageConfig)persistenceService.executeQueryForObject(query, conditionValues);
		if (sfmc !=null){
			sfmc.setNormalizationScript(script);
		}else{
			AssetType assetType =(AssetType)persistenceService.get(AssetType.class, assetTypeId);
			Provider provider =(Provider)persistenceService.get(Provider.class, Long.valueOf(providerId));
			SDMEnterprise enterprise =(SDMEnterprise)persistenceService.get(SDMEnterprise.class, Long.valueOf(enterpriseId));
			
			query="from StaticFieldConfig where assetTypeDetail.id='%s'";
			StaticFieldConfig sfc = (StaticFieldConfig)persistenceService.executeQueryForObject(query, detail.getId());
			if (sfc==null){
				sfc = new StaticFieldConfig("NORMALIZATIONCONFIGUSER", null, detail);
				persistenceService.save(sfc);
			}
			sfmc = new StaticFieldMessageConfig("NORMALIZATIONCONFIGUSER", sfc ,assetType, provider);
			sfmc.setNormalizationScript(script);
			sfmc.setEnterprise(enterprise);
			
		}
		persistenceService.save(sfmc);
	}
}