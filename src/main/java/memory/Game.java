package memory;


import org.json.JSONObject;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Game implements DataSaver {
    GameConstants gameConstants;
    Board board;
    Card btnLastClicked;
    Integer pairsFound;
    Integer numberOfClicks;

    Boolean started;

    public Game(Board board) {
        this.board = board;
        numberOfClicks = 0;
        board.shuffle();
        board.createLayout();
        addActionListeners();
        addCardActionListeners(board.cardList);
        started = true;
    }

    public void newGame(){
        started = true;
        numberOfClicks = 0;
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
        board.labelTitle.setText("Number of Clicks: " +
                (showNumOfClicks? numberOfClicks.toString():"Auto Resolution"));
        pairsFound = gameConstants.NUMBER_OF_PAIRS;
        btnLastClicked = null;

        for(int i = 0; i < board.cardList.size(); i++){
            Card button = board.cardList.get(i);
            button.setIcon(board.image.createIcon((Integer) board.shuffledList.get(i)));
            button.id = 0;
            board.cardList.set(i, button);
        }
        board.panelGrid.repaint();
        save();
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
                save();
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

                    board.labelTitle.setText("Number of Clicks: " + ++numberOfClicks);

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
                            save();
                        }

                    }else{
                        btnLastClicked.setIcon(board.image.getIcon(IconType.UNKNOWN));
                        btnLastClicked = card;
                    }
                }
            });
        }
    }

    @Override
    public void save() {
        String destination = System.getProperty("user.dir") + "/src/main/resources/data.json";
        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader(destination));
            JSONArray db = (JSONArray) obj;

            JSONObject data = new JSONObject();
            data.put("date", new Date().toString());
            data.put("numOfClicks", numberOfClicks);
            data.put("os", System.getProperty("os.name"));
            db.add(data);
            System.out.println(data);
            try (PrintWriter out = new PrintWriter(new FileWriter(destination))) {
                out.write(db.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }


    }
}
