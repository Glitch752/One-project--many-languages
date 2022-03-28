import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

enum GameState {
	X_TURN, O_TURN, X_WON, O_WON, DRAW
}

public class TicTacToeGUI {
	static GameState gameState;

	public static void main(String[] args) {
		gameState = GameState.X_TURN;

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

		JLabel label = new JLabel("");

		updateButtons(buttonPanel, label, c, 10);

		frame.setLayout(new GridBagLayout());

		c.gridx = 0;
		c.gridy = 0;
		
		c.weighty = 0;

		c.insets = new Insets(10, 0, 10, 0);

		updateLabel(label, gameState);

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

	public static void updateLabel(JLabel label, GameState gameState) {
		switch (gameState) {
			case X_TURN:
				label.setText("X's turn");
				break;
			case O_TURN:
				label.setText("O's turn");
				break;
			case X_WON:
				label.setText("X wins!");
				break;
			case O_WON:
				label.setText("O wins!");
				break;
			case DRAW:
				label.setText("Draw!");
				break;
		}
	}

	public static void updateButtons(JPanel buttonPanel, JLabel label, GridBagConstraints c, int buttonPadding) {
		for (int i = 0; i < 9; i++) {
			JButton button = new JButton("");

			c.gridx = i % 3;
			c.gridy = i / 3;

			c.insets = new Insets(buttonPadding, buttonPadding, buttonPadding, buttonPadding);

			buttonPanel.add(button, c);
			
			button.addActionListener(new ButtonListener(i, label));
		}
	}
}

class ButtonListener implements java.awt.event.ActionListener {
	private int buttonNumber;
	private JLabel label;

	public ButtonListener(int buttonNumber, JLabel label) {
		this.buttonNumber = buttonNumber;
		this.label = label;
	}

	public void actionPerformed(java.awt.event.ActionEvent e) {
		System.out.println("Button " + buttonNumber + " was clicked.");

		switch(TicTacToeGUI.gameState) {
			case X_TURN:
				TicTacToeGUI.gameState = GameState.O_TURN;
				break;
			case O_TURN:
				TicTacToeGUI.gameState = GameState.X_TURN;
				break;
			default:
				break;
		}

		TicTacToeGUI.updateLabel(label, TicTacToeGUI.gameState);
	}
}