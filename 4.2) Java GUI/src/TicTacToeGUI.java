import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

public class TicTacToeGUI {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tic Tac Toe");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int windowWidth = 500;
		int windowHeight = 544;

		frame.setSize(windowWidth, windowHeight);

		frame.setResizable(false);

		// Move to center of screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - windowWidth / 2, dim.height / 2 - windowHeight / 2);

		frame.requestFocus();

		// Add the 9 buttons to the frame
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		int buttonPadding = 10;

		for (int i = 0; i < 9; i++) {
			JButton button = new JButton("");

			c.gridx = i % 3;
			c.gridy = i / 3;

			c.insets = new Insets(buttonPadding, buttonPadding, buttonPadding, buttonPadding);

			buttonPanel.add(button, c);
			
			button.addActionListener(new ButtonListener(i));
		}

		frame.setLayout(new GridBagLayout());

		c.gridx = 0;
		c.gridy = 0;
		
		c.weighty = 0;

		c.insets = new Insets(10, 0, 10, 0);

		JLabel label = new JLabel("Tic Tac Toe");
		label.setFont(label.getFont().deriveFont(24.0f));
		label.setHorizontalAlignment(JLabel.CENTER);

		frame.add(label, c);

		c.gridx = 0;
		c.gridy = 1;

		c.weighty = 1;

		c.insets = new Insets(0, 0, 0, 0);

		frame.add(buttonPanel, c);

		frame.setVisible(true);
	}
}

class ButtonListener implements java.awt.event.ActionListener {
	private int buttonNumber;

	public ButtonListener(int buttonNumber) {
		this.buttonNumber = buttonNumber;
	}

	public void actionPerformed(java.awt.event.ActionEvent e) {
		System.out.println("Button " + buttonNumber + " was clicked.");
	}
}