package memory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Game implements ActionListener {
	int numOfClicks;
	Integer intQtdOpened;
	Integer intCombined;

	public Game(){
		
	}

	public void newGame(){
		Collections.shuffle(listShuffle);
		intQtdOpened = 0;
		intCombined = 0;
		labelTitle.setText("Number of Clicks: 0");
		buttonLastClicked = null;

		for(int i = 0; i < listButtons.size();i++){
			Memory.buttonGame button = listButtons.get(i);
			button.iCod = (Integer) listShuffle.get(i);
			button.setIcon(imagens.IconFactory(-1));
			listButtons.set(i, button);
		}
		panelGrid.repaint();
	}

	public solve(){
		if(intQtdOpened == -1) return;
		labelTitle.setText("Number of Clicks: " +
				(bMostrarCliques? intQtdOpened.toString():"Auto Resolution"));

		intQtdOpened = -1;
		intCombined = 12;
		buttonLastClicked = null;

		for(int i = 0; i < listButtons.size();i++){
			Memory.buttonGame button = listButtons.get(i);
			button.setIcon(imagens.IconFactory((Integer) listShuffle.get(i)));
			button.iCod = 0;
			listButtons.set(i, button);
		}
		panelGrid.repaint();
	}

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
	
}
