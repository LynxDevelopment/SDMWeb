package com.lynxspa.sdm.core.model.tasks;

import java.text.ParseException;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.quartz.CronExpression;

import com.lynxspa.entities.application.flow.operations.OperableAdapter;
import com.lynxspa.sdm.core.utils.CronSummary;

@Entity
@DiscriminatorValue("U")
public class UserTask extends SystemTask implements OperableAdapter {

	private static final long	serialVersionUID	= -1215431536889267588L;

	private String				cronExpression		= null;
	private String				planningMode		= null;

	public UserTask() {
		super(null);
	}

	public UserTask(long _task) {
		super(_task);
	}

	public UserTask(String user) {
		super(user);
	}

	@Column(name = "CRONEXPRESSION", nullable = true)
	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	@Column(name = "PLANNING_MODE", nullable = true)
	public String getPlanningMode() {
		return planningMode;
	}

	public void setPlanningMode(String planningMode) {
		this.planningMode = planningMode;
	}

	@SuppressWarnings("null")
	@Transient
	public CronSummary getCronSummary() throws ParseException {

		CronSummary cronSummary = null;
		CronExpression cron = null;
		String cronExpressionParameter = getCronExpression();

		if (cronExpressionParameter != null || !"".equals(cronExpressionParameter.trim())) {
			cronSummary = new CronSummary();
			cron = new CronExpression(cronExpressionParameter);

			String[] summaryData = cron.getExpressionSummary().split("\n"); // Split all summary data...

			String[] minutes = summaryData[1].substring(summaryData[1].indexOf(" ")).split(","); // Remove initial space and split elements...
			String[] hours = summaryData[2].substring(summaryData[2].indexOf(" ")).split(",");
			String[] daysOfMonth = summaryData[3].substring(summaryData[3].indexOf(" ")).split(",");
			String[] months = summaryData[4].substring(summaryData[4].indexOf(" ")).split(",");
			String[] daysOfWeek = summaryData[5].substring(summaryData[5].indexOf(" ")).split(",");
			String lastDayOfMonth = summaryData[9].substring(summaryData[9].indexOf(" "));

			// minutes
			cronSummary.getMinuteMap().put(minutes[0].trim(), minutes[0].trim());
			// hours
			cronSummary.getHourMap().put(hours[0].trim(), hours[0].trim());
			// repeat minutes
			if (minutes.length > 1) {
				cronSummary.setRepeatMinutes(String.valueOf(Integer.parseInt(minutes[1].trim()) - Integer.parseInt(minutes[0].trim())));
			}
			// stop hour
			if (hours.length > 1) {
				cronSummary.setStopHour(String.valueOf(Integer.parseInt(hours[hours.length - 1].trim()) + 1));
			} else if ((hours.length == 1) && (cronSummary.getRepeatMinutes() != null) && (!"0".equals(cronSummary.getRepeatMinutes()))) {
				cronSummary.setStopHour(String.valueOf(Integer.parseInt(hours[0].trim()) + 1));
			}
			// dayOfMonth
			for (String dayOfMonth : daysOfMonth) {
				cronSummary.getDayOfMonthMap().put(dayOfMonth.trim(), dayOfMonth.trim());
			}
			// month
			for (String month : months) {
				cronSummary.getMonthMap().put(month.trim(), month.trim());
			}

			// dayOfWeek
			for (String dayOfWeek : daysOfWeek) {
				cronSummary.getDayOfWeekMap().put(dayOfWeek.trim(), dayOfWeek.trim());
			}
			// lastDayOfMonth

			boolean isLastDayOfMonth = Boolean.parseBoolean(lastDayOfMonth.trim());
			if (isLastDayOfMonth) {
				cronSummary.getDayOfMonthMap().put("L", "L");
			}
		}

		return cronSummary;
	}
}
