import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	
	static HashMap<Integer,Line> lineList = new HashMap<Integer,Line>();
	
	public static void main(String[] args) throws IOException {
		List<Integer> tripHelper = new ArrayList<Integer>();
		Graph g1 = new Graph();
	    Management run = new Management();
	    run.initiate(g1,lineList, tripHelper);
		run.readTest_Stop(g1,lineList,tripHelper);
	    
	    
		}
		
		
		
		

	}



