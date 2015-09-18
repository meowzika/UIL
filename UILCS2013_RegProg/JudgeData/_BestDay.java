import java.io.*;
import java.util.Scanner;

public class _BestDay {
	public static void main(String[] args) throws IOException {
		Scanner file = new Scanner(new File("bestday.dat"));

		int numWeeks = Integer.parseInt(file.nextLine());
		int times = 0;

		while (times < numWeeks) {
			times++;

			int temp  = 0;
			int day = 0;

			String[] nums = file.nextLine().split(" ");

			for(int x = 0; x < 7; x++) {
				int curr = Integer.parseInt(nums[x]);
				if(temp < curr)	{	
					temp = curr; 
					day = x;
				}
			}								

			String off = "";

			switch (day) {
				case 0: off = "SUNDAY"; break;	
				case 1: off = "MONDAY"; break;	
				case 2: off = "TUESDAY"; break;	
				case 3: off = "WEDNESDAY"; break;	
				case 4: off = "THURSDAY"; break;	
				case 5: off = "FRIDAY"; break;	
				case 6: off = "SATURDAY"; break;	
			}

			System.out.println(off);
		}
	}
}
