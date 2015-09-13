import java.io.*;
import java.util.*;

public class ADQL
{
	public static class SortClause {
		String colName;
		boolean isDesc;
		boolean asInt;
	}
	
	
	public static boolean evaluateWhereClause(String[] headers, String[] dbLine, String whereCol, String whereOp, String whereCmp) {
		boolean bInclude = true;
		
		for (int c = 0; c < headers.length; ++c) {
			if (headers[c].equals(whereCol)) {
				if (whereOp.equals("=")) {
					bInclude = dbLine[c].equals(whereCmp);
				}
				else if (whereOp.equals("<")) {
					bInclude = Integer.parseInt(dbLine[c]) < Integer.parseInt(whereCmp);
				}
				else if (whereOp.equals(">")) {
					bInclude = Integer.parseInt(dbLine[c]) > Integer.parseInt(whereCmp);
				}
				else if (whereOp.equals("<=")) {
					bInclude = Integer.parseInt(dbLine[c]) <= Integer.parseInt(whereCmp);
				}
				else if (whereOp.equals(">=")) {
					bInclude = Integer.parseInt(dbLine[c]) >= Integer.parseInt(whereCmp);
				}
			}
		}
		
		return bInclude;
	}
	
	public static void moveAndShift(String[][] db, int row1, int row2) {
		String[] tmp = db[row2];
		for (int r = row2 - 1; r >= row1; --r) {
			db[r + 1] = db[r];
		}
		db[row1] = tmp;
	}
	
	public static void sortByColumn(String[] headers, String[][] db, String sortCol, boolean isDesc, boolean asInt, String groupCol) {
		int sortIdx = 0;
		int groupIdx = -1;
		for (int h = 0; h < headers.length; ++h) {
			if (headers[h].compareToIgnoreCase(sortCol) == 0) {
				sortIdx = h;
			}
			else if (headers[h].compareToIgnoreCase(groupCol) == 0) {
				groupIdx = h;
			}
		}
		
		int startIdx = 0;
		int endIdx = 0;
		while (startIdx < db.length) {
			if (groupIdx == -1) {
				endIdx = db.length - 1;
			}
			else {
				String cmpVal = db[startIdx][groupIdx];
				for (int i = startIdx + 1; i < db.length; ++i) {
					if (cmpVal.compareToIgnoreCase(db[i][groupIdx]) == 0) {
						endIdx = i;
					}
				}
			}
			
			for (int i = startIdx; i <= endIdx; ++i) {
				for (int j = i + 1; j <= endIdx; ++j) {
					if (isDesc) {
						if (asInt) {
							if (Integer.parseInt(db[i][sortIdx]) < Integer.parseInt(db[j][sortIdx])) {
								moveAndShift(db, i, j);
							}
						}
						else {
							if (db[i][sortIdx].compareToIgnoreCase(db[j][sortIdx]) < 0) {
								moveAndShift(db, i, j);
							}
						}
					}
					else {
						if (asInt) {
							if (Integer.parseInt(db[i][sortIdx]) > Integer.parseInt(db[j][sortIdx])) {
								moveAndShift(db, i, j);
							}
						}
						else {
							if (db[i][sortIdx].compareToIgnoreCase(db[j][sortIdx]) > 0) {
								moveAndShift(db, i, j);
							}
						}
					}
				}
			}
			
			startIdx = endIdx = endIdx + 1;
		}
	}
	
