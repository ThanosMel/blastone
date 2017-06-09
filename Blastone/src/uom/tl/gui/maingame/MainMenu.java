package uom.tl.gui.maingame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import uom.tl.gui.createdeck.ChooseHeroAndDeck;
import uom.tl.gui.createdeck.HeroChooseGui;

//To frame gia to kentriko menu tou paixnidiou exei 3 plhktra
//-play: na paikseis afou epilekseis hrwa kai deck.(mesw ths klashs ChooseHeroAndDck)
//-createdeck: epilegeis se poion hrwa theleis na dhmiourghseis to diko sou deck.(mesw twn klaswn CreateDeckGui & HeroChooseGui)
//-about: anaferei kapoia pragmata gia thn omada.
public class MainMenu extends JFrame {
	private JLabel menuLabel;
	private JButton playButton;
	private JButton crtDeckButton;
	private JButton aboutButton;

	public MainMenu(){
		this.setVisible(true);
		this.setSize(1205,768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		Image mainMenu= new ImageIcon(this.getClass().getResource("/mainmenu.png")).getImage();
		getContentPane().setLayout(null);
		menuLabel= new JLabel("");
		menuLabel.setBounds(0, 0, 1205, 768);
		menuLabel.setIcon(new ImageIcon(mainMenu));
		this.setContentPane(menuLabel);
		//this.getContentPane().add(menuLabel);
		this.revalidate();
	    
		
		playButton= new JButton("");
		Image play= new ImageIcon(this.getClass().getResource("/invisible.png")).getImage();
		playButton.setIcon(new ImageIcon(play));
		playButton.setBounds(476, 247, 256, 31);	
		getContentPane().add(playButton);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);	
		playButton.setOpaque(false);

		
		crtDeckButton= new JButton("");
	    Image crtDeck= new ImageIcon(this.getClass().getResource("/invisible.png")).getImage();
	    crtDeckButton.setIcon(new ImageIcon(crtDeck));
	    crtDeckButton.setBounds(435, 318, 256, 31);
		getContentPane().add(crtDeckButton);
		crtDeckButton.setOpaque(false);
		crtDeckButton.setContentAreaFilled(false);
		crtDeckButton.setBorderPainted(false);	
		crtDeckButton.setBorder(null);
	

	    Image about= new ImageIcon(this.getClass().getResource("/invisible.png")).getImage();
	    aboutButton= new JButton("");
		aboutButton.setIcon(new ImageIcon(about));
		aboutButton.setBounds(443, 395, 256, 31);
		getContentPane().add(aboutButton);	
		aboutButton.setOpaque(false);
		aboutButton.setContentAreaFilled(false);
		aboutButton.setBorderPainted(false);	
	

		
		ButtonListener bl= new ButtonListener();
		crtDeckButton.addActionListener(bl);
		playButton.addActionListener(bl);
		aboutButton.addActionListener(bl);
		this.validate();
		
		
	
	}
	class ButtonListener implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(playButton)){
				new ChooseHeroAndDeck();
				dispose();
			}
			else if(e.getSource().equals(crtDeckButton)){
				new HeroChooseGui();
				dispose();
			}
			else{
				
				new AboutGui();
				dispose();
			}
		}
		
	}

	
	
}
