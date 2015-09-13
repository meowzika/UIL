import java.io.*;
import java.util.*;
import static java.lang.System.*;  


import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;


public class Cheese {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("cheese.dat"));
		
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;
			String t = kb.nextLine().trim();
			String []s = t.split(" ");		
			Random rand = new Random(Long.parseLong(s[0]));	
 			int [] people = new int [6];
 			int numWords = Integer.parseInt(s[1]);
 			
			for(int i=0; i<numWords; i++) {
				int person = rand.nextInt(6);			
				int roll1 = rand.nextInt(6) + 1;			
				int roll2= rand.nextInt(6) + 1;
				
				people[person] += roll1 + roll2;
//				print random numbers generated
//				out.print(person + " " + roll1 + " " + roll2 + " ");
			}	
//				out.println();		
			for(int i=0; i<6; i++)		
				out.println((char)('A'+i) + " " + people[i]);

		
		}
	}	
}
