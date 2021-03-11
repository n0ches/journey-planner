
public class Edge {
	private Vertex source;
	private Vertex destination;
	private int distance;
	private boolean neighbourEdge;
	private int lineID;
	private int direction;
	
	
	public Edge(Vertex source, Vertex destination, int distance,boolean flag) {
		super();
		this.source = source;
		this.destination = destination;
		this.distance = distance;
		neighbourEdge=flag;
		lineID=-1;
		direction=-1;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getDestination() {
		return destination;
	}

	public void setDestination(Vertex destination) {
		this.destination = destination;
	}
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
	
	/**
	 * @return the lineID
	 */
	public int getLineID() {
		return lineID;
	}

	/**
	 * @param lineID the lineID to set
	 */
	public void setLineID(int lineID) {
		this.lineID = lineID;
	}

	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * @return the neighbourEdge
	 */
	public boolean isNeighbourEdge() {
		return neighbourEdge;
	}

	/**
	 * @param neighbourEdge the neighbourEdge to set
	 */
	public void setNeighbourEdge(boolean neighbourEdge) {
		this.neighbourEdge = neighbourEdge;
	}

	public String toString() {
		return source.getStopName() + "-" + destination.getStopName() + "-" + distance;
	}
}
