package uom.tl.gui.maingame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.JSONException;

import uom.tl.board.Board;
import uom.tl.card.Card;
import uom.tl.listeners.Bling;
import uom.tl.listeners.Listener;
import uom.tl.player.Player;
//H klash frame gia to paidio ths maxhs(h kartes kai o hrwas patiounte me double click ,to zoom allazei me aplo mouse-hover,kai to endturn me aplo click)
public class BackgroundGui extends JFrame{
	//ta jlabels me to mana tou kathe paikth
	private JLabel manap1;
	private JLabel manap2;
	private HeroButton herop1;
	private HeroButton herop2;
	private JButton endTurnButton;
	//to deksh panel pou periexei tous hrwes(herop1,herop2) ta mana label tous kai to endTurnButton
	private JPanel rightPanel;
	//ena zoom ths kartas pou o xrhsths exei panw to pontiki
	//voithaei ton xrhsth na diavasei ti kanei kathe karta
	private JLabel zoomedCardLbl;
	//ta panels me tis kartes pou exei o kathe paikths sto xeri tou.
	//mono otan einai h seira tou paikth einai emfanes to panel me tis kartes pou exei sto xeri tou.
	private HandPanel handPanelp1;
	private HandPanel handPanelp2;
	//ta hidden panels
	//mono otan den einai h seira tou paikth einai emfanoi.
	private HiddenCardPanel hiddenPanelp1;
	private HiddenCardPanel hiddenPanelp2;
	//to panel me tis kartes pou exei paiksei o paikths sto tablo 
	//einai panta emfanoi kai ta 2 panels.
	private CardsPanel cardPanelp1;
	private CardsPanel cardPanelp2;
	private Player p1;
	private Player p2;
	//to backgound
	private JLabel backgroundLabel;
	//sto panel bainoun oi kartes pou exei sto tablo o kathe paikths
	//kai an einai h seira tou bainei to handPanel alliws to hiddenPanel.
	private JPanel panel1;
	private JPanel panel2;
	
	private static Font hsFont ;
	//periexei tis prosfates kinhseis kathe paikth gia ton trexon guro.
	private static JTextArea logField;
	private static Listener listener;

	public BackgroundGui() throws JSONException{
		this.setVisible(true);
		this.setSize(1366,768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		
		Board b = new Board();
		p1=new Player();
		p1.setName("Player 1");
		p2=new Player();
		p2.setName("Player 2");
		b.startGame(p1, p2);
		this.createFont();
		
		final Image backgroundImage= new ImageIcon(this.getClass().getResource("/FONT.jpg")).getImage();
		getContentPane().setLayout(null);
		backgroundLabel= new JLabel("");
		backgroundLabel.setBounds(0, 0, 1366, 768);
		backgroundLabel.setIcon(new ImageIcon(backgroundImage));
		//kanoume ton contentPane sthn ousia to backgroundLabel
		//edw tha boun ola ta components.
		this.setContentPane(backgroundLabel);
		//this.getContentPane().add(backgroundLabel);
	
		herop1=new HeroButton(p1);

		herop2=new HeroButton(p2);
		
		//End turn buton
		endTurnButton = new JButton();
		Image endTurnImg= new ImageIcon(this.getClass().getResource("/endTurn1.png")).getImage();
		endTurnButton.setIcon(new ImageIcon(endTurnImg));
		endTurnButton.setContentAreaFilled(false);
		endTurnButton.setBorderPainted(true);	
		endTurnButton.setOpaque(false);
		endTurnButton.setBorder(null);
		
		//Zoomed card label
		zoomedCardLbl = new JLabel();
		zoomedCardLbl.setBounds(5,415 ,210,300);
		Image img =new ImageIcon(this.getClass().getResource("/cardBackZoom.png")).getImage();	
		zoomedCardLbl.setIcon(new ImageIcon(img));
		zoomedCardLbl.repaint();
		
		//Logfield
		logField=new JTextArea();
		logField.setLineWrap(true);
		logField.setWrapStyleWord(true);
		logField.setEditable(false);
		logField.setForeground(Color.WHITE);
		Font logFont =new Font(hsFont.getFontName(),Font.BOLD,15);
		logField.setFont(logFont);
		logField.setOpaque(false);
		
		JScrollPane sp= new JScrollPane(logField);
		sp.setBorder(null);
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL) {
			  public boolean isVisible() {
			    return true;
			  }
			};
		sp.setVerticalScrollBar(scrollBar);
		
		sp.getViewport().setOpaque(false);
		sp.setOpaque(false);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		//sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(35,35,235,280);
		
		getContentPane().add(sp);
		getContentPane().add(zoomedCardLbl);
		
		manap1=new JLabel(p1.getCurrentMana()+"/"+p1.getMaxMana());
		manap2=new JLabel(p2.getCurrentMana()+"/"+p2.getMaxMana());
		
		manap1.setFont(hsFont);
		manap1.setForeground(Color.WHITE);
		
		manap2.setFont(hsFont);
		manap2.setForeground(Color.WHITE);
	

		rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setOpaque(false);
		rightPanel.add(herop1);
		rightPanel.add(herop2);
		rightPanel.add(endTurnButton);
		rightPanel.add(manap1);
		rightPanel.add(manap2);
		getContentPane().add(rightPanel);
		
		rightPanel.setBounds(1158, 0, 202, 741);
		herop2.setBounds(10,66,185,237);
		herop1.setBounds(10,434,185,237);
		endTurnButton.setBounds(24, 326, 153, 73);	
		manap2.setBounds(65,30, 70, 50);
		manap1.setBounds(65,650, 70	,50);

		handPanelp1= new HandPanel(p1);
		hiddenPanelp1=new HiddenCardPanel(p1);
		cardPanelp1=new CardsPanel(p1);
		handPanelp2= new HandPanel(p2);
		hiddenPanelp2=new HiddenCardPanel(p2);
		cardPanelp2=new CardsPanel(p2);
		
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setOpaque(false);
		
		panel1.add(cardPanelp1,BorderLayout.CENTER);
		
		if(p1==Card.getBoard().getActivePlayer()){
			panel1.add(handPanelp1,BorderLayout.SOUTH);
			this.revalidate();
		this.revalidate();
		}else{
			panel1.add(hiddenPanelp1,BorderLayout.SOUTH);
			this.revalidate();
		}
		
		
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setOpaque(false);
		
		panel2.add(cardPanelp2,BorderLayout.CENTER);
		if(p2==Card.getBoard().getActivePlayer()){
			panel2.add(handPanelp2,BorderLayout.NORTH);
			this.revalidate();
		}else{
			panel2.add(hiddenPanelp2,BorderLayout.NORTH);
			this.revalidate();
			
		}
		
		this.add(panel1);
		panel1.setBounds(280, 380, 800, 350);
		
		this.add(panel2);
		panel2.setBounds(280, 0, 800, 350);
		
		listener=new Listener(b,this);
		
		
		this.repaint();
		this.revalidate();
		
		
	}
	public static Listener getListener(){
		return listener;
	}
	public static Font gethsFont(){
		return hsFont;
	}
	public void createFont(){
		try {
		    hsFont = Font.createFont(Font.TRUETYPE_FONT, new File("Font/hs_font.ttf")).deriveFont(Font.BOLD,25);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Font/hs_font.ttf")));
		} catch (IOException e) {
			System.out.println("Couldnt find your file");
		} catch(FontFormatException e) {
		    System.out.println("The file is not in ttf format");
		}
	}
	
