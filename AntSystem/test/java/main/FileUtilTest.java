package main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import setcovering.ColumnSet;

public class FileUtilTest {
	
	

	@Test
	public void testReadFile12_6() {
		ColumnSet c = FileUtil.readFile("test/resources/InputFileExample-12-6.dat");
		
		assertEquals(12, c.getColumns().size());
		
		assertEquals(new Double(71), c.getCost());
	}
	
	

	@Test
	public void testReadFile7_3() {
		ColumnSet c = FileUtil.readFile("test/resources/InputFileExample-7-3.dat");
		
		assertEquals(7, c.getColumns().size());
		
		assertEquals(new Double(7), c.getCost());
		
	}

}
