package com.salesforce.systesting.netload.resources;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import com.google.common.base.Optional;

public class SimpleLoadResourceTest {

	@Test
	public void testSleep() throws Exception {
		HttpServletRequest hsr = mock(HttpServletRequest.class);
		when(hsr.getRemoteAddr()).thenReturn("Test client @ foo.bar:666");
		SimpleLoadResource resource = new SimpleLoadResource(100);
		long then = System.currentTimeMillis();
		resource.sleep(Optional.of(100), hsr);
		assertTrue((System.currentTimeMillis() - then) >= 100);
	}
}
