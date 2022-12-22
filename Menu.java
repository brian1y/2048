package culminating;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Brian
 * June 5, 2021
 * Menu Class.
 */
public class Menu extends JPanel implements ActionListener{
	Image m; //image for menu
	int screen=0; //0=paint main menu 1=paint instructions
	JButton instructions=new JButton("Instructions"); //instructions button
	JButton back=new JButton("Back"); //back button
	public Menu() {
		super();
		Toolkit kit = Toolkit.getDefaultToolkit();
		m = kit.getImage("img2048/menu.png");
		m = m.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		//add action listener to buttons
		instructions.addActionListener(this);
		back.addActionListener(this);
		//add buttons to panel
		add(instructions);
		add(back);
	}
	public void paintComponent(Graphics comp) {
		Graphics2D comp2D = (Graphics2D) comp;
		if(screen==0) { //paints menu
			comp2D.setColor(new Color(232, 233, 235));
			comp2D.fillRect(0, 0, 550, 600);
			comp2D.drawImage(m,50,100,this);
		}
		if(screen==1) { //paints instructions
			comp2D.setColor(new Color(232, 233, 235));
			comp2D.fillRect(0, 0, 550, 600);
			comp2D.setColor(Color.black); //set color of text
			comp2D.setFont(new Font("SansSerif", Font.BOLD, 20 )); //set text font
			//draws game instructions
			comp2D.drawString("Instructions: ",180,60);
			comp2D.setFont(new Font("SansSerif", Font.BOLD, 10 ));
			comp2D.drawString("The objective of the game is to combine tiles with the same value to create larger value tiles. ",10,80);
			comp2D.drawString("Using the arrow keys you can slide the tiles on the board up, down, left or right.", 10, 90);
			comp2D.drawString("The game is won when the 2048 tile is created or lost when the tiles can no longer slide.", 10, 100);
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back){ //changes the screen to menu and repaints the panel
			screen=0;
			repaint();
		}
		if(e.getSource()==instructions){ //changes the screen to instructions and repaints the panel
			screen=1;
			repaint();
		}

	}
}
