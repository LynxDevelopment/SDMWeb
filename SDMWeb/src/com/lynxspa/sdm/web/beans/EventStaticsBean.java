package com.lynxspa.sdm.web.beans;

import java.util.List;

import com.lynxspa.entities.securities.assets.Asset;

public class EventStaticsBean {
	private List<Asset> assetList;
	private int listSize;
	private int currentPage;
	private int  availablePages;
	private int resultsPerPage;
	
	public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getAvailablePages() {
		return availablePages;
	}

	public void setAvailablePages(int availablePages) {
		this.availablePages = availablePages;
	}

	public int getResultsPerPage() {
		return resultsPerPage;
	}

	public void setResultsPerPage(int resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}
	
	
	
}
