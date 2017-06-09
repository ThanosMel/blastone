package uom.tl.gui.createdeck;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.json.JSONException;
import org.json.JSONObject;

import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.gui.maingame.Main;
import uom.tl.gui.maingame.MainMenu;
//--Sto frame o xrhsths epilegei ths kartes pou tha prostehesei sto deck tou.
//--afou exoume epileksei poies kartes tha prosthesoume sto deck mas patontas to plhktro done
//odhgoumaste sto MainMenu frame.
//--sthn ousia kathe fora pou dhmiourgoume custom deck gia kapoion hrwa prostithontai sto hashmap keyAndValue
//ws key to onoma tou hrwa pou ftiaksame deck kai ws value ena arraylist<jsonobject> pou periexei oles tis kartes 
//pou exoume prosthesei sto deck mas.
public class CreateDeckGui extends JFrame{
	private Image background, backButtonIcon;
	private String line, JSONfile, heroName, heroClass, className;
	private JButton button1, button2, button3, button4, button5, button6, button7, button8, backButton, next, previous, done; 
	private JLabel countCards = new JLabel();
	private ArrayList<JButton> JButtonsList = new ArrayList<>();
	private ArrayList<String> iconNames = new ArrayList<>();
	private ArrayList<String> cardsNames = new ArrayList<>();
	private HashMap<String, String> AllJsonFiles = new HashMap<>();
	private int count=0, modcount;
	//                    heroName
	private static HashMap<String, ArrayList<JSONObject>> keyAndValue = new HashMap<String, ArrayList<JSONObject>>();

