import java.io.*;
import java.util.*;
import static java.lang.System.*;  


import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;


public class Triangles {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("triangles.dat"));
		
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;		

			String t = kb.nextLine().trim();
			String []s = t.split(" ");
			int []sides = new int [5];
			for(int i=0; i<5; i++)
				sides[i] = Integer.parseInt(s[i]);
			int numTriangles = 0;
			for(int i=2; i<5; i++) 							// ABC, ABD, ABE
				if(isTriangle(sides[0],sides[1],sides[i]))
					numTriangles++;	
			for(int i=3; i<5; i++) {						
				if(isTriangle(sides[0],sides[2],sides[i]))	// ACD, ACE
					numTriangles++; 
				if(isTriangle(sides[1],sides[2],sides[i]))	// BCD, BCE
					numTriangles++; 
			}
			for(int i=0; i<3; i++) 
				if(isTriangle(sides[i],sides[3],sides[4]))	//ADE, BDE, CDE
					numTriangles++; 
			out.println(numTriangles);	
		}
	}
	public static boolean isTriangle(int a, int b, int c) {
																//out.println(a+" "+b+" "+c);  show combinations
		return (a+b>c && b+c>a && a+c>b);
	}	
}
