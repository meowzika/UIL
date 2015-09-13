import java.io.*;
import java.util.*;
import static java.lang.System.*;  





public class Sort {
	public static String s = "";
	public static String[] words;
	public static void main(String args[]) throws IOException {
		Scanner kb = new Scanner(new File("sort.dat"));
		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;			
			s = kb.nextLine().trim();
			int numWords = Integer.parseInt(kb.nextLine().trim());
			words = new String [numWords];
			for(int i=0; i<numWords; i++) 
				words[i] = kb.nextLine().trim();
			georgeSort(words);
			for(int i=0; i<numWords; i++)
				out.println(words[i]);
			out.println();
		}
	}
	public static void georgeSort(String []w) {
		int  i=0, j=0;
		int numWords = w.length;
		String temp;
		
		for (i=0; i<numWords-1; i++){ 
			for (j=i+1; j<numWords; j++) {  			
 				if (georgeCompare(w[i],w[j])>0){
   					temp = w[i];
    				w[i] = w[j];
    				w[j] = temp;					
  				}
			}
		}
	}

 	public static int georgeCompare(String w1, String w2) 	{
		char c1=' ', c2=' '; 
		for(int i=0 ; i<Math.min(w1.length(),w2.length()); i++) {
		 	c1 = w1.charAt(i); 
		 	c2 = w2.charAt(i);					
 		  	if (s.indexOf(c1) != s.indexOf(c2))  { 
		        return(s.indexOf(c1) - s.indexOf(c2)); 									}
		}
		if (s.indexOf(c1) == s.indexOf(c2) && w1.length() > w2.length())
			return (w1.length() - w2.length()); 
		return 0;
	}
}