	public static void main(String args[]) throws IOException {
    	Scanner kb = new Scanner(new File("adql.dat"));
    	
    	String[] tokens = kb.nextLine().trim().split(" ");
    	int cols = Integer.parseInt(tokens[0]);
    	int rows = Integer.parseInt(tokens[1]);
    	
    	String[] headers = kb.nextLine().trim().split("\\|");
    	String[][] db = new String[rows][cols];
    	for (int r = 0; r < rows; ++r) {
    		tokens = kb.nextLine().trim().split("\\|");
    		for (int c = 0; c < cols; ++c) {
    			db[r][c] = c < tokens.length ? tokens[c].trim() : "";
    			headers[c] = headers[c].trim();
    		}
    	}
    	
    	int numQueries = Integer.parseInt(kb.nextLine().trim());
    	for (int n = 0; n < numQueries; ++n) {
    		String[] queryTokens = kb.nextLine().trim().split("[ ,]");
    		
    		// First token should always be SELECT, so sanity check my own problem :)
    		if (!queryTokens[0].equals("SELECT")) {
    			System.out.println("BAD QUERY!");
    			continue;
    		}
    		
    		ArrayList<String> selectors = new ArrayList<String>();
    		String whereCol = "";
    		String whereOp = "";
    		String whereCmp = "";
    		boolean bWhereClause = false;
    		
    		ArrayList<SortClause> sorts = new ArrayList<SortClause>();
    		boolean bSortClause = false;
    		
    		for (int q = 1; q < queryTokens.length; ++q) {
    			String tok = queryTokens[q];
    			if (tok.equals("WHERE")) {
    				bWhereClause = true;
    				bSortClause = false;
    			}
    			else if (tok.equals("SORT")) {
    				bSortClause = true;
    				bWhereClause = false;
    			}
    			else if (bWhereClause) {
    				for (int s = 0; s < tok.length(); ++s) {
    					char ch = tok.charAt(s);
    					if (ch == '<' || ch == '>' || ch == '=') {
    						whereOp += ch;
    					}
    					else if (whereOp.isEmpty()) {
    						whereCol += ch;
    					}
    					else {
    						whereCmp += ch;
    					}
    				}
    			}
    			else if (bSortClause) {
    				SortClause sc = new SortClause();
    				sc.isDesc = tok.equalsIgnoreCase("DESC");
    				++q;
    				tok = queryTokens[q];
    				if (tok.equalsIgnoreCase("INT")) {
    					sc.asInt = true;
    					++q;
        				tok = queryTokens[q];
    				}
    				
    				sc.colName = tok;	
    				sorts.add(sc);
    				
    				bSortClause = false;
    			}
    			else {
    				selectors.add(tok);
    			}
    		}
    		
    		// Make a copy to sort
    		String[][] dbs = new String[rows][cols];
    		for (int r = 0; r < rows; ++r) {
    			for (int c = 0; c < cols; ++c) {
    				dbs[r][c] = db[r][c];
    			}
    		}
    		
    		// Sort
    		String prevSortCol = "";
    		for (int s = 0; s < sorts.size(); ++s) {
    			sortByColumn(headers, dbs, sorts.get(s).colName, sorts.get(s).isDesc, sorts.get(s).asInt, prevSortCol);
    			prevSortCol = sorts.get(s).colName;
    		}
    		
    		// Grab the rows we need
    		ArrayList<String> dataOut = new ArrayList<String>();
    		for (int r = 0; r < rows; ++r) {
    			if (evaluateWhereClause(headers, dbs[r], whereCol, whereOp, whereCmp)) {
    				String rowOut = "";
    				for (int c = 0; c < cols; ++c) {
        				for (int s = 0; s < selectors.size(); ++s) {
        					String selector = selectors.get(s);
        					if (selector.equals("*") || selector.equals(headers[c])) {
        						rowOut += dbs[r][c];
            					rowOut += " ";
        					}
        				}
        			}
    				
    				if (!rowOut.isEmpty()) {
        				dataOut.add(rowOut);
        			}
    			}
    		}
    		
    		System.out.printf("Query #%d\n", n + 1);
    		if (dataOut.size() == 0) {
    			System.out.println("<EMPTY>");
    		}
    		else {
	    		for (int i = 0; i < dataOut.size(); ++i) {
	    			System.out.println(dataOut.get(i).trim());
	    		}
    		}
    		System.out.println();
    	}
    	
    	kb.close();
    }
}
