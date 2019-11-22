package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.Player;
import view.AppFrame;

public class RemovePlayerListener implements ActionListener {

	private AppFrame appFrame;

	public RemovePlayerListener(AppFrame appFrame) {
		// TODO Auto-generated constructor stub
		this.appFrame = appFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Player removePlayer = appFrame.getSelectedPlayer();
		if(removePlayer!=null) {
			appFrame.deletePlayerStartList(removePlayer);
			appFrame.getsummaryPanel().removePlayer(removePlayer);
			appFrame.getToolBar().removePlayer(removePlayer);
			appFrame.getGameEngine().removePlayer(removePlayer);
			appFrame.checkAllPlayerSpin();
		}
	}

}
