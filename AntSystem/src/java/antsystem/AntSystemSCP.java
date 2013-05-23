package antsystem;

import java.util.List;

import setcovering.Column;

public interface AntSystemSCP {
	
//	public AntSystemSCP(Double alfa,
//						Double beta,
//						Double q,
//						Double nMax,
//						File file) {
//	}
	
	void atualizeFeromony();
	
	void execute();
	
	void loadFile();
	
	List<Column> getSolution();
	
	

}