	public JPanel getHiddenPanelp1(){
		return hiddenPanelp1;
	}
	public void setHiddenPanelp1(HiddenCardPanel hp){
		this.hiddenPanelp1=hp;
	}
	public JPanel getHiddenPanelp2(){
		return hiddenPanelp2;
	}
	public void setHiddenPanelp2(HiddenCardPanel hp){
		this.hiddenPanelp2=hp;
	}
	public JPanel getPanel2(){
		return panel2;
	}
	public void setPanel2(JPanel p){
		this.panel2=p;
	}
	public JPanel getPanel1(){
		return panel1;
	}
	public void setPanel1(JPanel p){
		this.panel1=p;
	}
	public Player getP1(){
		return p1;
	}
	public Player getP2(){
		return p2;
	}
	public CardsPanel getcardPanelp1(){
		return cardPanelp1;
	}
	public void setCardPanelp1(CardsPanel cp){
		this.cardPanelp1=cp;
	}
	public void setCardPanelp2(CardsPanel cp){
		this.cardPanelp2=cp;
	}
	public CardsPanel getcardPanelp2(){
		return cardPanelp2;
	}
	public JLabel getZoomedCardLbl(){
		return zoomedCardLbl;
	}
	public void setZoomedCardLbl(JLabel jl){
		this.zoomedCardLbl=jl;
	}
	public HandPanel getHandPanelp1(){
		return handPanelp1;
	}
	public void setHandPanelp1(HandPanel hp){
		this.handPanelp1=hp;
	}
	public void setHandPanelp2(HandPanel hp){
		this.handPanelp2=hp;
	}
	public HandPanel getHandPanelp2(){
		return handPanelp2;
	}
	public JButton getEndTurnButton(){
		return endTurnButton;
	}
	
	public void setP1(Player p1) {
		this.p1 = p1;
	}
	public void setP2(Player p2) {
		this.p2 = p2;
	}
	public  HeroButton getHeroButtonp1(){
		return herop1;
	}
	public  HeroButton getHeroButtonp2(){
		return herop2;
	}
	public void setHeroButtonp1(HeroButton hb){
		this.herop1=hb;
	}
	public void setHeroButtonp2(HeroButton hb){
		this.herop2=hb;
	}
	public JPanel getRightPanel(){
		return rightPanel;
	}
	public JLabel getManap1() {
		return manap1;
	}
	public void setManap1(JLabel manap1) {
		this.manap1 = manap1;
		manap1.setFont(gethsFont());
		manap1.setForeground(Color.WHITE);
	}
	public JLabel getManap2() {
		return manap2;
	}
	public void setManap2(JLabel manap2) {
		this.manap2 = manap2;
		manap2.setFont(gethsFont());
		manap2.setForeground(Color.WHITE);
	}
	public static JTextArea getLogField() {
		return logField;
	}
	public static void setLogField(JTextArea logField) {
		logField = logField;
	}
	

}
