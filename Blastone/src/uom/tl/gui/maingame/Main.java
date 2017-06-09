
package uom.tl.gui.maingame;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
//ta sxolia einai se greeklish giati se kapoious upologistes emfanizontai kinezika ta ellhnika logw
//kwdikopoihshs
public class Main {
	private static Font hsFont;
	public static void main(String[] args) throws JSONException  {
		createFont();
		new MainMenu();
		
		
	}
	
	public static void createFont(){
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
	public static Font getFont(){
		return hsFont;
	}
}
