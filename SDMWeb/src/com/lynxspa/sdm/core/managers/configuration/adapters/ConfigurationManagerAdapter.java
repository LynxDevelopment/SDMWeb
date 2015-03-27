package com.lynxspa.sdm.core.managers.configuration.adapters;

import java.util.Locale;

public interface ConfigurationManagerAdapter {
	public Object getConfiguration(final ConfigurationEntryAdapter entry);

	public void setConfiguration(final ConfigurationEntryAdapter entry, Object value);

	public boolean load();

	public boolean save();

	public Locale getApplicationLocale();
}
