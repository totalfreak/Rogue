import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;

import java.util.Properties;
 
public class HelloDungeon{
 
	public static void main(String[] args){
		Properties text = new Properties();
		text.setProperty("fontSize","20");
		text.setProperty("font", "Courier");
		ConsoleSystemInterface csi = null;
		try{
			csi = new WSwingConsoleInterface("Rogue", text);
		}
		catch (ExceptionInInitializerError eiie) {
			System.out.println("*** Error: Swing Console Box cannot be initialized. Exiting...");
			eiie.printStackTrace();
			System.exit(-1);
		}
		int x = 0;
		int y = 0;
		int width = 85;
		int height = 25;
		boolean stop = false;
		char wall = '-';
		char checkChar = 'A';
		//While loop to update x,y if @
		while(!stop) {
			csi.cls();
			csi.print(x, y, "@", CSIColor.WHITE);
			csi.print(10, 5, "-", CSIColor.AMBER);
			csi.print(60, 0, "w: " + Integer.toString(width), CSIColor.WHITE);
			csi.print(60, 1, "h: " + Integer.toString(height), CSIColor.WHITE);
			csi.refresh();
			CharKey dir = csi.inkey();
			if(dir.isDownArrow()&& (y+1 < 25)) {
				y++;
			}
			if(dir.isUpArrow() && (y-1 >= 0)) {
				y--;
			}
			if(dir.isRightArrow() && (x+1 < 80)) {
				x++;
			}
			if(dir.isLeftArrow() && (x-1 >= 0)) {
				x--;
			}
			if(dir.code == CharKey.Q) {
				stop = true;
			}
		}
	}
}