package sp2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class CheckEulerian{
	
	/*
	 the below algorithm to check the degree of each vertex runs in O(V) time - linear time
	 the while loop below breaks if the number of vertices with odd number of degrees is greater than 2
	*/ 
	
	public ResultOfCheckingDegreeToFindEulerian checkDegreeToFindEulerian(Graph G){
		Iterator<Vertex> graphIterator = G.iterator();
		boolean eulerianIndicator = true;
		int oddCounter = 0;
		ResultOfCheckingDegreeToFindEulerian result = new ResultOfCheckingDegreeToFindEulerian();
		
		while (graphIterator.hasNext()){
			if (oddCounter > 2){
				eulerianIndicator = false;
				break;
			}
			Vertex v = graphIterator.next();
			if ( ( v.Adj.size()  % 2 ) != 0 ){
				result.oddVertexList.add(v);
				oddCounter++;
			}
		}
		result.eulerianIndicator = eulerianIndicator;
		result.oddCounter = oddCounter;
		
		return result;
	}
	
	/*
     dfs of the graph which runs in O(V+E) + O(V) + O(V) time - linear
    */
	
	public boolean checkIfConnected(Graph G){
		Iterator<Vertex> graphIterator = G.iterator();
		dfs(G.verts.get(1));
		boolean checkIfConnected = true;
		
		while (graphIterator.hasNext())
			checkIfConnected &= graphIterator.next().seen;
		
		graphIterator = G.iterator();
		
		while (graphIterator.hasNext())
			graphIterator.next().seen = false;
		
		return checkIfConnected;
	}
	
    /*
      dfs of the graph which runs in O(V+E) time - linear
    */
    private void dfs (Vertex u){
    	Iterator<Edge> adjacentEdgesIterator = u.Adj.iterator();
    	u.seen = true;
    	while (adjacentEdgesIterator.hasNext()){
    		Vertex v =  adjacentEdgesIterator.next().otherEnd(u);
    		if (!v.seen)
    			dfs(v);
    	}
    }
	
	
	public static void main(String[] args){
		Graph G = Graph.readGraph(new Scanner(System.in),false);
		CheckEulerian ce = new CheckEulerian();
		
		ResultOfCheckingDegreeToFindEulerian resultOfDegreeFind = ce.checkDegreeToFindEulerian(G);
		boolean checkIfConnected = ce.checkIfConnected(G);
		
		if ( resultOfDegreeFind.eulerianIndicator && checkIfConnected ){
			if ( resultOfDegreeFind.oddCounter == 2 )
				System.out.println("Graph has an Eulerian Path between vertices "+resultOfDegreeFind.oddVertexList.get(0)+" and "+resultOfDegreeFind.oddVertexList.get(1)+".");
			else
				System.out.println("Graph is Eulerian.");
		}
		else if (!resultOfDegreeFind.eulerianIndicator)
			System.out.println("Graph is not Eulerian.  It has "+resultOfDegreeFind.oddCounter+" vertices of odd degree.");
		else if (checkIfConnected)
			System.out.println("Graph is not connected.");
			
		
	}
}

class ResultOfCheckingDegreeToFindEulerian{
	boolean eulerianIndicator = true;
	int oddCounter = 0;
	List<Vertex> oddVertexList = new ArrayList<Vertex>();
}
