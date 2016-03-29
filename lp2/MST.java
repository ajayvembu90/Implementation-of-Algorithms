package lp2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import lp2.BinaryHeap.EdgeMinComparator;
import lp2.BinaryHeap.VertexMinComparator;

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
        PriorityQueue<Edge> priorityqueue = new PriorityQueue<Edge>(g.numEdges+1,comp);
        for (Edge e : src.Adj)
        	priorityqueue.add(e);
        
        while (!priorityqueue.isEmpty()){
        	
        	Edge e = priorityqueue.remove();
        	//System.out.println(e);
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
        		if ( !(f.otherEnd(v).seen) ) 
        			priorityqueue.add(f);
        	}
        }
        
        return wmst;
    }
    
    static int Kruskal(Graph g){
    	
    	KruskalHelpers kh = new KruskalHelpers();
    	Edge[] edgeArray = new Edge[g.numEdges];
    	MultiPivotQuickSort<Edge> mpqs = new MultiPivotQuickSort<Edge>();
    	int i = 0,wmst = 0;
    	
    	for (Vertex u : g.verts){
    		if (u != null){
        		for (Edge e : u.Adj){
        			if (!e.added){
        				edgeArray[i++] = e;
        				e.added = true;
        			}
        		}
    		}
    	}
    	
    	for (Vertex u : g.verts)
    		if (u != null)
    			kh.makeSet(u);
    	
    	mpqs.quickSortRandom(edgeArray, 0, edgeArray.length-1);
    	
    	for (Edge e : edgeArray){
    		Vertex u = kh.findRepresentative(e.From);
    		Vertex v = kh.findRepresentative(e.To);
    		
    		if(u.name != v.name){
    			kh.unionVertex(u, v);
    			wmst += e.Weight;
    		}
    	}
    	
    	return wmst;
    }

    public static void main(String[] args) throws FileNotFoundException 
    {
        //Scanner in = new Scanner(new File("sp0pq-big.txt"));
    	Scanner in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, false);
        long startTime = System.currentTimeMillis();
        System.out.println(PrimMST2(g));
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken for PrimMST1 : "+ (endTime - startTime) +" ms ");
    }
}
