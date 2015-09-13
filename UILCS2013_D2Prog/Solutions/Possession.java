import java.io.*;
import java.util.*;
import static java.lang.System.*;  




public class Possession {

	public static void main(String args[]) throws IOException
	{
		Scanner kb = new Scanner(new File("possession.dat"));

		int times = 0; 
		int count = Integer.parseInt(kb.nextLine().trim());
		while(times < count) {
			times++;
			String[] t = kb.nextLine().trim().split(" ");
			String team = t[0];
			int seconds = 0;
			for(int i=1; i < t.length - 1; i+=4) {
				String qGetBall = t[i];
				int tGetBall = minToSecs(t[i+1]);
				String qLoseBall = t[i+2];
				int tLoseBall = minToSecs(t[i+3]);
				if(qGetBall.charAt(1) == qLoseBall.charAt(1)) // same quarter
					seconds += tGetBall - tLoseBall;
				else if (qGetBall.charAt(1)+ 1 == qLoseBall.charAt(1)) // consecutive quarters
					seconds += tGetBall + 900 - tLoseBall;
				else if (qGetBall.charAt(1)+ 2 == qLoseBall.charAt(1)) // 1st & 3rd or 2nd & 4th quarters
					seconds += tGetBall + 1800 - tLoseBall;
				else if (qGetBall.charAt(1)+ 3 == qLoseBall.charAt(1)) // 1st & 4th quarters
					seconds += tGetBall + 2700 - tLoseBall;
			}
			 	int minutes = seconds / 60;
			 	seconds %= 60;
			 	out.printf ("%s %d:%02d\n", team, minutes, seconds);
		}
	}
	public static int minToSecs(String s) {
		String []t = s.split(":");
		return 60*(Integer.parseInt(t[0]))+Integer.parseInt(t[1]);
	}
}
