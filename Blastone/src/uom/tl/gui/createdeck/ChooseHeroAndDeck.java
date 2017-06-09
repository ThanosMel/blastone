package uom.tl.gui.createdeck;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.JSONException;
import org.json.JSONObject;

import uom.tl.board.Field;
import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.gui.maingame.Main;
import uom.tl.gui.maingame.MainMenu;
import uom.tl.listeners.Bling;
import uom.tl.player.Deck;

//--Briskomaste sto frame opou o xrhsths epilegei me poion hrwa kai me pio deck thelei na paiksei(default h custom an uparxei).
//--Prwta epilegei o player 1 ,patwntas to koumpi lock epilegei o player 2 kai afou paththei to play odhgoumaste sth klash BackgroundGui
//opou kai ksekinaei h maxh.
public class ChooseHeroAndDeck extends JFrame{
	
	private Image image1, image2, image3, image4, background, backButtonIcon;
	private Image default1Icon,default2Icon,default3Icon,default4Icon;
	private Image custom1Icon,custom2Icon,custom3Icon,custom4Icon;
	private Image playIcon,lockIcon;
	//Ta plhktra custom1, custom2, custom3, custom4 emfanizontai mono efoson exoume dhmiourghsei custom deck gia ton sugekrimeno hrwa.
	private JLabel button1, button2, button3, button4;
	private JButton default1, default2, default3, default4, custom1, custom2, custom3, custom4, lock, playbutton,backButton;
	
	private JLabel player1label, player2label, player1heroname, player2heroname, player1decklabel, player2decklabel; 
	boolean player1Turn = false, player2Turn = false;
	private String hero_name;
	private CreateDeckGui createdDeck;
	private static Container c;
	private HashMap<String, ArrayList<JSONObject>> tempHash = createdDeck.getKeyAndValue();
	//ta telika decks pou tha paiksoun oi xrhstes
	private Deck Player1deck;  Deck Player2deck;
	private static Font textFont = Main.getFont();
	
	private static Field f2;
	private static Field f1;
	
	//ta onomata twn hrwwn
	private static String herop1;
	private static String herop2;
	//--arraylists pou periexoun sthn ousia ta antikeimena jsonobject me ta opoia tha dhmiourghthei to teliko deck
	private ArrayList<JSONObject> jsonDeck1 = new ArrayList<JSONObject>() , jsonDeck2 = new ArrayList<JSONObject>();
	
	private backButtonListener backlistener = new backButtonListener();
	private defaultDecksListener dlistener = new defaultDecksListener();
	private customDecksListener clistener = new customDecksListener();
	private lockAndPlayButtonListener lockAndPlayListener = new lockAndPlayButtonListener();
	
