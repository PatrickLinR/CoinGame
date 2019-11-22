package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BetConfirmListener;
import model.enumeration.BetType;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class PlaceBetDialog extends JDialog {
	private JTextField betInput;
	private JLabel betLabel;
	private JLabel betTypeLabel;
	private JPanel betPanel;
	private JButton placeBetbtn;
	private AppFrame appFrame;
	private BetType betType;
	private JComboBox<BetType> betTypeComboBox = new JComboBox<BetType>(BetType.values());
	public PlaceBetDialog(GameEngine gameEngine, AppFrame appFrame) {
		// TODO Auto-generated constructor stub
		setTitle("Bet");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.appFrame = appFrame;
		this.betInput = new JTextField(10);
		this.betLabel = new JLabel("Enter the bet: ");
		this.betTypeLabel = new JLabel("Bet type");
		
		this.placeBetbtn = new JButton("Place Bet!");

		this.placeBetbtn.addActionListener(new BetConfirmListener(this));
		
		this.betPanel = new JPanel();
		setBounds(500, 300, 240, 150);
		add(betPanel);
		
		this.betPanel.add(betLabel);
		this.betPanel.add(betInput);
		this.betPanel.add(betTypeLabel);
		this.betPanel.add(betTypeComboBox);
		this.betPanel.add(placeBetbtn);
	}

	@SuppressWarnings("static-access")
	public void placeBet() {
		String bet = this.betInput.getText();

		if (bet == null) {
			return;
		} else if (bet.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter a number");
			this.betInput.setText("");
		} else if (!this.isNumeric(bet)) {
			JOptionPane.showMessageDialog(this, "Please enter a positive number");
			this.betInput.setText("");
		} else if(this.tooLong(bet)) {
			JOptionPane.showMessageDialog(this, "Please enter a number less then 10 digits");
		} else if ((BetType)betTypeComboBox.getSelectedItem() != betType.NO_BET && this.isZero(bet)) {
			JOptionPane.showMessageDialog(this, "Please place bet above 0");
			this.betInput.setText("");
		} else {
			int betNum = Integer.parseInt(bet);			
			if ((BetType)betTypeComboBox.getSelectedItem() != betType.NO_BET && !appFrame.getGameEngine().placeBet(this.appFrame.getSelectedPlayer(), betNum,(BetType)betTypeComboBox.getSelectedItem())) {
				// error message for not enough balance
				JOptionPane.showMessageDialog(appFrame, "Your money is not enough");
			} else {
				appFrame.setPlayerBet(betNum);	
				appFrame.getSelectedPlayer().setBetType((BetType)betTypeComboBox.getSelectedItem());			 
				JOptionPane.showMessageDialog(appFrame, "Place bet successfully!");
				appFrame.updatePlayerStatusBar(appFrame.getSelectedPlayer());
				dispose();
			}
		}
	}

	private boolean tooLong(String points) {
		if(points.length() > 10) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isNumeric(String message) {
		if (message != null) {
			for (int i = 0; i < message.length(); i++) {
				if (!Character.isDigit(message.charAt(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private boolean isZero(String message) {
		if (Integer.parseInt(message) == 0) {
			return true;
		} else {
			return false;
		}
	}

}
