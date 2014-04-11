package com.cc.code.samples.hibernate.sorting;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;



public class ModelFooTest {

	

	@Test
	public final void testGetFoos() {
		
		long minId = 1;
		long maxId = 10;
		
		ModelFoo mf = new ModelFoo();
		assertEquals(10, mf.getSortedFoos().size());
	}

}
