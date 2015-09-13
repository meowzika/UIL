import java.io.*;
import java.util.*;

public class Eagles
{
	public static boolean bPrintBestMap = false;
	public static char[][] bestMap;
	
	public static final char CH_EAGLE	= 'E';
	public static final char CH_BEAR	= 'B';
	public static final char CH_MOOSE	= 'M';
	
	public static final char CH_VISITED	= '#';
	
	public static char[][] map;
	
	
	public static int recurse(int x, int y, int count, int curMin) {
		if (x < 0 || x > 9) {
			return 101;
		}
		else if (y < 0 || y > 9) {
			return 101;
		}
		
		if (count >= curMin) {
			return curMin;
		}
		else if (map[x][y] == CH_EAGLE) {
			for (int r = 0; r < 10; ++r) {
				for (int c = 0; c < 10; ++c) {
					bestMap[r][c] = map[r][c];
				}
			}
			return count;
		}
		else if (map[x][y] == CH_BEAR || map[x][y] == CH_MOOSE || map[x][y] == CH_VISITED) {
			return curMin;
		}
		else {
			char ch = map[x][y];
			map[x][y] = CH_VISITED;
			int moves = Integer.MAX_VALUE;
			
			moves = Math.min(moves, recurse(x - 1, y - 1, count + 1, curMin));
			moves = Math.min(moves, recurse(x - 1, y + 0, count + 1, moves));
			moves = Math.min(moves, recurse(x - 1, y + 1, count + 1, moves));
			
			moves = Math.min(moves, recurse(x + 0, y - 1, count + 1, moves));
			moves = Math.min(moves, recurse(x + 0, y + 1, count + 1, moves));
			
			moves = Math.min(moves, recurse(x + 1, y - 1, count + 1, moves));
			moves = Math.min(moves, recurse(x + 1, y + 0, count + 1, moves));
			moves = Math.min(moves, recurse(x + 1, y + 1, count + 1, moves));
			
			map[x][y] = ch;
			return moves;
		}
	}
	
	public static void main(String args[]) throws IOException {
    	Scanner kb = new Scanner(new File("eagles.dat"));
    	
    	int numMaps = Integer.parseInt(kb.nextLine().trim());
    	for (int m = 0; m < numMaps; ++m) {
    		String[] tokens = kb.nextLine().trim().split(" ");
    		int cabinR = Integer.parseInt(tokens[0]) - 1;
    		int cabinC = Integer.parseInt(tokens[1]) - 1;
    		
    		map = new char[10][10];
    		bestMap = new char[10][10];
    		for (int r = 0; r < 10; ++r) {
    			String line = kb.nextLine().trim();
    			for (int c = 0; c < 10; ++c) {
    				map[r][c] = line.charAt(c);
    			}
    		}
    		
    		int moves = recurse(cabinR, cabinC, 0, Integer.MAX_VALUE);
    		
    		for (int r = 0; r < 10 && bPrintBestMap; ++r) {
				for (int c = 0; c < 10; ++c) {
					System.out.print(bestMap[r][c]);
				}
				System.out.println();
			}
    		
    		System.out.println(moves);
    	}
    	
    	kb.close();
    }
}
