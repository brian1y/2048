package culminating;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Brian 
 * June 3, 2021
 * Class to create 2048 game
 */
public class Brian2048 extends JFrame implements ActionListener{
	JButton menu=new JButton("Menu"); //button to return to menu
	JButton start=new JButton("Start"); //button to start game
	Game g = new Game(); //creates game panel
	Menu m = new Menu(); //creates menu panel
	public Brian2048() {
		setTitle("2048"); //sets title 
		setPreferredSize(new Dimension(510,600)); //set size of frame
		setLayout(null);  //set null layout
		setResizable(false);  //contentPane is not resizable
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		menu.addActionListener(this);  //add action listener to menu button
		start.addActionListener(this); //add action listener to start button
		g.setBounds(0,0,510,600); //set bounds of game panel
		m.setBounds(0,0,510,600); //set bounds of menu panel 
		g.add(menu); //add menu button to game panel
		m.add(start); //add start button to menu panel
		setContentPane(m); //set contentPane to menu panel
		pack();
		setVisible(true); 
		
	}
	public static void main(String[] args) {
		Brian2048 frame = new Brian2048();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menu){ //changes to menu panel
			setContentPane(m);
		}
		if(e.getSource()==start){ //changes to game panel
			setContentPane(g);
			
		}
	}  
}
