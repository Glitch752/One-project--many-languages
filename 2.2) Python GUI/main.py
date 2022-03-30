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

    Text(app, text="Tic Tac Toe", size=titleHeight - 10, color="black")

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

    main(app, buttons)
    app.display()

def main(app, buttons):
    gameboard = [" ", " ", " ", " ", " ", " ", " ", " ", " "]
    update_buttons(app, gameboard, buttons)

def update_buttons(app, gameboard, buttons):
    for i in range(9):
        buttons[i].text = gameboard[i]
        buttons[i]._on_press = lambda i=i: button_clicked(app, i, gameboard, buttons)

def button_clicked(app, i, gameboard, buttons):
    gameboard[i] = "X"
    update_buttons(app, gameboard, buttons)

start()