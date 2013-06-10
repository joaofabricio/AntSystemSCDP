package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import setcovering.Column;
import setcovering.ColumnSet;
import setcovering.Line;

public class FileUtil {
	
	public static ColumnSet readFile(File file) {
		
		List<String> fileLines = divideFileLines(file);
		
		String lineZero = fileLines.get(0);
		if (!Pattern.matches("LINHAS\\s+\\d+", lineZero)) {
			throw new InvalidFileException("A primeira linha deve ser no formato \"LINHAS 10\"");
		}
		String lineZeroData[] = lineZero.split("\\s+");
		ColumnSet columnSet = new ColumnSet(Long.parseLong(lineZeroData[1]));
//		Integer amountLines = Integer.parseInt(lineZeroData[1]);

		String lineOne = fileLines.get(1);
		if (!Pattern.matches("COLUNAS\\s+\\d+", lineOne)) {
			throw new InvalidFileException("A segunda linha deve ser no formato \"COLUNAS 10\"");
		}
		String lineOneData[] = lineOne.split("\\s+");
		Integer amountColumns = Integer.parseInt(lineOneData[1]);
//		conjunto = treatLineOne(conjunto, fileLines);
		
		if (!(fileLines.size() == amountColumns + 3)) {
			throw new InvalidFileException("As linhas restantes do arquivo devem conter os dados precedidos pela linha \"DADOS\"");
		}

		Set<Line> set = new HashSet<>();
		for (int i = 3; i < amountColumns+3; i++) {
			
			String[] dataLines = fileLines.get(i).split("\\s+");
			
			if (dataLines.length < 3) {
				throw new InvalidFileException("Cada coluna deve cobrir pelo menos uma linha. Corrija a linha "+i);
			}
			
			int index = 0;
			if (dataLines[0].trim().length() == 0) {
				index = 1;
			}
			
			String label = dataLines[index++];
			Double cost = Double.parseDouble(dataLines[index]);
			
			Column column = new Column(label, cost, columnSet.getTotalLines());
			
			while (index++ < dataLines.length-1) {
				Line line = new Line(dataLines[index]);
				set.add(line);
				column.addLine(line);
			}
			
			columnSet.addColumn(column);
		}
		
		if (set.size() != columnSet.getTotalLines()) {
			for (int i = 1; i <= 200; i++) {
				if (!set.contains(new Line(String.valueOf(i)))) {
					System.out.println("Não contém: '" + i + "'");
				}
			}
			
			throw new InvalidFileException("Não foram atribuídas todas as linhas para a cobertura");
		}
		
		return columnSet;
	}

	public static List<String> divideFileLines(File file) {
		List<String> lines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (br.ready()) {
				lines.add(br.readLine());
			}
			br.close();
			return lines;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
