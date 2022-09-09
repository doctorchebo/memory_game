package memory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class Game {
    GameConstants gameConstants;
    Board board;
    Card btnLastClicked;
    Integer pairsFound;

    public Game(Board board) {
        this.board = board;
        board.shuffle();
        board.createLayout();
        addActionListeners();
        addCardActionListeners(board.cardList);
    }
    public void newGame(){
        board.shuffle();
        board.labelTitle.setText("Number of Clicks: 0");
        btnLastClicked = null;

        for(int i = 0; i < board.cardList.size(); i++){
            Card button = board.cardList.get(i);
            button.id = (Integer) board.shuffledList.get(i);
            button.setIcon(board.image.getIcon(IconType.UNKNOWN));
            board.cardList.set(i, button);
        }
        board.panelGrid.repaint();
    }

    public void solve(Boolean showNumOfClicks) {
        if(board.numberOfClicks == -1) return;
        board.labelTitle.setText("Number of Clicks: " +
                (showNumOfClicks? board.numberOfClicks.toString():"Auto Resolution"));

        board.numberOfClicks = -1;
        pairsFound = gameConstants.NUMBER_OF_PAIRS;
        btnLastClicked = null;

        for(int i = 0; i < board.cardList.size(); i++){
            Card button = board.cardList.get(i);
            button.setIcon(board.image.createIcon((Integer) board.shuffledList.get(i)));
            button.id = 0;
            board.cardList.set(i, button);
        }
        board.panelGrid.repaint();
    }

    public void addActionListeners(){
        board.new_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });

        board.solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve(false);
            }
        });

        board.about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(board.frame,"Just For Fun");
            }
        });
    }

    public void addCardActionListeners(List<Card> cards) {
        for(Card card: cards){
            card.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(card.id == 0){
                        return;
                    }
                    // rule
                    // if there was a repeated click on the same button it is not worth...
                    if(card.equals(btnLastClicked)) return;

                    board.labelTitle.setText("Number of Clicks: " + ++board.numberOfClicks);

                    card.setIcon(board.image.createIcon(card.id));

                    if(btnLastClicked == null){
                        btnLastClicked = card;
                        return;
                    }

                    if(Objects.equals(card.id, btnLastClicked.id)){

                        card.setIcon(board.image.getIcon(IconType.SUCCESS));
                        card.id = 0;

                        btnLastClicked.setIcon(board.image.getIcon(IconType.SUCCESS));
                        btnLastClicked.id = 0;

                        btnLastClicked = null;
                        board.pairsFound++;
                        if(board.pairsFound >= gameConstants.NUMBER_OF_PAIRS){
                            solve(true);
                        }

                    }else{
                        btnLastClicked.setIcon(board.image.getIcon(IconType.UNKNOWN));
                        btnLastClicked = card;
                    }
                }
            });
        }
    }
}
