package memory;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Board {
    private JFrame frame;
    private JPanel panelTitle, panelGrid, panelControl;
    private JButton buttonNew, buttonSolve, buttonAbout;
    private ButtonGame buttonLastClicked;
    private Images images;
    private UIFactory uiFactory;
    private JLabel labelTitle;

    private FontEnlarger fontEnlarger;

    private List<ButtonGame> listButtons;

    Integer intQtdOpened;
    Integer intCombined;
    ArrayList shuffledList;

    public Board(UIFactory uiFactory, FontEnlarger fontEnlarger){
        this.uiFactory = uiFactory;
        this.fontEnlarger = fontEnlarger;

        createLayout();
        addActionListeners();
    }

    private void solve(Boolean showNumOfClicks){
        if(intQtdOpened == -1) return;
        labelTitle.setText("Number of Clicks: " +
                (showNumOfClicks? intQtdOpened.toString():"Auto Resolution"));

        intQtdOpened = -1;
        intCombined = 12;
        buttonLastClicked = null;

        for(int i = 0; i < listButtons.size();i++){
            ButtonGame button = listButtons.get(i);
            button.setIcon(images.IconFactory((Integer) shuffledList.get(i)));
            button.iCod = 0;
            listButtons.set(i, button);
        }
        panelGrid.repaint();
    }
    private void newGame(){
        getShuffledList();
        labelTitle.setText("Number of Clicks: 0");
        buttonLastClicked = null;

        for(int i = 0; i < listButtons.size();i++){
            ButtonGame button = listButtons.get(i);
            button.iCod = (Integer) shuffledList.get(i);
            button.setIcon(images.IconFactory(-1));
            listButtons.set(i, button);
        }
        panelGrid.repaint();
    }
    private void createLayout(){
        JFrame frame = (JFrame) uiFactory.getUIElement(UIType.FRAME, "Memory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Component label = (Component) uiFactory.getUIElement(UIType.LABEL, "Number of Clicks: 0");
        fontEnlarger.enlargeFont(label, 2);

        //needs to implement hgap 50, vgap 0;
        JPanel panelControl = (JPanel) uiFactory.getPanelFlowElement(FlowType.CENTER);
        panelControl.setBorder(new BevelBorder(BevelBorder.RAISED));

        JButton new_game = (JButton) uiFactory.getUIElement(UIType.BUTTON, "New Game");
        fontEnlarger.enlargeFont(new_game, 2);

        JButton solve = (JButton) uiFactory.getUIElement(UIType.BUTTON, "Solve");
        fontEnlarger.enlargeFont(solve, 2);

        JButton about = (JButton) uiFactory.getUIElement(UIType.BUTTON, "About");
        fontEnlarger.enlargeFont(about, 2);

        panelControl.add(new_game);
        panelControl.add(solve);
        panelControl.add(about);

        JPanel panelTitle = (JPanel) uiFactory.getPanelFlowElement(FlowType.LEADING);
        panelTitle.setBorder(new BevelBorder(BevelBorder.RAISED));
        panelTitle.add(labelTitle);
        frame.add(panelTitle,BorderLayout.NORTH);

        // grid principal
        JPanel panelGrid = (JPanel) uiFactory.getPanelGridElement();
        panelGrid.setBorder(new BevelBorder(BevelBorder.RAISED));

        // 6 x 4 = 24
        // 24 have two possibilities of 12
        listButtons = new ArrayList<>();
        buttonLastClicked = null;
        int x = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 4; j++){
                Integer intNumSorteado = (Integer) shuffledList.get(x);
                Memory.buttonGame buttonItem = new Memory.buttonGame(intNumSorteado);

                buttonItem.setIcon(images.IconFactory(-1));
                x++;


                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.weightx = .5;
                c.weighty = .5;
                c.gridx = i;
                c.gridy = j;
                panelGrid.add(buttonItem, c);

                // list of buttons used in the Game
                listButtons.add(buttonItem);

                buttonItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(buttonItem.iCod == 0){
                            return;
                        }
                        // rule
                        // if there was a repeated click on the same button it is not worth...
                        if(buttonItem.equals(buttonLastClicked)) return;

                        labelTitle.setText("Number of Clicks: " + ++intQtdOpened);

                        buttonItem.setIcon(images.IconFactory(buttonItem.iCod));

                        if(buttonLastClicked == null){
                            buttonLastClicked = buttonItem;
                            return;
                        }

                        if(Objects.equals(buttonItem.iCod, buttonLastClicked.iCod)){

                            buttonItem.setIcon(images.IconFactory(0));
                            buttonItem.iCod = 0;

                            buttonLastClicked.setIcon(images.IconFactory(0));
                            buttonLastClicked.iCod = 0;

                            buttonLastClicked = null;
                            intCombined++;
                            if(intCombined >= 12){
                                solve(true);
                            }

                        }else{
                            buttonLastClicked.setIcon(images.IconFactory(-1));
                            buttonLastClicked = buttonItem;
                        }
                    }
                });
            }
        }
        frame.add(panelGrid,BorderLayout.CENTER);


        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
        frame.setVisible(true);


    }

    private void addActionListeners(){
        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });

        buttonSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve(false);
            }
        });

        buttonAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"Just For Fun");
            }
        });
    }


}
