package view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import controller.CancelBetListener;
import controller.PlaceBetListener;
import controller.SelectPlayerListener;
import controller.StartPlayerGameListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class CoinToolBar extends JToolBar{
	private JComboBox<Player> playersComboBox;
	private JButton startbtn;//start
	private JButton placeBetbtn;//bet
	private JButton cancelBetbtn;
	
	public CoinToolBar(AppFrame appFrame, GameEngine gameEngine) {
		Box toolBar = Box.createHorizontalBox();

		placeBetbtn = new JButton("Place Bet");
		cancelBetbtn = new JButton("Cancel Bet");
		startbtn = new JButton("Spin");
		
		playersComboBox = new JComboBox<>();
		playersComboBox.setPreferredSize(new Dimension(700, 0));
		playersComboBox.setMaximumSize(new Dimension(700, 50));
		playersComboBox.addActionListener(new SelectPlayerListener(appFrame));

		placeBetbtn.addActionListener(new PlaceBetListener(appFrame, gameEngine));
		cancelBetbtn.addActionListener(new CancelBetListener(appFrame, gameEngine));
		startbtn.addActionListener(new StartPlayerGameListener(appFrame));

		toolBar.add(Box.createHorizontalStrut(50));
		toolBar.add(new JLabel("Player: "));
		toolBar.add(playersComboBox);
		toolBar.add(Box.createHorizontalStrut(10));
		toolBar.add(placeBetbtn);
		toolBar.add(cancelBetbtn);
		toolBar.add(startbtn);
		toolBar.add(Box.createHorizontalStrut(10));
		
		toolBar.add(Box.createHorizontalGlue());
		
		this.add(toolBar);
	}

	public void unlockButtons(JButton btn) {
		btn.setEnabled(true);
	}

	public void lockButtons(JButton btn) {
		btn.setEnabled(false);
	}
	
	public void lockCombobox() {
		playersComboBox.setEnabled(false);
	}
	
	public void unlockCombobox() {
		playersComboBox.setEnabled(true);
	}
	
	public JButton getStartbtn() {
		return startbtn;
	}
	
	public JButton getBetbtn() {
		return placeBetbtn;
	}
	
	public JButton getCancelBetbtn() {
		return cancelBetbtn;
	}

	public void fillPlayers(Player player) {
		if(player != null) {
			playersComboBox.addItem(player);
			playersComboBox.setSelectedItem(player);
		}
	}
	
	public void removePlayer(Player player) {
		playersComboBox.removeItem(player);
	}
	
	public JComboBox<Player> getPlayersComboBox(){
		return playersComboBox;
	}
	
	public void setPlayersComboBox(JComboBox<Player> playersComboBox) {
		this.playersComboBox = playersComboBox;
	} 

}
