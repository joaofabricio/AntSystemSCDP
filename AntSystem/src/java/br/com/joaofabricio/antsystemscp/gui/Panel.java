package br.com.joaofabricio.antsystemscp.gui;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import br.com.joaofabricio.antsystemscp.antsystem.AntSystemSCP;
import br.com.joaofabricio.antsystemscp.antsystem.BestSolution;
import br.com.joaofabricio.antsystemscp.setcovering.ColumnSet;
import br.com.joaofabricio.antsystemscp.util.FileUtil;


public class Panel extends JFrame implements ActionListener, PropertyChangeListener {

	private static final long serialVersionUID = -3317813288766774217L;
	
//	private static final Logger LOG = Logger.getLogger(Panel.class.getPackage().getName());

	JButton openButton;
	JFileChooser fc;

	private int maxIter = 10;

	private JProgressBar progressBar;

	private JTextArea textArea;

	public Panel() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//File Chooser
		fc = new JFileChooser();
		openButton = new JButton("Open a File...");
		openButton.addActionListener(this);

		//botão
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(openButton);

		add(buttonPanel, BorderLayout.PAGE_START);

		//barra de progresso
		progressBar = new JProgressBar(0, maxIter);
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		progressBar.setBounds(0, 35, 300, 20);
		add(progressBar, BorderLayout.AFTER_LAST_LINE);
//		progressBar.setValue(n)
		
		
		textArea = new JTextArea();
		textArea.setBounds(0, 75, 300, 100);
		add(textArea);
		
		pack();
		setBounds(new Rectangle(300, 300));
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new Panel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			progressBar.setValue(0);
			int returnVal = fc.showOpenDialog(Panel.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				
				ColumnSet columnSet = FileUtil.readFile(file);

				double alfa = 1d;
				double beta = 1d;
				double ro = 0.8d;
				double q = 25000d;
				
				maxIter = 10;
				AntSystemSCP antSystem = new AntSystemSCP(alfa, beta, ro, maxIter, q);

				antSystem.execute(columnSet, progressBar);
				
			}
		}
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() == progressBar && BestSolution.getInstance().isAvaible()) {
			textArea.setText(BestSolution.getInstance().getColumnSet().toString());
		}
	}

}
