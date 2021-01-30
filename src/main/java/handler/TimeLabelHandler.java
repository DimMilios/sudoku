package handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class TimeLabelHandler implements ActionListener {
	private LocalDateTime date;
	private JLabel timeLabel;

	public TimeLabelHandler(LocalDateTime date, JLabel timeLabel) {
		this.date = date;
		this.timeLabel = timeLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		Date now = new Date();
//		long diff = now.getTime() - date.getTime();
//		int minutes = new Date(diff).getMinutes();
//		int seconds = new Date(diff).getSeconds();
		LocalTime now = LocalTime.now();
		Duration durationDifference = Duration.between(date.toLocalTime(), now);
		long hours = durationDifference.toHours();
		long minutes = durationDifference.toMinutes();
		long seconds = durationDifference.getSeconds();
		String hourStr = formatTimeDigits(hours);
		String minStr = formatTimeDigits(minutes);
		String secStr = formatTimeDigits(seconds);
		timeLabel.setText(hourStr + ":" + minStr + ":" + secStr);
	}

	private String formatTimeDigits(long timeUnit) {
		return String.valueOf(timeUnit).length() > 1 ? timeUnit + "" : "0" + timeUnit;
	}
}
