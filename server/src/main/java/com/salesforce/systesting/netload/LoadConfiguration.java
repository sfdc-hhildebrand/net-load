package com.salesforce.systesting.netload;

import com.yammer.dropwizard.config.Configuration;

public class LoadConfiguration extends Configuration {
	private int defaultSleep;

	public int getDefaultSleep() {
		return defaultSleep;
	}
}
