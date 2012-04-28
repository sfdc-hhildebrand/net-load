package com.salesforce.systesting.netload.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class SimpleDiffResourceTest {

	@Test
	public void testPostView() throws Exception {
		HttpServletRequest hsr = mock(HttpServletRequest.class);
		when(hsr.getRemoteAddr()).thenReturn("Test client @ foo.bar:666");
		SimpleDiffResource resource = new SimpleDiffResource();
		String diffResult = resource.view(getFile1(), getFile2(), hsr);
		assertTrue(diffResult.contains(getExpectedDiffResult(1)));
		assertTrue(diffResult.contains(getExpectedDiffResult(2)));
	}
	@Test
	public void testGetView() throws Exception {
		HttpServletRequest hsr = mock(HttpServletRequest.class);
		when(hsr.getRemoteAddr()).thenReturn("Test client @ foo.bar:666");
		SimpleDiffResource resource = new SimpleDiffResource();
		String diffResult = resource.view(hsr);
		assertTrue(diffResult.contains(getExpectedDiffResult(1)));
		assertTrue(diffResult.contains(getExpectedDiffResult(2)));
	}

	private String getExpectedDiffResult(int part) throws IOException {
		return IOUtils.toString(getClass().getClassLoader().getResourceAsStream("diff" + part + ".html"));
	}

	private String getFile2() throws IOException {
		return IOUtils.toString(getClass().getClassLoader().getResourceAsStream("file2.txt"));
	}

	private String getFile1() throws IOException {
		return IOUtils.toString(getClass().getClassLoader().getResourceAsStream("file1.txt"));
	}
}
