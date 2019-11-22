package view;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import controller.StartPlayerGameListener;
import model.GameEngineImpl;
import model.enumeration.BetType;
import model.enumeration.GameStatus;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class AppFrame extends JFrame {

	private GameEngine gameEngine;
	private CoinMenu menuBar;//menuBar
	private CoinToolBar toolBar;//toolBar
	private CoinPanel coinPanel;//coinPanel
	private SummaryPanel summaryPanel;//result
	private StatusBarPanel statusBar;//status
	private Player selectedPlayer;//selectedPlayer
	private int idIncrease;
	private boolean inProcess;
	private Map<String,String> playerStartList = new HashMap<String, String>();
	private Map<Player, Integer> pointBefore = new HashMap<Player, Integer>();
	private Map<Player, String> playerInProcess = new HashMap<Player, String>();
	private StartPlayerGameListener startGame = new StartPlayerGameListener(this);
	//Delay default initialize
	private int INITIAL_DELAY1 = 1;
	private int FINAL_DELAY1 = 500;
	private int DELAY_INCREMENT1 = 25;	
	private int INITIAL_DELAY2 = 1;
	private int FINAL_DELAY2 = 800;
	private int DELAY_INCREMENT2 = 50;
	
	public AppFrame(GameEngine gameEngine) 
	{
		super("CoinGame");
		this.gameEngine = gameEngine;
		
        setBounds(100, 100, 1120, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		this.summaryPanel = new SummaryPanel(this);
		this.statusBar = new StatusBarPanel(gameEngine);
		this.toolBar = new CoinToolBar(this, gameEngine);
		this.coinPanel = new CoinPanel();
		this.menuBar = new CoinMenu(this);
		
		this.setJMenuBar(menuBar);
        add(toolBar, BorderLayout.NORTH);
		add(coinPanel, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
		add(summaryPanel, BorderLayout.WEST);
		
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(this));
		
		setVisible(true);
	}
	
	public CoinPanel getCoinPanel() {
		return coinPanel;
	}
	
	public StatusBarPanel getStatusBar() {
		return statusBar;
	}
	
	public GameEngine getGameEngine() {
		return gameEngine;
	}
	
	public CoinToolBar getToolBar() {
		return toolBar;
	}
	
	public StatusBarPanel getStatusBarPanel() {
		return statusBar;
	}
	
	public SummaryPanel getsummaryPanel() {
		return summaryPanel;
	}
	
	public Player getSelectedPlayer() {
		return this.selectedPlayer;
	}
	
	public void setPlayerBet(int bet) {
		this.selectedPlayer.setBet(bet);			
		summaryPanel.updatePlayerInfo(this.selectedPlayer);
	}
	
	public void pointsBefore(Player player) {
		pointBefore.put(player, player.getPoints());
	}
	
	public int calcWinLoss(Player player) {
		int winLoss = player.getPoints() - pointBefore.get(player);
		return winLoss;
	}
	
	public void updatePlayerResult(Player player) {
		this.selectedPlayer = player;
		if(this.selectedPlayer!=null) {
			repaint();
			if(player.getResult() == null) {
				coinPanel.beforeStart();
			} else {
				coinPanel.updateCoin1Face(player.getResult().getCoin1());
				coinPanel.updateCoin2Face(player.getResult().getCoin2());
			}
		}
	}
	
	public void createPlayerStartList(Player player) {
		this.playerStartList.put(player.getPlayerId(), "false");
	}
	
	public void deletePlayerStartList(Player player) {
		this.playerStartList.remove(player.getPlayerId());
	}
	
	public void setPlayerInProcess(Player player) {
		playerInProcess.put(player, "true");
	}
	
	public void setPlayerFinish(Player player) {
		playerInProcess.remove(player);
	}
	
	public boolean checkPlayerInProcess(Player player) {
		if(playerInProcess.get(player) != null && playerInProcess.get(player) != "false") {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean setPlayerStartResult(Player player) {
		if(this.playerStartList.get(player.getPlayerId())=="false") {
			this.playerStartList.put(player.getPlayerId(),"true");
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkPlayerStart(Player player) {
		if(this.playerStartList.get(player.getPlayerId()) == "true") {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkAllPlayerSpin() {
		if(gameEngine.getAllPlayers().size() != 0 &&! inProcess && !this.playerStartList.containsValue("false")) {
				JOptionPane.showMessageDialog(this, "All Players have spinned, spinner start spin!");
				startGame.spinnerStart();
			return true;
		} else {
			return false;
		}
	}
	
	//get all delay
	public int getInitialDelay1() {
		return INITIAL_DELAY1;
	}

	public int getFinalDelay1() {
		return FINAL_DELAY1;
	}

	public int getDelayIncrement1() {
		return DELAY_INCREMENT1;
	}

	public int getInitialDelay2() {
		return INITIAL_DELAY2;
	}

	public int getFinalDelay2() {
		return FINAL_DELAY2;
	}

	public int getDelayIncrement2() {
		return DELAY_INCREMENT2;
	}
	//finish get delay
	
	public void setInitialDelay1(int initialDelay) {
		this.INITIAL_DELAY1 = initialDelay;
	}
	
	public void setInitialDelay2(int initialDelay) {
		this.INITIAL_DELAY2 = initialDelay;
	}
	
	public void setFinalDelay1(int finalDelay) {
		this.FINAL_DELAY1 = finalDelay;
	}
	
	public void setFinalDelay2(int finalDelay) {
		this.FINAL_DELAY2 = finalDelay;
	}
	
	public void setDelayIncrement1(int delayIncrement) {
		this.DELAY_INCREMENT1 = delayIncrement;
	}
	
	public void setDelayIncrement2(int delayIncrement) {
		this.DELAY_INCREMENT2 = delayIncrement;
	}
	
	//auto generate ID
	public int getIdIncrease() {
		return this.idIncrease;
	}
	
	public void increaseId() {
		this.idIncrease++;
	}
	//end of id increase
	
	// lock and update views during spin
	public void preSpinUIUpdate() 
	{
		statusBar.setReadyStatusLabel(GameStatus.READY);		
		toolBar.unlockButtons(toolBar.getBetbtn());
		toolBar.unlockButtons(toolBar.getCancelBetbtn());
		toolBar.unlockButtons(toolBar.getStartbtn());
		menuBar.unlockButton(menuBar.getRemovePlayerbtn());
		menuBar.unlockMenu();
	}
	
	public void spinnerSpinUIUpdate() {
		toolBar.lockButtons(toolBar.getBetbtn());
		toolBar.lockButtons(toolBar.getCancelBetbtn());
		toolBar.lockButtons(toolBar.getStartbtn());
		menuBar.lockMenu();
	}
		
	// unlock and update views post spin
	public void postSpinUIUpdate() 
	{	
		toolBar.lockButtons(toolBar.getBetbtn());
		toolBar.lockButtons(toolBar.getCancelBetbtn());
		toolBar.lockButtons(toolBar.getStartbtn());
		menuBar.lockButton(menuBar.getRemovePlayerbtn());
	}
	
	public void spinInProcess() {
		statusBar.setReadyStatusLabel(GameStatus.INPROGRESS);
		inProcess = true;
	}
		
	public void spinEnd() {
		inProcess = false;
	}
	
	public boolean checkInProcess() {
		return inProcess;
	}
	
	public void cancelBet(Player player) {
		player.setBetType(BetType.NO_BET); 
	}

	public void updatePlayerStatusBar(Player player) {
		// TODO Auto-generated method stub
		if(player != null) {
			if(checkPlayerInProcess(player)){
				this.getStatusBar().setReadyStatusLabel(GameStatus.INPROGRESS);
			} else if(this.checkPlayerStart(player)) {
				this.postSpinUIUpdate();
				this.getStatusBar().setReadyStatusLabel(GameStatus.WAITING);
			} else {
				this.preSpinUIUpdate();
				if(player.getBet()!=0 && !player.getBetType().equals(BetType.NO_BET)) {
					this.getStatusBar().setReadyStatusLabel(GameStatus.READY);
				} else {
					this.getStatusBar().setReadyStatusLabel(GameStatus.BEFOREBET);
				}
			}
		} else {
			this.getStatusBar().setReadyStatusLabel(GameStatus.ADDPLAYERS);
		}
	}


	
}
