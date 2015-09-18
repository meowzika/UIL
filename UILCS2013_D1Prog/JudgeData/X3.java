import java.io.*;
import java.util.Scanner;

public class X3 {
	public static void main(String[] args) throws IOException {
		Scanner file = new Scanner(new File("x3.dat"));

		int numEq = Integer.parseInt(file.nextLine());


		int times = 0;

		while(times < numEq) {
			times++;

			String[] nums = file.nextLine().split(" ");

			int a = Integer.parseInt(nums[0]);
			int b = Integer.parseInt(nums[1]);
			int c = Integer.parseInt(nums[2]);
			int d = Integer.parseInt(nums[3]);

			boolean flag = false;

			for(int i = - 100; i <= 100; i++) {
				if(a*i*i*i + b*i*i + c*i + d == 0) {
					System.out.print(i +  " ");
					flag = true;
				}
			}

			if(!flag)
				System.out.println("NO REAL SOLUTIONS");
			else
				System.out.println();
		}
	}
}
