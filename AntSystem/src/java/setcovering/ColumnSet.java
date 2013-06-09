package setcovering;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ColumnSet {
	
	private Long totalLines;
	
	public ColumnSet(Long totalLines) {
		this.totalLines = totalLines;
	}
	
	public ColumnSet(ColumnSet columnSet) {
		this.totalLines = columnSet.getTotalLines();
		this.cost = columnSet.getCost();
		this.columns.addAll(columnSet.getColumns());
	}

	private Set<Column> columns = new HashSet<Column>();
	private Double cost = 0d;
	private Set<Line> coveredLines = new HashSet<Line>();
	
	public void addColumn(Column column) {
		if (column != null) {
			cost += column.getCost();
			columns.add(column);
			coveredLines.addAll(column.getLines());
		}
	}
	
	public Set<Column> getColumns() {
		return columns;
	}
	
	public Double getCost() {
		return cost;
	}

	public Long getTotalLines() {
		return totalLines;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getCost());
		sb.append('[');
		Iterator<Column> i = columns.iterator();
		while(i.hasNext()) {
			sb.append(i.next()).append(',');
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(']');
		return sb.toString();
	}

	public void evaporePheromone(double ro) {
		Iterator<Column> it = columns.iterator();
		while (it.hasNext()) {
			Column column = it.next();
			column.evaporePheromone(ro);
		}
		
	}

	public Collection<Line> getCoveredLines() {
		return coveredLines;
	}
	
}
