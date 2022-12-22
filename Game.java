package culminating;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
/*
 * Brian
 * May 27, 2021
 * Class to create 2048 game panel
 */
public class Game extends JPanel implements Runnable,KeyListener,ActionListener{
	Image grid,n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11; //tile and grid images
	JButton newGame=new JButton("New Game"); //create button to start new game
	Thread runner;
	int[]tileX = {60,158,257,355}; //x coordinates to draw tile on panel
	int[]tileY = {111,210,307,404}; //y coordinates to draw tile on panel
	Board b= new Board(); //creates board object
	public Game() {
		super();
		addKeyListener(this); //adds key listener to panel
		newGame.addActionListener(this); //adds action listener to new game button
		add(newGame); //add newGame button to panel
		Toolkit kit = Toolkit.getDefaultToolkit();
		//sets images to appropriate variables
		grid = kit.getImage("img2048/grid.png");
		n1 = kit.getImage("img2048/2.png");
		n2 = kit.getImage("img2048/4.png");
		n3 = kit.getImage("img2048/8.png");
		n4 = kit.getImage("img2048/16.png");
		n5 = kit.getImage("img2048/32.png");
		n6 = kit.getImage("img2048/64.png");
		n7 = kit.getImage("img2048/128.png");
		n8 = kit.getImage("img2048/256.png");
		n9 = kit.getImage("img2048/512.png");
		n10 = kit.getImage("img2048/1024.png");
		n11 = kit.getImage("img2048/2048.png");
		//scales images
		grid = grid.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		n1 = n1.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		n2 = n2.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		n3 = n3.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		n4 = n4.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		n5 = n5.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		n6 = n6.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		n7 = n7.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		n8 = n8.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		n9 = n9.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		n10 = n10.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		n11 = n11.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		runner = new Thread(this);
		runner.start();
	}
	/*
	 * draws the board, tiles, score and win/loss screen
	 */
	public void paintComponent(Graphics comp) {
		Graphics2D comp2D = (Graphics2D) comp;
		//draws background and grid
		comp2D.setColor(new Color(232, 233, 235));
		comp2D.fillRect(0, 0, 550, 600);
		comp2D.drawImage(grid,50,100,this);
		for(int r=0;r<4;r++) {
			for(int c=0;c<4;c++) { //draws tiles on the grid
				if(b.getValue(r, c)==2) {
					comp2D.drawImage(n1,tileX[c],tileY[r],this);
				}
				if(b.getValue(r, c)==4) {
					comp2D.drawImage(n2,tileX[c],tileY[r],this);
				}
				if(b.getValue(r, c)==8) {
					comp2D.drawImage(n3,tileX[c],tileY[r],this);
				}
				if(b.getValue(r, c)==16) {
					comp2D.drawImage(n4,tileX[c],tileY[r],this);
				}
				if(b.getValue(r, c)==32) {
					comp2D.drawImage(n5,tileX[c],tileY[r],this);
				}
				if(b.getValue(r, c)==64) {
					comp2D.drawImage(n6,tileX[c],tileY[r],this);
				}
				if(b.getValue(r, c)==128) {
					comp2D.drawImage(n7,tileX[c],tileY[r],this);
				}
				if(b.getValue(r, c)==256) {
					comp2D.drawImage(n8,tileX[c],tileY[r],this);
				}
				if(b.getValue(r, c)==512) {
					comp2D.drawImage(n9,tileX[c],tileY[r],this);
				}
				if(b.getValue(r, c)==1024) {
					comp2D.drawImage(n10,tileX[c],tileY[r],this);
				}
				if(b.getValue(r, c)==2048) {
					comp2D.drawImage(n11,tileX[c],tileY[r],this);
				}}}
		//draws score
		comp2D.setColor(Color.black);
		comp2D.setFont(new Font("SansSerif", Font.BOLD, 16 ));
		comp2D.drawString("Score: "+b.score,50,50);
		if(b.gameOver()==-1) { //draws win screen
			comp2D.setColor(new Color(232, 233, 235,150));
			comp2D.fillRect(0, 0, 510, 600);
			comp2D.setColor(Color.black);
			comp2D.setFont(new Font("SansSerif", Font.BOLD, 30 ));
			comp2D.drawString("You Lost.",190,300);
		}
		if(b.gameOver()==1) { //draws lost screen
			comp2D.setColor(new Color(232, 233, 235,150));
			comp2D.fillRect(0, 0, 510, 600);
			comp2D.setColor(Color.black);
			comp2D.setFont(new Font("SansSerif", Font.BOLD, 30 ));
			comp2D.drawString("You Won!",190,300);
		}
	}
	public void run() {
		Thread thisThread = Thread.currentThread();
		while(runner == thisThread) {	
			repaint(); //repaints the board
			requestFocusInWindow();
			try { Thread.sleep(25); }
			catch (InterruptedException e) {} 
		}
	}
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP ) { //slides tiles up when up arrow key is pressed
			if(b.gameOver()==0) { //player can only move tiles when game is not over
				b.slide(1);

				if(b.tilesMove) { //only executes spawn method if tiles combine or slide
					b.spawn();
				}}}
		if(event.getKeyCode() == KeyEvent.VK_DOWN ) { //slides tiles down when down arrow key is pressed
			if(b.gameOver()==0) { //player can only move tiles when game is not over
				b.slide(2);

				if(b.tilesMove) { //only executes spawn method if tiles combine or slide
					b.spawn();
				}}}
		if(event.getKeyCode() == KeyEvent.VK_LEFT ) { //slides tiles left when left arrow key is pressed
			if(b.gameOver()==0) { //player can only move tiles when game is not over
				b.slide(3);

				if(b.tilesMove) { //only executes spawn method if tiles combine or slide
					b.spawn();
				}}}
		if(event.getKeyCode() == KeyEvent.VK_RIGHT ) { //slides tiles right when right arrow key is pressed
			if(b.gameOver()==0) { //player can only move tiles when game is not over
				b.slide(4);

				if(b.tilesMove) { //only executes spawn method if tiles combine or slide
					b.spawn();
				}}}
	}
	public void keyReleased(KeyEvent event){  }

	public void keyTyped(KeyEvent event){  }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==newGame){ //new game button pressed
			//resets the game 
			for(int r=0;r<4;r++) {
				for(int c=0;c<4;c++) {
					b.setValue(r,c,0);
				}
			}
			Tile.numTiles=0; //sets number of tiles on grid to 0
			b.score=0; //resets score
			//spawns two tiles
			b.spawn();
			b.spawn();
		}
	}    

}

