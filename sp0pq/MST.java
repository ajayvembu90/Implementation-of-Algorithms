package sp0pq;
import java.util.Comparator;
import java.util.Scanner;

import sp0pq.BinaryHeap.VertexMinComparator;
import sp0pq.BinaryHeap.EdgeMinComparator;

public class MST {
    static final int Infinity = Integer.MAX_VALUE;

    static int PrimMST2(Graph g)
    {
        int wmst = 0;
        Vertex src = g.verts.get(1);
        Vertex[] vertexArray = new Vertex[g.numNodes+1];
        Comparator<Vertex> comp = new VertexMinComparator() ;
        int i = 1;
        for (Vertex u : g.verts){
        	if (u != null){
            	u.seen = false;
            	u.parent = null;
            	u.distance = Infinity;
            	u.index = i;
            	vertexArray[i++] = u;	
        	}
        }
        src.distance = 0;
        
        IndexedHeap<Vertex> indexedPriorityqueue = new IndexedHeap<Vertex>(vertexArray,comp);
        
        while (!indexedPriorityqueue.isEmpty()){
        	Vertex u = indexedPriorityqueue.remove();
        	u.seen = true;
        	wmst += u.distance;
        	for (Edge e : u.Adj){
        		Vertex v = e.otherEnd(u);
        		if (!v.seen && e.Weight < v.distance){
        			v.distance = e.Weight;
        			v.parent = u;
        			indexedPriorityqueue.decreaseKey(v);
        		}
        	}
        }
        return wmst;
    }
    
    static int PrimMST1(Graph g)
    {
        Vertex src = g.verts.get(1);
        for (Vertex u : g.verts){
        	if (u != null){
            	u.seen = false;
            	u.parent = null;
        	}
        }
        int wmst = 0;
        src.seen = true;
        Comparator<Edge> comp = new EdgeMinComparator() ;
        BinaryHeap<Edge> priorityqueue = new BinaryHeap<Edge>(g.numEdges+1,comp);
        
        for (Edge e : src.Adj)
        	priorityqueue.add(e);
        
        while (!priorityqueue.isEmpty()){
        	Edge e = priorityqueue.remove();
        	if (e.From.seen && e.To.seen) 
        		continue;
        	Vertex v =null;
        	Vertex u =null;
        	if (e.From.seen){
        		u = e.From;
        		v = e.To;
        	}
        	else{
        		u = e.To;
        		v = e.From;
        	}
        	v.parent = u;
        	wmst = wmst + e.Weight;
        	v.seen = true;
        	for (Edge f : v.Adj){
        		if ( !(f.From.seen && f.To.seen) ) 
        			priorityqueue.add(f);
        	}
        }
        
        return wmst;
    }

    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, false);
        System.out.println(PrimMST1(g));
    }
}