	public ChooseHeroAndDeck(){
		super("Choose Enemy");
		//H methodos CastJButton dhmiourgei kai emfanizei ola ta koumpia tou frame. 
		CastJButtons();
		
		//background
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
		//c=this.getContentPane();
		//image1 
		button1 = new JLabel();
		image1 = new ImageIcon(this.getClass().getResource("/Gul'dan.jpg")).getImage();
		button1.setIcon(new ImageIcon(image1));
		button1.setBounds(220, 80, image1.getWidth(button1), image1.getHeight(button1));
		this.add(button1);
		
		
	
	
		
		//image2
		button2 = new JLabel();
		image2 = new ImageIcon(this.getClass().getResource("/Jaina Proudmoore.jpg")).getImage();
		button2.setIcon(new ImageIcon(image2));
		button2.setBounds(826, 80, image2.getWidth(button2), image2.getHeight(button2));
		this.add(button2);
		
		this.validate();

		//image3
		button3 = new JLabel();
		image3 = new ImageIcon(this.getClass().getResource("/Rexxar.jpg")).getImage();
		button3.setIcon(new ImageIcon(image3));
		button3.setBounds(220, 410, image3.getWidth(button3), image3.getHeight(button3));
		this.add(button3);
		
		this.validate();
		
		//image4
		button4 = new JLabel();
		image4 = new ImageIcon(this.getClass().getResource("/Uther Lightbringer.jpg")).getImage();
		button4.setIcon(new ImageIcon(image4));
		button4.setBounds(826, 410, image4.getWidth(button4), image4.getHeight(button4));
		this.validate();
		this.add(button4);
		
		// backbutton
		backButton = new JButton();
		backButtonIcon = new ImageIcon(this.getClass().getResource("/back button.png")).getImage();
		backButton.setIcon(new ImageIcon(backButtonIcon));
		backButton.setBounds(60,  40, backButtonIcon.getWidth(backButton), backButtonIcon.getHeight(backButton));
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		this.add(backButton);
		backButton.addActionListener(backlistener);
		
		// player 1 label
		player1label = new JLabel();
		player1label.setBounds(625, 430, 200, 40);
		player1label.setFont(textFont);
		player1label.setForeground(Color.WHITE);
		player1label.setText("Player 1");
		this.add(player1label);
		// player 1 hero name label
		player1heroname = new JLabel();
		player1heroname.setFont(textFont);
		player1heroname.setForeground(Color.WHITE);
		player1heroname.setBounds(625, 490, 200, 40);
		this.add(player1heroname);
		// player  1 deck
		player1decklabel = new JLabel();
		player1decklabel.setFont(textFont);
		player1decklabel.setForeground(Color.WHITE);
		player1decklabel.setBounds(625, 550, 200, 40);
		this.add(player1decklabel);
		
		// player 2 label
		player2label = new JLabel();
		player2label.setBounds(625, 90, 200, 40);
		player2label.setFont(textFont);
		player2label.setForeground(Color.WHITE);
		this.add(player2label);
		// player 2 hero name label
		player2heroname = new JLabel();
		player2heroname.setFont(textFont);
		player2heroname.setForeground(Color.WHITE);
		player2heroname.setBounds(625, 150, 200, 40);
		this.add(player2heroname);
		// player  2 deck
		player2decklabel = new JLabel();
		player2decklabel.setFont(textFont);
		player2decklabel.setForeground(Color.WHITE);
		player2decklabel.setBounds(625,210 , 200, 40);
		this.add(player2decklabel);
		
		//defaults and custom buttons
		// for hero 1
		
		default1 = new JButton();
		default1Icon = new ImageIcon(this.getClass().getResource("/DEFAULT1.png")).getImage();
		default1.setIcon(new ImageIcon(default1Icon));
		default1.setBorderPainted(false);
		default1.setContentAreaFilled(false);
		default1.setBounds(90, 150,default1Icon.getWidth(default1) , default1Icon.getHeight(default1));
		default1.addActionListener(dlistener);
		this.add(default1);
		custom1 = new JButton();
		custom1Icon = new ImageIcon(this.getClass().getResource("/CUSTOM1.png")).getImage();
		custom1.setIcon(new ImageIcon(custom1Icon));
		custom1.setBorderPainted(false);
		custom1.setContentAreaFilled(false);
		if(tempHash.containsKey("hero_guldan.json")&& containsValues(tempHash,"hero_guldan.json"))
			custom1.setVisible(true);
		else
			custom1.setVisible(false);
		custom1.setBounds(90, 230, custom1Icon.getWidth(custom1), custom1Icon.getHeight(custom1));
		custom1.addActionListener(clistener);
		this.add(custom1);
		
		// for hero 2
		default2 = new JButton();
		default2Icon = new ImageIcon(this.getClass().getResource("/DEFAULT2.png")).getImage();
		default2.setIcon(new ImageIcon(default2Icon));
		default2.setBorderPainted(false);
		default2.setContentAreaFilled(false);
		default2.setBounds(1176, 150,default2Icon.getWidth(default2) , default2Icon.getHeight(default2));
		default2.addActionListener(dlistener);
		this.add(default2);
		custom2 = new JButton();
		custom2Icon = new ImageIcon(this.getClass().getResource("/CUSTOM2.png")).getImage();
		custom2.setIcon(new ImageIcon(custom2Icon));
		custom2.setBorderPainted(false);
		
		custom2.setContentAreaFilled(false);
		if(tempHash.containsKey("hero_jaina.json")&& containsValues(tempHash,"hero_jaina.json"))
			custom2.setVisible(true);
		else
			custom2.setVisible(false);
		custom2.setBounds(1176, 230, custom2Icon.getWidth(custom2), custom2Icon.getHeight(custom2));
		custom2.addActionListener(clistener);
		this.add(custom2);
		
		// for hero 3
		default3 = new JButton();
		default3Icon = new ImageIcon(this.getClass().getResource("/DEFAULT3.png")).getImage();
		default3.setIcon(new ImageIcon(default3Icon));
		default3.setBorderPainted(false);
		default3.setContentAreaFilled(false);
		default3.setBounds(90, 480,default3Icon.getWidth(default3) , default3Icon.getHeight(default3));
		default3.addActionListener(dlistener);
		this.add(default3);
		custom3 = new JButton();
		custom3Icon = new ImageIcon(this.getClass().getResource("/CUSTOM3.png")).getImage();
		custom3.setIcon(new ImageIcon(custom3Icon));
		custom3.setBorderPainted(false);
		custom3.setContentAreaFilled(false);
		if(tempHash.containsKey("hero_rexxar.json")&& containsValues(tempHash,"hero_rexxar.json"))
			custom3.setVisible(true);
		else
			custom3.setVisible(false);
		custom3.setBounds(90, 560, custom3Icon.getWidth(custom3), custom3Icon.getHeight(custom3) );
		custom3.addActionListener(clistener);
		this.add(custom3);
		
		// for hero 4
		default4 = new JButton();
		default4Icon = new ImageIcon(this.getClass().getResource("/DEFAULT4.png")).getImage();
		default4.setIcon(new ImageIcon(default4Icon));
		default4.setBorderPainted(false);
		default4.setContentAreaFilled(false);
		default4.setBounds(1176, 480,default4Icon.getWidth(default4) , default4Icon.getHeight(default4));
		default4.addActionListener(dlistener);
		this.add(default4);
		custom4 = new JButton();
		custom4Icon = new ImageIcon(this.getClass().getResource("/CUSTOM4.png")).getImage();
		custom4.setIcon(new ImageIcon(custom4Icon));
		custom4.setBorderPainted(false);
		custom4.setContentAreaFilled(false);
		if(tempHash.containsKey("hero_uther.json")&& containsValues(tempHash,"hero_uther.json"))
			custom4.setVisible(true);
		else
			custom4.setVisible(false);
		custom4.setBounds(1176, 560,custom4Icon.getWidth(custom4), custom3Icon.getHeight(custom4));
		custom4.addActionListener(clistener);
		this.add(custom4);
		
		// lock button
		lock = new JButton();
		lockIcon=new ImageIcon(this.getClass().getResource("/LOCK.png")).getImage();
		lock.setIcon(new ImageIcon(lockIcon));
		lock.setBorderPainted(false);
		lock.setContentAreaFilled(false);
		lock.setBounds(633, 355,lockIcon.getWidth(lock),lockIcon.getHeight(lock));
		lock.addActionListener(lockAndPlayListener);
		this.add(lock);
		
		// play button
		playbutton = new JButton();
		playIcon=new ImageIcon(this.getClass().getResource("/PLAY1.png")).getImage();
		playbutton.setIcon(new ImageIcon(playIcon));
		playbutton.setBorderPainted(false);
		playbutton.setContentAreaFilled(false);
		playbutton.setVisible(false);
		playbutton.setBounds(633, 355, playIcon.getWidth(playbutton), playIcon.getHeight(playbutton));
		playbutton.addActionListener(lockAndPlayListener);
		this.add(playbutton);
	}
	//an o xrhsths epileksei na paiksei me default deck diavazetai apo ton fakelo default-decks 
	//to arxeio me to onoma tou hrwa pou epileksame, to opoio periexei ta onomata twn json arxeiwn 
	//pou tha diavastoun.
	class defaultDecksListener implements ActionListener{ 
		String defaultdeckname ;
		//Dhmiourgoume ena ArrayList<JSONObject> opou se auto tha prosthesoume oles ta jsonObject antikeimena ,
		//ta onomata twn opoiwn diavastikan apo ta txt arxeia ston fakelo default-decks.
		ArrayList<JSONObject> defaultDeck ;
		
