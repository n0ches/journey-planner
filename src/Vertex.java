import java.util.ArrayList;
import java.util.HashMap;

public class Vertex implements Comparable<Vertex> {
	
	private int distance=Integer.MAX_VALUE;
	private Vertex previous;
	private boolean visited;
	private int stopCountForReach=0;
	
	private int stopID;
	private String stopName;
	private double coordinateX,coordinateY;
	private int vehicleTypeID;
	private ArrayList<Edge> edges;//adjencies vertices
	
	private HashMap<String, Integer> lines;
	
	public Vertex(int stopID) {
		this.stopID = stopID;
		this.stopName = "Unknown stop name";
		this.vehicleTypeID = 1;
		visited=false;
		edges = new ArrayList<Edge>();
		lines = new HashMap<String,Integer>();
	}
	
	public Vertex(int stopID, String stopName, double coordinateX, double coordinateY, int vehicleTypeID) {
		this.stopID = stopID;
		this.stopName = stopName;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.vehicleTypeID = vehicleTypeID;
		visited=false;
		edges = new ArrayList<Edge>();
		lines = new HashMap<String,Integer>();
	}
	
	//adding adjencies
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	public int getStopID() {
		return stopID;
	}
	
	public void setStopID(int stopID) {
		this.stopID = stopID;
	}
	
	public String getStopName() {
		return stopName;
	}
	
	public void setStopName(String stopName) {
		this.stopName = stopName;
	}
	
	public double getCoordinateX() {
		return coordinateX;
	}
	
	public void setCoordinateX(double coordinateX) {
		this.coordinateX = coordinateX;
	}
	
	public double getCoordinateY() {
		return coordinateY;
	}
	
	public void setCoordinateY(double coordinateY) {
		this.coordinateY = coordinateY;
	}
	
	public int getVehicleTypeID() {
		return vehicleTypeID;
	}
	
	public void setVehicleTypeID(int vehicleTypeID) {
		this.vehicleTypeID = vehicleTypeID;
	}
	
	public double getDistance() {
		return distance;
	}
 
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
	public int getStopCountForReach() {
		return stopCountForReach;
	}

	
	public void setStopCountForReach(int stopCountForReach) {
		this.stopCountForReach = stopCountForReach;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	
	public Vertex getPrevious() {
		return previous;
	}

	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	/**
	 * @return the lines
	 */
	public HashMap<String,Integer> getLines() {
		return lines;
	}

	public int getLinesElement(String key) {
		int a = lines.get(key);
		return a;
	}

	
	public void addLine(String e,int order) {
		lines.put(e,order);
	}

	public int compareTo(Vertex otherVertex) {
		return Double.compare(this.distance, otherVertex.getDistance());
	}
	
	public String printAdjencies() {
        StringBuilder edgesStr = new StringBuilder();
		for (int i = 0; i < edges.size(); i++) {
			edgesStr.append("\nEdge - " + (i+1) + " -->" + " [ ")
             .append(edges.get(i).getSource().getStopID() + "(" + edges.get(i).getSource().getStopName() +")")
             .append(" - ")
			.append(edges.get(i).getDestination().getStopID() + "(" + edges.get(i).getDestination().getStopName() +")")
			.append(" ]" + "( " + edges.get(i).getDistance() + "m )");
		}
		return edgesStr.toString();
	}
}
