import java.io.*;
import java.util.*;  


public class Scrabble {
	
	public static boolean bDebugMode = false;
	
	public static class Player implements Comparable<Player> {
		String name = "";
		int[] scores = new int[3];
		
		int currentScore;
		int lastRank;
		
		ArrayList<Player> played = new ArrayList<Player>();
		
		public int compareTo(Player player) {
			int scoreDiff = player.currentScore - currentScore;
			int rankDiff = player.lastRank - lastRank;
			return scoreDiff == 0 ? rankDiff : scoreDiff;
		}
	}

	public static void main(String args[]) throws IOException  {
		Scanner kb = new Scanner(new File("scrabble.dat"));
		
		int counter = Integer.parseInt(kb.nextLine().trim());
		for (int c = 0; c < counter; ++c) {
			int numPlayers = Integer.parseInt(kb.nextLine().trim());
			ArrayList<Player> players = new ArrayList<Player>();
			
			for (int p = 0; p < numPlayers; ++p) {
				String[] tokens = kb.nextLine().trim().split(" ");
				Player player = new Player();
				player.name = tokens[0];
				player.scores[0] = Integer.parseInt(tokens[1]);
				player.scores[1] = Integer.parseInt(tokens[2]);
				player.scores[2] = Integer.parseInt(tokens[3]);
				player.lastRank = p + 1;
				
				players.add(player);
			}
			
			for (int round = 0; round < 4; ++round) {
				if (round > 0 || bDebugMode) {
					System.out.printf("TEST CASE #%d ROUND %d:\n", c + 1, round + 1);
					for (int p = 0; p < numPlayers; ++p) {
						Player player = players.get(p);
						System.out.printf("%s %d\n", player.name, player.currentScore);
						player.lastRank = p + 1;
					}
					System.out.println();
				}
				
				if (round == 3)
					break;
				
				for (int p = 0; p < numPlayers; p += 2) {
					Player p1 = players.get(p);
					Player p2 = players.get(p + 1);
					
					p1.currentScore += p1.scores[round];
					p2.currentScore += p2.scores[round];
					
					p1.played.add(p2);
					p2.played.add(p1);
				}
				
				Collections.sort(players);
				
				if (bDebugMode) {
					System.out.printf("=== AFTER SORTING ===\n");
					for (int p = 0; p < numPlayers; ++p) {
						Player player = players.get(p);
						System.out.printf("%s %d\n", player.name, player.currentScore);
						player.lastRank = p + 1;
					}
					System.out.println();
				}
				
				for (int p = 0; p < numPlayers; p += 2) {
					Player p1 = players.get(p);
					Player p2 = players.get(p + 1);
					
					if (p1.played.contains(p2)) {
						if (bDebugMode) {
							System.out.printf("%s played %s\n\n", p1.name, p2.name);
						}
						
						boolean bSwapped = false;
						for (int q = p + 2; q < numPlayers; ++q) {
							Player p3 = players.get(q);
							if (!p1.played.contains(p3)) {
								if (bDebugMode) {
									System.out.printf("Shifting %s in front of %s\n\n", p3.name, p2.name);
								}
								players.remove(p3);
								players.add(p + 1, p3);
								bSwapped = true;
								break;
							}
						}
						
						if (!bSwapped) {	
							for (int q = p - 2; q >= 0; q-=2) {
								Player p3 = players.get(q);
								Player p4 = players.get(q + 1);
								if (!p1.played.contains(p3) && !p2.played.contains(p4)) {
									if (bDebugMode) {
										System.out.printf("Swapping %s with %s\n\n", p1.name, p3.name);
									}
									players.remove(p1);
									players.remove(p4);
									players.add(q + 1, p1);
									players.add(p, p4);
									bSwapped = true;
									break;
								}
								else if (!p1.played.contains(p4) && !p2.played.contains(p3)) {
									if (bDebugMode) {
										System.out.printf("Swapping %s with %s\n\n", p1.name, p4.name);
									}
									players.remove(p1);
									players.remove(p3);
									players.add(q, p1);
									players.add(p, p3);
									bSwapped = true;
									break;
								}
							}
						}
						
						if (bDebugMode && !bSwapped) {
							System.out.printf("UHHHH, CAN'T RESEED PLAYERS!\n\n");
						}
					}
				}
			}
		}
	}	
}
