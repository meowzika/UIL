import java.io.*;
import java.util.*;

public class Mortgage
{
	public static void main(String args[]) throws IOException {
    	Scanner kb = new Scanner(new File("mortgage.dat"));
    	while (kb.hasNextLine()) {
    		String[] tok = kb.nextLine().trim().split(" ");
    		
    		double H = Double.parseDouble(tok[0]);
    		double D = Double.parseDouble(tok[1]);
    		double A = Double.parseDouble(tok[2]) / 12.0;
    		double N = Double.parseDouble(tok[3]);
    		
    		double P = (H - D) * (A * Math.pow(1 + A, N)) / (Math.pow(1 + A, N) - 1);
    		
    		System.out.print("$");
    		System.out.println((int) Math.ceil(P));
    	}
    	kb.close();
    }
}
