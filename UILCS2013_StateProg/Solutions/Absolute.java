import java.io.*;
import java.util.*;
import static java.lang.System.*;  




public class Absolute {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("absolute.dat"));
		
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;		

			String t = kb.nextLine().trim();
			String []s = t.split(" ");
			int num = 0;
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			int c = Integer.parseInt(s[2]);
			int d = Integer.parseInt(s[3]);    
			while(!(a==b && b==c && c==d)) {
				num++;
				int temp = a;
				a = Math.abs(a-b);
				b = Math.abs(b-c);
				c = Math.abs(c-d);
				d = Math.abs(d-temp);  //out.println(a+ " " + b+ " " + c + " " +d);
			}
			out.println(num + " " + a);
		}
	}	
}
