package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel{
	private ArrayList<JPanel> playerPanel = new ArrayList<JPanel>();
	private ArrayList<JLabel> playerId = new ArrayList<JLabel>();
	private ArrayList<JLabel> playerName = new ArrayList<JLabel>();
	private ArrayList<JLabel> playerPoint = new ArrayList<JLabel>();
	private ArrayList<JLabel> playerBet = new ArrayList<JLabel>();
	private ArrayList<JLabel> playerBettype = new ArrayList<JLabel>();
	private ArrayList<JLabel> playerResult = new ArrayList<JLabel>();
	private ArrayList<JLabel> playerWinLoss = new ArrayList<JLabel>();
	private Map<Player, JPanel> players = new HashMap<Player, JPanel>();
	private Map<JPanel, JLabel> idLabel = new HashMap<JPanel, JLabel>();
	private Map<JPanel, JLabel> nameLabel = new HashMap<JPanel, JLabel>();
	private Map<JPanel, JLabel> pointLabel = new HashMap<JPanel, JLabel>();
	private Map<JPanel, JLabel> betLabel = new HashMap<JPanel, JLabel>();
	private Map<JPanel, JLabel> bettypeLabel = new HashMap<JPanel, JLabel>();
	private Map<JPanel, JLabel> resultLabel = new HashMap<JPanel, JLabel>();
	private Map<JPanel, JLabel> winLossLabel = new HashMap<JPanel, JLabel>();
	private ArrayList<Player> allPlayer = new ArrayList<Player>();
	private JScrollPane summaryScroll;
	private Box summary;
	private int numOfPanel;
	private AppFrame appFrame;
	private int winpoint;
	
	
	public SummaryPanel(AppFrame appFrame) {
		this.appFrame = appFrame;
		numOfPanel = 0;
		
		summary = Box.createVerticalBox();
		summaryScroll = new JScrollPane(summary);
		summaryScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		summaryScroll.setAlignmentY(TOP_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(280, 0));
		setBorder(BorderFactory.createTitledBorder("Summary"));
		this.add(summaryScroll);
	}
	
	public void updatePlayerInfo(Player player) {
		JPanel targetPanel = players.get(player);
		String id = String.format("Player ID: %s", player.getPlayerId());
		String name = String.format("Player Name: %s", player.getPlayerName());
		String point = String.format("Player Point: %d", player.getPoints());
		String bet = String.format("Player Bet: %d", player.getBet());
		String bettype = String.format("Player Bet Type: %s", player.getBetType());
		String result, winLoss;
		if(player.getResult() == null) {
			result = String.format("Recent Result: Not spin yet");
			winLoss = String.format("Win/Loss: Not spin yet");
		} else {
			result = String.format("Recent Result: %s", player.getResult());
			winpoint = appFrame.calcWinLoss(player);
			if(winpoint > 0) {
				winLoss = String.format("Win/Loss: You win %d points", appFrame.calcWinLoss(player));
			} else if (winpoint < 0){
				winLoss = String.format("Win/Loss: You lose %d points", appFrame.calcWinLoss(player));
			} else {
				winLoss = String.format("Win/Loss: Please wait");
			}
		}
		idLabel.get(targetPanel).setText(id);
		nameLabel.get(targetPanel).setText(name);
		pointLabel.get(targetPanel).setText(point);
		betLabel.get(targetPanel).setText(bet);
		bettypeLabel.get(targetPanel).setText(bettype);
		resultLabel.get(targetPanel).setText(result);
		winLossLabel.get(targetPanel).setText(winLoss);
			
	}
	
	public void updateAllPlayerInfo() {
		for(int i = 0; i < allPlayer.size(); ++i) {
			appFrame.cancelBet(allPlayer.get(i));
			updatePlayerInfo(allPlayer.get(i));
			appFrame.createPlayerStartList(allPlayer.get(i));
		}
	}
	
	
	public void addNewPlayer(Player player) {
		
		playerPanel.add(new JPanel());
		allPlayer.add(player);
		players.put(player, playerPanel.get(numOfPanel));
		playerPanel.get(numOfPanel).setBorder(BorderFactory.createEtchedBorder());
		playerPanel.get(numOfPanel).setMaximumSize(new Dimension(300, 150));
		playerPanel.get(numOfPanel).setLayout(new BoxLayout(playerPanel.get(numOfPanel), BoxLayout.Y_AXIS));
		summary.add(playerPanel.get(numOfPanel));
		
		
		String id = String.format("Player ID: %s", player.getPlayerId());
		String name = String.format("Player Name: %s", player.getPlayerName());
		String point = String.format("Player Point: %d", player.getPoints());
		String bet = String.format("Player Bet: %d", player.getBet());
		String bettype = String.format("Player Bet Type: %s", player.getBetType());
		String result, winLoss;
		if(player.getResult() == null) {
			result = String.format("Recent Result: Not spin yet");
			winLoss = String.format("Win/Loss: Not spin yet");
		} else {
			result = String.format("Recent Result: %s", player.getResult());
			winLoss = String.format("Win/Loss: %d", appFrame.calcWinLoss(player));
		}
		
		playerId.add(new JLabel());
		playerName.add(new JLabel());
		playerPoint.add(new JLabel());
		playerBet.add(new JLabel());
		playerBettype.add(new JLabel());
		playerResult.add(new JLabel());
		playerWinLoss.add(new JLabel());
		
		idLabel.put(playerPanel.get(numOfPanel), playerId.get(numOfPanel));
		nameLabel.put(playerPanel.get(numOfPanel), playerName.get(numOfPanel));
		pointLabel.put(playerPanel.get(numOfPanel), playerPoint.get(numOfPanel));
		betLabel.put(playerPanel.get(numOfPanel), playerBet.get(numOfPanel));
		bettypeLabel.put(playerPanel.get(numOfPanel), playerBettype.get(numOfPanel));
		resultLabel.put(playerPanel.get(numOfPanel), playerResult.get(numOfPanel));
		winLossLabel.put(playerPanel.get(numOfPanel), playerWinLoss.get(numOfPanel));
		
		playerId.get(numOfPanel).setText(id);
		playerName.get(numOfPanel).setText(name);
		playerPoint.get(numOfPanel).setText(point);
		playerBet.get(numOfPanel).setText(bet);
		playerBettype.get(numOfPanel).setText(bettype);
		playerResult.get(numOfPanel).setText(result);
		playerWinLoss.get(numOfPanel).setText(winLoss);
		
		playerPanel.get(numOfPanel).add(playerId.get(numOfPanel));
		playerPanel.get(numOfPanel).add(playerName.get(numOfPanel));
		playerPanel.get(numOfPanel).add(playerPoint.get(numOfPanel));
		playerPanel.get(numOfPanel).add(playerBet.get(numOfPanel));
		playerPanel.get(numOfPanel).add(playerBettype.get(numOfPanel));
		playerPanel.get(numOfPanel).add(playerResult.get(numOfPanel));
		playerPanel.get(numOfPanel).add(playerWinLoss.get(numOfPanel));
		numOfPanel++;
		
	}
	
	public void removePlayer(Player player) {
		JPanel targetPanel = players.get(player);
		players.remove(player);
		summary.remove(targetPanel);
		allPlayer.remove(player);
		summary.repaint();
	}
	
	
}
