import java.io.*;
import java.util.*;
import static java.lang.System.*;  


public class OldFolks {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("oldfolks.dat"));
		
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;		

			String t = kb.nextLine().trim();
			int len = t.length();
			String s = t.substring(0,len-5);
			int year = Integer.parseInt(t.substring(len-4));
			if(year<1953)
				out.println(s);
		}
	}	
}
