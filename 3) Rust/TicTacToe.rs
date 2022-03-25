use std::io;

fn main() {
    let board = [" ", " ", " ", " ", " ", " ", " ", " ", " "];
    
    println!("Welcome to Tic Tac Toe!");

    x_turn(board);
}

fn print_board(board:[&str; 9]) {
    println!("{}|{}|{}", board[0], board[1], board[2]);
    println!("-+-+-");
    println!("{}|{}|{}", board[3], board[4], board[5]);
    println!("-+-+-");
    println!("{}|{}|{}", board[6], board[7], board[8]);
}

fn x_turn(mut board:[&str; 9]) {
    print_board(board);

    win_condition(board);

    println!("X, input your move (1-9):");
    let mut input = String::new();
    io::stdin().read_line(&mut input).expect("Failed to read line");
    let input: usize = input.trim().parse().expect("Please type a number");

    if board[input - 1] == " " {
        board[input - 1] = "X";
    } else {
        println!("That spot is taken, try again.");
        x_turn(board);
    }

    o_turn(board);
}

fn o_turn(mut board:[&str; 9]) {
    print_board(board);

    win_condition(board);

    println!("O, input your move (1-9):");
    let mut input = String::new();
    io::stdin().read_line(&mut input).expect("Failed to read line");
    let input: usize = input.trim().parse().expect("Please type a number");

    if board[input - 1] == " " {
        board[input - 1] = "O";
    } else {
        println!("That spot is taken, try again.");
        o_turn(board);
    }

    x_turn(board);
}

fn win_condition(board:[&str; 9]) {
    let win = check_win(board);

    if win == "x" {
        println!("X wins!");
        main();
    } else if win == "o" {
        println!("O wins!");
        main();
    } else if win == "tie" {
        println!("It's a tie!");
        main();
    }
}

fn check_win(board:[&str; 9]) -> &str {
    let win_combos = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ];

    for combo in win_combos.iter() {
        if board[combo[0]] == "X" && board[combo[1]] == "X" && board[combo[2]] == "X" {
            return "x";
        } else if board[combo[0]] == "O" && board[combo[1]] == "O" && board[combo[2]] == "O" {
            return "o";
        }
    }

    let mut tie = true;
    for i in 0..9 {
        if board[i] == " " {
            tie = false;
        }
    }

    if tie {
        return "tie";
    } else {
        return "none";
    }
}