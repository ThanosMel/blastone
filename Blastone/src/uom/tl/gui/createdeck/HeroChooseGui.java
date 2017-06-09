package uom.tl.gui.createdeck;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import uom.tl.gui.maingame.Main;
import uom.tl.gui.maingame.MainMenu;

//--Sto sugekrimeno frame o xrhsths epilegei se poion apo tous 4 hrwes thelei na dhmiourghseis deck.
//--se kathe hrwa mporoume na dhmourghsoume mexri kai 1 deck.
//--an exei hdh dhmiourghthei ena deck se kapoion hrwa kai prospathsoume na dhmiourghsoume ksana ston idio, to palio
// diagrafetai kai menei to kainourgio.
//--me thn epilogh hrwa odhgoumaste sth klash CreateDeckGui opou kai telika epilegoume to deck mas.
public class HeroChooseGui extends JFrame {
	private Image image1, image2, image3, image4, background, backButtonIcon;
	private JButton button1, button2, button3, button4, backButton;
	private JLabel label1, label2, label3, label4;
	
	
	private SelectHeroActionListener listener = new SelectHeroActionListener();
	private backButtonListener backlistener = new backButtonListener();
	private MouseMoveInfo info = new MouseMoveInfo();
	private Font textFont=Main.getFont() ;

	public HeroChooseGui(){
		super("Hearthstone");		
		CastJButtons();
		
		
		// background
		background = new ImageIcon(this.getClass().getResource("/choosehero background.jpg")).getImage();
		JLabel backgroundlabel = new JLabel();
		backgroundlabel.setBounds(0,0,getWidth(),getHeight());
		backgroundlabel.setIcon(new ImageIcon(background));
		this.add(backgroundlabel);
		

		
		
		this.setVisible(true);
		this.setSize(1366, 768); 
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void CastJButtons(){
		//image1 
		button1 = new JButton();
		image1 = new ImageIcon(this.getClass().getResource("/Gul'dan.jpg")).getImage();
		button1.setIcon(new ImageIcon(image1));
		button1.setBounds(220, 80, image1.getWidth(button1), image1.getHeight(button1));
		button1.addActionListener(listener);
		this.add(button1);
		button1.setBorderPainted(true);
		button1.setContentAreaFilled(false);
		button1.addMouseListener(info);
		this.validate();
		//label1
		label1 = new JLabel();
		label1.setFont(textFont);
		label1.setForeground(Color.WHITE);
		label1.setBounds(270, 350, 240, 25);
		this.add(label1);
		
		//image2
		button2 = new JButton();
		image2 = new ImageIcon(this.getClass().getResource("/Jaina Proudmoore.jpg")).getImage();
		button2.setIcon(new ImageIcon(image2));
		button2.setBounds(826, 80, image2.getWidth(button2), image2.getHeight(button2));
		button2.addActionListener(listener);
		this.add(button2);
		button2.setBorderPainted(true);
		button2.setContentAreaFilled(false);
		button2.addMouseListener(info);
		this.validate();
		//label2
		label2 = new JLabel();
		label2.setFont(textFont);
		label2.setForeground(Color.WHITE);
		label2.setBounds(886, 350, 240, 25);
		this.add(label2);
		
		//image3
		button3 = new JButton();
		image3 = new ImageIcon(this.getClass().getResource("/Rexxar.jpg")).getImage();
		button3.setIcon(new ImageIcon(image3));
		button3.setBounds(220, 410, image3.getWidth(button3), image3.getHeight(button3));
		button3.addActionListener(listener);
		this.add(button3);
		button3.setBorderPainted(true);
		button3.setContentAreaFilled(false);
		button3.addMouseListener(info);
		this.validate();
		//label3
		label3 = new JLabel();
		label3.setFont(textFont);
		label3.setForeground(Color.WHITE);
		label3.setBounds(270, 680, 240, 25);
		this.add(label3);
		
		//image4
		button4 = new JButton();
		image4 = new ImageIcon(this.getClass().getResource("/Uther Lightbringer.jpg")).getImage();
		button4.setIcon(new ImageIcon(image4));
		button4.setBounds(826, 410, image4.getWidth(button4), image4.getHeight(button4));
		button4.addActionListener(listener);
		this.validate();
		button4.setBorderPainted(true);
		button4.setContentAreaFilled(false);
		button4.addMouseListener(info);
		this.add(button4);
		//label4
		label4 = new JLabel();
		label4.setFont(textFont);
		label4.setForeground(Color.WHITE);
		label4.setBounds(886, 680, 240, 25);
		this.add(label4);
		
		//backbutton
		backButton = new JButton();
		backButtonIcon = new ImageIcon(this.getClass().getResource("/back button.png")).getImage();
		backButton.setIcon(new ImageIcon(backButtonIcon));
		backButton.setBounds(60,  40, backButtonIcon.getWidth(backButton), backButtonIcon.getHeight(backButton));
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.addActionListener(backlistener);
		this.add(backButton);
	}
	
	class SelectHeroActionListener implements ActionListener{
		String hero_name = null;
		@Override
		public void actionPerformed(ActionEvent click) {
			if(click.getSource().equals(button1))
				hero_name = "hero_guldan.json";
			else if(click.getSource().equals(button2))
				hero_name = "hero_jaina.json";
			else if(click.getSource().equals(button3))
				hero_name = "hero_rexxar.json";
			else
				hero_name = "hero_uther.json";
			//setVisible(false);
			dispose();
			new CreateDeckGui("Hero_choose_panel",hero_name);
		}
	}
	
	class backButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent back) {
			new MainMenu();
			dispose();
		}
	}
	
	class MouseMoveInfo implements MouseListener{
		@Override
		public void mouseEntered(MouseEvent button) {
			if(button.getSource().equals(button1))
				label1.setText("Gul'dan (Warlock)");
			else if(button.getSource().equals(button2))
				label2.setText("Jaina (Mage)");
			else if(button.getSource().equals(button3))
				label3.setText("Rexxar (Hunter)");
			else
				label4.setText("Uther (Paladin)");
		}
		@Override
		public void mouseExited(MouseEvent button) {
			if(button.getSource().equals(button1))
				label1.setText("");
			else if(button.getSource().equals(button2))
				label2.setText("");
			else if(button.getSource().equals(button3))
				label3.setText("");
			else
				label4.setText("");
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

}
