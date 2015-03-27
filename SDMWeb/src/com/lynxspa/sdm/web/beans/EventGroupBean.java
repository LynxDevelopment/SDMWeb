package com.lynxspa.sdm.web.beans;

import java.util.List;

import com.lynxspa.entities.securities.assets.SecurityAsset;

public class EventGroupBean {

	private List<SecurityAsset> securityAssetList;
	private int listSize;
	private int currentPage;
	private int  availablePages;
	private int resultsPerPage;

	public List<SecurityAsset> getSecurityAssetList() {
		return securityAssetList;
	}

	public void setSecurityAssetList(List<SecurityAsset> securityAssetList) {
		this.securityAssetList = securityAssetList;
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

