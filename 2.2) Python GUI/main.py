# Requires guizero
from guizero import App, Text, PushButton, Box

def start():
    windowWidth = 400
    buttonBoxHeight = 400
    titleHeight = 50
    windowHeight = buttonBoxHeight + titleHeight
    buttonMargin = 10
    buttonWidth = round((windowWidth - (buttonMargin * 4)) / 3)
    buttonHeight = round((buttonBoxHeight - (buttonMargin * 4)) / 3)

    app = App(title="Tic Tac Toe", width=windowWidth, height=windowHeight)

    text = Text(app, text="", size=titleHeight - 30, color="black")

    buttonBoxes = []
    buttons = []
    for i in range(9):
        buttonButtonBox = Box(app)
        buttonBoxes.append(buttonButtonBox)

    for i in range(9):
        buttonButtonBox = buttonBoxes[i]
        buttonButtonBox.tk.place(
            x=buttonMargin + ((buttonWidth + buttonMargin) * (i % 3)), 
            y=buttonMargin + (titleHeight + (buttonHeight + buttonMargin) * (i // 3)), 
            width=buttonWidth, 
            height=buttonHeight)
        button = PushButton(buttonButtonBox, text=" ", width="fill", height="fill")
        button.text_size = 24
        buttons.append(button)

    main(app, buttons, text)
    app.display()

def main(app, buttons, text):
    gameboard = [" ", " ", " ", " ", " ", " ", " ", " ", " "]
    gameState = "XTURN"

    update_text(gameState, text)

    update_buttons(app, gameboard, buttons, text, gameState)

def update_buttons(app, gameboard, buttons, text, gameState):
    for i in range(9):
        buttons[i].text = gameboard[i]
        buttons[i].update_command(button_clicked, args=[app, i, gameboard, buttons, text, gameState])

def update_text(gameState, text):
    if gameState == "XTURN":
        text.value = "X's turn"
    elif gameState == "OTURN":
        text.value = "O's turn"
    elif gameState == "XWON":
        text.value = "X wins! Click to restart."
    elif gameState == "OWON":
        text.value = "O wins! Click to restart."
    else:
        text.value = "Draw! Click to restart."

def button_clicked(app, i, gameboard, buttons, text, gameState):
    if not gameState == "XTURN" and not gameState == "OTURN":
        main(app, buttons, text)
        return
    
    if not gameboard[i] == " ": return
    
    gameboard[i] = "X" if gameState == "XTURN" else "O"
    gameState = "XTURN" if gameState == "OTURN" else "OTURN"

    win = check_win(gameboard)
    if win == "X":
        gameState = "XWON"
    elif win == "O":
        gameState = "OWON"
    elif win == "DRAW":
        gameState = "DRAW"

    update_text(gameState, text)

    update_buttons(app, gameboard, buttons, text, gameState)

def check_win(gameboard):
    winCombos = [[0, 1, 2], [3, 4, 5], [6, 7, 8], [0, 3, 6], [1, 4, 7], [2, 5, 8], [0, 4, 8], [2, 4, 6]]

    for combo in winCombos:
        if gameboard[combo[0]] == gameboard[combo[1]] == gameboard[combo[2]] and gameboard[combo[0]] != " ":
            return gameboard[combo[0]]
    
    for i in range(9):
        if gameboard[i] == " ":
            return None

    return "DRAW"

start()