package com.salesforce.systesting.netload;

import com.salesforce.systesting.netload.health.DummyHealthCheck;
import com.salesforce.systesting.netload.resources.SimpleDiffResource;
import com.salesforce.systesting.netload.resources.SimpleLoadResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;

public class LoadService extends Service<LoadConfiguration> {

	public static void main(String[] argv) throws Exception {
		new LoadService().run(argv);
	}

	protected LoadService() {
		super("Ye olde load service");
	}

	@Override
	protected void initialize(LoadConfiguration config, Environment environment)
			throws Exception {
		environment.addResource(new SimpleLoadResource(config.getDefaultSleep()));
		environment.addResource(new SimpleDiffResource());
		environment.addHealthCheck(new DummyHealthCheck());
	}

}
