import java.io.*;
import java.util.*;
import static java.lang.System.*;  


import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;


public class Savings {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("savings.dat"));
		String t = kb.nextLine().trim();
		String []s = t.split(" ");		
		int times = 0; 
		int count = s.length;
		while(times < count) {
			int totalDays = Integer.parseInt(s[times]);
			times++;		
			int total = 1;
			int dayNumber = 1;
			int amt = 1;
			while(dayNumber < totalDays) {
				amt++;
				for(int i=0; i<amt && dayNumber<totalDays; i++) {
					total += amt;
					dayNumber++;
				}
			}
			out.println(totalDays + " $" + total);
				
		}
	}	
}
