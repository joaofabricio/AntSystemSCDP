package main;

import setcovering.Column;
import setcovering.ColumnSet;
import antsystem.AntSystemSCP;


public class Main {
	

	private static final String FILE_NAME = "src/resources/InputFile.dat";

	public static void main(String[] args) {
		ColumnSet columnSet = FileUtil.readFile(FILE_NAME);
		
		AntSystemSCP asscp = new AntSystemSCP(0.3d, 0.5d, 0.8d, 5, 5d);
		
		ColumnSet best = asscp.execute(columnSet);
		
		for (Column column : best.getColumns()) {
			System.out.println(column.getLabel());
		}
		
		System.out.println("****");
		System.out.println("Custo total "+best.getCost());
	}

}
