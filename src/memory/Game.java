package memory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Game {
	private Board board;
	Integer intQtdOpened;
	Integer intCombined;

	ArrayList shuffledList;

	private Images images;
	private ImageCard buttonLastClicked;
	private List<ImageCard> cardsList;

	public Game(Board board){
		this.board = board;
	}

	public void newGame(){
		Collections.shuffle(shuffledList);
		intQtdOpened = 0;
		intCombined = 0;
//		labelTitle.setText("Number of Clicks: 0");
		buttonLastClicked = null;

		for(int i = 0; i < cardsList.size(); i++){
			ImageCard button = cardsList.get(i);
			button.iCod = (Integer) shuffledList.get(i);
			button.setIcon(images.IconFactory(-1));
			cardsList.set(i, button);
		}
//		panelGrid.repaint();
	}

	public void solve(Boolean showClicks){
		if(intQtdOpened == -1) return;
//		labelTitle.setText("Number of Clicks: " +
//				(showClicks? intQtdOpened.toString():"Auto Resolution"));

		intQtdOpened = -1;
		intCombined = 12;
		buttonLastClicked = null;

		for(int i = 0; i < cardsList.size(); i++){
			ImageCard card = cardsList.get(i);
			card.setIcon(images.IconFactory((Integer) shuffledList.get(i)));
			card.iCod = 0;
			cardsList.set(i, card);
		}
//		panelGrid.repaint();
	}

//	buttonNew.addActionListener(new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			newGame();
//		}
//	});
//
//        buttonSolve.addActionListener(new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			solve(false);
//		}
//	});
//
//        buttonAbout.addActionListener(new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			JOptionPane.showMessageDialog(frame,"Just For Fun");
//		}
//	});

}
