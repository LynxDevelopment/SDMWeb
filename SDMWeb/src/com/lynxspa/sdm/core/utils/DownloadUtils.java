package com.lynxspa.sdm.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

public final class DownloadUtils {
	private DownloadUtils() {}
	
	private static Logger	logger	= Logger.getLogger(DownloadUtils.class);

	/**
	 * Descarga un fichero que se encuentra en una URL remota y lo guarda
	 * en un fichero.
	 * 
	 * @param urlOrigin La URL externa
	 * @param pathToSave El objeto File donde se va a guardar
	 * @return true si se ha podido descargar
	 */
	public static boolean downloadUrlFile(String urlOrigin, File pathToSave) {
		try {
			URL url = new URL(URIUtil.encodeQuery(urlOrigin));
			FileUtils.copyURLToFile(url, pathToSave);
			return true;
		} catch (MalformedURLException e) {
			logger.warn("[MALFORMED] URL: '" + urlOrigin + "' => " + e.getMessage());
		} catch (FileNotFoundException e) {
			logger.warn("[HTTP  404] URL: '" + urlOrigin + "' => " + e.getMessage());
		} catch (Throwable e) {
			logger.warn("[  OTHER  ] URL: '" + urlOrigin + "' =>", e);
		}
		return false;
	}

	/**
	 * Devuelve el nombre de un fichero en una url convertida a String
	 * @param url
	 * @return El nombre del fichero con la extensión
	 */
	public static String getFileNameInUrl(String url) {
		String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
		String fileNameNormalize = NormalizationUtils.normalizeFileName(fileName);
		return fileNameNormalize;
	}

	/**
	 * Devuelve un nombre único para un fichero
	 * @param filename Nombre a tomar de base
	 */
	public static String uidFor(String filename) {
		return TimeUtils.Now().getTime() + "." + FilenameUtils.getExtension(filename);
	}
	
	/**
	 * Devuelve un identificador único para un fichero cualquiera
	 */
	public static String uid() {
		return String.valueOf(TimeUtils.Now().getTime());
	}
}
