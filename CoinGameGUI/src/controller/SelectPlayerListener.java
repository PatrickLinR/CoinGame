package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.enumeration.BetType;
import model.enumeration.GameStatus;
import model.interfaces.Player;
import view.AppFrame;

public class SelectPlayerListener implements ActionListener {

	private AppFrame appFrame;

	public SelectPlayerListener(AppFrame appFrame) {
		// TODO Auto-generated constructor stub
		this.appFrame = appFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		appFrame.setTempPlayer(appFrame.getSelectedPlayer());
		JComboBox<Player> playerBox = (JComboBox<Player>) e.getSource();
		Player player = (Player) playerBox.getSelectedItem();
		this.appFrame.updatePlayerResult(player);
		this.appFrame.updatePlayerStatusBar(player);
		
		
	}

}
