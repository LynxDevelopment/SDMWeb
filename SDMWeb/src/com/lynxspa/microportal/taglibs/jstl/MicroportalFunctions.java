package com.lynxspa.microportal.taglibs.jstl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import com.lynxit.xweb.i18n.I18nSystem;
import com.lynxit.xweb.initialization.initializers.DataSourcesManager;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.microportal.MicroportalManager;
import com.lynxspa.microportal.configuration.MicroportalConfig;
import com.lynxspa.microportal.utils.MicroportalUtils;

public class MicroportalFunctions {

	public static final Object getConfigurationValue(String _configValue, String _dataSource) throws Exception {
		return MicroportalUtils.getConfigValue(_configValue, DataSourcesManager.getLocalInstance().getHibernateSession(_dataSource));
	}

	public static final String getDefaultLanguage(String _dataSource) throws Exception {
		return (String) MicroportalManager.getInstance().getConfiguration(DataSourcesManager.getLocalInstance().getHibernateSession(_dataSource), MicroportalConfig.DEFAULTLANGUAGE);
	}

	public static final String getUploadFilePath(String _dataSource) throws Exception {
		return (String) MicroportalManager.getInstance().getConfiguration(DataSourcesManager.getLocalInstance().getHibernateSession(_dataSource), MicroportalConfig.UPLOADFILESPATH);
	}

	public static final String[] getConfiguredLanguages(String _dataSource) throws Exception {
		return ((String) MicroportalManager.getInstance().getConfiguration(DataSourcesManager.getLocalInstance().getHibernateSession(_dataSource), MicroportalConfig.CONFIGUREDLANGUAGES)).split(",");
	}

	public static final String getLocalizedKey(String _bundle, String _localeISO, String _key) throws Exception {
		return I18nSystem.getInstance().getBundle(_bundle, new Locale(_localeISO)).getString(_key);
	}

	public static final String clearAndDecodeEmbededUrl(String _url) throws Exception {

		String reply = null;

		reply = _url.substring("/fpm/microportal/common/link_display.xwb?title=".length());
		reply = reply.substring(reply.indexOf('=') + 1);
		reply = URLDecoder.decode(reply, "UTF-8");

		return reply;
	}

	public static final int getLevelMenu(final String _pathMenu) {
		return _pathMenu.replaceAll("[^/]", "").length();
	}

	public static final String[] getBoxletTemplates(final String _basePath) throws Exception {
		return findFiles(_basePath, "/fpm/microportal/common/templates", new String[] { "*.xwb" });
	}

	public static final File getFile(final String _basePath, final String _relativePath) throws Exception {
		return new File(_basePath + _relativePath);
	}

	public static final String getFileName(final String _filePath) throws Exception {
		final File file = new File(_filePath);
		return (file.exists()) ? (new File(_filePath)).getName() : _filePath;
	}

	public static final boolean existFile(final String _basePath, final String _relativePath) throws Exception {
		return getFile(_basePath, _relativePath).exists();
	}

	public static final String[] findFiles(final String _basePath, final String _relativePath, final String[] _fileFilter) throws Exception {
		return findFiles(_basePath, _relativePath, _fileFilter, null);
	}

	public static final String[] findFiles(final String _basePath, final String _relativePath, final String[] _fileFilter, final String _filterFiles) throws Exception {
		String[] reply = null;
		final File dir = new File(_basePath + _relativePath);
		reply = dir.list(new WildcardFileFilter(_fileFilter));
		if (reply != null) {
			if ((reply.length > 0) && (_filterFiles != null) && (_filterFiles.length() > 0)) {
				final List<String> newFileList = new ArrayList<String>(reply.length);
				final List<String> filteredNames = Arrays.asList(_filterFiles.split(","));
				for (String fileName : reply) {
					if (!filteredNames.contains(fileName)) {
						newFileList.add(fileName);
					}
				}
				reply = newFileList.toArray(new String[] {});
			}
			Arrays.sort(reply);
		}

		return reply;
	}

	public static final String[] findDirectories(final String _basePath, final String _relativePath) throws Exception {

		return findDirectories(_basePath, _relativePath, null);
	}

	public static final String[] findDirectories(final String _basePath, final String _relativePath, final String _filterDirectories) throws Exception {

		String[] reply = null;

		final File dir = new File(_basePath + _relativePath);
		reply = dir.list(DirectoryFileFilter.DIRECTORY);
		if (reply != null) {
			if ((reply.length > 0) && (_filterDirectories != null) && (_filterDirectories.length() > 0)) {
				final List<String> newDirectoryList = new ArrayList<String>(reply.length);
				final List<String> filteredNames = Arrays.asList(_filterDirectories.split(","));
				for (String fileName : reply) {
					if (!filteredNames.contains(fileName)) {
						newDirectoryList.add(fileName);
					}
				}
				reply = newDirectoryList.toArray(new String[] {});
			}
			Arrays.sort(reply);
		}

		return reply;
	}

	public static final Properties getProperties(final String _basePath, final String _relativePath) throws FPMException {

		final Properties reply = new Properties();

		try {
			final File propertiesFile = new File(_basePath + _relativePath);
			if (!propertiesFile.exists())
				throw new FPMException(BasicErrorDict.FILENOTEXIST, new Object[] { _basePath + _relativePath });
			final FileInputStream inputStream = new FileInputStream(propertiesFile);
			reply.load(inputStream);
			inputStream.close();
		} catch (FileNotFoundException e) {
			throw new FPMException(BasicErrorDict.FILENOTEXIST, new Object[] { _basePath + _relativePath }, e);
		} catch (IOException e) {
			throw new FPMException(BasicErrorDict.FILECANTREAD, new Object[] { _basePath + _relativePath }, e);
		}

		return reply;
	}

	public static final boolean existsFile(String _basePath, String _relativePath) {
		final File file = new File(_basePath + _relativePath);
		return file.exists();
	}
}