		InputStream input;
		InputStreamReader reader;
		BufferedReader buffer;
		
		@Override
		public void actionPerformed(ActionEvent click) {
			defaultDeck= new ArrayList<JSONObject>();
			System.out.println(Bling.getTimer());
			if(Bling.getTimer()!=null){
				if(Bling.getTimer().isRunning()){
					Bling.getTimer().stop();
					
				}
			}
			if(click.getSource().equals(default1)){
				defaultdeckname = "default-warlock";
				hero_name = "Guldan";
			}
			else if(click.getSource().equals(default2)){
				defaultdeckname = "default-mage";
				hero_name = "Jaina";
			}
			else if(click.getSource().equals(default3)){
				defaultdeckname = "default-hunter";
				hero_name = "Rexxar";
			}
			else if(click.getSource().equals(default4)){
				defaultdeckname = "default-paladin";
				hero_name = "Uther";
			}
			
			if(!(jsonDeck1.size()==0)){
				jsonDeck1.removeAll(jsonDeck1);
			}
			if(!(jsonDeck2.size()==0)){
				jsonDeck2.removeAll(jsonDeck2);
			}
			
			try {
				input = this.getClass().getResourceAsStream("/"+ defaultdeckname +".txt");
				reader = new InputStreamReader(input);
				buffer = new BufferedReader(reader);
				
				
				String line;
				ArrayList<String> defaultDeckListString = new ArrayList<>();
				while((line = buffer.readLine()) != null){
					defaultDeckListString.add(line);
				}
				for(int i=0; i<defaultDeckListString.size(); i++){
					InputStream in = this.getClass().getResourceAsStream("/"+ defaultDeckListString.get(i) +".json");
					InputStreamReader re = new InputStreamReader(in);
					BufferedReader b = new BufferedReader(re);
				
					StringBuffer stringbuffer = new StringBuffer();
					
					String templine;
					while((templine = b.readLine()) != null){
						stringbuffer.append(templine);
					}
					String str = stringbuffer.toString();
					JSONObject jsonobject = new JSONObject(str);
					defaultDeck.add(jsonobject);
					
				}
				buffer.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			if(player1Turn == false){
				jsonDeck1.addAll(defaultDeck);
				player1heroname.setText(hero_name);
				herop1=hero_name;
				player1decklabel.setText("Default deck");
			}
			else{
				jsonDeck2.addAll(defaultDeck);
				player2heroname.setText(hero_name);
				herop2=hero_name;
				player2decklabel.setText("Default deck");
			}

	
			
		}
	}
	
