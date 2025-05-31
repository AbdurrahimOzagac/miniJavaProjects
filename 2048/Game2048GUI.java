import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game2048GUI extends JFrame implements KeyListener { //From ChatGPT

    private Game2048 game;
    private final int SIZE = 4;
    private JLabel[][] labels;

    public Game2048GUI() {
        game = new Game2048();
        labels = new JLabel[SIZE][SIZE];

        setTitle("2048 Swing");
        setSize(400, 400);
        setLayout(new GridLayout(SIZE, SIZE));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initBoard();
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);

        game.startGame();
        updateBoard();
    }

    private void initBoard() {
        Font font = new Font("Arial", Font.BOLD, 24);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                labels[i][j] = new JLabel("", SwingConstants.CENTER);
                labels[i][j].setFont(font);
                labels[i][j].setOpaque(true);
                labels[i][j].setBackground(Color.LIGHT_GRAY);
                labels[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                add(labels[i][j]);
            }
        }
    }

    private void updateBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int val = game.board[i][j];
                labels[i][j].setText(val == 0 ? "" : String.valueOf(val));
                labels[i][j].setBackground(getColor(val));
            }
        }
    }

    private Color getColor(int val) {
        switch (val) {
            case 2: return new Color(0xeee4da);
            case 4: return new Color(0xede0c8);
            case 8: return new Color(0xf2b179);
            case 16: return new Color(0xf59563);
            case 32: return new Color(0xf67c5f);
            case 64: return new Color(0xf65e3b);
            case 128: return new Color(0xedcf72);
            case 256: return new Color(0xedcc61);
            case 512: return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);
            default: return Color.LIGHT_GRAY;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean moved = false;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                game.left();
                moved = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                game.right();
                moved = true;
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                game.up();
                moved = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                game.down();
                moved = true;
                break;
        }

        if (moved) {
            game.addTile();
            updateBoard();

            if (!game.canMove()) {
                JOptionPane.showMessageDialog(this, "Game Over!");
            }
        }
    }

    @Override public void keyReleased(KeyEvent e) { }
    @Override public void keyTyped(KeyEvent e) { }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game2048GUI::new);
    }
}
