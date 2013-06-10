package antsystem;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import setcovering.Column;
import setcovering.ColumnSet;
import setcovering.Line;

public class Ant {

	private ColumnSet bestSolution;

	private ColumnSet totalColumns;

	private ColumnSet notChoosedColumns;

	private Collection<Line> coveredLines = new HashSet<Line>();

	private Collection<Line> nonCoveredLines = new HashSet<Line>();

	public Ant(ColumnSet totalColumns) {
		this.totalColumns = totalColumns;
		notChoosedColumns = new ColumnSet(totalColumns);
		bestSolution = new ColumnSet(totalColumns.getTotalLines());
		nonCoveredLines.addAll(allLines());
	}

	/**
	 * Escolhe o caminho a ser percorrido pela formiga
	 * 
	 * @param alfa
	 *            Peso do valor do feromônio na escolha do caminho. O Feromônio
	 *            recebe um incremento inversamente proporcional ao custo do
	 *            caminho toda vez em que uma formiga o percorre.
	 * @param beta
	 *            Peso da visibilidade da coluna na escolha do caminho. A
	 *            visibilidade da coluna é dada pelo quociente do total de
	 *            linhas ainda não cobertas pelo custo da coluna.
	 * @param q
	 *            Referência de evaporação do feromônio. Ao final da escolha do
	 *            caminho, a formiga escolhe faz evaporar o feromônio que já
	 *            estava nas colunas, fazendo com que dados antigos não
	 *            sobreescrevam dados novos.
	 * @return
	 */
	public ColumnSet run(Double alfa, Double beta, Double q) {
		while (!coverAllLines(bestSolution)) {
			Line line = chooseLine();

			Column column = chooseColumn(line, alfa, beta);
			bestSolution.addColumn(column);
			coveredLines.addAll(column.getLines());
			nonCoveredLines.removeAll(column.getLines());
		}

		updatePheromone(q);

		return bestSolution;
	}

	private Set<Line> allLines() {
		Set<Line> set = new HashSet<Line>();
		for (Column column : totalColumns.getColumns()) {
			set.addAll(column.getLines());
		}
		return set;
	}

	private Line chooseLine() {
		return nonCoveredLines.isEmpty() ? null : nonCoveredLines.iterator()
				.next();
	}

	private void updatePheromone(Double q) {
		for (Column column : bestSolution.getColumns()) {
			column.incPheromone(q, bestSolution.getCost());
		}

	}

	private boolean coverAllLines(ColumnSet bestSolution) {
		return coveredLines.size() == totalColumns.getTotalLines();
	}

	private Column chooseColumn(Line line, Double alfa, Double beta) {
		Set<Column> avaibleColumns = new HashSet<>();
		Iterator<Column> columns = notChoosedColumns.getColumns().iterator();
		while (columns.hasNext()) {
			Column column = columns.next();
			if (column.cover(line)) {
				avaibleColumns.add(column);
			}
		}

		calculateProb(alfa, beta, avaibleColumns);

		double randomNumber = Math.random();
		double ac = 0;
		columns = avaibleColumns.iterator();
		Column choosed = null;
		while (ac < randomNumber && columns.hasNext()) {
			choosed = columns.next();
			ac += choosed.getProb();
		}

		return choosed;
	}

	private void calculateProb(Double alfa, Double beta,
			Collection<Column> avaibleColumns) {
		double sumProbs = 0;
		Set<Line> nonCoveredLines = allLines();
		nonCoveredLines.removeAll(coveredLines);

		for (Column column : avaibleColumns) {
			sumProbs += Math.pow(column.getPheromone(), alfa)
					+ Math.pow(column.getVisibility(nonCoveredLines), beta);
		}

		for (Column column : avaibleColumns) {
			double probCol = Math.pow(column.getPheromone(), alfa)
					+ Math.pow(column.getVisibility(nonCoveredLines), beta);
			column.setProb(probCol / sumProbs);
		}
	}

}
