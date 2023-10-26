import random

def print_board(board):
    for row in board:
        print(" ".join(row))
    print()

def check_winner(board, player):
    # Check rows
    for row in board:
        if all(cell == player for cell in row):
            return True

    # Check columns
    for col in range(3):
        if all(board[row][col] == player for row in range(3)):
            return True

    # Check diagonals
    if all(board[i][i] == player for i in range(3)) or all(board[i][2 - i] == player for i in range(3)):
        return True

    return False

def is_board_full(board):
    return all(cell != ' ' for row in board for cell in row)

def minimax(board, depth, maximizing_player):
    if check_winner(board, 'O'):
        return -1
    if check_winner(board, 'X'):
        return 1
    if is_board_full(board):
        return 0

    if maximizing_player:
        max_eval = -float('inf')
        for row in range(3):
            for col in range(3):
                if board[row][col] == ' ':
                    board[row][col] = 'X'
                    eval = minimax(board, depth + 1, False)
                    board[row][col] = ' '
                    max_eval = max(max_eval, eval)
        return max_eval
    else:
        min_eval = float('inf')
        for row in range(3):
            for col in range(3):
                if board[row][col] == ' ':
                    board[row][col] = 'O'
                    eval = minimax(board, depth + 1, True)
                    board[row][col] = ' '
                    min_eval = min(min_eval, eval)
        return min_eval

def best_move(board):
    best_val = -float('inf')
    best_move = None

    for row in range(3):
        for col in range(3):
            if board[row][col] == ' ':
                board[row][col] = 'X'
                move_val = minimax(board, 0, False)
                board[row][col] = ' '
                if move_val > best_val:
                    best_val = move_val
                    best_move = (row, col)

    return best_move

def main():
    board = [[' ' for _ in range(3)] for _ in range(3)
    ]

    while True:
        print_board(board)
        player_row, player_col = map(int, input("Enter your move (row and column): ").split())
        if board[player_row][player_col] == ' ':
            board[player_row][player_col] = 'O'

            if check_winner(board, 'O'):
                print_board(board)
                print("You win!")
                break
            elif is_board_full(board):
                print_board(board)
                print("It's a tie!")
                break

            print("Computer is making its move...")
            computer_move = best_move(board)
            board[computer_move[0]][computer_move[1]] = 'X'

            if check_winner(board, 'X'):
                print_board(board)
                print("Computer wins!")
                break
            elif is_board_full(board):
                print_board(board)
                print("It's a tie!")
                break

if __name__ == "__main__":
    main()
