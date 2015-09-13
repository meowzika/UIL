import java.io.*;
import java.util.*;
import static java.lang.System.*;  


import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;


public class Patterns {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("patterns.dat"));
		
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;		

			String t = kb.nextLine().trim();
			String []s = t.split(" ");
			String word1 = s[0];
			String word2 = s[1];
			boolean flag = true;
			Map <Character, Character> m = new TreeMap<Character, Character>() ;
			m.put(word1.charAt(0),word2.charAt(0));
			for(int i=1; flag && i<word1.length(); i++) {  
				if(m.containsKey((word1.charAt(i))) && m.get(word1.charAt(i)).equals(word2.charAt(i))) {
					
	//				out.println(word1.charAt(i) +" "+m.get(word1.charAt(i)) +" "+(word2.charAt(i)));
					continue;
	
				}		
				else if (m.containsValue(word2.charAt(i)))
						flag = false;
				else 
					m.put(word1.charAt(i),word2.charAt(i));
			}

			if (flag) 
				out.println("YES");
			else 
				out.println("NO");
		}
	}	
}
