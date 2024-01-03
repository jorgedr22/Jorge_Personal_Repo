import pygame as pg
import sys

# Initialize pg
pg.init()

# Constants for the window
WINDOW_SIZE = 450
GRID_SIZE = 9
CELL_SIZE = WINDOW_SIZE // GRID_SIZE

# Colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
GRAY = (200, 200, 200)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
LIGHT_GREEN = (100, 255, 100)

# Create the pg window
window = pg.display.set_mode((WINDOW_SIZE, WINDOW_SIZE))
pg.display.set_caption("Sudoku Solver")

# Define the Sudoku grid (0 means empty cell)
sudoku_grid = [
    [5, 3, 0, 0, 7, 0, 0, 0, 0],
    [6, 0, 0, 1, 9, 5, 0, 0, 0],
    [0, 9, 8, 0, 0, 0, 0, 6, 0],
    [8, 0, 0, 0, 6, 0, 0, 0, 3],
    [4, 0, 0, 8, 0, 3, 0, 0, 1],
    [7, 0, 0, 0, 2, 0, 0, 0, 6],
    [0, 6, 0, 0, 0, 0, 2, 8, 0],
    [0, 0, 0, 4, 1, 9, 0, 0, 5],
    [0, 0, 0, 0, 8, 0, 0, 7, 9]
]

# Colors
# ...

# Buttons
BUTTON_WIDTH = 150
BUTTON_HEIGHT = 50
AUTO_SOLVE_BUTTON_POS = (25, 475)
MANUAL_SOLVE_BUTTON_POS = (225, 475)
SUBMIT_BUTTON_POS = (425, 475)

# Manual input
manual_input_active = False
selected_cell = None

# Solving speed
SLOW_SPEED = 500
MEDIUM_SPEED = 100
FAST_SPEED = 10
current_speed = MEDIUM_SPEED

# Speed indicator
speed_indicator = "MEDIUM"

# Buttons rectangles
AUTO_SOLVE_BUTTON = pg.Rect(*AUTO_SOLVE_BUTTON_POS, BUTTON_WIDTH, BUTTON_HEIGHT)
MANUAL_SOLVE_BUTTON = pg.Rect(*MANUAL_SOLVE_BUTTON_POS, BUTTON_WIDTH, BUTTON_HEIGHT)
SUBMIT_BUTTON = pg.Rect(*SUBMIT_BUTTON_POS, BUTTON_WIDTH, BUTTON_HEIGHT)


