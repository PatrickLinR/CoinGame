package view;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.AddPlayerConfirmListener;
import model.SimplePlayer;
import model.enumeration.BetType;
import model.enumeration.GameStatus;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class AddPlayerDialog extends JDialog {

	private AppFrame appFrame;
	private JLabel idLabel;
	private JLabel nameLabel;
	private JLabel pointsLabel;
	private JLabel idInput;
	private JTextField nameInput;
	private JTextField pointsInput;
	private JButton addbtn;
	private Container addPlayerContainer;

	public AddPlayerDialog(AppFrame appFrame) {
		// TODO Auto-generated constructor stub
		this.appFrame = appFrame;
		
		setTitle("Add Player");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 360, 160);
		
		this.idInput = new JLabel(String.valueOf(appFrame.getIdIncrease()));
		this.idLabel = new JLabel("Your Player ID: ");
		this.nameInput = new JTextField(10);
		this.nameLabel = new JLabel("New Player Name: ");
		this.pointsInput = new JTextField(10);
		this.pointsLabel = new JLabel("Player Initial points: ");
		
		this.addbtn = new JButton("Add");
		this.addbtn.addActionListener(new AddPlayerConfirmListener(appFrame, this));

		this.addPlayerContainer=getContentPane();
		this.addPlayerContainer.setLayout(null);
		
		this.idLabel.setBounds(50, 20, 150, 20);
		this.idInput.setBounds(200, 20, 100, 20);
		this.nameLabel.setBounds(50, 40, 150, 20);
		this.nameInput.setBounds(200, 40, 100, 20);
		this.pointsLabel.setBounds(50, 60, 150, 20);
		this.pointsInput.setBounds(200, 60, 100, 20);
		this.addbtn.setBounds(130, 90, 100, 20);
		
		addPlayerContainer.add(idLabel);
		addPlayerContainer.add(idInput);
		addPlayerContainer.add(nameLabel);
		addPlayerContainer.add(nameInput);
		addPlayerContainer.add(pointsLabel);
		addPlayerContainer.add(pointsInput);
		addPlayerContainer.add(addbtn);
	}
	
	public void addPlayer() {
		String id = String.valueOf(appFrame.getIdIncrease());
		String name = this.nameInput.getText();
		String points = this.pointsInput.getText();
		
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter the player id!");
		} else if(name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter the player name!");;
		} else if(points.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter the initial points of this player!");
		} else if(!this.isNumeric(points)) {
			JOptionPane.showMessageDialog(this, "Please enter a positive number of points!");
		} else if(this.tooLong(points)) {
			JOptionPane.showMessageDialog(this, "Please enter the points less then 10 digits!");
		} else if(this.isZero(points)) {
			JOptionPane.showMessageDialog(this, "You need some points to join game, please enter a number above 0!");
		} else if(this.checkExisted(id)) {
			JOptionPane.showMessageDialog(this, "This player is already existed, please enter another player id!");
		} else {
			int intPoint = Integer.parseInt(points);
			Player player = new SimplePlayer(id, name, intPoint);
			player.setBetType(BetType.NO_BET);//default bet type
			appFrame.getGameEngine().addPlayer(player);
			appFrame.getsummaryPanel().addNewPlayer(player);
			appFrame.getToolBar().fillPlayers(player);
			appFrame.createPlayerStartList(player);
			appFrame.getStatusBarPanel().setReadyStatusLabel(GameStatus.BEFOREBET);
			appFrame.increaseId();
			dispose();
			JOptionPane.showMessageDialog(appFrame, "Add player successfully");
		}
	}

	private boolean checkExisted(String id) {
		for(Player p : appFrame.getGameEngine().getAllPlayers()) {
			if(p.getPlayerId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	private boolean isZero(String points) {
		if(Integer.parseInt(points) == 0) {
			return true;
		} else {
			return false;
		}		
	}
	
	private boolean tooLong(String points) {
		if(points.length() > 10) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isNumeric(String points) {
		if(points != null) {
			for(int i = 0; i < points.length(); i++) {
				if(!Character.isDigit(points.charAt(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
