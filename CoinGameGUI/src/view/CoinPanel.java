package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.interfaces.Coin;

@SuppressWarnings("serial")
public class CoinPanel extends JPanel {
	
	private Image head;
	private Image tail;
    private double factor;
    private double factorWidth;
    private double factorHeight;
	private Image coin1Face;
	private Image coin2Face;
	private double coin1Coefficient;
	private double coin2Coefficient;
    private int coin1Width;
    private int coin1Height;
    private int positionX1;
    private int positionY1;
    private int coin2Width;
    private int coin2Height;
    private int positionX2;
    private int positionY2;
    private Coin coin1;
    private Coin coin2;
    

	public CoinPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Coin Table"));
		this.head = headsImg();
		this.tail = tailsImg();
	}

	private Image headsImg(){
		Image image = null;
		try {
			image = ImageIO.read(new File("src/img/heads.png"));
		}
		catch (IOException e) {
		}
		return image;
	}
	
	private Image tailsImg(){
		Image image = null;
		try {
			image = ImageIO.read(new File("src/img/tails.png"));
		}
		catch (IOException e) {
		}
		return image;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// determine scaling coefficient and dimensions from size of background image and parent component
		coin1Coefficient = Math.min(1, getScaleCoefficient(new Dimension(head.getWidth(null), head.getHeight(null)), getSize()));
		coin2Coefficient = Math.min(1, getScaleCoefficient(new Dimension(tail.getWidth(null), tail.getHeight(null)), getSize()));
		coin1Width = (int) Math.round(head.getWidth(null) * coin1Coefficient) - 60;
		coin1Height = (int) Math.round(head.getHeight(null) * coin1Coefficient) - 60;
		coin2Width = (int) Math.round(tail.getWidth(null) * coin2Coefficient) - 60;
		coin2Height = (int) Math.round(tail.getHeight(null) * coin2Coefficient) - 60;
		
		// scale the image to appropriate new size
		
		if(coin1 == null || coin2 == null) {
			beforeStart();
		} 
		
		// determine where to draw image from, accounting for border pixels
		positionX1 = ((getWidth() -1) - this.coin1Face.getWidth(this)) / 2 - 160;
		positionY1 = ((getHeight() -1) - this.coin1Face.getHeight(this)) / 2;
		positionX2 = positionX1 + coin1Width;
		positionY2 = positionY1;
		// paint the scaled image at the appropriate coordinates
	    g.drawImage(this.coin1Face, positionX1, positionY1, this);
	    g.drawImage(this.coin2Face, positionX2, positionY2, this);
	}
	
	public void beforeStart() {
		coin1Face = this.head.getScaledInstance(this.coin1Width-30, this.coin1Height-30, Image.SCALE_SMOOTH);
		coin2Face = this.tail.getScaledInstance(this.coin2Width-30, this.coin2Height-30, Image.SCALE_SMOOTH);
		repaint();
	}
	
	public void updateCoin1Face(Coin coin) {
		coin1 = coin;
		if(coin.getFace().value() == "Heads") {
			coin1Face = this.head.getScaledInstance(this.coin1Width-30, this.coin1Height-30, Image.SCALE_SMOOTH);
		} else {
			coin1Face = this.tail.getScaledInstance(this.coin1Width-30, this.coin1Height-30, Image.SCALE_SMOOTH);
		}
		repaint();
	}
	
	public void updateCoin2Face(Coin coin) {
		coin2 = coin;
		if(coin.getFace().value() == "Heads") {
			coin2Face = this.head.getScaledInstance(this.coin2Width-30, this.coin2Height-30, Image.SCALE_SMOOTH);
		} else {
			coin2Face = this.tail.getScaledInstance(this.coin2Width-30, this.coin2Height-30, Image.SCALE_SMOOTH);
		}
		repaint();
	}

	public double getScaleCoefficient(Dimension start, Dimension finish) 
	{	
		// calculate the background scaling coefficient from start and finish dimension values
	    factor = 1;
        factorWidth = getScaleDegree(start.width, finish.width);
	    factorHeight = getScaleDegree(start.height, finish.height);
	    factor = Math.min(factorHeight, factorWidth);
	    return factor;
	}
	
	public double getScaleDegree(int startSize, int targetSize) 
	{
		// calculate degree of scaling, or return neutral (1) if no change
	    double scaleFactor = 1;
	    if (startSize != targetSize) 
	    {
	    	scaleFactor = (double) targetSize / startSize;
	    }
	    return scaleFactor;
	}
	
}
