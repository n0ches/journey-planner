import java.util.HashMap;

public class Graph {
	private HashMap<Integer,Vertex> vertices;
	private HashMap<String,Edge> edges;
	private int edge_count;
	private int total_weight;
	

	Graph() {
		this.vertices = new HashMap<>();
		this.edges = new HashMap<>();
		edge_count=0;
		total_weight=0;
	}
	
	public void addVertex(Vertex stop) {
		vertices.put(stop.getStopID(),stop);
	}

	public void addEgde(Vertex source, Vertex destination, int weight,boolean flag) {

		//if(edges.get(source.getStopID() + "-" + destination.getStopID()) == null && edges.get(destination.getStopID() + "-" + source.getStopID()) == null)
		//{
		edge_count++;
			Vertex source_v,destination_v;
			if(vertices.get(source.getStopID()) == null) 
				{
					source_v=source;
					vertices.put(source.getStopID(), source);
				}
			else source_v=source;
			
			if(vertices.get(destination.getStopID()) == null) 
				{
				destination_v=destination;
				vertices.put(destination.getStopID(),destination);
				}
			else destination_v=destination;
			
			Edge edge = new Edge(source_v, destination_v, weight,flag);
			source.addEdge(edge);
			edges.put(source_v.getStopID() + "-" + destination_v.getStopID(), edge);
		//}
	}
	
	public void printVertices() {
		int i=1;
		for(Vertex v : vertices.values()) {
			System.out.println("Vertex" + i);
			System.out.println(" " + v.getStopID() + " "+v.getStopName());
			System.out.println(v.printAdjencies());
			System.out.println();
			i++;
		}
	}
	public void printEdges() {
		for(Edge e : edges.values()) {
			System.out.println(" " + e.getSource().getStopID() + " ---> " + e.getDestination().getStopID() + " ( " + e.getDistance()+"m )");
			
			System.out.println();
			
		}
	}
	
	public void print(){
		System.out.println("Source\tDestination\tWeight");
	for (Edge e : edges.values()) {
		System.out.println("" + e.getSource().getStopName() + "\t" + e.getDestination().getStopName() + "\t\t" + e.getDistance()+ " ");
		edge_count++;
		total_weight += e.getDistance();
	}
	
	System.out.println("Total " + edge_count + " edges");
	System.out.println("Total weigh is " + total_weight);
	System.out.println();
}
	
	

	/**
	 * @return the edge_count
	 */
	public int getEdge_count() {
		return edge_count;
	}

	public Vertex getVertex(int stopID) {
		return vertices.get(stopID);
	}
	
	public Edge getEdge(int stopID,int destinationID) {
		return edges.get(stopID+"-"+destinationID);
	}
	
	public String getEdgeString(int stopID,int destinationID) {
		return edges.get(stopID+"-"+destinationID).toString();
	}
	
public  Iterable<Vertex> vertices()
{
	return vertices.values();
}

public  Iterable<Edge> edges()
{
	return edges.values();
}

public int size()
{
	return vertices.size();
}


}