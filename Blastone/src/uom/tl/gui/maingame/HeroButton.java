package uom.tl.gui.maingame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import uom.tl.listeners.Bling;
import uom.tl.player.Player;
//to button tou hrwa 
public class HeroButton extends JButton {
	private Player p;
	private JLabel hp;
	ImageIcon img;
	public HeroButton(Player p){
		super();
		this.p=p;
		this.setVisible(true);
		this.setLayout(null);
		this.revalidate();
		img= new ImageIcon(this.getClass().getResource("/"+p.getHero().toLowerCase()+".png"));
		this.setIcon(img);
		
		this.setContentAreaFilled(false);
		this.setBorderPainted(true);	
		this.setOpaque(false);
		this.setBorder(null);
		int hp1=p.getHp();
		hp=new JLabel(Integer.toString(hp1));
		this.add(hp);
		
		Font font=new Font(BackgroundGui.gethsFont().getFontName(),Font.BOLD,30);
		if(p.getHp()<9){
			hp.setBounds(150, 130, 50, 50);
		}
		else{
			hp.setBounds(142,130, 50, 50);
		}
	
		hp.setFont(font);
		hp.setForeground(Color.WHITE);
		
		
	
		
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public Player getPlayerFromHeroButton(){
		return p;
	}
	public  JLabel getHp(){
		return hp;
	}
	

}