	private buttonsActionListener listener = new buttonsActionListener();
	private addCardsToDeck adder = new addCardsToDeck();
	
	
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList list = new JList(model);
	private JScrollPane pane = new JScrollPane(list);
	
	
	public CreateDeckGui(String className, String heroName){
		super("Create deck");
		this.className = className;
		this.heroName = heroName;
		heroClass = checkHeroClass();
		CastJButtons();//H methodos CastJButtons dhmiourgei kai emfanizei ola ta plhktra tou frame kathws kai th lista me ths kartes pou exoume epileksei.
	
		// background
		background = new ImageIcon(this.getClass().getResource("/Background with Scroll.jpg")).getImage();
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
		backgroundLabel.setIcon(new ImageIcon(background));
		this.add(backgroundLabel);
		
		//H methodos getCardsIconFromJSONfiles dexetai ena string kai diavazei ta arxeia json gia ton hrwa pou exoume epileksei
		//sto frame HeroChooseGui 
		getCardsIconFromJSONfiles(heroClass);
		//diavazei ola ta json arxeia neutral pou einai sthn ousia kartes koines gia olous tous 
		//hrwes.
		getCardsIconFromJSONfiles("neutralJSON");
		
		
		for(int i=0; i<JButtonsList.size(); i++){
			//To ArrayList iconNames periexei thn topothesia (se string) ths eikonas ths kathe kartas pou exei diavastei 
			//apo th methodo getCardsIconFromJSONfiles.
			Image icon = new ImageIcon("images/cardImagesOriginal/"+iconNames.get(i)).getImage();
			JButtonsList.get(i).setIcon(new ImageIcon(icon));
			count++;
		}
		checkPreviousAndNextButton();
			
		this.setVisible(true);
		this.setSize(1366, 768); 
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public void CastJButtons(){
		// cast buttons
		button1 = new JButton();
		button1.setBounds(70, 60, 210, 300);
		button1.setVisible(true);
		button1.setBorderPainted(false);
		button1.setContentAreaFilled(false);
		button1.addActionListener(adder);
		this.add(button1);
		JButtonsList.add(button1);
		
		
		button2 = new JButton();
		button2.setBounds(290, 60, 210, 300);
		button2.setVisible(true);
		button2.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		button2.addActionListener(adder);
		this.add(button2);
		JButtonsList.add(button2);
		
		button3 = new JButton();
		button3.setBounds(510, 60, 210, 300);
		button3.setVisible(true);
		button3.setBorderPainted(false);
		button3.setContentAreaFilled(false);
		button3.addActionListener(adder);
		this.add(button3);
		JButtonsList.add(button3);
		
		button4 = new JButton();
		button4.setBounds(730, 60, 210, 300);
		button4.setVisible(true);
		button4.setBorderPainted(false);
		button4.setContentAreaFilled(false);
		button4.addActionListener(adder);
		this.add(button4);
		JButtonsList.add(button4);
		
		button5 = new JButton();
		button5.setBounds(70, 370, 210, 300);
		button5.setVisible(true);
		button5.setBorderPainted(false);
		button5.setContentAreaFilled(false);
		button5.addActionListener(adder);
		this.add(button5);
		JButtonsList.add(button5);
		
		button6 = new JButton();
		button6.setBounds(290, 370, 210, 300);
		button6.setVisible(true);
		button6.setBorderPainted(false);
		button6.setContentAreaFilled(false);
		button6.addActionListener(adder);
		this.add(button6);
		JButtonsList.add(button6);
		
		button7 = new JButton();
		button7.setBounds(510, 370, 210, 300);
		button7.setVisible(true);
		button7.setBorderPainted(false);
		button7.setContentAreaFilled(false);
		button7.addActionListener(adder);
		this.add(button7);
		JButtonsList.add(button7);
		
		button8 = new JButton();
		button8.setBounds(730, 370, 210, 300);
		button8.setVisible(true);
		button8.setBorderPainted(false);
		button8.setContentAreaFilled(false);
		button8.addActionListener(adder);
		this.add(button8);
		JButtonsList.add(button8);
		
		// backbutton
		backButton = new JButton();
		backButtonIcon = new ImageIcon(this.getClass().getResource("/back button.png")).getImage();
		backButton.setIcon(new ImageIcon(backButtonIcon));
		backButton.setBounds(1000,  50, backButtonIcon.getWidth(backButton), backButtonIcon.getHeight(backButton));
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		this.add(backButton);
		backButton.addActionListener(listener);
		
		// next page button
		next = new JButton("Next");
		Image nexticon = new ImageIcon(this.getClass().getResource("/next.png")).getImage();
		next.setIcon(new ImageIcon(nexticon));
		next.setBounds(1125, 610, nexticon.getWidth(next), nexticon.getHeight(next));
		next.setBorderPainted(false);
		next.setContentAreaFilled(false);
		this.add(next);
		next.addActionListener(listener);
		
		// previous page button 
		previous = new JButton("Previous");
		Image previcon = new ImageIcon(this.getClass().getResource("/previous.png")).getImage();
		previous.setIcon(new ImageIcon(previcon));
		previous.setBounds(1050, 610, previcon.getWidth(previous), previcon.getHeight(previous));
		previous.setBorderPainted(false);
		previous.setContentAreaFilled(false);
		this.add(previous);
		previous.addActionListener(listener);
		
		//    button
		done = new JButton("Done");
		Image doneicon = new ImageIcon(this.getClass().getResource("/done.png")).getImage();
		done.setIcon(new ImageIcon(doneicon));
		done.setBounds(1125, 45, doneicon.getWidth(done), doneicon.getHeight(done));
		done.setBorderPainted(false);
		done.setContentAreaFilled(false);
		this.add(done);
		done.addActionListener(listener);
		
		// model-list
		list.setOpaque(false);
		list.setCellRenderer(new TransparentListCellRenderer());
		            list.setFont(new Font(Main.getFont().getFontName(), Font.BOLD, 16));
		list.setForeground(Color.BLACK);
		pane.setBounds(1040, 190, 200, 345);
		pane.setOpaque(false);
		pane.setBorder(null);
		pane.getViewport().setOpaque(false);
		this.add(pane);
		
		// count cards
		countCards.setBounds(1200, 555, 50, 30);
		countCards.setFont(new Font(Main.getFont().getFontName(), Font.BOLD, 16));
		countCards.setForeground(Color.BLACK);
		this.add(countCards);
	}
	
	public String checkHeroClass(){
		
		if(heroName.equals("hero_guldan.json"))
			return  "warlockJSON";
		else if(heroName.equalsIgnoreCase("hero_jaina.json"))
			return "mageJSON";
		else if(heroName.equals("hero_rexxar.json"))
			return "hunterJSON";
		else
			return "paladinJSON";
	}

	class buttonsActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent click) {

