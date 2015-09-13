import java.io.*;
import java.util.*;

public class Spellcheck
{
	private static class Key {
		private char key;
		private char[] adjacentTo;
		
		public Key(char k, char[] adj) {
			key = k;
			adjacentTo = adj;
		}
		
		public boolean isAdjacentTo(char key) {
			for (int i = 0; i < adjacentTo.length; ++i) {
				if (adjacentTo[i] == key) {
					return true;
				}
			}
			
			return false;
		}
	}
    
    public static void main(String args[]) throws IOException {
    	Key[] keys = new Key[26];
    	keys[ 0] = new Key('A', new char[]{'Q', 'W', 'S', 'Z'});
    	keys[ 1] = new Key('B', new char[]{'V', 'G', 'H', 'N'});
    	keys[ 2] = new Key('C', new char[]{'X', 'D', 'F', 'V'});
    	keys[ 3] = new Key('D', new char[]{'E', 'R', 'S', 'F', 'X', 'C'});
    	keys[ 4] = new Key('E', new char[]{'W', 'S', 'D', 'R'});
    	keys[ 5] = new Key('F', new char[]{'R', 'T', 'D', 'G', 'C', 'V'});
    	keys[ 6] = new Key('G', new char[]{'T', 'Y', 'F', 'H', 'V', 'B'});
    	keys[ 7] = new Key('H', new char[]{'Y', 'U', 'G', 'J', 'B', 'N'});
    	keys[ 8] = new Key('I', new char[]{'U', 'O', 'J', 'K'});
    	keys[ 9] = new Key('J', new char[]{'U', 'I', 'H', 'K', 'N', 'M'});
    	keys[10] = new Key('K', new char[]{'I', 'O', 'J', 'L', 'M'});
    	keys[11] = new Key('L', new char[]{'O', 'P', 'K'});
    	keys[12] = new Key('M', new char[]{'N', 'J', 'K'});
    	keys[13] = new Key('N', new char[]{'B', 'H', 'J', 'M'});
    	keys[14] = new Key('O', new char[]{'I', 'P', 'K', 'L'});
    	keys[15] = new Key('P', new char[]{'O', 'L'});
    	keys[16] = new Key('Q', new char[]{'W', 'A'});
    	keys[17] = new Key('R', new char[]{'E', 'D', 'F', 'T'});
    	keys[18] = new Key('S', new char[]{'W', 'E', 'A', 'D', 'Z', 'X'});
    	keys[19] = new Key('T', new char[]{'R', 'F', 'G', 'Y'});
    	keys[20] = new Key('U', new char[]{'Y', 'H', 'J', 'I'});
    	keys[21] = new Key('V', new char[]{'C', 'F', 'G', 'B'});
    	keys[22] = new Key('W', new char[]{'Q', 'A', 'S', 'E'});
    	keys[23] = new Key('X', new char[]{'Z', 'S', 'D', 'C'});
    	keys[24] = new Key('Y', new char[]{'T', 'G', 'H', 'U'});
    	keys[25] = new Key('Z', new char[]{'A', 'S', 'X'});
    	
    	Scanner kb = new Scanner(new File("spellcheck.dat"));
    	int numWords = Integer.parseInt(kb.nextLine().trim());
    	
    	ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < numWords; ++i) {
        	words.add(kb.nextLine().trim());
        }
        
        numWords = Integer.parseInt(kb.nextLine().trim());
        for (int i = 0; i < numWords; ++i) {
        	String word = kb.nextLine().trim();
        	String outWord = word;
        	
        	boolean isExactMatch = false;
        	for (int j = 0; j < words.size(); ++j) {
        		String compare = words.get(j);
        		
        		if (compare.equals(word)) {
        			outWord = compare;
        			isExactMatch = true;
        		}
        		else if (!isExactMatch && compare.length() == word.length()) {
        			int count = 0;
        			while (	count < compare.length() &&
        					(	compare.charAt(count) == word.charAt(count) ||
        						keys[word.charAt(count) - 'A'].isAdjacentTo(compare.charAt(count)) ) ) {
        				++count;
        			}
        			
        			if (count == compare.length()) {
        				outWord = compare;
        			}
        		}
        	}
        	
        	System.out.println(outWord);
        }
    }
}
