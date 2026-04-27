def is_safe(board, row, col, n):
    # check column
    for i in range(row):
        if board[i][col] == 1:
            return False

    # check left diagonal
    i, j = row-1, col-1
    while i >= 0 and j >= 0:
        if board[i][j] == 1:
            return False
        i -= 1
        j -= 1

    # check right diagonal
    i, j = row-1, col+1
    while i >= 0 and j < n:
        if board[i][j] == 1:
            return False
        i -= 1
        j += 1

    return True


def solve_n_queens(board, row, n):
    if row == n:
        for r in board:
            print(r)
        print()
        return

    for col in range(n):
        if is_safe(board, row, col, n):
            board[row][col] = 1
            solve_n_queens(board, row + 1, n)
            board[row][col] = 0   # backtrack


# main
n = 4
board = [[0]*n for _ in range(n)]
solve_n_queens(board, 0, n)
