package memory;

import uiComponents.UIFactory;
import uiComponents.UIHelpers;
import uiComponents.UIType;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    JFrame frame;
    JPanel panelTitle;
    JPanel panelGrid;
    JPanel panelControl;
    JButton new_game, solve, about;
    Card card;
    Image image;
    UIFactory uiFactory;
    FontEnlarger fontEnlarger;
    List<Card> cardList;
    UIHelpers uiHelpers;
    JLabel labelTitle;
    Integer count = 0;
    Integer numberOfClicks;

    Integer pairsFound;
    ArrayList shuffledList;


    public Board(UIFactory uiFactory,
                 FontEnlarger fontEnlarger,
                 Image image,
                 UIHelpers uiHelpers){
        this.image = image;
        this.uiFactory = uiFactory;
        this.fontEnlarger = fontEnlarger;
        this.uiHelpers = uiHelpers;
    }

    public void createLayout() {
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

        cardList = new ArrayList<>();
        int x = 0;
        System.out.println(shuffledList);
        for(int i = 0; i < GameConstants.NUMBER_OF_COLUMNS; i++){
            for(int j = 0; j < GameConstants.NUMBER_OF_ROWS; j++){
                Integer randomNum = (Integer) shuffledList.get(x);
                x++;
                card = new Card(randomNum);
                card.setIcon(image.getIcon(IconType.UNKNOWN));

                panelGrid.add(card, uiHelpers.generateConstraints(i , j));

                // list of buttons used in the Game
                cardList.add(card);
            }
        }
        frame.add(panelGrid,BorderLayout.CENTER);
        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
        frame.setVisible(true);
    }


    public void shuffle() {
//        numberOfClicks = 0;
        pairsFound = 0;
        shuffledList = new ArrayList<>();

        for (int i = 1; i <= (GameConstants.NUMBER_OF_PAIRS); i++) {
            shuffledList.add(i);
            shuffledList.add(i);
        }
        Collections.shuffle(shuffledList);
    }
}
