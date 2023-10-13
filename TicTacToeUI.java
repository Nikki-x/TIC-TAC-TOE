package tictactoe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class TicTacToeUI extends JFrame {
    private TicTacToeP game;

    public TicTacToeUI() {
        super("Tic Tac Toe");
        this.game = new TicTacToeP();
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JPanel gamePanel = new JPanel(new GridLayout(3, 3));
        for (int i = 1; i <= 9; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.addActionListener(e -> playTurn(e));
            gamePanel.add(btn);
        }
        this.add(gamePanel, BorderLayout.CENTER);
    }

    private void playTurn(ActionEvent e) {
        JButton btn = (JButton)(e.getSource());
        int i = Integer.parseInt(btn.getText());
        btn.setEnabled(false);
        int across = (i-1)%3;
        int down = (i-1)/3;
        across++;
        down++;
        // System.out.println(across +  " " + down);
        btn.setText(game.getPlayerTurn());
        game.takeTurn(across,down,game.getPlayerTurn());
        if(game.isDone()){
            JOptionPane.showMessageDialog(null, game.winner() + " Won!");
            this.dispose();
        }
    }

    public TicTacToeP getGame(){
        return this.game;
    }

}
