package com.salesforce.systesting.netload.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

@Path("/simple-load")
@Produces(MediaType.TEXT_PLAIN)
public class SimpleLoadResource {
	private static final Logger log = LoggerFactory.getLogger(SimpleLoadResource.class);
	private final Integer defaultSleep;

	public SimpleLoadResource(Integer defaultSleep) {
		this.defaultSleep = defaultSleep;
	}

	@GET
	@Path("sleep")
	public String sleep(@QueryParam("period") Optional<Integer> sleepPeriod,
			@Context HttpServletRequest hsr) throws NumberFormatException,
			InterruptedException {
		log.info(String.format("request from %s", hsr.getRemoteAddr()));
		Thread.sleep(sleepPeriod.or(defaultSleep));
		return Long.toString(System.currentTimeMillis()) + "\n";
	}
}
