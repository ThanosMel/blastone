package uom.tl.gui.maingame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uom.tl.card.Card;
import uom.tl.card.MinionCard;
import uom.tl.player.Player;
//panel me tis krufes kartes tou kathe paikth pou emfanizetai mono otan den einai h seira tou.
public class HiddenCardPanel extends JPanel {
	private ArrayList<JLabel> hiddenCards;
	
	
	public HiddenCardPanel(Player p) {
		super();
		this.removeAll();
		this.revalidate();
		hiddenCards= new ArrayList<JLabel>(20);
		this.setOpaque(false);
		ArrayList<Card> hand = p.getField().getHand();
		this.setLayout(new FlowLayout());
		ImageIcon img = new ImageIcon("images/cardBack.png");
		Image img2 = img.getImage();
		Image newimg = img2.getScaledInstance(100, 146,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		
		for(int i = 0; i < hand.size(); i++){
			JLabel jl=new JLabel();
			jl.setIcon(newIcon);
			jl.setPreferredSize(new Dimension(100,146));
			hiddenCards.add(jl);
			this.add(jl);
			hiddenCards.get(i).setVisible(true);
		}
		
			
	}
	

}