	class customDecksListener implements ActionListener{
		String hero_name;
		ArrayList<JSONObject> customdeck=new ArrayList<JSONObject>();
	
		@Override
		public void actionPerformed(ActionEvent click) {
			if(Bling.getTimer()!=null){
				if(Bling.getTimer().isRunning()){
					Bling.getTimer().stop();
					
				}
			} 
			if(!(jsonDeck1.size()==0)){
				jsonDeck1.removeAll(jsonDeck1);
			}
			if(!(jsonDeck2.size()==0)){
				jsonDeck2.removeAll(jsonDeck2);
			}
			
			if(click.getSource().equals(custom1)){
				customdeck = tempHash.get("hero_guldan.json");
				hero_name = "Guldan";
			}
			else if(click.getSource().equals(custom2)){
				customdeck = tempHash.get("hero_jaina.json");
				hero_name = "Jaina";
			}
			else if(click.getSource().equals(custom3)){
				customdeck = tempHash.get("hero_rexxar.json");
				hero_name = "Rexxar";
			}
			else if(click.getSource().equals(custom4)){
				customdeck = tempHash.get("hero_uther.json");
				hero_name = "Uther";
			}
		
			
			if(!customdeck.equals(null)){
				if(player1Turn == false){
					jsonDeck1.addAll(customdeck);
					player1heroname.setText(hero_name);
					herop1=hero_name;
					player1decklabel.setText("Custom deck");
				}
				else {
					jsonDeck2.addAll(customdeck);
					player2heroname.setText(hero_name);
					herop2=hero_name;
					player2decklabel.setText("Custom deck");
				}
			
			}
			
			
		}
	}
	

