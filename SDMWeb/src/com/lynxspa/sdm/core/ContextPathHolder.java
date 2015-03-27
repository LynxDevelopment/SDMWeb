package com.lynxspa.sdm.core;

public class ContextPathHolder {
	private static ContextPathHolder	instance;

	private String						contextPath;
	private String						absolutePath;
	private String						baseUrl;

	public static ContextPathHolder getInstance() {
		if (instance == null) {
			instance = new ContextPathHolder();
		}
		return instance;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath.replace('\\', '/');
	}

	public String getPath(String file) {
		return this.contextPath + file;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath.replace('\\', '/');
	}

	public String getAbsolutePath(String file) {
		return absolutePath + file;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getRemotePath(String file) {
		return this.baseUrl + file;
	}

}
