package main

import "fmt"

func main() {

	board := []string{" ", " ", " ", " ", " ", " ", " ", " ", " "}

	fmt.Println("Welcome to tic-tac-toe!")

	xTurn(board)
}

func printBoard(board []string) {
	fmt.Println(board[0] + " | " + board[1] + " | " + board[2])
	fmt.Println("---------")
	fmt.Println(board[3] + " | " + board[4] + " | " + board[5])
	fmt.Println("---------")
	fmt.Println(board[6] + " | " + board[7] + " | " + board[8])
}

func xTurn(board []string) {
	printBoard(board)

	winCondition(board)

	fmt.Println("X, make your move! (1-9)")

	var spot int
	fmt.Scanln(&spot)

	// Check if spot is a number
	if !(spot > 0 && spot < 10) {
		fmt.Println("Invalid move!")
		xTurn(board)
	}

	// Check if spot is already taken
	if board[spot-1] != " " {
		fmt.Println("Spot is already taken!")
		xTurn(board)
	}

	board[spot-1] = "X"

	oTurn(board)
}

func oTurn(board []string) {
	printBoard(board)

	winCondition(board)

	fmt.Println("O, make your move! (1-9)")

	var spot int
	fmt.Scanln(&spot)

	// Check if spot is a number
	if !(spot > 0 && spot < 10) {
		fmt.Println("Invalid move!")
		oTurn(board)
	}

	// Check if spot is already taken
	if board[spot-1] != " " {
		fmt.Println("Spot is already taken!")
		oTurn(board)
	}

	board[spot-1] = "O"

	xTurn(board)
}

func winCondition(board []string) {
	resetBoard := []string{" ", " ", " ", " ", " ", " ", " ", " ", " "}
	switch checkWin(board) {
	case "X":
		fmt.Println("X wins!")
		xTurn(resetBoard)
	case "O":
		fmt.Println("O wins!")
		xTurn(resetBoard)
	case "tie":
		fmt.Println("Tie!")
		xTurn(resetBoard)
	}
}

func checkWin(board []string) string {
	// Winning combos
	combos := [][]int{
		{0, 1, 2},
		{3, 4, 5},
		{6, 7, 8},
		{0, 3, 6},
		{1, 4, 7},
		{2, 5, 8},
		{0, 4, 8},
		{2, 4, 6},
	}

	for _, combo := range combos {
		if board[combo[0]] == "X" && board[combo[1]] == "X" && board[combo[2]] == "X" {
			return "X"
		} else if board[combo[0]] == "O" && board[combo[1]] == "O" && board[combo[2]] == "O" {
			return "O"
		}
	}

	// Check for tie
	for _, spot := range board {
		if spot == " " {
			return "none"
		}
	}

	return "tie"
}
