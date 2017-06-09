package uom.tl.listeners;

import java.awt.Color;import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.gui.maingame.HeroButton;

public class Bling {
	private JLabel jl;
	private HeroButton hb;
	private int counter;
	private static Timer bling;
	
	private String component;
	
	private boolean bool;
	
	public Bling(JLabel jl,String s){
		component="jlabel1";
		counter=0;
		this.jl=jl;
		 bool=false;
		 
		 bling=new Timer(500,new TimerListener());
		 bling.setInitialDelay(0);
		 bling.start();
	}

	public Bling(JLabel jl){
		component="jlabel";
		counter=0;
		this.jl=jl;
		 
		 bling=new Timer(500,new TimerListener());
		 bling.setInitialDelay(5);
		 bling.start();

			 	 
	}
	public Bling(HeroButton hb,String s){
		component="button1";
		counter=0;
		this.hb=hb;
		bling=new Timer(200,new TimerListener());
		 bling.setInitialDelay(0);
		
		 bling.start();
	}
	
	
	public Bling(HeroButton hb){
		component="button";
		counter=0;
		this.hb=hb;
		bling=new Timer(200,new TimerListener());
		 bling.setInitialDelay(0);
		
		 bling.start();
	}
	

	class TimerListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			if(component=="jlabel1"){
				counter++;
			
				if(bool==true){
					bool=false;
					jl.setText("");
				}
				else{
					bool=true;
					jl.setText("Choose Deck");
				}
				
				if(counter==4){
					jl.setText("Choose Deck");
					bling.stop();
				}
				
			}
			if(component=="jlabel"){
				counter++;
				if(jl.getForeground()==Color.WHITE){
					jl.setForeground(Color.RED);
				}
				else{
					jl.setForeground(Color.WHITE);
						
				}
				if(counter==6){
					((Timer) e.getSource()).stop();
					jl.setForeground(Color.WHITE);
					
				}
			}
			if(component=="button1"){
				counter++;
				if(bool ==false){
					bool=true;
					hb.setIcon(new ImageIcon("images/"+hb.getPlayerFromHeroButton().getHero().toLowerCase()+".png"));
					int hp1=hb.getPlayerFromHeroButton().getHp();
					hb.getHp().setText(Integer.toString(hp1));
					hb.getHp().setForeground(Color.GREEN);
					
				}
				else{
					bool=false;
					hb.setIcon(new ImageIcon("images/"+hb.getPlayerFromHeroButton().getHero().toLowerCase()+ ".png"));
					int hp1=hb.getPlayerFromHeroButton().getHp();
					hb.getHp().setText(Integer.toString(hp1));
	
					hb.getHp().setForeground(Color.WHITE);
				}
			
				if(counter==2){
					((Timer) e.getSource()).stop();
					BackgroundGui.getListener().updateField("heroHp");
					
				}
			}
			if(component=="button"){
				counter++;
				if(bool ==false){
					bool=true;
					System.out.println("wtf");
					hb.setIcon(new ImageIcon("images/"+hb.getPlayerFromHeroButton().getHero().toLowerCase()+"Effe.png"));
					int hp1=hb.getPlayerFromHeroButton().getHp();
					hb.getHp().setText(Integer.toString(hp1));
					hb.getHp().setForeground(Color.RED);
					
				}
				else{
					bool=false;
					hb.setIcon(new ImageIcon("images/"+hb.getPlayerFromHeroButton().getHero().toLowerCase()+ ".png"));
					int hp1=hb.getPlayerFromHeroButton().getHp();
					hb.getHp().setText(Integer.toString(hp1));
	
					hb.getHp().setForeground(Color.WHITE);
				}
			
				if(counter==2){
					((Timer) e.getSource()).stop();
					BackgroundGui.getListener().updateField("heroHp");
					
				}
				
			}
			
		
		
						 

		}
	}
	public static Timer getTimer(){
		return bling;
	}
	
		
	
}
