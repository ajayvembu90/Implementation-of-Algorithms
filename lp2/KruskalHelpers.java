package lp2;

public class KruskalHelpers {

	void makeSet(Vertex x){
		x.parent = x;
		x.rank = 0;
	}
	
	Vertex findRepresentative(Vertex x){
		if (x.name != x.parent.name)
			x.parent = findRepresentative(x.parent);
		return x.parent;
	}
	
	void unionVertex(Vertex x,Vertex y){
		if (x.rank > y.rank)
			y.parent = x;
		else if (y.rank > x.rank)
			x.parent = y;
		else{
			x.parent = y;
			y.rank++;
		}	
	}
}
