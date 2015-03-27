package com.lynxspa.sdm.core.services.quartz.jobs.maintenance;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.Logger;

import com.lynxspa.sdm.core.services.quartz.api.SdmBasicJob;
import com.lynxspa.sdm.core.services.quartz.api.PropertyBean;
import com.lynxspa.sdm.core.services.quartz.api.TaskExecutionBean;
import com.lynxspa.sdm.core.services.quartz.api.exceptions.ExecutionException;
import com.lynxspa.utils.UtilDate;
import com.lynxspa.validation.utils.ValidationsDict;

public class RemoveOldFilesJob extends SdmBasicJob {
	private static final Logger			logger				= Logger.getLogger(RemoveOldFilesJob.class);

	private static final PropertyBean	LISTFOLDER			= new PropertyBean("listFolder", ValidationsDict.ISNOTEMPTY);
	private static final PropertyBean	BEFOREDAYSPERIOD	= new PropertyBean("beforeDaysPeriod", ValidationsDict.ISNOTEMPTY, ValidationsDict.ISINTEGER);
	private static final PropertyBean	BEFOREMONTHSPERIOD	= new PropertyBean("beforeMonthsPeriod", ValidationsDict.ISNOTEMPTY, ValidationsDict.ISINTEGER);
	private static final PropertyBean	BEFOREYEARSPERIOD	= new PropertyBean("beforeYearsPeriod", ValidationsDict.ISNOTEMPTY, ValidationsDict.ISINTEGER);
	private static final PropertyBean	PATTERNFILTER		= new PropertyBean("patternFilter");

	private static final String			LISTSEPARATOR		= ",";

	/**
	 * Init TaskExecutionBean
	 */
	private void initializeExecutionVariables(TaskExecutionBean state) {
		for (PropertyBean p : getPropertyBeans()) {
			state.setExecutionVariable(p, state.getProperty(p));
		}
	}

	@Override
	protected void executeJob(TaskExecutionBean _execution) throws ExecutionException {
		try {
			// Init execution properties
			initializeExecutionVariables(_execution);

			// Get setup variables
			String listFolder = (String) _execution.getExecutionVariable(LISTFOLDER);
			int beforeDays = Integer.parseInt((String) _execution.getExecutionVariable(BEFOREDAYSPERIOD));
			int beforeMonths = Integer.parseInt((String) _execution.getExecutionVariable(BEFOREMONTHSPERIOD));
			int beforeYears = Integer.parseInt((String) _execution.getExecutionVariable(BEFOREYEARSPERIOD));
			String patternFilters = (String) _execution.getExecutionVariable(PATTERNFILTER);

			// Get folder list
			String[] patternFilter = patternFilters.split(LISTSEPARATOR);
			String[] folders = listFolder.split(LISTSEPARATOR);
			_execution.setTotalReferenceProgress(Long.valueOf(String.valueOf(folders.length)));
			for (String folderPath : folders) {
				_execution.setActualReferenceProgress(_execution.getActualReferenceProgress() + 1l);
				// Get candidates files to remove.
				Collection<File> listFiles = com.lynxspa.utils.FileUtils.searchFile(new File(folderPath), new WildcardFileFilter(patternFilter), false, false);
				for (File candidateFile : listFiles) {
					Date now = UtilDate.getMidnightDateObject(Calendar.getInstance().getTime());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(now);
					calendar.add(Calendar.DAY_OF_MONTH, (-1) * beforeDays);
					calendar.add(Calendar.MONTH, (-1) * beforeMonths);
					calendar.add(Calendar.YEAR, (-1) * beforeYears);
					Date controlDate = calendar.getTime();
					Date candidateFileDate = UtilDate.getMidnightDateObject(new Date(candidateFile.lastModified()));
					// We remove old files with datetime before controlDate that we make with offset days.
					if (candidateFileDate.before(controlDate)) {
						try {
							FileUtils.forceDelete(candidateFile);
						} catch (IOException e) {
							// We do not must be throw this Exception !!!!
							logger.error(new StringBuilder("Error deleting file :").append(candidateFile.getAbsolutePath()).append(" . Error message:").append(e.getMessage()).toString(), e);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}

	public static PropertyBean[] getPropertyBeans() {
		return new PropertyBean[] { LISTFOLDER, BEFOREDAYSPERIOD, BEFOREMONTHSPERIOD, BEFOREYEARSPERIOD, PATTERNFILTER };
	}
}
