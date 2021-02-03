package com.tei.log;

import java.util.logging.*;

public class StdOutHandler extends StreamHandler {

	public StdOutHandler() {
		super(System.out, new SimpleFormatter());
	}

	@Override
	public void publish(LogRecord record) {
		super.publish(record);
		super.flush();
	}
}
