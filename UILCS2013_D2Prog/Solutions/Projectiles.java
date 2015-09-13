import java.io.*;
import java.util.*;
import static java.lang.System.*;  




public class Projectiles {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("projectiles.dat"));
		
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;		

			String t1 = kb.nextLine().trim();
			String []s = t1.split(" ");
			String name = s[0];
			int v = Integer.parseInt(s[0]);	
			int c = Integer.parseInt(s[1]);
			int t=1;
			int height = c;
			int temp = c;
			while(t< 25 && temp<=height) { 
				height = v*t - 16*t*t + c;
				if(height < temp) {
					out.println("HEIGHT " + temp + " TIME " + (t-1));
					break;
				}
				temp = height;
				t++;
			}
//			out.println();
		}
	}	
}