			if(click.getSource().equals(backButton)){
				new HeroChooseGui();
				dispose();
				// mhnyma 
			}
			else if(click.getSource().equals(next)){
				
				if(!(iconNames.size() == count)){
					for(int i=0; i<JButtonsList.size(); i++){
						if(count < iconNames.size()){
							Image icon = new ImageIcon("images/cardImagesOriginal/"+ iconNames.get(count)).getImage();
							JButtonsList.get(i).setIcon(new ImageIcon(icon));
							count++;
						}
						else
							JButtonsList.get(i).setVisible(false);
						checkPreviousAndNextButton();
					}
				}
				else
					next.setVisible(false);
				
				if(count % 8 != 0)
					modcount = count % 8;
			}
			else if(click.getSource().equals(previous)){
				try{	
					if(count % 8 == 0)
						count -= 16;
					else{
						count -= modcount+ 8 ;
						modcount = count % 8;
					}
					for(int i=0; i<JButtonsList.size(); i++)
						JButtonsList.get(i).setVisible(true);

					if(!(iconNames.size() == count)){
						for(int i=0; i<JButtonsList.size(); i++){
							if(count < iconNames.size()){
								Image icon = new ImageIcon("images/cardImagesOriginal/"+ iconNames.get(count)).getImage();
								JButtonsList.get(i).setIcon(new ImageIcon(icon));
								count++;
							}
							else
								JButtonsList.get(i).setVisible(false);
							checkPreviousAndNextButton();
						}
					}
					else
						previous.setVisible(false);
				}catch(ArrayIndexOutOfBoundsException e){
					previous.setVisible(false);
				}
			}
			else if(click.getSource().equals(done)){
				ArrayList<JSONObject> tempJSONObjectlist = new ArrayList<>();
				for(int i=0; i<model.size(); i++){
					String cardname = AllJsonFiles.get(model.get(i));
	
					try {
						InputStream input = this.getClass().getResourceAsStream("/"+ cardname);
						InputStreamReader reader = new InputStreamReader(input);
						BufferedReader b = new BufferedReader(reader);
						
						StringBuffer buffer = new StringBuffer();
						
						while((line = b.readLine()) != null){
							buffer.append(line);
						}
						
						String jsonfile = buffer.toString();
						JSONObject parent = new JSONObject(jsonfile);
						
						// prosthiki se array
						tempJSONObjectlist.add(parent);
						
						} catch (IOException e) {
							e.printStackTrace();
						} catch (JSONException e) {
							e.printStackTrace();
						}
				}
			
				// -------------
				keyAndValue.put(heroName, tempJSONObjectlist);
				
				if(className.equals("Hero_choose_panel"))
					new MainMenu();
				dispose();
			}
		}
	}
		
	public static HashMap<String, ArrayList<JSONObject>> getKeyAndValue() {
		return keyAndValue;
	}

	class addCardsToDeck implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent add) {
			for(int i=0; i<JButtonsList.size(); i++)
				if(add.getSource().equals(JButtonsList.get(i))){
					String tempcardname;
					int location = count + i;
					if(count > 40){
						tempcardname =(cardsNames.get(count - modcount + i ));
						location -= modcount;
					}
					else{
						tempcardname =(cardsNames.get(count - 8 + i ));
						location -= 8;	
					}
					if(model.size() < 30){
						int t = checkHowManyCards(tempcardname);
						if(t < 2)
							model.addElement(tempcardname);
					}
				}
			countCards.setText(model.getSize() +"/30");
		}
	}
	
	public int checkHowManyCards(String tempcardname){
		String name = tempcardname;
		int tempNameCount = 0;
	
		for(int i = 0; i<model.size(); i++)
			if(model.get(i).equals(name))
				tempNameCount ++;
		return tempNameCount;
	}
	
	public class TransparentListCellRenderer extends DefaultListCellRenderer {

	    @Override
	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        setOpaque(isSelected);
	        return this;
	    }
	}
	
	
	public void getCardsIconFromJSONfiles(String name){
		
		// json
		File folder = new File("json/"+name);
		System.out.println(name);
		File[] listOfFile = folder.listFiles();
		
		for(int i=0; i< listOfFile.length; i++){
			if(listOfFile[i].isFile()){
				String iconName = null;
				try{
					InputStream input = CreateDeckGui.class.getResourceAsStream("/"+name+"/"+listOfFile[i].getName());
					
					InputStreamReader reader = new InputStreamReader(input);
					BufferedReader buffer = new BufferedReader(reader);
				
					StringBuffer Sbuffer = new StringBuffer();
				
					while((line = buffer.readLine()) != null){
						Sbuffer.append(line);
					}
					JSONfile = Sbuffer.toString();
					JSONObject parent = new JSONObject(JSONfile);
					
					// lista me ta onomata twn kartwn
					String cardname = parent.getString("name");
					cardsNames.add(cardname);
					
					// hashmap me ola ta onomata twn json arxeiwn ( class + neutral )
					AllJsonFiles.put(cardname, listOfFile[i].getName());
		
	
					// lista me ta onomata olwn twn icon
					iconName = parent.getString("iconName");
					iconNames.add(iconName);
				
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public void checkPreviousAndNextButton(){
		if(count > 40)
			next.setVisible(false);
		else
			next.setVisible(true);
		
		if(count <= 8)
			previous.setVisible(false);
		else
			previous.setVisible(true);
	}
}
