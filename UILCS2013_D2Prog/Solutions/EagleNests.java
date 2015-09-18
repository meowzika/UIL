import java.io.*;
import java.util.*;
import static java.lang.System.*;  

public class EagleNests {
	public static void main(String args[]) throws IOException {
	
		Scanner kb = new Scanner(new File("eaglenests.dat"));
		
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());

		while(times < count) {
			times++;		
			String maps="";

			for(int i=0; i<10; i++)
				maps += kb.nextLine().trim();

			int x=0, y=0;
			for(int j=0; j<maps.length(); j++) 
				if(maps.charAt(j) == 'E')
					out.println((j/10+1) + " " + (j%10+1));
                	out.println();
			
		}	
	}	
}
