import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {

		String[] board = { " ", " ", " ", " ", " ", " ", " ", " ", " " };
		
		System.out.println("Welcome to Tic Tac Toe!");

		Scanner input = new Scanner(System.in);
		xTurn(board, input);
	}

	public static void printBoard(String[] board) {
		System.out.println(board[0] + "|" + board[1] + "|" + board[2]);
		System.out.println("-+-+-");
		System.out.println(board[3] + "|" + board[4] + "|" + board[5]);
		System.out.println("-+-+-");
		System.out.println(board[6] + "|" + board[7] + "|" + board[8]);
	}

	public static void xTurn(String[] board, Scanner input) {
		printBoard(board);

		winCondition(board);
		
		System.out.println("X, choose where to move (1-9):");

		// Receives input from user
		int move = input.nextInt();

		// Checks if the move is valid
		if (move < 1 || move > 9) {
			System.out.println("Invalid move. Try again.");
			xTurn(board, input);
		} else if (board[move - 1] != " ") {
			System.out.println("Invalid move. Try again.");
			xTurn(board, input);
		} else {
			board[move - 1] = "X";
			oTurn(board, input);
		}
	}

	public static void oTurn(String[] board, Scanner input) {
		printBoard(board);

		winCondition(board);
		
		System.out.println("O, choose where to move (1-9):");

		// Receives input from user
		int move = input.nextInt();

		// Checks if the move is valid
		if (move < 1 || move > 9) {
			System.out.println("Invalid move. Try again.");
			oTurn(board, input);
		} else if (board[move - 1] != " ") {
			System.out.println("Invalid move. Try again.");
			oTurn(board, input);
		} else {
			board[move - 1] = "O";
			xTurn(board, input);
		}
	}

	public static void winCondition(String[] board) {
		String win = checkWin(board);

		if(win == "X") {
			System.out.println("X wins!");
			main(null);
		} else if(win == "O") {
			System.out.println("O wins!");
			main(null);
		} else if(win == "tie") {
			System.out.println("Tie!");
			main(null);
		}
	}

	public static String checkWin(String[] board) {
		Integer[][] winConditions = {
			{0, 1, 2},
			{3, 4, 5},
			{6, 7, 8},
			{0, 3, 6},
			{1, 4, 7},
			{2, 5, 8},
			{0, 4, 8},
			{2, 4, 6}
		};

		for (Integer[] winCondition : winConditions) {
			if (board[winCondition[0]] != " " && board[winCondition[0]] == board[winCondition[1]] && board[winCondition[1]] == board[winCondition[2]]) {
				return board[winCondition[0]];
			}
		}

		for (int i = 0; i < board.length; i++) {
			if (board[i] == " ") {
				return "none";
			}
		}

		return "tie";
	}
}
