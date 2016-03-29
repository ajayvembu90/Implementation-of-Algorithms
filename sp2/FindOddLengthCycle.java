package sp2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FindOddLengthCycle {
	
	class ResultOfBipartite{
		boolean isBipartite=true;
		Vertex sourceVertexForOddLength;
		Vertex destVertexForOddLength;
	}
	
	ResultOfBipartite checkBipartite(Graph g, Vertex src){
		Queue<Vertex> Q = new LinkedList<>();
		ResultOfBipartite rob = new ResultOfBipartite();
		// add the source vertex to the queue
		Q.add(src);
		// mark the source as visited
		src.seen = true;

		// Perform BFS
		Outer: while (!Q.isEmpty()) {
		    // remove a vertex from the head of the queue
		    Vertex u = Q.remove();
		    // iterate through the u's adjacency list
		    for (Edge e : u.Adj) {
			Vertex v = e.otherEnd(u);
			/*
			 * if the vertex v is not visited then mark v as visited and
			 * update v's distance and parent and then add v to the queue
			 */
			if (!v.seen) {
			    v.seen = true;
			    v.parent = u;
			    v.distance = u.distance + 1;
			    Q.add(v);
			} else {
			    /*
			     * if the ends of edge (u,v), vertices u and v, are at the 
			     * same distance from the source, the graph is not bipartite
			     */
			    if (u.distance == v.distance){
			    	rob.isBipartite = false;
			    	rob.sourceVertexForOddLength = u;
			    	rob.destVertexForOddLength = v;
			    	break Outer;
			    }
			}
		    }
		}
		return rob;
	}
	
	class ResultOfFindingOddLengthCycle{
		Vertex parent;
		int oddLength;
	}
	
	ResultOfFindingOddLengthCycle findOddLengthCycle(Vertex A,Vertex B){
		ResultOfFindingOddLengthCycle rofolc = new ResultOfFindingOddLengthCycle();
		// the minimum odd length is 1
		int length = 1;
		/*
		 *  A and B are the vertices which has an odd length cycle. The odd
		 *  length cycle is between A,B and a common parent(immediate).
		 */
		while(A.parent.name != B.parent.name){
			A = A.parent;
			B = B.parent;
			length++;
		}
		rofolc.parent = A.parent;
		rofolc.oddLength = 2*length+1;
		return rofolc;
	}

	public static void main(String[] args){
		Graph G = Graph.readGraph(new Scanner(System.in),false);
		FindOddLengthCycle folc = new FindOddLengthCycle();
		ResultOfBipartite rob = null;
		
		/*
		 *  to check if the graph is bipartite in all the 
		 *  components of the graph
		 */
		for (Vertex u : G.verts){
			// null check for the 0th vertex
			if (u != null){
				if (!u.seen )
					rob = folc.checkBipartite(G,u);
				// breaks if one of the component of the graph is not bipartite
				if (!rob.isBipartite)
					break;	
			}
		}
		
		if (!rob.isBipartite){
			ResultOfFindingOddLengthCycle rofolc = folc.findOddLengthCycle(rob.sourceVertexForOddLength, rob.destVertexForOddLength);
			System.out.println("There is an odd length cycle between vertices "+rob.sourceVertexForOddLength.name+" and "
					+ rob.destVertexForOddLength.name+" with vertex "+rofolc.parent.name+" as it's parent and the odd length is "+rofolc.oddLength);
		}
		else
			System.out.println("The graph is bipartite");
	}
}
