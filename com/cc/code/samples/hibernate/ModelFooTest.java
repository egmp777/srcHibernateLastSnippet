package com.cc.code.samples.hibernate;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;



public class ModelFooTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public final void testGetFoos() {
		
		long minId = 1;
		long maxId = 10;
		
		ModelFoo mf = new ModelFoo();
		assertEquals(10, mf.getFoos(minId, maxId).size());
	}

}
