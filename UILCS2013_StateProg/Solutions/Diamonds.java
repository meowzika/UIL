import java.io.*;
import java.util.*;
import static java.lang.System.*;  


import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;


public class Diamonds {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("diamonds.dat"));
		
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;
			long seed = Long.parseLong(kb.nextLine().trim());
			Random rand = new Random(seed);	
			int front = 15;
			int back = 80;
 			int playerA = 0;
 			int playerB = 0;
 			int flip = 0;	
			for(int i=0; i<7; i++) {   		
				flip = rand.nextInt(2);		//out.print(flip + " ");	//player A's turn
				if(flip == 0) {
					playerA += back;
					back -= 5;
				}
				else {
					playerA += front;
					front += 5;
				}
				flip = rand.nextInt(2);  	//out.print(flip + " ");  //player B's turn
				if(flip == 0) {
					playerB += back;
					back -= 5;
				}
				else {
					playerB += front;
					front += 5;
				} 							
			}
			if(playerA > playerB)
				out.println("PLAYER A WON");
			else if (playerA < playerB)
				out.println("PLAYER B WON");
			else
				out.println("TIE"); 				
		}
	}	
}
