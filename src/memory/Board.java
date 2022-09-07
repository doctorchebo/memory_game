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
    private JButton buttonNew, buttonSolve, buttonAbout;

    private buttonGame buttonLastClicked;


    private JPanel panelTitle, panelGrid, panelControl;
    private JButton buttonNew, buttonSolve, buttonAbout;

    private List<JButton> listButtons;
    private JLabel labelTitle;

    private Images imagens;

    Integer intQtdOpened;
    Integer intCombined;
    ArrayList  listShuffle;
    Integer iCod;

    public Board (){
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
        createLayout();
    }
    public void createLayout(){
        //creating initial variables for the layout
        imagens = new Images();
        intQtdOpened = 0;
        intCombined = 0;
        listShuffle = new ArrayList<>();


        // shuffling list of 24 numbers from 1 to 12
        for (int i = 1; i <= 12; i++) {
            listShuffle.add(i);
            listShuffle.add(i);
        }
        Collections.shuffle(listShuffle);


        //creating layout

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

        // grid principal
        panelGrid = new JPanel(new GridBagLayout());
        panelGrid.setBorder(new BevelBorder(BevelBorder.RAISED));
        // 6 x 4 = 24
        // 24 have two possibilities of 12
        listButtons = new ArrayList<>();
        buttonLastClicked = null;
        int x = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 4; j++){
                Integer intNumSorteado = (Integer) listShuffle.get(x);
                buttonGame buttonItem = new buttonGame(intNumSorteado);

                buttonItem.setIcon(imagens.IconFactory(-1));
                x++;


                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.weightx = .5;
                c.weighty = .5;
                c.gridx = i;
                c.gridy = j;
                panelGrid.add(buttonItem, c);

                // list botoes usado no novo jogo
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

                        buttonItem.setIcon(imagens.IconFactory(buttonItem.iCod));

                        if(buttonLastClicked == null){
                            buttonLastClicked = buttonItem;
                            return;
                        }




                        if(Objects.equals(buttonItem.iCod, buttonLastClicked.iCod)){

                            buttonItem.setIcon(imagens.IconFactory(0));
                            buttonItem.iCod = 0;

                            buttonLastClicked.setIcon(imagens.IconFactory(0));
                            buttonLastClicked.iCod = 0;

                            buttonLastClicked = null;
                            intCombined++;
                            if(intCombined >= 12){
                                Solve(true);
                            }

                        }else{
                            buttonLastClicked.setIcon(imagens.IconFactory(-1));
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


    private void enlargeFont(java.awt.Component c, float factor) {
        c.setFont(c.getFont().deriveFont(c.getFont().getSize() * factor));
    }

}
