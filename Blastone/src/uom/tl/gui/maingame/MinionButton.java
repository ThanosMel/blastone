
package uom.tl.gui.maingame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import uom.tl.card.Card;
import uom.tl.card.MinionCard;
//klash -button gia tis kartes tupou minion
public class MinionButton extends CardButton {
	
	private MinionCard minion;
	
	public MinionButton(){
		this.setVisible(true);
	}
	//methodos pou prosthetei sto button 3 labels gia to health,mana kai damage tou minion.
	public void customizeButton(){
		this.setLayout(null);
		JLabel hp= new JLabel(Integer.toString(minion.getHp()));
		JLabel dmg= new JLabel(Integer.toString(minion.getDmg()));
		JLabel mana= new JLabel(Integer.toString(minion.getMana()));

		this.add(hp);
		this.add(mana);
		this.add(dmg);
		
	
		Font hsFont=BackgroundGui.gethsFont();
		
		if(minion.getHp()>9){
			hp.setBounds(73,115 ,28,30);
		}
		else{
			hp.setBounds(81,115 ,28,30);
			
		}
		hp.setFont(hsFont);
		hp.setForeground(Color.WHITE);
		
		if(minion.getMana()>9){
			mana.setBounds(0,0 , 28, 30);
		}
		else{
			mana.setBounds(7,0, 28, 30);
		}
		mana.setFont(hsFont);
		mana.setForeground(Color.WHITE);
		if(minion.getDmg()>9){
			dmg.setBounds(0, 115, 28, 30);
		}
		else{
			dmg.setBounds(9, 115, 28, 30);
		}
		dmg.setFont(hsFont);
		dmg.setForeground(Color.BLACK);	
		
		this.setContentAreaFilled(false);
		this.setBorderPainted(true);	
		this.setOpaque(false);
		this.setBorder(null);
		
	}

	public MinionCard getMinion(){
		return minion;
	}
	
	public void setMinion(MinionCard minion){
		this.minion=minion;
	}
	@Override
	public Card getCard() {
		return minion;
	}
	
	
	

}
