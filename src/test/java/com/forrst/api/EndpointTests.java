package com.forrst.api;

import junit.framework.TestCase;

import org.testng.annotations.Test;

public class EndpointTests {
	
	@Test (groups={"ready"})
	public void testGetInstance() {
		TestCase.assertNotNull(Endpoint.getInstance());
	}

}
