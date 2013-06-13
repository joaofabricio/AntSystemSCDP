package main;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import br.com.joaofabricio.antsystemscp.setcovering.ColumnSet;
import br.com.joaofabricio.antsystemscp.util.FileUtil;


public class FileUtilTest {
	
	

	@Test
	public void testReadFile12x6() {
		ColumnSet c = FileUtil.readFile(new File("test/resources/InputFileExample-12-6.dat"));
		
		assertEquals("Verificação de quantidade de colunas arquivo 12x6 incorreta", 12, c.getColumns().size());
		
		assertEquals("Verificação de custo arquivo 12x6 incorreta", new Double(71), c.getCost());
	}
	
	

	@Test
	public void testReadFile7x3() {
		ColumnSet c = FileUtil.readFile(new File("test/resources/InputFileExample-7-3.dat"));
		
		assertEquals("Verificação de quantidade de colunas arquivo 7x3 incorreta", 7, c.getColumns().size());
		
		assertEquals("Verificação de custo arquivo 7x3 incorreta", new Double(7), c.getCost());
		
	}

}
