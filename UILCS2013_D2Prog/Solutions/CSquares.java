import java.io.*;
import java.util.*;
import static java.lang.System.*;  


import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;


public class CSquares {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("csquares.dat"));
		
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;		

			int center = Integer.parseInt(kb.nextLine().trim());
			int len = 2*center-1;		
			
			char [][]maze = new char [len][len] ;
			for(int r=0; r<len; r++) // set up matrix of *
				for(int c=0; c<len; c++)  
					maze[r][c]='*';
				
			int i=0;
			int j=1;
			for(i=1; i<center; i+=2) {
				for(j=i; j<len-i; j++) {
					maze[i][j] = ' ';			
					maze[len-i-1][j] = ' ';
					maze[j][i] = ' ';
					maze[j][len-i-1] = ' ';
												
				}
			}
			printMaze(maze);
		}
	}
	public static void printMaze(char[][] maze) {
			int len = maze[1].length;
			for(int r=0; r<len; r++) {
				for(int c=0; c<len; c++)  
					out.print(maze[r][c]);
				out.println();
			}	
			out.println();		
		
	}
}
