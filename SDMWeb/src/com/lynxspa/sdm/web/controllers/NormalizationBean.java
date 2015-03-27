package com.lynxspa.sdm.web.controllers;

import java.util.List;
import java.util.Map;

import com.lynxspa.entities.jobs.SDMValue;
import com.lynxspa.entities.securities.assets.AssetTypeDetail;
import com.lynxspa.sdm.interfaces.FiltrableAdapter;

public class NormalizationBean {
	private String selectedNormalizationProcessorConfig;
	private Map <String, List<FiltrableAdapter>> mapFilters;
	private String assetTypes;
	private String enterprises;
	private String providers;
	private boolean normalizationConfigured;
	private List<SDMValue> lSDMValues;
	private int currentRow;
	private int currentStaticFieldRow;
	private int currentStaticFieldRowId;
	private List<Map<String,Object>> varAssetFieldConfig;
	private AssetTypeDetail detailSelected;
	private String normalizationScript;
	private String action;
	
	public String getSelectedNormalizationProcessorConfig() {
		System.out.println("getSelectedNormalizationProcessorConfig:"+selectedNormalizationProcessorConfig);
		return selectedNormalizationProcessorConfig;
	}

	public void setSelectedNormalizationProcessorConfig(String selectedNormalizationProcessorConfig) {
		System.out.println("setSelectedNormalizationProcessorConfig:"+selectedNormalizationProcessorConfig);
		this.selectedNormalizationProcessorConfig = selectedNormalizationProcessorConfig;
	}

	public Map<String, List<FiltrableAdapter>> getMapFilters() {
		return mapFilters;
	}

	public void setMapFilters(Map<String, List<FiltrableAdapter>> mapFilters) {
		this.mapFilters = mapFilters;
	}

	public String getAssetTypes() {
		return assetTypes;
	}

	public void setAssetTypes(String assetTypes) {
		this.assetTypes = assetTypes;
	}

	public String getEnterprises() {
		return enterprises;
	}

	public void setEnterprises(String enterprises) {
		this.enterprises = enterprises;
	}

	public String getProviders() {
		return providers;
	}

	public void setProviders(String providers) {
		this.providers = providers;
	}

	public boolean isNormalizationConfigured() {
		return normalizationConfigured;
	}

	public void setNormalizationConfigured(boolean normalizationConfigured) {
		this.normalizationConfigured = normalizationConfigured;
	}

	public List<SDMValue> getlSDMValues() {
		return lSDMValues;
	}

	public void setlSDMValues(List<SDMValue> lSDMValues) {
		this.lSDMValues = lSDMValues;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public List<Map<String, Object>> getVarAssetFieldConfig() {
		return varAssetFieldConfig;
	}

	public void setVarAssetFieldConfig(List<Map<String, Object>> varAssetFieldConfig) {
		this.varAssetFieldConfig = varAssetFieldConfig;
	}

	public int getCurrentStaticFieldRow() {
		return currentStaticFieldRow;
	}

	public void setCurrentStaticFieldRow(int currentStaticFieldRow) {
		this.currentStaticFieldRow = currentStaticFieldRow;
	}

	public int getCurrentStaticFieldRowId() {
		return currentStaticFieldRowId;
	}
	public void setCurrentStaticFieldRowId(int currentStaticFieldRowId) {
		this.currentStaticFieldRowId = currentStaticFieldRowId;
	}

	public AssetTypeDetail getDetailSelected() {
		return detailSelected;
	}

	public void setDetailSelected(AssetTypeDetail detailSelected) {
		this.detailSelected = detailSelected;
	}

	public String getNormalizationScript() {
		return normalizationScript;
	}

	public void setNormalizationScript(String normalizationScript) {
		this.normalizationScript = normalizationScript;
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