def is_valid_move(grid, row, col, num):
    # Check if the number is not in the same row or column
    for i in range(GRID_SIZE):
        if grid[row][i] == num or grid[i][col] == num:
            return False

    # Check if the number is not in the same 3x3 box
    box_row, box_col = 3 * (row // 3), 3 * (col // 3)
    for i in range(3):
        for j in range(3):
            if grid[box_row + i][box_col + j] == num:
                return False

    return True


def solve_sudoku(grid):
    empty_cell = find_empty_cell(grid)
    if not empty_cell:
        return True

    row, col = empty_cell
    for num in range(1, 10):
        if is_valid_move(grid, row, col, num):
            grid[row][col] = num

            # Visualize the backtracking process
            draw_grid(grid)
            pg.display.update()
            pg.time.delay(current_speed)

            if solve_sudoku(grid):
                return True

            grid[row][col] = 0

    return False


def find_empty_cell(grid):
    for i in range(GRID_SIZE):
        for j in range(GRID_SIZE):
            if grid[i][j] == 0:
                return i, j
    return None


def draw_button(text, pos, active=False):
    button_color = GREEN if active else LIGHT_GREEN
    pg.draw.rect(window, button_color, (pos[0], pos[1], BUTTON_WIDTH, BUTTON_HEIGHT))
    font = pg.font.Font(None, 30)
    text_surface = font.render(text, True, BLACK)
    text_rect = text_surface.get_rect(center=(pos[0] + BUTTON_WIDTH // 2, pos[1] + BUTTON_HEIGHT // 2))
    window.blit(text_surface, text_rect)


def draw_grid(grid):
    for i in range(GRID_SIZE):
        for j in range(GRID_SIZE):
            cell_value = grid[i][j]
            cell_color = WHITE if cell_value != 0 else GRAY
            pg.draw.rect(window, cell_color, (j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE))
            if cell_value != 0:
                font = pg.font.Font(None, 36)
                text = font.render(str(cell_value), True, BLACK)
                text_rect = text.get_rect(center=(j * CELL_SIZE + CELL_SIZE // 2, i * CELL_SIZE + CELL_SIZE // 2))
                window.blit(text, text_rect)

    # Draw grid lines
    for i in range(GRID_SIZE + 1):
        thickness = 2 if i % 3 == 0 else 1
        pg.draw.line(window, BLACK, (0, i * CELL_SIZE), (WINDOW_SIZE, i * CELL_SIZE), thickness)
        pg.draw.line(window, BLACK, (i * CELL_SIZE, 0), (i * CELL_SIZE, WINDOW_SIZE), thickness)


def is_valid_solution(grid):
    # Check rows, columns, and 3x3 boxes for duplicate numbers
    for i in range(GRID_SIZE):
        row = set()
        col = set()
        box = set()
        for j in range(GRID_SIZE):
            if grid[i][j] in row or grid[j][i] in col or grid[(i // 3) * 3 + j // 3][(i % 3) * 3 + j % 3] in box:
                return False
            row.add(grid[i][j])
            col.add(grid[j][i])
            box.add(grid[(i // 3) * 3 + j // 3][(i % 3) * 3 + j % 3])
    return True


def auto_solve():
    global manual_input_active
    manual_input_active = False
    solve_sudoku(sudoku_grid)
    if is_valid_solution(sudoku_grid):
        print("Sudoku solved!")
    else:
        print("Sudoku is unsolvable.")


def manual_solve():
    global manual_input_active
    manual_input_active = True
    selected_cell = None


def check_solution():
    global manual_input_active
    manual_input_active = False
    if is_valid_solution(sudoku_grid):
        print("Congratulations! You solved the Sudoku.")
    else:
        print("Sorry, the solution is incorrect. Keep trying.")


def update_speed_indicator():
    global speed_indicator
    if current_speed == SLOW_SPEED:
        speed_indicator = "SLOW"
    elif current_speed == MEDIUM_SPEED:
        speed_indicator = "MEDIUM"
    elif current_speed == FAST_SPEED:
        speed_indicator = "FAST"

def main():
    global manual_input_active, selected_cell, current_speed

    while True:
        for event in pg.event.get():
            if event.type == pg.QUIT:
                pg.quit()
                sys.exit()
            elif event.type == pg.MOUSEBUTTONDOWN:
                if not manual_input_active:
                    # Check if auto solve button is clicked
                    if AUTO_SOLVE_BUTTON.collidepoint(event.pos):
                        auto_solve()

                    # Check if manual solve button is clicked
                    elif MANUAL_SOLVE_BUTTON.collidepoint(event.pos):
                        manual_solve()

                    # Check if submit button is clicked
                    elif SUBMIT_BUTTON.collidepoint(event.pos):
                        check_solution()

                else:
                    # Check for cell selection during manual input
                    x, y = event.pos
                    if x < WINDOW_SIZE and y < WINDOW_SIZE:
                        i, j = y // CELL_SIZE, x // CELL_SIZE
                        if sudoku_grid[i][j] == 0:
                            selected_cell = (i, j)
                        else:
                            selected_cell = None

            elif event.type == pg.KEYDOWN:
                if not manual_input_active:
                    if event.key == pg.K_a:
                        auto_solve()
                    elif event.key == pg.K_m:
                        manual_solve()
                    elif event.key == pg.K_RETURN:
                        check_solution()
                    elif event.key == pg.K_UP:  # Scroll Up
                        if current_speed == SLOW_SPEED:
                            current_speed = MEDIUM_SPEED
                        elif current_speed == MEDIUM_SPEED:
                            current_speed = FAST_SPEED
                        update_speed_indicator()
                    elif event.key == pg.K_DOWN:  # Scroll Down
                        if current_speed == FAST_SPEED:
                            current_speed = MEDIUM_SPEED
                        elif current_speed == MEDIUM_SPEED:
                            current_speed = SLOW_SPEED
                        update_speed_indicator()
                    elif event.key == pg.K_q:  # Quit (close window)
                        pg.quit()
                        sys.exit()
                else:
                    if event.key == pg.K_q:  # Quit (close window)
                        pg.quit()
                        sys.exit()
                    # Allow the user to input numbers (manual solving)
                    if event.unicode.isdigit() and selected_cell:
                        i, j = selected_cell
                        sudoku_grid[i][j] = int(event.unicode)

                    elif event.key == pg.K_BACKSPACE and selected_cell:
                        # Clear the selected cell (set it to 0)
                        i, j = selected_cell
                        sudoku_grid[i][j] = 0

        # Clear the window and draw the grid
        window.fill(WHITE)
        draw_grid(sudoku_grid)

        # Draw buttons
        draw_button("Auto Solve (A)", AUTO_SOLVE_BUTTON_POS)
        draw_button("Manual Solve (M)", MANUAL_SOLVE_BUTTON_POS, manual_input_active)
        if manual_input_active:
            draw_button("Submit", SUBMIT_BUTTON_POS)

        # Draw speed indicator
        font = pg.font.Font(None, 30)
        speed_text = font.render(f"Speed: {speed_indicator}", True, BLACK)
        speed_rect = speed_text.get_rect(center=(WINDOW_SIZE // 2, 500))
        window.blit(speed_text, speed_rect)

        # Update the display
        pg.display.update()

        # Delay based on the current speed
        pg.time.delay(current_speed)


if __name__ == "__main__":
    main()