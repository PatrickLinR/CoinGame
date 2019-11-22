package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerListener;
import controller.ExitGameListener;
import controller.RemovePlayerListener;

@SuppressWarnings("serial")
public class CoinMenu extends JMenuBar {
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem addPlayerMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem removePlayerMenuItem;
	
	public CoinMenu(AppFrame appFrame) {
		this.menuBar = new JMenuBar();
		this.fileMenu = new JMenu("File");

	    this.menuBar.add(fileMenu);
	    //menu items
        addPlayerMenuItem = new JMenuItem("Add Player");
        removePlayerMenuItem = new JMenuItem("Remove Player");
        exitMenuItem = new JMenuItem("Exit");
        //action listener
        addPlayerMenuItem.addActionListener(new AddPlayerListener(appFrame));
        removePlayerMenuItem.addActionListener(new RemovePlayerListener(appFrame));
        exitMenuItem.addActionListener(new ExitGameListener(appFrame));
        //add items into menu
        fileMenu.add(addPlayerMenuItem);
        fileMenu.add(removePlayerMenuItem);
        fileMenu.addSeparator();       // split line
        fileMenu.add(exitMenuItem);
        //build menu "File"
        this.add(fileMenu);
	}
	
	public void lockMenu() {
		fileMenu.setEnabled(false);
	}
	
	public void unlockMenu() {
		fileMenu.setEnabled(true);
	}
	
	public void lockButton(JMenuItem btn) {
		btn.setEnabled(false);
	}
	
	public void unlockButton(JMenuItem btn) {
		btn.setEnabled(true);
	}
    
	public JMenuItem getRemovePlayerbtn() {
		return removePlayerMenuItem;
	}
}
