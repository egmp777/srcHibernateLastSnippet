package com.cc.code.samples.hibernate.pagination;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ModelFooTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public final void testGetFoos() {
		
		ModelFoo mf = new ModelFoo();
		assertEquals(10, mf.getFoos(1, 10).size());
	}

}
