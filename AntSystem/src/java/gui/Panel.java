package gui;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel extends JFrame implements ActionListener {

	private static final long serialVersionUID = -3317813288766774217L;

	JButton openButton;
	JFileChooser fc;

	public Panel() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		fc = new JFileChooser();
		openButton = new JButton("Open a File...");
		openButton.addActionListener(this);

		JPanel buttonPanel = new JPanel(); // use FlowLayout
		buttonPanel.add(openButton);

		add(buttonPanel, BorderLayout.PAGE_START);

		pack();
		setBounds(new Rectangle(300, 300));
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new Panel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Handle open button action.
		if (e.getSource() == openButton) {
			int returnVal = fc.showOpenDialog(Panel.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				// This is where a real application would open the file.
//				log.append("Opening: " + file.getName() + "." + newline);
				System.out.println("open");
			} else {
//				log.append("Open command cancelled by user." + newline);
			}
//			log.setCaretPosition(log.getDocument().getLength());

			// Handle save button action.
		}
	}

}
