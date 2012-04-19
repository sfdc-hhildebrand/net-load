package com.salesforce.systesting.netload.health;

import com.yammer.metrics.core.HealthCheck;

// Only exists to keep dropwizard from complaining about health checks.

public class DummyHealthCheck extends HealthCheck {

	public DummyHealthCheck() {
		super("Dummy Health Check");
	}

	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}

}
