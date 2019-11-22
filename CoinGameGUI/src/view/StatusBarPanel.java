package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.enumeration.GameStatus;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class StatusBarPanel extends JPanel {
	
	private GameEngine engine;
	private JLabel playingLabel;
	private JLabel betLabel;
	private JLabel balanceLabel;
	private JLabel readyStatusLabel;	
	private Box infoBox;
	private Box statusBar;
	private Map<String, JLabel> coinList = new HashMap<String, JLabel>();
	
	public StatusBarPanel(GameEngine engine) {
		this.engine=engine;
		setLayout(new GridLayout(1, 2));
		
		playingLabel = new JLabel("Waiting for spinning");
		betLabel = new JLabel();
		balanceLabel = new JLabel();
		readyStatusLabel = new JLabel("Status:"+GameStatus.ADDPLAYERS.statusString());
       	statusBar = Box.createHorizontalBox();
       	statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		infoBox = Box.createHorizontalBox();
		infoBox.setBorder(BorderFactory.createLoweredBevelBorder());
		infoBox.add(playingLabel);
		statusBar.add(betLabel);
		statusBar.add(balanceLabel);
		statusBar.add(readyStatusLabel);		
		this.add(statusBar, BorderLayout.WEST);
		this.add(infoBox, BorderLayout.EAST);
	}
	
	public void switchLabel(Player player) {
		this.infoBox.removeAll();
		this.infoBox.add(this.coinList.get(player.getPlayerId()));
		repaint();
	}
	
	public void setPlayingLabel(String string) {
	   
		this.playingLabel.setText("Current Playing: " + string);
	}
	
//	public void setBetLabel(Player player) {
//		this.betLabel.setText(" Bet: " + player.getBet());
//	}
	
	public void setReadyStatusLabel(GameStatus status){
		this.readyStatusLabel.setText("Status: "+status.statusString());
	}
//	public void setBalanceLabel(Player player) {
//		this.balanceLabel.setText(" Balance: " + player.getPoints());
//	}
	

	public void clearCoinLabel() {
		for(Player p : this.engine.getAllPlayers()) {
			this.coinList.get(p.getPlayerId()).setText("");;
			this.coinList.get(p.getPlayerId()).repaint();
		}
	}

	public void createCoinLabel(Player player) {
		JLabel newCoinLabel = new JLabel();
		this.coinList.put(player.getPlayerId(), newCoinLabel);
	}

}
