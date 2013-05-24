package main;

import java.util.List;

import antsystem.AntSystemSCP;
import setcovering.Column;
import setcovering.ColumnSet;

public class Main {
	

	private static final String FILE_NAME = "src/resources/InputFile.dat";

	public static void main(String[] args) {
		ColumnSet columnSet = FileUtil.readFile(FILE_NAME);
		
		List<Column> solution = AntSystemSCP.execute(columnSet);
		
		
	}

}
