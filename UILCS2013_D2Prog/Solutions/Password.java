import java.io.*;
import java.util.*;
import static java.lang.System.*;  


import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;


public class Password {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("password.dat"));
		
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;		

			String t = kb.nextLine().trim();
			String []s = t.split(" ");
			String str = s[0];
			int len = Integer.parseInt(s[1]);
			// parallel array and ArrayList to contain counts and possible passwords

			int[] num = new int[str.length()];
			ArrayList<String> password = new ArrayList<String> ();
			int most = 0;		// number of times possible password appears
			int indexMost = 0; 	// index of the possible password appearing the most times
			for(int i=0; i<str.length() - len; i++) {
				String temp = str.substring(i,i+len);
				
				int index = password.indexOf(temp);
				if(index == -1)	{	//word is new
					password.add(temp);
					num[password.size()-1] = 1;
				}
				else {
					num[index]++;	
					if(num[index] > most) {
						most = num[index];
						indexMost = index;
					}
				}
			}
			out.println(password.get(indexMost));
		
		}
	}	
}
