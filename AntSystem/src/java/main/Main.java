package main;

import java.util.Calendar;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import setcovering.ColumnSet;
import antsystem.AntSystemSCP;


public class Main {
	

	private static final String FILE_NAME = "src/resources/InputFile.dat";
	private static final Logger LOG = Logger.getLogger(Main.class.getPackage().getName());

	public static void main(String[] args) {
		ColumnSet columnSet = FileUtil.readFile(FILE_NAME);
		
		double alfa = 1d;
		double beta = 1d;
		double ro = 0.8d;
		int maxIter = 10;
		double q = 10d;
		AntSystemSCP asscp = new AntSystemSCP(alfa, beta, ro, maxIter, q);
		
		Long t1 = Calendar.getInstance().getTimeInMillis();
		ColumnSet best = asscp.execute(columnSet);
		Long t2 = Calendar.getInstance().getTimeInMillis();
		LOG.info("Tempo de execução: "+(t2-t1)+"ms");
		LOG.info("****");
		LOG.info("Solução: "+best);
		
	}

}
