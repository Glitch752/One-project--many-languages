def main():
    print("Welcome to Tic Tac Toe!")
    board = [' '] * 10

    xTurn(board)

def printBoard(board):
    print(board[0] + '|' + board[1] + '|' + board[2])
    print('-+-+-')
    print(board[3] + '|' + board[4] + '|' + board[5])
    print('-+-+-')
    print(board[6] + '|' + board[7] + '|' + board[8])

def xTurn(board):
    printBoard(board)

    winCondition(board)

    position = input("X, Choose a position (1-9): ")

    if not position.isdigit():
        print("Invalid input!")
        xTurn(board)
    
    position = int(position) - 1

    if board[position] == ' ':
        board[position] = 'X'
    else:
        print("Invalid input!")
        xTurn(board)

    oTurn(board)

def oTurn(board):
    printBoard(board)

    winCondition(board)

    position = input("O, Choose a position (1-9): ")

    if not position.isdigit():
        print("Invalid input!")
        oTurn(board)
    
    position = int(position) - 1

    if board[position] == ' ':
        board[position] = 'O'
    else:
        print("Invalid input!")
        oTurn(board)

    xTurn(board)

def winCondition(board):
    win = checkWin(board)

    if win == "x":
        print("X Wins!")
        main()
    elif win == "o":
        print("O Wins!")
        main()
    elif win == "tie":
        print("Tie!")
        main()

def checkWin(board):
    winConditions = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ]

    for winCondition in winConditions:
        if board[winCondition[0]] == board[winCondition[1]] == board[winCondition[2]] == 'X':
            return "x"
        elif board[winCondition[0]] == board[winCondition[1]] == board[winCondition[2]] == 'O':
            return "o"

    # Check for tie
    tie = True
    for i in range(9):
        if board[i] == ' ':
            tie = False

    if tie:
        return "tie"
    
    return "none"

main()