package com.lynxspa.sdm.core.managers.configuration;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lynxspa.sdm.core.managers.configuration.adapters.ConfigurationEntryAdapter;
import com.lynxspa.sdm.core.managers.configuration.adapters.ConfigurationManagerAdapter;

@Service
public abstract class AbstractConfigurationManager implements ConfigurationManagerAdapter {
	protected Map<String, Object>	cache	= new HashMap<String, Object>();

	@Override
	public Object getConfiguration(ConfigurationEntryAdapter entry) {
		Object result = cache.get(entry.getKey());
		if (result == null) {
			result = entry.getValue();
		}
		try {
			return ConfigurationDictionary.castValue(entry, result.toString());
		} catch (ParseException ex) {
			throw new IllegalArgumentException("Could not parse configuration: " + entry.getKey() + " ; value: " + entry.getValue().toString());
		}
	}

	public String getCustomConfiguration(String keyEntry) {
		Object result = cache.get(keyEntry);
		if (result == null) {
			return null;
		}
		return result.toString();
	}

	@Override
	public void setConfiguration(ConfigurationEntryAdapter entry, Object value) {
		cache.put(entry.getKey(), value);
	}

	public void setCustomConfiguration(String key, String value) {
		cache.put(key, value);
	}

	public Locale getApplicationLocale() {
		return new Locale("ca");
	}
}
