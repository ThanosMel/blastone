
package uom.tl.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import uom.tl.board.Board;
import uom.tl.card.MinionCard;
import uom.tl.card.Position;
import uom.tl.card.spells.BuffSpell;
import uom.tl.card.spells.DrawCardSpell;
import uom.tl.card.spells.HealSpell;
import uom.tl.card.spells.MultiTargetSpell;
import uom.tl.card.spells.MultiplyAttackSpell;
import uom.tl.card.spells.SpellCard;
import uom.tl.gui.createdeck.ChooseHeroAndDeck;
import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.gui.maingame.CardButton;
import uom.tl.gui.maingame.CardsPanel;
import uom.tl.gui.maingame.HandPanel;
import uom.tl.gui.maingame.HeroButton;
import uom.tl.gui.maingame.HiddenCardPanel;
import uom.tl.gui.maingame.MainMenu;
import uom.tl.gui.maingame.MinionButton;
import uom.tl.gui.maingame.SpellButton;


//o listener gia to frame ths maxhs.
public class Listener implements ActionListener,MouseListener{
	//ta components ths klashs BackgroundGui ta opoia tha paroume mesw getters h mesw tou kataskeuasth.
	private static BackgroundGui bg;
	private JButton endTurnButton;
	private Board board;
	private ArrayList<CardButton> handp1;
	private ArrayList<CardButton> handp2;
	private ArrayList<CardButton> cardAreap1;
	private ArrayList<CardButton> cardAreap2;
	private HeroButton herop1;
	private HeroButton herop2;
	private boolean firstTime;
	//h prwth karta pou epelekse o paikths
	//e.g prwta epilegeis ena apo ta dika sou minions to apothikeueis sto firstCard
	//    meta epilegeis ena apo ta minions tou antipalou kai kaneis epithesh me to diko sou minion pou einai apothikeumeno sto firstCard.
	private JButton firstCard=null;
	private JTextArea logField;

