import java.io.*;
import java.util.*;
import static java.lang.System.*;  


import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;


public class WordFind2 {
	
	public static boolean bPrintInstances = false;
	
	
	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("wordfind2.dat"));
		
		int count = Integer.parseInt(kb.nextLine().trim());
		for (int x = 0; x < count; ++x) {
			String[] s = kb.nextLine().trim().split(" ");
			int r = Integer.parseInt(s[0]);
			int c = Integer.parseInt(s[1]);
			char[][] mat = new char[r][c];

			for (int i = 0; i < r; ++i) {
				String str = kb.nextLine().trim();
				for (int j = 0; j < c; ++j) {
					mat[i][j] = str.charAt(j);
				}
			}
			
			s = kb.nextLine().trim().split(" ");
			for (int w = 0; w < s.length; ++w) {
				String word = s[w];
				
				int numOccurances = 0;
				for (int i = 0; i < r; ++i) {
					for (int j = 0; j < c; ++j) {
						numOccurances += recurse(mat, i, j, word, 0);
					}
				}
				
				System.out.printf("%s %d\n", word, numOccurances);
			}
		}
	}
	
	public static int recurse(char[][] mat, int r, int c, String str, int index) {
		if (r < 0 || r >= mat.length) {
			return 0;
		}
		if (c < 0 || c >= mat[0].length) {
			return 0;
		}
		
		if (mat[r][c] != str.charAt(index)) {
			return 0;
		}
		
		++index;
		if (index == str.length()) {
			if (bPrintInstances) {
				char ch = mat[r][c];
				mat[r][c] = '#';
				for (int i = 0; i < mat.length; ++i) {
					System.out.print("\t");
					for (int j = 0; j < mat[0].length; ++j) {
						System.out.print(mat[i][j]);
					}
					System.out.println();
				}
				System.out.println();
				mat[r][c] = ch;
			}
			
			return 1;
		}
		
		char ch = mat[r][c];
		mat[r][c] = '#';
		
		int numOccurances = 0;
		numOccurances += recurse(mat, r - 1, c - 1, str, index);
		numOccurances += recurse(mat, r - 1, c + 0, str, index);
		numOccurances += recurse(mat, r - 1, c + 1, str, index);
		
		numOccurances += recurse(mat, r + 0, c - 1, str, index);
		numOccurances += recurse(mat, r + 0, c + 1, str, index);
		
		numOccurances += recurse(mat, r + 1, c - 1, str, index);
		numOccurances += recurse(mat, r + 1, c + 0, str, index);
		numOccurances += recurse(mat, r + 1, c + 1, str, index);
		
		mat[r][c] = ch;
		
		return numOccurances;
	}
}