	class lockAndPlayButtonListener implements ActionListener{
		//afou o xhrsths exei epileksei me pio deck tha paiksei(default h custom) pataei to lock.
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(lock)){
				
				if(player1Turn == false){
					if(!jsonDeck1.isEmpty()){
						player2label.setText("Player 2");
						player1Turn = true;
						try {
							//patontas to lock dhmiourgeite ena antikeimeno ths klashs Deck.
							Player1deck=new Deck();
						} catch (JSONException e2) {					
							System.out.println("cant make deck object");
						}
						Player1deck.setJsonObj(jsonDeck1);
						f1=new Field();
						f1.setDeck(Player1deck);
						try {					
							f1.buildDeck();
							
						} catch (JSONException e1) {
							System.out.println("cant build deck");
						}

					}
					else{
						if(Bling.getTimer()==null || !Bling.getTimer().isRunning()){
							System.out.println("new");
							new Bling(player1heroname,"r");
						}
			
						
					}
					
				}
				else if((player1Turn == true) && (player2Turn == false)){
					if(!jsonDeck2.isEmpty()){
						player2Turn = true;	
						// set player2deck
						try {
							Player2deck=new Deck();
						} catch (JSONException e2) {
							System.out.println("cant make deck object");
						}
						Player2deck.setJsonObj(jsonDeck2);
						f2=new Field();
						f2.setDeck(Player2deck);
						try {
							f2.buildDeck();
							
						} catch (JSONException e1) {
							System.out.println("cant build deck");
						}
						// replace lock button with play
						lock.setVisible(false);
						playbutton.setVisible(true);
					}
					else{
						if(Bling.getTimer()==null || !Bling.getTimer().isRunning()){
							new Bling(player2heroname,"r");
						}
					
					}
			
				}
				
			}
			else if(e.getSource().equals(playbutton)){
				try {
					new BackgroundGui();
					dispose();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
	}
	private boolean containsValues(HashMap<String,ArrayList<JSONObject>> map,String string){
		if(!map.isEmpty()){
			if(map.containsKey(string)){
				if(map.get(string).isEmpty()){
					return false;
				}
				else{
					return true;
				}
			}
		
		}
		return false;
		
	}
		
	public static Field getP1Field(){
		return f1;
	}
	public static Field getP2Field(){
		return f2;
	}
	
	class backButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent back) {
			new MainMenu();
			dispose();
		}
	}
	public static String getHerop1() {
		return herop1;
	}

	public void setHerop1(String herop1) {
		this.herop1 = herop1;
	}

	public static String getHerop2() {
		return herop2;
	}

	public void setHerop2(String herop2) {
		this.herop2 = herop2;
		
	}
	public static Container getContentPane1(){
		return c;
	}

	public JLabel getPlayer1heroname() {
		return player1heroname;
	}

	public void setPlayer1heroname(JLabel player1heroname) {
		this.player1heroname = player1heroname;
	}

	public JLabel getPlayer2heroname() {
		return player2heroname;
	}

	public void setPlayer2heroname(JLabel player2heroname) {
		this.player2heroname = player2heroname;
	}
	

	
}
	
	
