package uom.tl.gui.maingame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AboutGui extends JFrame{
	
	public AboutGui(){
		this.setVisible(true);
		this.setSize(1205,768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel backgroundlabel = new JLabel();
		
		ImageIcon img =new ImageIcon(this.getClass().getResource("/choosehero background.jpg"));
		Image img2 = img.getImage();
		Image newimg = img2.getScaledInstance(1205, 768,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
	
	
		backgroundlabel.setBounds(0,0,1205,768);
		this.setContentPane(backgroundlabel);
		backgroundlabel.setIcon(newIcon);
		
		
		JLabel text=new JLabel("<html>This project has been developed for the course Software Technology. <br> <br> University Of Macedonia,Thessaloniki 2017</html>",SwingConstants.CENTER);
		
		text.setVisible(true);

		
		text.setForeground(Color.WHITE);
		text.setBounds(80,100,1000,200);
		this.add(text);
		text.setFont(new Font(Main.getFont().getFontName(), Font.BOLD, 35));
		
		
		JButton backButton = new JButton();
		Image backButtonIcon = new ImageIcon(this.getClass().getResource("/back button.png")).getImage();
		backButton.setIcon(new ImageIcon(backButtonIcon));
		backButton.setBounds(60,  40, backButtonIcon.getWidth(backButton), backButtonIcon.getHeight(backButton));
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		this.add(backButton);
		
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(backButton)){
					new MainMenu();
					dispose();
				}
				
			}
		});
	}

}
