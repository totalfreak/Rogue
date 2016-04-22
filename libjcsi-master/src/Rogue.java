import java.util.*;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
 
public class Rogue{
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
		//Getting random startX for player
		int startX = 10;
				//15+(int)(Math.random()*((75-15)+1));
		//Getting random startY for player
		int startY = 5;
				//5+(int)(Math.random()*((18-5)+1));
		//Start variables
		int x = startX;
		int y = startY;
		int width = 85;
		int height = 25;
		boolean stop = false;
		char wallH = '#';
		char wallV = '#';
		char ground = '.';
		char gate = '+';
		char passage = '+';
		CSIColor gColor = CSIColor.ARMY_GREEN;
		CSIColor wallColor = CSIColor.AMBER;
		//Getting the random values for the rooms
		int maxW = startX+(int)(Math.random() * ((25-5)+1));
		int maxH = startY+(int)(Math.random() * ((15-5)+1));
		//While loop to update x,y if @
		while(!stop) {
			csi.cls();
			//Start room left top corner
			for(int i = startX-3; i < startX+4; i++) {
				csi.print(i, startY-3, wallH, wallColor);
				csi.print(i, startY+3, wallH, wallColor);
			}
			for(int i = startY-3; i < startY+4; i++) {
				csi.print(startX-4, i, wallV, wallColor);
				csi.print(startX+4, i, wallV, wallColor);
			}
			//Room two right top corner
			for(int i = width-21; i < width-12; i++) {
				csi.print(i, startY-2, wallH, wallColor);
				csi.print(i, startY+4, wallH, wallColor);
			}
			for(int i = startY-2; i < startY+5; i++) {
				csi.print(width-21, i, wallV, wallColor);
				csi.print(width-12, i, wallV, wallColor);
			}
			//Making the first passage
			csi.print(startX+4, startY+1, gate, CSIColor.ASPARAGUS);
			for(int i = startX+5; i < startX+26; i++) {
				csi.print(i, startY+1, passage, CSIColor.ASPARAGUS);
			}
			for(int i = startX+25; i < width-21;i++) {
				csi.print(i, startY, passage, CSIColor.ASPARAGUS);
			}
			csi.print(width-21, startY, gate, CSIColor.ASPARAGUS);
			//Print the player
			csi.safeprint(x, y, '@', csi.WHITE);
			//Printing the width and height at the top of the screen
			csi.print(60, 0, "w: " + Integer.toString(width), CSIColor.WHITE);
			csi.print(60, 1, "h: " + Integer.toString(height), CSIColor.WHITE);
			
			csi.refresh();
			//Getting the input charkey
			CharKey dir = csi.inkey();
			if(dir.isDownArrow()&& (y+1 < height) && csi.peekChar(x, y+1) != wallH) {
				y++;
				}
			if(dir.isUpArrow() && (y-1 >= 0) && csi.peekChar(x, y-1) != wallH) {
				y--;
			}
			if(dir.isRightArrow() && (x+6 < width) && csi.peekChar(x+1, y) != wallV) {
				x++;
			}
			if(dir.isLeftArrow() && (x-1 >= 0) && csi.peekChar(x-1, y) != wallV) {
				x--;
			}
			if(dir.code == CharKey.Q) {
				stop = true;
			}
		}
	}
}