	public Listener(Board b,BackgroundGui bg){
		this.bg=bg;
		this.board=b;
		addActionListeners();
		endTurnButton=bg.getEndTurnButton();
		endTurnButton.addActionListener(this);
		logField=bg.getLogField();
		firstTime=true;
		
	
		
	}
	//methodos h opoia afairei olous tous listeners apo ta koumpia
	//einai xrhshmh giati otan to paixnidi teliwnei kai emfanizetai to frame me ton nikhth de theloume 
	//o xrhsths na borei na pataei tis kartes h ton hrwa.
	public void removeActionListeners(){
		handp1=bg.getHandPanelp1().getCards();
		handp2=bg.getHandPanelp2().getCards();
		cardAreap1=bg.getcardPanelp1().getCards();
		cardAreap2=bg.getcardPanelp2().getCards();
		herop1=bg.getHeroButtonp1();
		herop1.addMouseListener(this);
		herop2=bg.getHeroButtonp2();
		herop2.addMouseListener(this);
		for(int i=0; i<handp1.size();i++){
			handp1.get(i).removeActionListener(this);
			handp1.get(i).removeMouseListener(this);
		}
		for(int i=0; i<handp2.size();i++){
			handp2.get(i).removeActionListener(this);
			handp2.get(i).removeMouseListener(this);
		}
		for(int i=0; i<cardAreap1.size(); i++){
			
			cardAreap1.get(i).removeMouseListener(this);
			cardAreap1.get(i).removeActionListener(this);
		}
		for(int i=0; i<cardAreap2.size(); i++){
			cardAreap2.get(i).removeActionListener(this);
			cardAreap2.get(i).removeMouseListener(this);
		}
	
	}
	//prosthetie listeners se ola ta current components pou briskontai sto BackgroundGui frame.
	public void addActionListeners(){
		handp1=bg.getHandPanelp1().getCards();
		handp2=bg.getHandPanelp2().getCards();
		cardAreap1=bg.getcardPanelp1().getCards();
		cardAreap2=bg.getcardPanelp2().getCards();
		herop1=bg.getHeroButtonp1();
		herop1.addMouseListener(this);
		herop2=bg.getHeroButtonp2();
		herop2.addMouseListener(this);
	
		for(int i=0; i<handp1.size();i++){
			handp1.get(i).addActionListener(this);
			handp1.get(i).addMouseListener(this);
		}
		for(int i=0; i<handp2.size();i++){
			handp2.get(i).addActionListener(this);
			handp2.get(i).addMouseListener(this);
		}
		for(int i=0; i<cardAreap1.size(); i++){
			
			cardAreap1.get(i).addActionListener(this);
			cardAreap1.get(i).addMouseListener(this);
		}
		for(int i=0; i<cardAreap2.size(); i++){
			cardAreap2.get(i).addActionListener(this);
			cardAreap2.get(i).addMouseListener(this);
		}

		//bg.revalidate();
		
		
	}	
	//methodos h opoia dexetai ena string to opoio antiproswpeuei thn allagh pou tha ginei sto BackgroundGui
	//kai telika allazei ta components tou.
	public void updateField(String change) {
	
		if(change=="heroHp"){
			bg.getRightPanel().remove(herop1);
			bg.getRightPanel().remove(herop2);
			bg.setHeroButtonp1(new HeroButton(bg.getP1()));
			bg.setHeroButtonp2(new HeroButton(bg.getP2()));
			bg.getRightPanel().add(bg.getHeroButtonp1());
			bg.getHeroButtonp2().setBounds(10,66,185,237);
			bg.getRightPanel().add(bg.getHeroButtonp2());
			bg.getRightPanel().revalidate();
			bg.getHeroButtonp1().setBounds(10,434,185,237);
			bg.revalidate();
			if(!board.isGameOver()){
				bg.repaint();
				
			}
			
		
		}
		else if(change=="hand"){
			bg.getRightPanel().remove(bg.getManap1());
			bg.getRightPanel().remove(bg.getManap2());
			bg.setManap1(new JLabel(bg.getP1().getCurrentMana()+"/"+bg.getP1().getMaxMana()));
			bg.setManap2(new JLabel(bg.getP2().getCurrentMana()+"/"+bg.getP2().getMaxMana()));
			bg.getRightPanel().add(bg.getManap1());
			bg.getRightPanel().add(bg.getManap2());
			bg.getManap2().setBounds(65,30, 70, 50);
			bg.getManap1().setBounds(65,650, 70, 50);
			if(bg.getP1()==board.getActivePlayer()){
				bg.getPanel1().remove(bg.getHiddenPanelp1());
				bg.getPanel1().remove(bg.getHandPanelp1());
				bg.setHandPanelp1(new HandPanel(bg.getP1()));
				bg.getPanel1().add(bg.getHandPanelp1(),BorderLayout.SOUTH);
				bg.revalidate();
			}
			else{
				bg.getPanel1().remove(bg.getHiddenPanelp1());
				bg.getPanel1().remove(bg.getHandPanelp1());
				bg.setHiddenPanelp1(new HiddenCardPanel(bg.getP1()));
				bg.getPanel1().add(bg.getHiddenPanelp1(),BorderLayout.SOUTH);
				bg.revalidate();
				
			}
			if(bg.getP2()==board.getActivePlayer()){
				bg.getPanel2().remove(bg.getHiddenPanelp2());
				bg.getPanel2().remove(bg.getHandPanelp2());
				bg.setHandPanelp2(new HandPanel(bg.getP2()));
				bg.getPanel2().add(bg.getHandPanelp2(),BorderLayout.NORTH);
				bg.revalidate();
			}
			else{
				
				bg.getPanel2().remove(bg.getHiddenPanelp2());
				bg.getPanel2().remove(bg.getHandPanelp2());
				bg.setHiddenPanelp2(new HiddenCardPanel(bg.getP2()));
				bg.getPanel2().add(bg.getHiddenPanelp2(),BorderLayout.NORTH);
				bg.revalidate();
				
			}
			bg.getPanel1().remove(bg.getcardPanelp1());
			bg.setCardPanelp1(new CardsPanel(bg.getP1()));
			bg.getPanel1().add(bg.getcardPanelp1(),BorderLayout.CENTER);
			
			bg.getPanel2().remove(bg.getcardPanelp2());
			bg.setCardPanelp2(new CardsPanel(bg.getP2()));
			bg.getPanel2().add(bg.getcardPanelp2(),BorderLayout.CENTER);
			
		
		}
		else if(change=="field"){
			bg.getPanel1().remove(bg.getcardPanelp1());
			bg.setCardPanelp1(new CardsPanel(bg.getP1()));
			bg.getPanel1().add(bg.getcardPanelp1(),BorderLayout.CENTER);
			
			bg.getPanel2().remove(bg.getcardPanelp2());
			bg.setCardPanelp2(new CardsPanel(bg.getP2()));
			bg.getPanel2().add(bg.getcardPanelp2(),BorderLayout.CENTER);
			
			
		}
		else if (change=="all"){
			logField.setText("");
			
			bg.getRightPanel().remove(bg.getManap1());
			bg.getRightPanel().remove(bg.getManap2());
			bg.setManap1(new JLabel(bg.getP1().getCurrentMana()+"/"+bg.getP1().getMaxMana()));
			bg.setManap2(new JLabel(bg.getP2().getCurrentMana()+"/"+bg.getP2().getMaxMana()));
			bg.getRightPanel().add(bg.getManap1());
			bg.getRightPanel().add(bg.getManap2());
			bg.getManap2().setBounds(65,30, 70, 50);
			bg.getManap1().setBounds(65,650, 70, 50);
			bg.getRightPanel().remove(herop1);
			bg.getRightPanel().remove(herop2);
			
			bg.setHeroButtonp1(new HeroButton(bg.getP1()));
			bg.setHeroButtonp2(new HeroButton(bg.getP2()));
			bg.getRightPanel().add(bg.getHeroButtonp1());
			bg.getHeroButtonp2().setBounds(10,66,185,237);
			bg.getRightPanel().add(bg.getHeroButtonp2());
			bg.getHeroButtonp1().setBounds(10,434,185,237);
			bg.revalidate();
			
			if(bg.getP1()==board.getActivePlayer()){
				bg.getPanel1().remove(bg.getHiddenPanelp1());
				bg.getPanel1().remove(bg.getHandPanelp1());
				bg.setHandPanelp1(new HandPanel(bg.getP1()));
				bg.getPanel1().add(bg.getHandPanelp1(),BorderLayout.SOUTH);
				bg.revalidate();
			}
			else{
				bg.getPanel1().remove(bg.getHiddenPanelp1());
				bg.getPanel1().remove(bg.getHandPanelp1());
				bg.setHiddenPanelp1(new HiddenCardPanel(bg.getP1()));
				bg.getPanel1().add(bg.getHiddenPanelp1(),BorderLayout.SOUTH);
				bg.revalidate();
				
			}
			if(bg.getP2()==board.getActivePlayer()){
				bg.getPanel2().remove(bg.getHiddenPanelp2());
				bg.getPanel2().remove(bg.getHandPanelp2());
				bg.setHandPanelp2(new HandPanel(bg.getP2()));
				bg.getPanel2().add(bg.getHandPanelp2(),BorderLayout.NORTH);
				bg.revalidate();
			}
			else{
				
				bg.getPanel2().remove(bg.getHiddenPanelp2());
				bg.getPanel2().remove(bg.getHandPanelp2());
				bg.setHiddenPanelp2(new HiddenCardPanel(bg.getP2()));
				bg.getPanel2().add(bg.getHiddenPanelp2(),BorderLayout.NORTH);
				bg.revalidate();
				
			}
			
			bg.getPanel1().remove(bg.getcardPanelp1());
			bg.setCardPanelp1(new CardsPanel(bg.getP1()));
			bg.getPanel1().add(bg.getcardPanelp1(),BorderLayout.CENTER);
			
			bg.getPanel2().remove(bg.getcardPanelp2());
			bg.setCardPanelp2(new CardsPanel(bg.getP2()));
			bg.getPanel2().add(bg.getcardPanelp2(),BorderLayout.CENTER);
			//bg.repaint();
			
		}
		if(board.isGameOver()){
			gameOver();
		}
		else{
			addActionListeners();
			bg.revalidate();
		}
		
		
	}
	//JLabel to opoio emfanizetai mono otan exei teliwsei to paixnidi me 2 epiloges einai na pame sto arxiko menu h na ksanapaiksoume.
	public void gameOver(){
		removeActionListeners();
		endTurnButton.removeActionListener(this);
		bg.remove(bg.getPanel1());
		bg.remove(bg.getPanel2());
		JLabel gameOver=new JLabel();
		ImageIcon img = new ImageIcon("images/gameover.jpg");
		Image img2 = img.getImage();
		Image newimg = img2.getScaledInstance(400, 224,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		gameOver.setIcon(newIcon);     
		gameOver.setPreferredSize(new Dimension(400,224)) ;
		gameOver.setOpaque(false );
		gameOver.setVisible(true);
		
		JLabel winner = new JLabel(board.getWinner().getName()+" won with");
		JLabel winnerHero= new JLabel(board.getWinner().getHero().toUpperCase()+"!");
		JButton playAgain=new JButton("Play Again");
		
		ImageIcon img1 = new ImageIcon("images/buttons_wooden.jpg");
		Image img3 = img1.getImage();
		Image newimg1 = img3.getScaledInstance(150,60,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon1 = new ImageIcon(newimg1);
		playAgain.setIcon(newIcon1);
		playAgain.setHorizontalTextPosition(JButton.CENTER);
		playAgain.setVerticalTextPosition(JButton.CENTER);
		playAgain.setBorder(BorderFactory.createLineBorder(new Color(156, 93, 82), 1));
		playAgain.setOpaque(false);
		
		JButton mainMenu=new JButton("Main menu");
		Image newimg2=img3.getScaledInstance(170, 60,java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon2= new ImageIcon(newimg2);
		mainMenu.setIcon(newIcon2);
		mainMenu.setHorizontalTextPosition(JButton.CENTER);
		mainMenu.setVerticalTextPosition(JButton.CENTER);
		mainMenu.setBorder(BorderFactory.createLineBorder(new Color(156, 93, 82), 1));
		mainMenu.setOpaque(false);
		
		gameOver.setLayout(null);
		gameOver.add(winner);
		gameOver.add(winnerHero);
		gameOver.add(playAgain);
		gameOver.add(mainMenu);
		
		mainMenu.setBounds(210,117,170,60);
		playAgain.setBounds(30,117,150,60);
		winner.setBounds(25, 0, 310,50);
		winnerHero.setBounds(150,50, 300, 60);
		
		winner.setForeground(Color.BLACK);
		winner.setFont(new Font(BackgroundGui.gethsFont().getFontName(),Font.BOLD,30));

		winnerHero.setForeground(Color.BLACK);
		winnerHero.setFont(new Font(BackgroundGui.gethsFont().getFontName(),Font.BOLD,30));
		
		mainMenu.setFont(new Font(BackgroundGui.gethsFont().getFontName(),Font.BOLD,20));
		playAgain.setFont(new Font(BackgroundGui.gethsFont().getFontName(),Font.BOLD,20));
		
		playAgain.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			  new ChooseHeroAndDeck();
			  bg.dispose();
		  }
		});
		mainMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new MainMenu();
				bg.dispose();
			}
			
		});
	
		bg.getContentPane().add(gameOver);
		gameOver.setBounds(483,272,400,224);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(endTurnButton)){
			firstCard=null;
			firstTime=true;
			boolean statement1	=board.getActivePlayer().getField().getHand().size()==0 && board.getActivePlayer().getField().getDeck().getDeck().size()==0;
			boolean statement2=board.getActivePlayer().getField().getCardsInField().size()==0;
			if(statement1==true&&statement2==true){
				board.setWinner(board.getOpponentPlayer());
				gameOver();
				
			}
			else{
				board.getActivePlayer().endTurn();
				updateField("all");
				
			}
			
		}
		
	}



	public void mouseClicked(MouseEvent e ) {
		if(firstTime){
			if(e.getClickCount()==1 && e.getClickCount()!=2){
				logField.append("-You must double click in order to play!!\n"+"************\n");
				firstTime=false;
				return;
			}
			
		}
		if (e.getClickCount() == 2) {
			firstTime=false;
			//an to click prohlthe apo mia karta tupou minion
			if(e.getSource() instanceof MinionButton){
				//kratame thn karta minion
				MinionCard minion= ((MinionButton)e.getSource()).getMinion();
				//an auth h karta einai sto xeri mas.
				if(minion.getPosition()==Position.HAND){
					if(board.getActivePlayer().getCurrentMana()-minion.getMana()<0){
						if(board.getActivePlayer().getName()=="Player 1"){
							new Bling(bg.getManap1());
						}
						else{
							new Bling(bg.getManap2());
						}
						logField.append("-You dont have enought mana to play this card!!\n"+"************\n");
						return;
					}
					//tote thn paizoume sto tablo
					board.getActivePlayer().throwCard(minion);
					updateField("hand");
					return;
				}
				//an auth  karta den einai sto xeri mas kai einai sto tablo
				if(minion.getPosition().equals(Position.FIELD)){
					//elenxoume an einai sto diko mas tablo h stou antipalou
					if(board.getActivePlayer().getField().getCardsInField().contains(minion)){
						//an exoume epileksei prwth karta kai einai tupou spell.
						if(firstCard!=null && firstCard instanceof SpellButton){
							SpellCard yourSpell=((SpellButton) firstCard).getSpell();
							if(yourSpell instanceof BuffSpell || yourSpell instanceof MultiplyAttackSpell||yourSpell instanceof HealSpell){
								yourSpell.action(minion);
								firstCard=null;
								updateField("field");
								return;
							}
							
						}//an den exoume epileksei prwth karta tote apothikeuoume thn minion karta mas sth metavlhth firstCard
						else{
							firstCard=((MinionButton) e.getSource());
							logField.append("-You clicked one of your minion cards in field!\n"+"************\n");
							return;
						}
						
					
					
						
						
						
					}
					//an h karta minion pou pathsame den einai sto diko mas tablo
					else{
						//an h prwth karta einai tupou minion tote kanoume epithesh me thn karta firstCard mas sth karta minion.
						if(firstCard instanceof MinionButton){
							
							MinionCard yourMinion=((MinionButton) firstCard).getMinion();
							yourMinion.action(minion);
							updateField("field");
							return;
						}
						//alliws kanoume "epithesh" me thn karta spell sth karta minion
						else{
							if(firstCard!=null){
								SpellCard yourSpell=((SpellButton) firstCard).getSpell();
								yourSpell.action(minion);
								if(yourSpell instanceof MultiTargetSpell){
									//updateField("heroHp");
								}
								firstCard=null;
								updateField("field");
								
								return;
							}
							
							
						}
					
						
					}
				}
			}
			//an to click prohlthe apo karta tupou spell
			if(e.getSource() instanceof SpellButton){
				//kratame thn karta spell
				SpellCard spell=((SpellButton)e.getSource()).getSpell();
				//an auth h karta einai sto xeri mas thn paizoume.
				if(spell.getPosition()==Position.HAND){
					if(board.getActivePlayer().getCurrentMana()-spell.getMana()<0){
						if(board.getActivePlayer().getName()=="Player 1"){
							new Bling(bg.getManap1());
						}
						else{
							new Bling(bg.getManap2());
						}
						logField.append("-You dont have enought mana to play this card!!\n"+"************\n");
						return;
					}
					board.getActivePlayer().throwCard(spell);
					updateField("hand");
					return;
				}
				//an den einai sto xeri mas kai einai sto tablo
				if(spell.getPosition()==Position.FIELD){
					//elenxoume an einai sto diko mas tablo klp
					if(board.getActivePlayer().getField().getCardsInField().contains(spell)){
						if(spell.getTargetSelection().equals("NONE")){
							logField.append("-You clicked one of your spell cards in field that NEED NO target selection to be played\n"+"************\n");
							firstCard=null;
						
							spell.action();
							if(spell instanceof DrawCardSpell){
								updateField("hand");
							}
							//updateField("heroHp");
							updateField("field");
							return;
						}
						else{
							logField.append("-You clicked one of your spell cards in field that NEED target selection to be played\n"+"************\n");
							firstCard=(SpellButton) e.getSource();
							//updateField("field");
							return;
						}
						
					}
					
				}
			}
			if(e.getSource() instanceof HeroButton){
				if(firstCard!=null){
					HeroButton hb=(HeroButton)e.getSource();
					if(hb.getPlayerFromHeroButton().equals(board.getActivePlayer())){
						if(firstCard instanceof SpellButton){
							SpellCard yourSpell= ((SpellButton) firstCard).getSpell();
							if(yourSpell instanceof HealSpell){
								 	yourSpell.action();
								 	firstCard=null;
									//updateField("heroHp");
									updateField("field");
									return;
								
							}
							
						}
						
					}
					else{
						
						if(firstCard instanceof MinionButton ){
							MinionCard yourMinion=((MinionButton) firstCard).getMinion();
							yourMinion.action();
						
							//updateField("heroHp");
							firstCard=null;
							return;
							
							
						
							
						}
						else{
							SpellCard yourSpell= ((SpellButton) firstCard).getSpell();
							yourSpell.action(null);
							updateField("field");
							//updateField("heroHp");
							firstCard=null;
							return;
						}
						
					}
					
					
				}
			}
		}

		
		
			
		
	}
	//methodos h opoia allazei to zoom ths kartas analogws se poia karta vrisketai panw to pontiki
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() instanceof MinionButton){
			JLabel zoomedLbl=bg.getZoomedCardLbl();
			zoomedLbl.removeAll();
		
			MinionButton mb= (MinionButton) e.getSource();
			MinionCard mc= mb.getMinion();
			
			String path = mc.getImagePath();
			
			ImageIcon img= new ImageIcon("images/cardImages/"+path);
			zoomedLbl.setIcon(img);
			
			Font font=new Font(BackgroundGui.gethsFont().getFontName(),Font.BOLD,65);
			
			zoomedLbl.setLayout(null);
			JLabel hp= new JLabel(Integer.toString(mc.getHp()));
			JLabel dmg= new JLabel(Integer.toString(mc.getDmg()));
			JLabel mana= new JLabel(Integer.toString(mc.getMana()));
			zoomedLbl.add(hp);
			zoomedLbl.add(dmg);
			zoomedLbl.add(mana);
		
			
			if(mc.getHp()>9){
				hp.setBounds(143,238,78,60);
			}
			else{
				hp.setBounds(168,238 ,50,60);
			}
			hp.setFont(font);
			hp.setForeground(Color.WHITE);
			
			if(mc.getMana()>9){
				mana.setBounds(-10,5 , 78, 60);
			}
			else{
				mana.setBounds(9,0, 50, 60);
			}
			mana.setFont(font);
			mana.setForeground(Color.WHITE);
			if(mc.getDmg()>9){
				dmg.setBounds(-10, 238, 78, 60);
			}
			else{
				dmg.setBounds(15, 238, 50, 60);
			}
			dmg.setFont(font);
			dmg.setForeground(Color.BLACK);
			
			bg.getZoomedCardLbl().revalidate();
			bg.revalidate();
			
			
		}
		if(e.getSource() instanceof SpellButton){
			JLabel zoomedLbl=bg.getZoomedCardLbl();
			zoomedLbl.removeAll();
		
			SpellButton sb= (SpellButton) e.getSource();
			SpellCard sc= sb.getSpell();
			
			String path = sc.getImagePath();
			
			ImageIcon img= new ImageIcon("images/cardImages/"+path);
			zoomedLbl.setIcon(img);
			
			Font font=new Font(BackgroundGui.gethsFont().getFontName(),Font.BOLD,65);
			
			zoomedLbl.setLayout(null);
			JLabel mana= new JLabel(Integer.toString(sc.getMana()));
			
			zoomedLbl.add(mana);
			
			if(sc.getMana()>9){
				mana.setBounds(0,0 , 28, 30);
			}
			else{
				mana.setBounds(9,0, 50, 60);
			}
			mana.setFont(font);
			mana.setForeground(Color.WHITE);
		
			bg.getZoomedCardLbl().revalidate();
			bg.revalidate();
			
		}
	
			
		
		
	}
	
	public static BackgroundGui getBg(){
		return bg;
	}
	
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	public void mousePressed(MouseEvent arg0) {
		
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
		
	}

}
