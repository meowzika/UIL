import java.io.*;
import java.util.*;
import static java.lang.System.*;  


import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;


public class Eureka {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("eureka.dat"));
		
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;
			String []t = kb.nextLine().trim().split(" ");
					
			Random rand = new Random(Long.parseLong(t[0]));	
 			String word = t[1];
 			int numLetters = 0;					// numLetters counts the number of letters generated
 			
			for (int i=0; i<word.length(); i++) {
				boolean flag = false;
				while (!flag) {
					char r = (char)(rand.nextInt(26) + 'A');
					numLetters++;						
																	// out.print(r);
					if(word.charAt(i)== r)
						flag = true;
				}
			}
			out.println(numLetters);	
		}
	}	
}
