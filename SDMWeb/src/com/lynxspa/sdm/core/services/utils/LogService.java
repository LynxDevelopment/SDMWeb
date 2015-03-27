package com.lynxspa.sdm.core.services.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lynxspa.entities.InsertAuditor;
import com.lynxspa.entities.application.logs.Log;
import com.lynxspa.entities.application.logs.LogOperation;
import com.lynxspa.entities.application.logs.utils.LogLevel;
import com.lynxspa.sdm.core.services.PersistenceService;
import com.lynxspa.sdm.core.utils.TimeUtils;

@Service
public class LogService {
	private @Autowired
	PersistenceService	persistenceService;

	/**
	 * Registra un mensaje de log en BBDD.
	 * 
	 * @param user
	 * @param message
	 */
	@Transactional
	public void log(String user, String message) {
		Log log = new LogOperation();
		log.setApplicationId("MICROPORTAL");
		log.setLevel(LogLevel.AUDIT.getLevel());
		log.setMessage(message);
		log.setType("TRACE");
		log.setAuditor(new InsertAuditor(user, TimeUtils.Now()));

		persistenceService.save(log);
	}
}
