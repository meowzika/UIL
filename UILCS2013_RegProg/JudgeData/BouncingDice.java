import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BouncingDice {

	public static void main(String[] args) throws IOException {
		Scanner file = new Scanner( new File("bouncing.dat"));

		int numSims = Integer.parseInt(file.nextLine());

		int times = 0 ;

		while(times < numSims) {
			times++;

			long seed = Long.parseLong(file.nextLine());
			
			Random r = new Random(seed);
			
			int r1 = r.nextInt(10) + 1;

			for(int x = 0; x < r1; x++) {
				r2 = r.nextInt(4) + 1;
				r3 = r.nextInt(5) + 1;

				
			}	
		}
	}
}
