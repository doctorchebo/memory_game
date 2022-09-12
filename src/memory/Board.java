package memory;

import uiComponents.UIFactory;
import uiComponents.UIHelpers;
import uiComponents.UIType;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Board {

    JFrame frame;
    JPanel panelTitle;
    JPanel panelGrid;
    JPanel panelControl;
    JButton new_game, solve, about;
    ButtonGame buttonLastClicked;
    Images images;
    UIFactory uiFactory;
    FontEnlarger fontEnlarger;
    List<ButtonGame> listButtons;
    UIHelpers uiHelpers;
    JLabel labelTitle;

    Integer count = 0;
    Integer numberOfClicks;
    Integer pairsFound;
    ArrayList shuffledList;


    public Board(UIFactory uiFactory,
                 FontEnlarger fontEnlarger,
                 Images images,
                 UIHelpers uiHelpers){
        this.images = images;
        this.uiFactory = uiFactory;
        this.fontEnlarger = fontEnlarger;
        this.uiHelpers = uiHelpers;
        shuffle();
        createLayout();
        addActionListeners();
    }

    public void addButtonActionListeners(ButtonGame buttonItem) {
        buttonItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buttonItem.id == 0){
                    return;
                }
                // rule
                // if there was a repeated click on the same button it is not worth...
                if(buttonItem.equals(buttonLastClicked)) return;

                labelTitle.setText(GameConstants.COUNT_LABEL + ++numberOfClicks);

                buttonItem.setIcon(images.createIcon(buttonItem.id));

                if(buttonLastClicked == null){
                    buttonLastClicked = buttonItem;
                    return;
                }

                if(Objects.equals(buttonItem.id, buttonLastClicked.id)){

                    buttonItem.setIcon(images.getIcon(IconType.SUCCESS));
                    buttonItem.id = 0;

                    buttonLastClicked.setIcon(images.getIcon(IconType.SUCCESS));
                    buttonLastClicked.id = 0;

                    buttonLastClicked = null;
                    pairsFound++;
                    if(pairsFound >= GameConstants.NUMBER_OF_PAIRS){
                        solve(true);
                    }

                }else{
                    buttonLastClicked.setIcon(images.getIcon(IconType.UNKNOWN));
                    buttonLastClicked = buttonItem;
                }
            }
        });
    }

    public void addActionListeners() {
        new_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });

        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve(false);
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,GameConstants.ABOUT_LABEL);
            }
        });
    }

    private void createLayout() {
        frame = (JFrame) uiFactory.getUIElement(UIType.FRAME).getElement(GameConstants.FRAME_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        labelTitle = (JLabel) uiFactory.getUIElement(UIType.LABEL).getElement(GameConstants.COUNT_LABEL + count);
        fontEnlarger.enlargeFont(labelTitle, 2);

        panelControl = (JPanel) uiFactory.getPanelFlowElement().getElement("CENTER");
        panelControl.setBorder(new BevelBorder(BevelBorder.RAISED));

        new_game = (JButton) uiFactory.getUIElement(UIType.BUTTON).getElement(GameConstants.NEW_GAME_BTN);
        fontEnlarger.enlargeFont(new_game, 2);

        solve = (JButton) uiFactory.getUIElement(UIType.BUTTON).getElement(GameConstants.SOLVE_BTN);
        fontEnlarger.enlargeFont(solve, 2);

        about = (JButton) uiFactory.getUIElement(UIType.BUTTON).getElement(GameConstants.ABOUT_BTN);
        fontEnlarger.enlargeFont(about, 2);
        frame.add(panelControl,BorderLayout.SOUTH);

        panelControl.add(new_game);
        panelControl.add(solve);
        panelControl.add(about);

        panelTitle = (JPanel) uiFactory.getPanelFlowElement().getElement("LEADING");
        panelTitle.setBorder(new BevelBorder(BevelBorder.RAISED));
        panelTitle.add(labelTitle);
        frame.add(panelTitle,BorderLayout.NORTH);

        // grid principal
        panelGrid = (JPanel) uiFactory.getPanelGridElement().getElement("GRID");
        panelGrid.setBorder(new BevelBorder(BevelBorder.RAISED));

        listButtons = new ArrayList<>();
        buttonLastClicked = null;
        int x = 0;
        System.out.println(shuffledList);
        for(int i = 0; i < GameConstants.NUMBER_OF_COLUMNS; i++){
            for(int j = 0; j < GameConstants.NUMBER_OF_ROWS; j++){
                Integer randomNum = (Integer) shuffledList.get(x);
                x++;
                ButtonGame buttonItem = new ButtonGame(randomNum);
                buttonItem.setIcon(images.getIcon(IconType.UNKNOWN));

                panelGrid.add(buttonItem, uiHelpers.generateConstraints(i , j));

                // list of buttons used in the Game
                listButtons.add(buttonItem);
                addButtonActionListeners(buttonItem);
            }
        }
        frame.add(panelGrid,BorderLayout.CENTER);
        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
        frame.setVisible(true);
    }

    public void newGame() {
        shuffle();
        count = 0;
        labelTitle.setText(GameConstants.COUNT_LABEL + count);
        buttonLastClicked = null;

        for(int i = 0; i < listButtons.size();i++){
            ButtonGame button = listButtons.get(i);
            button.id = (Integer) shuffledList.get(i);
            button.setIcon(images.getIcon(IconType.UNKNOWN));
            listButtons.set(i, button);
        }
        panelGrid.repaint();
    }

    public void shuffle() {
        numberOfClicks = 0;
        pairsFound = 0;
        shuffledList = new ArrayList<>();

        for (int i = 1; i <= (GameConstants.NUMBER_OF_PAIRS); i++) {
            shuffledList.add(i);
            shuffledList.add(i);
        }
        Collections.shuffle(shuffledList);
    }

    public void solve(Boolean showNumOfClicks) {
        if(numberOfClicks == -1) return;
        labelTitle.setText(GameConstants.COUNT_LABEL +
                (showNumOfClicks? numberOfClicks.toString(): GameConstants.SOLVE_MESSAGE));

        numberOfClicks = -1;
        pairsFound = 12;
        buttonLastClicked = null;

        for(int i = 0; i < listButtons.size();i++){
            ButtonGame button = listButtons.get(i);
            button.setIcon(images.createIcon((Integer) shuffledList.get(i)));
            button.id = 0;
            listButtons.set(i, button);
        }
        panelGrid.repaint();
    }
}
