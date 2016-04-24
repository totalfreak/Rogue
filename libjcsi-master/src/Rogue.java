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
			csi = new WSwingConsoleInterface("Dungeon of boss", text);
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
		//Making a simple intro
				boolean startOver = false;
				csi.print(32, 3, "Dungeons of boss", CSIColor.WHITE);
				csi.print(10, 5, "In elden times, there was an evil presence that threatened to");
				csi.print(11, 7, "destroy all that is good. You as a choosen warrior have been");
				csi.print(9, 9, "sent into the dungeon of the evil presence to rid the world of it.");
				csi.print(10, 15, "Unfortunatly, you were robbed on the way to the dungeon. And lost");
				csi.print(10, 17, "all that you were bringing to defeat the evil. Now it is just you");
				csi.print(14, 19, "and your bare fists. If just you had a weapon of sorts.");
				csi.print(20, 23, "Press any button to delve into the dungeon", CSIColor.RED);
		csi.inkey();
		startOver = true;
		boolean stop = false;
		boolean gameOver = false;
		char wallH = '#';
		char wallV = '#';
		char ground = '.';
		char gate = '+';
		char passage = '-';
		//Item list
		char sword = 'S';
		boolean pass = false;
		
		//Player stats and weapon pickups
			int hp = 30;
			int atk = 3;
			boolean inv = false;
			boolean pick1 = false;
			
		//Boss stats
			int mon1Hp = 55;
			int mon1Atk = 2;
			char mon1 = 'B';
			int mon1X = 70;
			int mon1Y = 6;
			boolean mon1Kill = false;
			
		CSIColor gColor = CSIColor.ARMY_GREEN;
		CSIColor wallColor = CSIColor.AMBER;
		//Getting the random values for the rooms
		int maxW = startX+(int)(Math.random() * ((25-5)+1));
		int maxH = startY+(int)(Math.random() * ((15-5)+1));
		List<String> inventory = new ArrayList<String>();
		//While loop to update x,y if @
		if(startOver) {
		while(!stop) {
			if(!gameOver) {
			if(!inv) {
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
				csi.print(i, startY, wallH, CSIColor.ASPARAGUS);
			}
			for(int i = startX+25; i < width-21; i++) {
				csi.print(i, startY-1, wallH, CSIColor.ASPARAGUS);
			}
			for(int i = startX+5; i < startX+28; i++) {
				csi.print(i, startY+2, wallH, CSIColor.ASPARAGUS);
			}
			for(int i = startX+27; i < width-21; i++) {
				csi.print(i,startY+1, wallH, CSIColor.ASPARAGUS);
			}
			csi.print(width-21, startY, gate, CSIColor.ASPARAGUS);
			//Sword 1
			if(!pick1) {
				csi.print(startX+1, startY+2, sword, CSIColor.AMARANTH);
			} else {
				atk = 5;
			}
			//Print the player
			csi.safeprint(x, y, '@', csi.WHITE);

			//Printing the player stats
			csi.print(2, height-3, "Player", CSIColor.WHITE);
			csi.print(2, height-2, "HP: " + hp, CSIColor.WHITE);
			csi.print(2, height-1, "ATK: " + atk, CSIColor.WHITE);
			if(!mon1Kill) {
			//Printing mon1 stats
			csi.print(60, height-3, "Boss", CSIColor.WHITE);
			csi.print(60, height-2, "HP: " + mon1Hp, CSIColor.WHITE);
			csi.print(60, height-1, "Atk: " + mon1Atk, CSIColor.WHITE);
			
			//Printing mon1
			csi.print(mon1X, mon1Y, mon1, CSIColor.AQUA);
			}
			csi.refresh();
			//Getting the input CharKey
			CharKey dir = csi.inkey();
			if(dir.isDownArrow()&& (y+1 < height) && csi.peekChar(x, y+1) != wallH && csi.peekChar(x, y+1) != mon1) {
				if(csi.peekChar(x, y+1) == passage) {
					pass = true;
				} else {
					pass = false;
				}
				y++;
				
				}
			if(dir.isUpArrow() && (y-1 >= 0) && csi.peekChar(x, y-1) != wallH && csi.peekChar(x, y-1) != mon1) {
				if(csi.peekChar(x, y-1) == passage) {
					pass = true;
				} else {
					pass = false;
				}
				y--;
			}
			if(dir.isRightArrow() && (x+6 < width) && csi.peekChar(x+1, y) != wallV && csi.peekChar(x+1, y) != mon1) {
				if(csi.peekChar(x+1, y) == passage) {
					pass = true;
				} else {
					pass = false;
				}
				x++;
			}
			if(dir.isLeftArrow() && (x-1 >= 0) && csi.peekChar(x-1, y) != wallV && csi.peekChar(x-1, y) != mon1) {
				if(csi.peekChar(x-1, y) == passage) {
					pass = true;
				} else {
					pass = false;
				}
				x--;
			}
			if(dir.code == CharKey.Q) {
				stop = true;
			}
			if(dir.code == CharKey.e) {
				inv = true;
				System.out.print(inventory);
				System.out.print("\r\n");
			}
			if(x == startX+1 && y == startY+2 && !pick1) {
				pick1 = true;
				inventory.add("Sword +2 attack");
			}
			//mon1 AI and shit
			//Horizontal right attack
			if(mon1X+1 == x && mon1Y == y) {
				csi.print(10, 1, "Got striked by Boss for " + mon1Atk, CSIColor.RED);
				hp -= mon1Atk;
				csi.refresh();
				csi.inkey();
				csi.print(10, 1, "Stroke Boss for " + atk + "        ");
				mon1Hp -= atk;
				csi.refresh();
				csi.inkey();
			}
			//Horizontal left attack
			if(mon1X-1 == x && mon1Y == y) {
				csi.print(10, 1, "Got striked by Boss for " + mon1Atk, CSIColor.RED);
				hp -= mon1Atk;
				csi.refresh();
				csi.inkey();
				csi.print(10, 1, "Stroke Boss for " + atk + "        ");
				mon1Hp -= atk;
				csi.refresh();
				csi.inkey();
			}
			//Vertical up attack
			if(mon1X == x && mon1Y-1 == y) {
				csi.print(10, 1, "Got striked by Boss for " + mon1Atk, CSIColor.RED);
				hp -= mon1Atk;
				csi.refresh();
				csi.inkey();
				csi.print(10, 1, "Stroke Boss for " + atk + "        ");
				mon1Hp -= atk;
				csi.refresh();
				csi.inkey();
			}
			//Vertical down attack
			if(mon1X == x && mon1Y+1 == y) {
				csi.inkey();
				csi.print(10, 1, "Got striked by Boss for " + mon1Atk, CSIColor.RED);
				hp -= mon1Atk;
				csi.refresh();
				csi.inkey();
				csi.print(10, 1, "Stroke Boss for " + atk + "        ");
				mon1Hp -= atk;
				csi.refresh();
				csi.inkey();
			}
			if(mon1Hp <= 0) {
				gameOver = true;
				mon1Hp = 0;
				csi.cls();
			}
			if(hp <= 0) {
				gameOver = true;
				hp = 0;
				csi.cls();
			}
			} else {
				//Showing the inventory
				csi.saveBuffer();
				csi.cls();
				csi.print(2, 2, "Your inventory", CSIColor.WHITE);
				for (int i = 0; i < inventory.size(); i++) {
					csi.print(2, i+4, Integer.toString(i+1), CSIColor.WHITE);
					csi.print(4, i+4,inventory.get(i), CSIColor.WHITE);
				}
				csi.refresh();
				CharKey key = csi.inkey();
				if(key.code == CharKey.e) {
					csi.cls();
					System.out.print("exit inventory");
					inv  = false;
				}
			}
		} else {
			if(hp <= 0) {
			csi.print(32, 3, "Game Over!");
			csi.print(21, 10, "You didn't defeat the evil presence,");
			csi.print(19, 12, "and have forsaken your world to the evil.");
			csi.print(22, 14, "You only got the boss down to " + mon1Hp + " HP");
			csi.print(21, 16, "Perhaps you should invest in a sword?");
			csi.refresh();
			} 
			if(mon1Hp <= 0){
				csi.print(35, 3, "You win!");
				csi.print(21, 5, "You saved the world from destruction");
				csi.print(5, 10, "You were punched down to " + hp + " HP");
				csi.print(5, 12, "You had " + atk + " attack");
				csi.print(55, 10, "Your inventory", CSIColor.WHITE);
				for (int i = 0; i < inventory.size(); i++) {
					csi.print(55, i+12, Integer.toString(i+1), CSIColor.WHITE);
					csi.print(57, i+12,inventory.get(i), CSIColor.WHITE);
				}
				csi.print(29, 23, "Press any key to leave");
				csi.refresh();
				csi.inkey();
				System.exit(-1);
			}
		}
		}
	}
	}
}