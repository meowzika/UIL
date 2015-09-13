import java.io.*;
import java.util.*;

public class Friendlier {
	
	public static void main(String args[]) throws IOException {
    	Scanner kb = new Scanner(new File("friendlier.dat"));
    	
    	final byte MAGIC_NUMBER 		= 97;
    	final byte RIGHT_SHIFT_AMOUNT 	= 5;
    	final byte LEFT_SHIFT_AMOUNT 	= 3;
    	final byte BOTTOM_BITS			= (byte) 0x1F;
    	
    	while (kb.hasNextLine()) {
    		String line = kb.nextLine().trim();
    		String[] tokens = line.split(":");
    		
    		String name = tokens[0];
    		String message = "";
    		for (int i = 1; i < tokens.length; ++i) {
    			message = message.concat(tokens[i]);
    			if (i < tokens.length - 1) {
    				message = message.concat(":");
    			}
    		}
    		
    		message = message.trim();
    		
    		byte seed = MAGIC_NUMBER;
    		for (int i = 0; i < name.length(); ++i) {
    			seed = (byte) (seed ^ (byte) name.charAt(i));
    		}
    		
    		System.out.printf("%s: ", name);
    		
    		int length = message.length();
    		for (int i = 0; i < length; ++i) {
    			byte ch = (byte) message.charAt(i);
    			byte ch2 = (i == 0) ? (byte) message.charAt(length - 1) : (byte) message.charAt(i - 1);
    			
    			ch = (byte) ( (ch >> RIGHT_SHIFT_AMOUNT) | ((ch2 & BOTTOM_BITS) << LEFT_SHIFT_AMOUNT) );
    			
    			System.out.printf("0x%02X ", (ch ^ seed) & 0xFF);
    		}
    		
    		System.out.println();
    	}
    	
    	kb.close();
    }
}
