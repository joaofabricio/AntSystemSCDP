package antsystem;

import java.util.List;

import setcovering.Column;
import setcovering.ColumnSet;


public class AntSystemSCP {
	
	private Double alfa;
	
	private Double beta;
	
	private Double ro;
	
	private Long nMax;
	
	private Long q;
	
	private Integer antPopulation = 0;
	
	public AntSystemSCP(Double alfa,
						Double beta, 
						Double ro, 
						Long nMax, 
						Long q) {
		this.alfa = alfa;
		this.beta = beta;
		this.ro = ro;
		this.nMax = nMax;
		this.q = q;
	}
	
	public static List<Column> execute(ColumnSet columnSet) {
		
		Ant ant = new Ant();
		
		List<Column> partialSolution= ant.run();
		
		
		return null;//TODO
	}
	
	

}
