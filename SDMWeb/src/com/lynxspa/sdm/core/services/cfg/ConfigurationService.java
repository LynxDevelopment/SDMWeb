package com.lynxspa.sdm.core.services.cfg;

import java.util.Date;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lynxspa.entities.application.configurations.Config;
import com.lynxspa.sdm.core.services.PersistenceService;

@Service
@Scope("singleton")
public class ConfigurationService {
	@Autowired
	private PersistenceService	persistenceService;

	private String				APPLICATION_ID	= "MICROPORTAL";

	@Transactional
	public int getInteger(String name) {
		return getInternalConfiguration(APPLICATION_ID, name).getLongValue().intValue();
	}

	@Transactional
	public long getLong(String name) {
		return getInternalConfiguration(APPLICATION_ID, name).getLongValue();
	}

	@Transactional
	public String getString(String name) {
		return getInternalConfiguration(APPLICATION_ID, name).getVeryLongStringValue();
	}

	@Transactional
	public boolean getBoolean(String name) {
		return getInternalConfiguration(APPLICATION_ID, name).getBooleanValue();
	}

	@Transactional
	public Date getTimeStamp(String name) {
		return getInternalConfiguration(APPLICATION_ID, name).getTimestampValue();
	}

	@Transactional
	public void setInteger(String name, Integer value) {
		Config cfg = getInternalConfiguration(APPLICATION_ID, name);
		cfg.setLongValue(Long.valueOf(value));
		persistenceService.save(cfg);
	}

	@Transactional
	public void setLong(String name, Long value) {
		Config cfg = getInternalConfiguration(APPLICATION_ID, name);
		cfg.setLongValue(value);
		persistenceService.save(cfg);
	}

	@Transactional
	public void setString(String name, String value) {
		Config cfg = getInternalConfiguration(APPLICATION_ID, name);
		cfg.setVeryLongStringValue(value);
		persistenceService.save(cfg);
	}

	@Transactional
	public void setBoolean(String name, Boolean value) {
		Config cfg = getInternalConfiguration(APPLICATION_ID, name);
		cfg.setBooleanValue(value);
		persistenceService.save(cfg);
	}

	@Transactional
	public void setTimestamp(String name, Date value) {
		Config cfg = getInternalConfiguration(APPLICATION_ID, name);
		cfg.setTimestampValue(value);
		persistenceService.save(cfg);
	}

	public Config getInternalConfiguration(String app, String name) {
		Query query = persistenceService.createQuery("from Config where id.application=:app and id.code=:code");
		query.setString("app", app);
		query.setString("code", name);

		Config cfg = (Config) query.uniqueResult();
		if (cfg == null) {
			throw new IllegalArgumentException("Configuration " + name + " for application " + app + " does not exists");
		}
		return cfg;
	}

}
