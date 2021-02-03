package com.tei.model;

import java.time.LocalDateTime;

public class StartDateTimeSingleton {

	private LocalDateTime startTime;
	private static StartDateTimeSingleton instance = null;

	private StartDateTimeSingleton() {}

	public static StartDateTimeSingleton getInstance() {
		if (instance == null) {
			instance = new StartDateTimeSingleton();
		}
		return instance;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}
}
