package com.lynxspa.sdm.core.services.cfg;

import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lynxspa.sdm.core.managers.configuration.AbstractConfigurationManager;
import com.lynxspa.sdm.core.managers.configuration.ConfigurationDictionary;
import com.lynxspa.sdm.core.managers.configuration.adapters.ConfigurationEntryAdapter;

@Service
@Scope("singleton")
public class XWebConfigurationService extends AbstractConfigurationManager {
	@Autowired
	private ConfigurationService	configurationService;

	private static Logger			logger	= Logger.getLogger(XWebConfigurationService.class);

	@Override
	@Transactional(readOnly = true)
	public Object getConfiguration(ConfigurationEntryAdapter adapter) {
		try {
			Object value = null;
			if (adapter.getType().isAssignableFrom(Integer.class)) {
				value = configurationService.getInteger(adapter.getKey());
			} else if (adapter.getType().isAssignableFrom(String.class)) {
				value = configurationService.getString(adapter.getKey());
			}
			if (adapter.getType().isAssignableFrom(Date.class)) {
				value = configurationService.getTimeStamp(adapter.getKey());
			}
			if (value == null) {
				return super.getConfiguration(adapter);
			}
			return ConfigurationDictionary.castValue(adapter, value);
		} catch (Throwable ex) {
			logger.error("Unable to retrieve configuration for key: " + adapter.getKey() + ". Message: " + ex.getMessage(), ex);
			return super.getConfiguration(adapter);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public String getCustomConfiguration(String keyEntry) {
		String value = configurationService.getString(keyEntry);
		if (value == null) {
			value = super.getCustomConfiguration(keyEntry);
		}
		return value;
	}

	@Override
	@Transactional
	public void setConfiguration(ConfigurationEntryAdapter adapter, Object value) {
		if (adapter.getType().isAssignableFrom(Integer.class)) {
			configurationService.setInteger(adapter.getKey(), (Integer) value);
		} else if (adapter.getType().isAssignableFrom(String.class)) {
			configurationService.setString(adapter.getKey(), value.toString());
		}
		if (adapter.getType().isAssignableFrom(Date.class)) {
			configurationService.setTimestamp(adapter.getKey(), (Date) value);
		} else {
			throw new IllegalArgumentException("Unknown type " + adapter.getClass() + " in configuration with key " + adapter.getKey());
		}
	}

	@Override
	@Transactional
	public void setCustomConfiguration(String key, String value) {
		configurationService.setString(key, value);
	}

	@Transactional(readOnly = true)
	public Locale getApplicationLocale() {
		return new Locale(getConfiguration(ConfigurationDictionary.APPLICATION_LOCALE).toString());
	}

	@Override
	public boolean load() { // ignorar, está en BBDD
		return true;
	}

	@Override
	public boolean save() { // ignorar, está en BBDD
		return true;
	}
}
