package memory;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Board {
    private JFrame frame;
    private JButton buttonNew, buttonSolve, buttonAbout;
    private ImageCard buttonLastClicked;
    private JPanel panelTitle, panelGrid, panelControl;
    private List<JButton> cardList;
    private JLabel labelTitle;
    private Images images;
    Integer intQtdOpened;
    Integer intCombined;
    ArrayList shuffledList;

    public Board() {
        try {
            createLayout();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void basicLayoutElements(){
        //frame
        frame = new JFrame("Memory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title
        labelTitle = new JLabel("Number of Clicks: 0");
        enlargeFont(labelTitle, 2);

        panelTitle = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panelTitle.setBorder(new BevelBorder(BevelBorder.RAISED));
        panelTitle.add(labelTitle);
        //adding title to the frame
        frame.add(panelTitle,BorderLayout.NORTH);

        // Controls
        panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER
                , 50, 0));
        panelControl.setBorder(new BevelBorder(BevelBorder.RAISED));
        buttonNew = new JButton("New Game");
        enlargeFont(buttonNew, 2);
        panelControl.add(buttonNew);
        buttonSolve = new JButton("Solve");
        enlargeFont(buttonSolve, 2);
        panelControl.add(buttonSolve);
        buttonAbout = new JButton("About");
        enlargeFont(buttonAbout, 2);
        panelControl.add(buttonAbout);
        frame.add(panelControl,BorderLayout.SOUTH);
    }
    public void createLayout() throws IOException {
        //creating initial variables for the layout
        images = new Images();
        intQtdOpened = 0;
        intCombined = 0;
        shuffledList = new ArrayList<>();

        // shuffling list of 24 numbers from 1 to 12
        for (int i = 1; i <= 12; i++) {
            shuffledList.add(i);
            shuffledList.add(i);
        }
        Collections.shuffle(shuffledList);

        //creating layout
        basicLayoutElements();

        // grid principal
        panelGrid = new JPanel(new GridBagLayout());
        panelGrid.setBorder(new BevelBorder(BevelBorder.RAISED));
        // 6 x 4 = 24
        // 24 have two possibilities of 12
        cardList = new ArrayList<>();
        buttonLastClicked = null;
        int x = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 4; j++){
                Integer intNumSorteado = (Integer) shuffledList.get(x);
                ImageCard cardItem = new ImageCard(intNumSorteado);

                cardItem.setIcon(images.IconFactory(-1));
                x++;


                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.weightx = .5;
                c.weighty = .5;
                c.gridx = i;
                c.gridy = j;
                panelGrid.add(cardItem, c);

                // list of cards used in the game
                cardList.add(cardItem);

                cardItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(cardItem.iCod == 0){
                            return;
                        }
                        // rule
                        // if there was a repeated click on the same button it is not worth...
                        if(cardItem.equals(buttonLastClicked)) return;

                        labelTitle.setText("Number of Clicks: " + ++intQtdOpened);

                        cardItem.setIcon(images.IconFactory(cardItem.iCod));

                        if(buttonLastClicked == null){
                            buttonLastClicked = cardItem;
                            return;
                        }

                        if(Objects.equals(cardItem.iCod, buttonLastClicked.iCod)){

                            cardItem.setIcon(images.IconFactory(0));
                            cardItem.iCod = 0;

                            buttonLastClicked.setIcon(images.IconFactory(0));
                            buttonLastClicked.iCod = 0;

                            buttonLastClicked = null;
                            intCombined++;
//                            if(intCombined >= 12){
//                                solve(true);
//                            }

                        }else{
                            buttonLastClicked.setIcon(images.IconFactory(-1));
                            buttonLastClicked = cardItem;
                        }
                    }
                });

//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if(cardItem.iCod == 0){
//                        return;
//                    }
//                    // rule
//                    // if there was a repeated click on the same button it is not worth...
//                    if(cardItem.equals(buttonLastClicked)) return;

//		labelTitle.setText("Number of Clicks: " + ++intQtdOpened);

                    cardItem.setIcon(images.IconFactory(cardItem.iCod));

                    if(buttonLastClicked == null){
                        buttonLastClicked = cardItem;
                        return;
                    }

                    if(Objects.equals(cardItem.iCod, buttonLastClicked.iCod)){

                        cardItem.setIcon(images.IconFactory(0));
                        cardItem.iCod = 0;

                        buttonLastClicked.setIcon(images.IconFactory(0));
                        buttonLastClicked.iCod = 0;

                        buttonLastClicked = null;
                        intCombined++;
//                        if(intCombined >= 12){
//                            solve(true);
//                        }


                    }else{
                        buttonLastClicked.setIcon(images.IconFactory(-1));
                        buttonLastClicked = cardItem;
                    }
                }
            }
        }
        frame.add(panelGrid,BorderLayout.CENTER);
        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
        frame.setVisible(true);
    }

    private void enlargeFont(java.awt.Component c, float factor) {
        c.setFont(c.getFont().deriveFont(c.getFont().getSize() * factor));
    }

}
