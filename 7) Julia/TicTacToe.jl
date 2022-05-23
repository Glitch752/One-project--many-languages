function main()
    local board = [" ", " ", " ", " ", " ", " ", " ", " ", " "]

    println("Welcome to Tic Tac Toe!")

    xTurn(board)
end

function xTurn(board)
    printBoard(board)

    winCondition(board)

    print("X, make your move! (1-9) ")
    local move = readline()

    # Check if move is a number
    if (tryparse(Int64, move) !== nothing)
        move = tryparse(Int64, move)
        # Check if move is valid
        if (move > 0 && move < 10)
            # Check if move is empty
            if (board[move] == " ")
                board[move] = "X"
                oTurn(board)
            else
                println("That space is already taken!")
                xTurn(board)
            end
        else
            println("That is not a valid move!")
            xTurn(board)
        end
    else
        println("That is not a valid move!")
        xTurn(board)
    end
end

function oTurn(board)
    printBoard(board)

    winCondition(board)

    print("O, make your move! (1-9) ")
    local move = readline()

    # Check if move is a number
    if (tryparse(Int64, move) !== nothing)
        move = tryparse(Int64, move)
        # Check if move is valid
        if (move > 0 && move < 10)
            # Check if move is empty
            if (board[move] == " ")
                board[move] = "O"
                xTurn(board)
            else
                println("That space is already taken!")
                oTurn(board)
            end
        else
            println("That is not a valid move!")
            oTurn(board)
        end
    else
        println("That is not a valid move!")
        oTurn(board)
    end
end

function printBoard(board)
    println(" " * board[1] * " | " * board[2] * " | " * board[3])
    println("-----------")
    println(" " * board[4] * " | " * board[5] * " | " * board[6])
    println("-----------")
    println(" " * board[7] * " | " * board[8] * " | " * board[9])
end

function winCondition(board)
    local resetBoard = [" ", " ", " ", " ", " ", " ", " ", " ", " "]
    local win = checkWin(board)

    if (win == "X")
        println("X wins!")
        xTurn(resetBoard)
    elseif (win == "O")
        println("O wins!")
        xTurn(resetBoard)
    elseif (win == "Tie")
        println("Tie!")
        xTurn(resetBoard)
    else
        return
    end
end

function checkWin(board)
    local winConditions = [
        [1, 2, 3],
        [4, 5, 6],
        [7, 8, 9],
        [1, 4, 7],
        [2, 5, 8],
        [3, 6, 9],
        [1, 5, 9],
        [3, 5, 7]
    ]

    for winCondition in winConditions
        if (board[winCondition[1]] == "O" && board[winCondition[2]] == "O" && board[winCondition[3]] == "O")
            return "O"
        elseif (board[winCondition[1]] == "X" && board[winCondition[2]] == "X" && board[winCondition[3]] == "X")
            return "X"
        end
    end

    for i in board
        if(i == " ")
            return "None"
        end
    end

    return "Tie"
end

main()