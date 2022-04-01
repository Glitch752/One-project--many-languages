function start()
    local gameBoard;

    gameBoard = {" "," "," "," "," "," "," "," "," "};

    print("Welcome to Tic Tac Toe!");
    xTurn(gameBoard);
end

function printBoard(board)
    print(board[1] .. "|" .. board[2] .. "|" .. board[3]);
    print("-+-+-");
    print(board[4] .. "|" .. board[5] .. "|" .. board[6]);
    print("-+-+-");
    print(board[7] .. "|" .. board[8] .. "|" .. board[9]);
end

function xTurn(board)
    local xMove;

    printBoard(board);

    winDecision(board);

    print("X, make your move! (1-9):");

    xMove = io.read();
    xMove = tonumber(xMove);
    
    if xMove == nil then
        print("Invalid move!");
        xTurn(board);
    end

    if xMove < 1 or xMove > 9 then
        print("Invalid move!");
        xTurn(board);
    else if (board[xMove] == " ") then
        board[xMove] = "X";
        oTurn(board);
    else
        print("Invalid move!");
        xTurn(board);
    end
end
end

function oTurn(board)
    winDecision(board);

    local oMove;

    printBoard(board);

    winDecision(board);

    print("O, make your move! (1-9):");

    oMove = io.read();
    oMove = tonumber(oMove);

    if oMove == nil then
        print("Invalid move!");
        oTurn(board);
    end

    if oMove < 1 or oMove > 9 then
        print("Invalid move!");
        oTurn(board);
    else if (board[oMove] == " ") then
        board[oMove] = "O";
        xTurn(board);
    else
        print("Invalid move!");
        oTurn(board);
    end
end
end

function winDecision(board)
    local win;
    win = checkWin(board);

    if win == "X" then
        print("X wins!");
        start();
    else if win == "O" then
        print("O wins!");
        start();
    else if win == "T" then
        print("Tie!");
        start();
    end
end
end
end

function checkWin(board)
    local winCombinations;

    winCombinations = {
        {1,2,3},
        {4,5,6},
        {7,8,9},
        {1,4,7},
        {2,5,8},
        {3,6,9},
        {1,5,9},
        {3,5,7}
    };

    for i = 1, #winCombinations do
        if (board[winCombinations[i][1]] == "X" and board[winCombinations[i][2]] == "X" and board[winCombinations[i][3]] == "X") then
            return "X";
        else if (board[winCombinations[i][1]] == "O" and board[winCombinations[i][2]] == "O" and board[winCombinations[i][3]] == "O") then
            return "O";
        end end
    end

    for i = 0, 9 do
        if (board[i] == " ") then
            return " ";
        end
    end

    return "T";
end

start();