package assignment5_f20;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

public class DiGraph implements DiGraphInterface {
int numNodes = 0;
int numEdges = 0;

  public DiGraph ( ) { 
  }  
  
  HashMap<String, Vertex> nodeMap = new HashMap<>();
  HashMap <Vertex, Edge> incoming = new HashMap<Vertex, Edge>();

@Override
public boolean addNode(long idNum, String label) {	
	if(idNum < 0 || label == null || nodeMap.get(label) != null) {
		return false;
	} else {
		nodeMap.put(label, new Vertex(idNum, label));
		numNodes ++;
		return true;
	} 
}
@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
if(idNum < 0 || !nodeMap.containsKey(sLabel) || !nodeMap.containsKey(dLabel)) { return false;}

	Vertex sNode = nodeMap.get(sLabel);

	if(sNode.getEdge(dLabel) == null) {
	Edge newEdge = new Edge(idNum, sLabel, dLabel, weight, eLabel);
	sNode.setEdge(dLabel, newEdge);
	incoming.put(sNode, newEdge);
	numEdges++;
	return true;
	}
	return false;
}
public boolean delNode(String label) {
	if(!nodeMap.containsKey(label)){
		return false;
	} else {
		Vertex v = nodeMap.get(label);
		nodeMap.remove(label); //remove from map
		// remove incoming edges to source node
		incoming.remove(v);	
		}
		return true;
	}
@Override
public boolean delEdge(String sLabel, String dLabel) {
Vertex sNode = nodeMap.get(sLabel);
if(sNode == null) { return false; }
else {
	Vertex dNode = nodeMap.get(dLabel);
	if(dNode == null ) { return false; }
	else {
		Edge e = sNode.getEdge(dLabel);
		if(e == null) {
			return false;
		} else {
			sNode.removeEdge(dLabel);
			numEdges--;
			return true;
		}
	}
}
}

@Override
public long numNodes() {return nodeMap.size();}
@Override
public long numEdges() {return numEdges; }
  
public ShortestPathInfo [] shortestPath(String label) {
	
	ShortestPathInfo [] paths = new ShortestPathInfo[(int)this.numNodes()]; // (distance label, total weight)
	PriorityQueue<Vertex> pqueue = new PriorityQueue<Vertex>(1, new distanceSort());
	nodeMap.get(label).setDist(0); // distance from source to itself is set to 0
	pqueue.add(nodeMap.get(label)); // add source vertex to p queue
	
	while(pqueue.isEmpty() == false) { //while priority queue is not empty
		Vertex v = pqueue.peek();
		long dist = v.getDist();
		pqueue.remove();
		Collection<Edge> eLB =  v.getEdgeList().values();
		if(!nodeMap.get(v.getLabel()).known) {
			nodeMap.get(v.getLabel()).known = true;
			for(Edge e: eLB) {
				Vertex destination = nodeMap.get(e.getDLabel());
				if(destination.getDist() > dist + e.getWeight()) {
					destination.setDist(dist + e.getWeight());
					pqueue.add(destination);
			}
		} 
	}
	int x = 0 ;
	for(String sNode : nodeMap.keySet()) {
		if(nodeMap.get(sNode).getDist() == Integer.MAX_VALUE) {
			paths[x] = new ShortestPathInfo(sNode, -1);
		} else {
			paths[x] = new ShortestPathInfo(sNode, nodeMap.get(sNode).getDist());
		}
		x++;
	}
}
	return paths;
}

}
	
class distanceSort implements Comparator<Vertex> {

	@Override
	public int compare(Vertex o1, Vertex o2) {
		// TODO Auto-generated method stub
		return (int)o1.getDist()-(int)o2.getDist();
	}
	
}



	









