package com.tei.handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeLabelHandler implements ActionListener {
	private final LocalDateTime date;
	private final JLabel timeLabel;

	public TimeLabelHandler(LocalDateTime date, JLabel timeLabel) {
		this.date = date;
		this.timeLabel = timeLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LocalTime now = LocalTime.now();
		Duration durationDifference = Duration.between(date.toLocalTime(), now);
		long hours = durationDifference.toHours() % 24;
		long minutes = durationDifference.toMinutes() % 60;
		long seconds = durationDifference.getSeconds() % 60;
		String hourStr = formatTimeDigits(hours);
		String minStr = formatTimeDigits(minutes);
		String secStr = formatTimeDigits(seconds);
		timeLabel.setText(hourStr + ":" + minStr + ":" + secStr);
	}

	private String formatTimeDigits(long timeUnit) {
		return String.valueOf(timeUnit).length() > 1 ? timeUnit + "" : "0" + timeUnit;
	}
}
