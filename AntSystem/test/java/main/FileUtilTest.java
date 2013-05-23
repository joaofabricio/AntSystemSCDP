package main;

import static org.junit.Assert.*;

import org.junit.Test;

import setcovering.Column;
import setcovering.Conjunto;

public class FileUtilTest {
	
	

	@Test
	public void testReadFile12_6() {
		Conjunto c = FileUtil.readFile("test/resources/InputFileExample-12-6.dat");
		
		assertEquals(12, c.getAmountColumns());
		
		assertEquals(6, c.getAmountLines());
		
		assertEquals(12, c.getColumns().size());
	}
	
	

	@Test
	public void testReadFile7_3() {
		Conjunto c = FileUtil.readFile("test/resources/InputFileExample-7-3.dat");
		
		assertEquals(7, c.getAmountColumns());
		
		assertEquals(3, c.getAmountLines());
		
		assertEquals(7, c.getColumns().size());
		
		
		Double cost = 0d;
		for (Column column: c.getColumns()) {
			cost = column.getCost();
		}
		
		assertTrue(cost.equals(7d));
		
	}

}
