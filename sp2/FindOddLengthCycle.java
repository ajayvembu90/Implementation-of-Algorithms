package sp2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
		
	// to maintain the check cycle result
	class CheckCycleResult{
		Vertex cycledVertex;
		boolean isCycle;
		CheckCycleResult(Vertex u,boolean isCycle){
			this.cycledVertex = u;
			this.isCycle = isCycle;
		}
	}
	
	// to check if a cycle exist 
	List<Edge> checkIfCycleExists(Graph g){
		List<Edge> edgesInCycle = new ArrayList<Edge>();
		checkIfCycleExists(g.verts.get(1),g,edgesInCycle);
		return edgesInCycle;
	}

	
	/*
	 * to check if there is a cycle in a graph 
	 * runs in O(V+E)
	 */
	Vertex checkIfCycleExists(Vertex u,Graph g,List<Edge> edgesInCycle){
		if (u.seen){
			// if the below condition is met then a cycle is found
			if (u.cycleTestInd)
				return u;
			else
				return null;
		}
		else{
			u.seen = true;
			// set the index to check for cycle
			u.cycleTestInd = true;
			Vertex cycledVertex = null;
			// to check for all the adjacent vertex of a corresponding vertex
			for (Edge e : u.Adj){
				Vertex v = e.otherEnd(u);
				cycledVertex = checkIfCycleExists(v,g,edgesInCycle);
				// print the cycle of vertices by back tracking
				if (cycledVertex!= null && cycledVertex.name != u.name){
					edgesInCycle.add(e);
					//System.out.println(e);
					break;
				}
				else{
					// to print the first vertex in the cycle
					if (cycledVertex!= null && cycledVertex.name == u.name){
						//System.out.println(e);
						edgesInCycle.add(e);
						g.foundCycle = true;
						cycledVertex =  null;
						break;
					}
					// break if a single cycle is found 
					if (g.foundCycle)
						break;
				}
			}
			// reset the index
			
			u.cycleTestInd = false;
			return cycledVertex;
		}
	}

	
	/*
	 * to check if there is a cycle in a graph 
	 * runs in O(V+E)
	 */
	CheckCycleResult checkIfCycle(Vertex u){
		if (u.seen){
			// if the below condition is met then a cycle is found
			if (u.cycleTestInd){
				//System.out.println(u.name);
				return new CheckCycleResult(u,true);
			}
			else
				return new CheckCycleResult(null,false);
		}
		else{
			u.seen = true;
			// set the index to check for cycle
			u.cycleTestInd = true;
			CheckCycleResult cCR = null;
			// to check for all the adjacent vertex of a corresponding vertex
			for (Edge e : u.Adj){
				Vertex v = e.otherEnd(u);
				cCR = checkIfCycle(v);
				// print the cycle of vertices by back tracking
				if (cCR.cycledVertex!= null && cCR.cycledVertex.name != u.name){
					//System.out.println(u.name);
					System.out.println(e);
					break;
				}
				else{
					if (cCR.cycledVertex!= null && cCR.cycledVertex.name == u.name){
						//System.out.println(u.name);
						System.out.println(e);
						cCR =  new CheckCycleResult(null,true);
						break;
					}
					// if cycledVertex is null and isCycle is true then break 
					if (cCR.isCycle)
						break;
				}
			}
			if (cCR == null)
				cCR = new CheckCycleResult(null,false);
			// reset the index
			u.cycleTestInd = false;
			return cCR;
		}
	}
	
	CheckCycleResult checkIfCycle(Vertex u, boolean[] vertexStack){
		if (u.seen){
			if (vertexStack[u.name]){
				System.out.println(u.name);
				return new CheckCycleResult(u,true);
			}
			else
				return new CheckCycleResult(null,false);
		}
		else{
			u.seen = true;
			vertexStack[u.name] = true;
			CheckCycleResult cCR = null;
			for (Edge e : u.Adj){
				Vertex v = e.otherEnd(u);
				cCR = checkIfCycle(v,vertexStack);
				if (cCR.cycledVertex!= null && cCR.cycledVertex.name != u.name){
					System.out.println(u.name);
					break;
				}
				else{
					if (cCR.cycledVertex!= null && cCR.cycledVertex.name == u.name){
						System.out.println(u.name);
						cCR =  new CheckCycleResult(null,true);
						break;
					}
				}
			}
			if (cCR == null)
				cCR = new CheckCycleResult(null,false);
			vertexStack[u.name] = false;
			return cCR;
		}
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
		Graph G = Graph.readGraph(new Scanner(System.in),true);
		FindOddLengthCycle folc = new FindOddLengthCycle();
		//ResultOfBipartite rob = null;
		List<Edge> getCycleEdgeList = folc.checkIfCycleExists(G);
		
		for (Edge e : getCycleEdgeList)
			System.out.println(e);
		
		/*
		 *  to check if the graph is bipartite in all the 
		 *  components of the graph
		 */
		
		/*
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
		*/
	}
}
