package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import setcovering.Column;
import setcovering.ColumnSet;
import setcovering.Line;

public class FileUtil {
	
	public static ColumnSet readFile(String fileName) {
		ColumnSet conjunto = new ColumnSet();
		
		List<String> fileLines = separaLinhasArquivo(fileName);
		
		String lineZero = fileLines.get(0);
		if (!Pattern.matches("LINHAS\\s+\\d+", lineZero)) {
			throw new InvalidFileException("A primeira linha deve ser no formato \"LINHAS 10\"");
		}
//		String lineZeroData[] = lineZero.split("\\s+");
//		conjunto.setAmountLines(Integer.parseInt(lineZeroData[1]));
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
		
		for (int i = 3; i < amountColumns+3; i++) {
			
			String[] dataLines = fileLines.get(i).split("\\s+");
			
			if (dataLines.length < 3) {
				throw new InvalidFileException("Cada coluna deve cobrir pelo menos uma linha. Corrija a linha "+i);
			}
			
			int index = 0;
			if (dataLines[0].trim().length() == 0) {
				index = 1;
			}
			
			String rotulo = dataLines[index++];
			Double cost = Double.parseDouble(dataLines[index++]);
			
			Column column = new Column(rotulo, cost);
			
			while (index++ < dataLines.length-1) {
				Line linha = new Line(dataLines[index]);
				column.addLine(linha);
			}
			
			conjunto.addColumn(column);
		}
		
		return conjunto;
	}

	public static List<String> separaLinhasArquivo(String nomeArquivo) {
		List<String> linhas = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
			while (br.ready()) {
				linhas.add(br.readLine());
			}
			br.close();
			return linhas;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
