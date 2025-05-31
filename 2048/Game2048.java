
import java.util.*;

public class Game2048 {

    Random rand = new Random();

    private final int boardSize = 4;    //Board size is adjustable
    private final int[][] board = new int[boardSize][boardSize];

    public void printBoard() {
        for (int[] row : board) {
            for (int num : row) {
                System.out.print(num + "  ");
            }
            System.out.println();
        }
    }

    public void gameLoop() {

        Scanner scanner = new Scanner(System.in);

        startGame();
        printBoard();

        while (canMove()) {
            System.out.print("Enter move (w/a/s/d): ");
            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "a":
                    left();
                    break;
                case "d":
                    right();
                    break;
                case "w":
                    up();
                    break;
                case "s":
                    down();
                    break;
                default:
                    System.out.println("Invalid input!");
                    continue;
            }

            addTile();
            printBoard();
        }

        System.out.println("Game over!");
        scanner.close();
    }

    public void startGame() {
        addTile();
        addTile();
    }

    public void addTile() {

        List<int[]> emptyCells = new ArrayList<>();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == 0) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }

        if (emptyCells.isEmpty()) {
            return;
        }

        int[] pos = emptyCells.get(rand.nextInt(emptyCells.size()));
        int x = pos[0];
        int y = pos[1];

        board[x][y] = 2;

        int r = rand.nextInt(100);

        // %80 = 2, %19 = 4, %1 = 8
        if (r < 80) {
            board[x][y] = 2;
        } else if (r < 99) {
            board[x][y] = 4;
        } else {
            board[x][y] = 8;
        }
    }

    public boolean canMove() {
        for (int i = 0; i < boardSize; i++) {

            //Check if there are any empty cell
            for (int j = 0; j < boardSize; j++) {

                if (board[i][j] == 0) {
                    return true;
                }
            }
            // Check if there are any adjacent equal numbers in the row
            for (int j = 0; j < boardSize - 1; j++) {
                if (board[i][j] == board[i][j + 1]) {
                    return true;
                }
            }
            // Check if there are any adjacent equal numbers in the column
            for (int j = 0; j < boardSize - 1; j++) {
                if (board[j][i] == board[j + 1][i]) {
                    return true;
                }
            }

        }

        return false;
    }

    public void original() { //original left method

        for (int i = 0; i < boardSize; i++) {

            int[] row = compressLeft(board[i]);

            for (int j = 0; j < row.length - 1; j++) {
                if (row[j] != 0 && row[j] == row[j + 1]) {
                    row[j] = row[j] * 2;
                    row[j + 1] = 0;
                }
            }

            board[i] = compressLeft(row);
        }
    }

    public int[] compressLeft(int[] row) {

        int[] newRow = new int[row.length];
        int index = 0;

        for (int num : row) {
            if (num != 0) {
                newRow[index++] = num;
            }
        }
        return newRow;
    }

    public void left() {
        original();
    }

    public void right() {

        mirror();
        original();
        mirror();

    }

    public void mirror() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize / 2; j++) {
                int temp = board[i][j];
                board[i][j] = board[i][boardSize - 1 - j];
                board[i][boardSize - 1 - j] = temp;
            }
        }
    }

    public void up() {
        transpose();
        original();
        transpose();
    }

    public void down() {
        transpose();
        mirror();
        original();
        mirror();
        transpose();
    }

    public void transpose() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = i + 1; j < boardSize; j++) {
                int temp = board[i][j];
                board[i][j] = board[j][i];
                board[j][i] = temp;
            }
        }

    }

    public static void main(String[] args) {

        Game2048 game = new Game2048();

        game.gameLoop();

    }
}
