import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {

    private char currentPlayer = 'X';
    private TicTacToeButton[][] buttons = new TicTacToeButton[3][3];
    private int moveCount = 0;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TicTacToeButton button = (TicTacToeButton) e.getSource();

                if (button.getText().equals("")) {
                    button.setText(String.valueOf(currentPlayer));
                    moveCount++;

                    if (checkWin()) {
                        int result = JOptionPane.showConfirmDialog(null, currentPlayer + " wins! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            resetBoard();
                        } else {
                            System.exit(0);
                        }
                        return;
                    } else if (moveCount == 9) {
                        int result = JOptionPane.showConfirmDialog(null, "Tie game! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            resetBoard();
                        } else {
                            System.exit(0);
                        }
                        return;
                    }

                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                } else {
                    JOptionPane.showMessageDialog(null, "This square is already taken!");
                }
            }
        };

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                TicTacToeButton button = new TicTacToeButton(row, col);
                button.setFont(new Font("Arial", Font.BOLD, 40));
                button.addActionListener(buttonListener);
                buttons[row][col] = button;
                boardPanel.add(button);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        quitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(quitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText())) {
                return true;
            }

            if (!buttons[0][i].getText().equals("") &&
                    buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[0][i].getText().equals(buttons[2][i].getText())) {
                return true;
            }
        }

        if (!buttons[0][0].getText().equals("") &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText())) {
            return true;
        }

        if (!buttons[0][2].getText().equals("") &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText())) {
            return true;
        }

        return false;
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        moveCount = 0;
        currentPlayer = 'X';
    }
}