package com.lynxspa.sdm.core.managers.configuration.adapters;

public interface ConfigurationEntryAdapter {
	public String getKey();

	public <T extends Object> T getValue();

	public Class<?> getType();
}
