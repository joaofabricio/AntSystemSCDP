package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import main.InvalidFileException;
import setcovering.Column;
import setcovering.Conjunto;
import setcovering.Line;

public class FileUtil {
	
	public static Conjunto readFile(String fileName) {
		Conjunto conjunto = new Conjunto();
		
		List<String> fileLines = separaLinhasArquivo(fileName);
		
		conjunto = treatLineZero(conjunto, fileLines);
		
		conjunto = treatLineOne(conjunto, fileLines);
		
		conjunto = treatData(conjunto, fileLines);
		
		return conjunto;
	}

	private static Conjunto treatData(Conjunto conjunto, List<String> fileLines) {
		if (!(fileLines.size() == conjunto.getAmountColumns() + 3)) {
			throw new InvalidFileException("As linhas restantes do arquivo devem conter os dados precedidos pela linha \"DADOS\"");
		}
		
		for (int i = 3; i < conjunto.getAmountColumns()+3; i++) {
			
			String[] dataLines = fileLines.get(i).split("\\s+");
			
			String rotulo = dataLines[0];
			Double cost = Double.parseDouble(dataLines[1]);
			
			Column column = new Column(rotulo, cost);
			
			
			if (dataLines.length < 3) {
				throw new InvalidFileException("Cada coluna deve cobrir pelo menos uma linha. Corrija a linha "+i);
			}
			
			int index = 1;
			while (index++ < dataLines.length-1) {
				Line linha = new Line(dataLines[index]);
				column.addLinha(linha);
			}
			
			conjunto.addColumn(column);
		}
		return conjunto;
	}

	private static Conjunto treatLineOne(Conjunto conjunto, List<String> fileLines) {
		String lineOne = fileLines.get(1);
		if (!Pattern.matches("COLUNAS\\s+\\d+", lineOne)) {
			throw new InvalidFileException("A segunda linha deve ser no formato \"COLUNAS 10\"");
		}
		String lineOneData[] = lineOne.split("\\s+");
		conjunto.setAmountColumns(Integer.parseInt(lineOneData[1]));
		return conjunto;
	}

	private static Conjunto treatLineZero(Conjunto conjunto, List<String> fileLines) {
		String lineZero = fileLines.get(0);
		if (!Pattern.matches("LINHAS\\s+\\d+", lineZero)) {
			throw new InvalidFileException("A primeira linha deve ser no formato \"LINHAS 10\"");
		}
		String lineZeroData[] = lineZero.split("\\s+");
		conjunto.setAmountLines(Integer.parseInt(lineZeroData[1]));
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
