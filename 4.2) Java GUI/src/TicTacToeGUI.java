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
	static String[] board = { " ", " ", " ", " ", " ", " ", " ", " ", " " };
	static int buttonPadding = 10;

	static Boolean frameCreated = false;

	static JFrame frame;

	public static void main(String[] args) {
		for(int i = 0; i < board.length; i++) {
			board[i] = " ";
		}

		gameState = GameState.X_TURN;

		if(!frameCreated) {
			frameCreated = true;

			frame = new JFrame("Tic Tac Toe");

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			int windowWidth = 500;
			int windowHeight = 544;

			frame.setSize(windowWidth, windowHeight);

			frame.setResizable(false);

			// Move to center of screen
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width / 2 - windowWidth / 2, dim.height / 2 - windowHeight / 2);

			frame.requestFocus();
		}

		frame.getContentPane().removeAll();

		// Add the 9 buttons to the frame
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		JLabel label = new JLabel("");

		updateButtons(buttonPanel, label, c, buttonPadding, board);

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
				label.setText("X wins! Click to restart.");
				break;
			case O_WON:
				label.setText("O wins! Click to restart.");
				break;
			case DRAW:
				label.setText("Draw! Click to restart.");
				break;
		}
	}

	public static void updateButtons(JPanel buttonPanel, JLabel label, GridBagConstraints c, int buttonPadding, String[] board) {
		buttonPanel.removeAll();

		for (int i = 0; i < 9; i++) {
			JButton button = new JButton();

			button.setFont(button.getFont().deriveFont(48.0f));
			button.setText(board[i]);

			c.gridx = i % 3;
			c.gridy = i / 3;

			c.insets = new Insets(buttonPadding, buttonPadding, buttonPadding, buttonPadding);

			buttonPanel.add(button, c);
			
			button.addActionListener(new ButtonListener(i, label, buttonPanel));
		}

		buttonPanel.revalidate();
		buttonPanel.repaint();
	}

	public static void checkForWin(String[] board, JLabel label) {
		int[][] winCombos = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };

		for (int[] winCombo : winCombos) {
			if (board[winCombo[0]] != " " && board[winCombo[0]] == board[winCombo[1]] && board[winCombo[0]] == board[winCombo[2]]) {
				if (board[winCombo[0]] == "X") {
					gameState = GameState.X_WON;
				} else {
					gameState = GameState.O_WON;
				}
			}
		}

		if (gameState != GameState.X_WON && gameState != GameState.O_WON) {
			for (int i = 0; i < 9; i++) {
				if (board[i] == " ") {
					return;
				}
			}

			gameState = GameState.DRAW;
		}

		updateLabel(label, gameState);
	}
}

class ButtonListener implements java.awt.event.ActionListener {
	private int buttonNumber;
	private JLabel label;
	private JPanel buttonPanel;

	public ButtonListener(int buttonNumber, JLabel label, JPanel buttonPanel) {
		this.buttonNumber = buttonNumber;
		this.label = label;
		this.buttonPanel = buttonPanel;
	}

	public void actionPerformed(java.awt.event.ActionEvent e) {

		if(TicTacToeGUI.gameState == GameState.X_WON || TicTacToeGUI.gameState == GameState.O_WON || TicTacToeGUI.gameState == GameState.DRAW) {
			TicTacToeGUI.main(null);
			return;
		}

		if(TicTacToeGUI.board[buttonNumber] != " ") return;
		
		switch(TicTacToeGUI.gameState) {
			case X_TURN:
				TicTacToeGUI.gameState = GameState.O_TURN;
				break;
			case O_TURN:
				TicTacToeGUI.gameState = GameState.X_TURN;
				break;
			default:
				return;
		}

		TicTacToeGUI.board[buttonNumber] = TicTacToeGUI.gameState == GameState.O_TURN ? "X" : "O";

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		TicTacToeGUI.updateButtons(buttonPanel, label, c, TicTacToeGUI.buttonPadding, TicTacToeGUI.board);

		TicTacToeGUI.checkForWin(TicTacToeGUI.board, label);

		TicTacToeGUI.updateLabel(label, TicTacToeGUI.gameState);
	}
}