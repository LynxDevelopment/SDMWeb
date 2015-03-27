package com.lynxspa.sdm.core.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lynxspa.sdm.core.ContextPathHolder;
import com.lynxspa.sdm.core.managers.configuration.ConfigurationDictionary;
import com.lynxspa.sdm.core.managers.configuration.adapters.ConfigurationManagerAdapter;
import com.lynxspa.sdm.core.utils.DownloadUtils;

@Service
public class FileStateService {
	private static Logger					logger	= Logger.getLogger(FileStateService.class);

	private Map<String, Map<String, File>>	files;

	private @Autowired
	ConfigurationManagerAdapter				xwebCfgService;

	
	private FileStateService() {
		this.files = new TreeMap<String, Map<String, File>>();
	}

	/**
	 * Crea una sesi�n de ficheros para una sesi�n cualquiera.
	 * 
	 * @param sessionId
	 */
	public void createSession(String sessionId) {
		File directory = new File(getTemporaryDirectory(), sessionId);
		try {
			FileUtils.forceMkdir(directory);
		} catch (IOException ex) {
			logger.fatal(directory.getAbsolutePath() + " is not a valid directory to create.", ex);
			throw new IllegalArgumentException(ex);
		}
		if (files.get(sessionId) == null) {
			files.put(sessionId, new TreeMap<String, File>());
		}
	}

	/**
	 * Limpia una sesi�n y borra los ficheros asociados a ella.
	 * 
	 * @param sessionId
	 */
	public void clearSession(String sessionId) {
		File directory = new File(getTemporaryDirectory(), sessionId);
		try {
			FileUtils.deleteDirectory(directory);
		} catch (IOException ex) {
			logger.fatal(directory.getAbsolutePath() + " is not a valid directory to delete.", ex);
			throw new IllegalArgumentException(ex);
		}
		files.remove(sessionId);
		
	}

	/**
	 * Sube un fichero a la sesi�n de ficheros.
	 * 
	 * @param sessionId La sesi�n
	 * @param fileName El nombre del fichero en la sesi�n, si ya existe se sobreescribe
	 * @param realFileName El nombre real del fichero subido, con su extensi�n
	 * @param data El contenido del fichero
	 */
	public void uploadFile(String sessionId, String fileName, String realFileName, byte[] data) {
		File f = getFile(sessionId, fileName);
		if (f != null) {
			f.delete();
		}
		String finalFilename = DownloadUtils.uidFor(realFileName);
		File finalFile = new File(new File(getTemporaryDirectory(), sessionId), finalFilename);
		try {
			FileUtils.writeByteArrayToFile(finalFile, data);
		} catch (IOException ex) {
			logger.fatal("Could not save " + finalFile.getAbsolutePath(), ex);
			throw new IllegalArgumentException(ex);
		}
		
		files.get(sessionId).put(fileName, finalFile);
	}
	
	/**
	 * Guarda el fichero en una ruta del servidor y elimina la referencia temporal
	 * 
	 * @param sessionId La sesi�n del usuario
	 * @param filename El fichero en la sesi�n
	 * @param path Ruta en un objeto File donde se comitar�.
	 */
	public void commitFileTo(String sessionId, String filename, File path) {
		File file = getFile(sessionId, filename);
		files.get(sessionId).remove(filename);
		if (path.isDirectory()) {
			file.renameTo(new File(path, file.getName()));
		} else {
			file.renameTo(path);
		}
	}
	
	/**
	 * Devuelve una URL p�blica al fichero de la sesi�n con el nombre especificado.
	 * 
	 * @param sessionId
	 * @param filename El nombre del fichero en sesi�n
	 * @return La URL p�blica del ficheor
	 */
	public URL getUrlOfFile(String sessionId, String filename) {
		try {
			return new URL(ContextPathHolder.getInstance().getRemotePath(xwebCfgService.getConfiguration(ConfigurationDictionary.CONTEXT_TEMPORAL_DIRECTORY).toString()) + sessionId + "/" + getFile(sessionId, filename).getName());
		} catch (MalformedURLException ex) {
			throw new IllegalArgumentException("Builded URL: " + ContextPathHolder.getInstance().getRemotePath(xwebCfgService.getConfiguration(ConfigurationDictionary.CONTEXT_TEMPORAL_DIRECTORY).toString()) + "/" + sessionId + "/" + getFile(sessionId, filename).getName(), ex);
		}
	}

	/**
	 * Devuelve una referencia al fichero de sesi�n especificado
	 * 
	 * @param sessionId
	 * @param fileId
	 * @return
	 */
	public File getFile(String sessionId, String fileId) {
		return files.get(sessionId).get(fileId);
	}

	/**
	 * Limpia todas las sesiones y ficheros
	 */
	public void clearAll() {
		for (String sessionId : this.files.keySet()) {
			clearSession(sessionId);
		}
	}
	
	public File getTemporaryDirectory() {
		return new File(ContextPathHolder.getInstance().getAbsolutePath(), xwebCfgService.getConfiguration(ConfigurationDictionary.CONTEXT_TEMPORAL_DIRECTORY).toString());
	}
}
