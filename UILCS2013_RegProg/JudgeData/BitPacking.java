import java.io.*;
import java.util.Scanner;

public class BitPacking {

	public static void main(String[] args) throws IOException {
		Scanner file = new Scanner( new File("bitpacking.dat"));

		int numPack = Integer.parseInt(file.nextLine());

		int times = 0 ;

		while(times < numPack) {
			times++;

			int pack = Integer.parseInt(file.nextLine());

			// base 10 to base 2

			// trunicate 9 bits.
			// next 5 is type (char) 1-26
			// next 6 is sub-type 
			// the last few is the amount

			
		}
	}
}
