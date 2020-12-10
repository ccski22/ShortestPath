package assignment5_f20;

import java.util.HashMap;

public class Vertex {
	
	private long idNum;
	private String label;
	private long dist;
	private HashMap <String, Edge> edgeList; // label, edge
	public boolean known;

	public Vertex (long idNum, String label) {

		this.idNum = idNum;
		this.label = label; 
		this.edgeList = new HashMap <String, Edge>();
		dist = Integer.MAX_VALUE;
		known = false;
	}

	public long getID() {return this.idNum;}
	public String getLabel()  {return label;}
	public long getDist() {	return dist;}
	public void setKnown (boolean known) {this.known=known;}
	public void resetDist () {this.dist = Integer.MAX_VALUE;}
	public void setDist (long dist) {this.dist = dist;}
	public void setEdge (String dLabel, Edge e) {edgeList.put(dLabel, e);} // O(1) to add to hash set
	public HashMap<String, Edge> getEdgeList () {return edgeList;}
	
	public Edge getEdge (String destinationLabel) {
		return edgeList.get(destinationLabel);	
	}
	public void removeEdge (String dLabel) {
		edgeList.remove(dLabel);
	}


}
