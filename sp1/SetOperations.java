package sp1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SetOperations {
	
	// intersection of pre-sorted lists
	// Loop invariant - both x1 and x2 are non null while inside the loop

    public static <T extends Comparable<? super T>> void intersect(List<T> l1, List<T> l2, List<T> outList) {
       Iterator<T> it1 = l1.iterator();
       Iterator<T> it2 = l2.iterator();
       
       T x1 = it1.next();
       T x2 = it2.next();
       
       while (x1 != null && x2 != null ){
    	   int comp = x1.compareTo(x2);
    	   if ( comp < 0){
    		   if (!it1.hasNext())
    			   return ;
    		   x1 = it1.next();
    	   }
    	   else if ( comp > 0){
    		   if (!it2.hasNext())
    			   return ;
    		   x2 = it2.next();
    	   }
    	   else{
    		   outList.add(x1);
    		   if (!it1.hasNext() || !it2.hasNext())
    			   return ;
    		   x1 = it1.next();
    		   x2 = it2.next();
    	   }
       }
    }
    
    // union of pre-sorted lists
    // Loop invariant - both x1 and x2 are non null while inside the loop
    
    public static <T extends Comparable<? super T>> void union(List<T> l1, List<T> l2, List<T> outList) {
        Iterator<T> it1 = l1.iterator();
        Iterator<T> it2 = l2.iterator();
        
        T x1 = it1.next();
        T x2 = it2.next();
        T prevElement = null;
        
        while (x1 != null && x2 != null ){
     	   int comp = x1.compareTo(x2);
     	   if ( comp < 0){
     		   if ( prevElement == x1){}
     		   else outList.add(x1);
     		   prevElement = x1;
     		   if (!it1.hasNext()){
     			  x1 = null; 
   			      break ;
     		   }
      		   x1 = it1.next();
     	   }
     	   else if ( comp > 0){
     		   if ( prevElement == x2){}
     		   else outList.add(x2);
     		   prevElement = x2;
     		   if (!it2.hasNext()){
     			  x2 = null;
   			      break ;
     		   }
     		   x2 = it2.next();   
     	   }
     	   else{
     		   if ( prevElement == x1){}
    		   else outList.add(x1);
    		   prevElement = x1;
     		   if (!it1.hasNext()){
     			  x1 = null; 
   			      break ;
     		   }
     		  if (!it2.hasNext()){
     			  x2 = null; 
   			      break ;
     		   }
     		   x1 = it1.next();
     		   x2 = it2.next();
     	   }
        }
        
        while (x1 != null){
        	if ( prevElement == x1){}
 		    else outList.add(x1);
        	prevElement = x1;
        	if (!it1.hasNext()) break;
        	x1 = it1.next();
        }
        
        while (x2 != null){
        	if ( prevElement == x2){}
  		    else outList.add(x2);
  		    prevElement = x2;
        	if (!it2.hasNext()) break;
        	x2 = it2.next();
        }
        
     }
    
    // difference of pre-sorted lists
    // Loop invariant - both x1 and x2 are non null while inside the loop
    
    public static <T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2, List<T> outList) {
    	Iterator<T> it1 = l1.iterator();
        Iterator<T> it2 = l2.iterator();
        
        T x1 = it1.next();
        T x2 = it2.next();
        T prevElement = null;
        
        while (x1 != null && x2 != null ){
     	   int comp = x1.compareTo(x2);
     	   if ( comp < 0){
     		   if ( prevElement == x1){}
   		       else outList.add(x1);
   		       prevElement = x1;
     		   if (!it1.hasNext()) break;
     		   x1 = it1.next();
     	   }
     	   else if ( comp > 0){
     		   if (!it2.hasNext()) break;
     		   x2 = it2.next();
     	   }
     	   else{
     		   if (!it1.hasNext() || !it2.hasNext()) break;
     		   x1 = it1.next();
     		   x2 = it2.next();
     	   }
        }
    }
    
    public static void main(String[] args){
    	ArrayList<Integer> set1 = new ArrayList<Integer>();
    	ArrayList<Integer> set2 = new ArrayList<Integer>();
    	ArrayList<Integer> intersection = new ArrayList<Integer>();
    	
    	set1.add(1);
    	set1.add(2);
    	set1.add(3);
    	set1.add(4);
    	set1.add(5);
    	set1.add(6);
    	set1.add(7);
    	set1.add(12);
    	set1.add(12);
    	set1.add(13);
    	set1.add(15);
    	set1.add(18);
    	set1.add(20);
    	
    	set2.add(4);
    	set2.add(5);
    	set2.add(6);
    	set2.add(7);
    	set2.add(8);
    	set2.add(15);
    	set2.add(19);
    	set2.add(20);
    	set2.add(21);
    	
    	difference(set1,set2,intersection);
    	
    	System.out.println(intersection);
    	
    }
}
