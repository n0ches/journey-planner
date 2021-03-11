import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
	
public class Dijkstra {
	public void clearGraphDistances(Graph g1) {
		for(Vertex v: g1.vertices()) {
			v.setDistance(Integer.MAX_VALUE);
			v.setPrevious(null);
			v.setVisited(false);
			v.setStopCountForReach(0);
		}
	}
	public int calculateDist(Graph g1,int sourceID, boolean isWalkFromSource, int walkSourceID, String line1, int lineTransferID, boolean lineTransfer, boolean isWalkToLine, int walkLineID, String line2, boolean isWalkToTarget, int walkTargetID, int target, int criteria) {
		clearGraphDistances(g1);
		g1.getVertex(target).setVisited(false);
		g1.getVertex(sourceID).setDistance(0);
		g1.getVertex(sourceID).setStopCountForReach(0);
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.add(g1.getVertex(sourceID));
		g1.getVertex(sourceID).setVisited(true);
		int tempOrder=0;
		int stopCount=0;
		boolean isLine1=true;
		boolean isLine2=false;
		while(!pq.isEmpty()) {
			Vertex currVertex=pq.poll();
			if(currVertex.getStopID()==target &&!isWalkToTarget) {
				break;
			}
				for(Edge edge: currVertex.getEdges()) {
					Vertex v=edge.getDestination();
					if(currVertex.getStopID()==sourceID && isWalkFromSource && !v.isVisited() && v.getStopID()==walkSourceID) {
						int newDistance =  ((int)currVertex.getDistance()) + edge.getDistance();
						if(newDistance < v.getDistance()) {
							pq.remove(v);
							v.setDistance(newDistance);
							v.setPrevious(currVertex);
							pq.add(v);
							stopCount++;
							v.setStopCountForReach(stopCount);
							isWalkFromSource=false;
						}
					}
					if(v.getLines().containsKey(line1) && !v.isVisited() && v.getLines().get(line1)>tempOrder && isLine1 && v.getStopID()!=target) {
						int newDistance = (int) (currVertex.getDistance() + edge.getDistance());
						if(newDistance < v.getDistance()) {
							pq.remove(v);
							v.setDistance(newDistance);
							v.setPrevious(currVertex);
							pq.add(v);
							stopCount++;
							v.setStopCountForReach(stopCount);
						}
						tempOrder=v.getLines().get(line1);
						if(v.getStopID()==lineTransferID) {
							isLine1=false;
							isLine2=true;
							tempOrder=0;
						}
					}
					
					if(currVertex.getStopID()==lineTransferID && v.getStopID()==walkLineID && isWalkToLine && !v.isVisited()) {
						if(v.getLines().containsKey(line2)) {
							tempOrder=0;
							int newDistance = (int) (currVertex.getDistance() + edge.getDistance());
							if(newDistance < v.getDistance()) {
								pq.remove(v);
								v.setDistance(newDistance);
								v.setPrevious(currVertex);
								pq.add(v);
								stopCount++;
								v.setStopCountForReach(stopCount);
								isWalkToLine=false;
							}
						}
					}
					if(v.getLines().containsKey(line2) && !v.isVisited() && v.getLines().get(line2)>tempOrder && v.getStopID()!=lineTransferID && isLine2 && v.getStopID()!=target && currVertex.getStopID()!=walkTargetID) {
						int newDistance = (int) (currVertex.getDistance() + edge.getDistance());
						if(newDistance < v.getDistance()) {
							pq.remove(v);
							v.setDistance(newDistance);
							v.setPrevious(currVertex);
							pq.add(v);
							stopCount++;
							v.setStopCountForReach(stopCount);
						}
						tempOrder=v.getLines().get(line2);
					}
					if(currVertex.getStopID()==walkTargetID && v.getStopID()==target && isWalkToTarget && !v.isVisited()) {
						int newDistance = (int) (currVertex.getDistance() + edge.getDistance());
						if(newDistance < v.getDistance()) {
							pq.remove(v);
							v.setDistance(newDistance);
							v.setPrevious(currVertex);
							pq.add(v);
							stopCount++;
							v.setStopCountForReach(stopCount);
							isWalkToTarget=false;
						}
					}
					if(!isWalkToTarget && v.getStopID()==target && !v.isVisited()) {
						int newDistance = (int) (currVertex.getDistance() + edge.getDistance());
						if(newDistance < v.getDistance()) {
							pq.remove(v);
							v.setDistance(newDistance);
							v.setPrevious(currVertex);
							pq.add(v);
							stopCount++;
							v.setStopCountForReach(stopCount);
						}
					}
				}
				currVertex.setVisited(true);
		}
		if(criteria==1) {
			return g1.getVertex(target).getStopCountForReach();
		}
		else {
			return (int)g1.getVertex(target).getDistance();
		}
	}
	
	public void dijkstraAdvance(Graph g1,int sourceID, boolean isWalkFromSource, int walkSourceID, String line1, int lineTransferID, boolean lineTransfer, boolean isWalkToLine, int walkLineID, String line2, boolean isWalkToTarget, int walkTargetID, int target) {
		g1.getVertex(sourceID).setDistance(0);
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.add(g1.getVertex(sourceID));
		g1.getVertex(sourceID).setVisited(true);
		int tempOrder=0;
		int stopCount=0;
		boolean isLine1=true;
		boolean isLine2=false;
		while(!pq.isEmpty()) {
			Vertex currVertex=pq.poll();
			if(currVertex.getStopID()==target) {
				break;
			}
				for(Edge edge: currVertex.getEdges()) {
					Vertex v=edge.getDestination();
					if(currVertex.getStopID()==sourceID && isWalkFromSource && !v.isVisited() && v.getStopID()==walkSourceID) {
						int newDistance = (int) (currVertex.getDistance() + edge.getDistance());
						if(newDistance < v.getDistance()) {
							System.out.println(v.getStopID()+" "+v.getStopName());
							pq.remove(v);
							v.setDistance(newDistance);
							v.setPrevious(currVertex);
							pq.add(v);
							stopCount++;
							isWalkFromSource=false;
						}
					}
					if(v.getLines().containsKey(line1) && !v.isVisited() && v.getLines().get(line1)>tempOrder && isLine1) {
						int newDistance = (int) (currVertex.getDistance() + edge.getDistance());
						if(newDistance < v.getDistance()) {
							System.out.println(v.getStopID()+" "+v.getStopName());
							pq.remove(v);
							v.setDistance(newDistance);
							v.setPrevious(currVertex);
							pq.add(v);
							stopCount++;
						}
						tempOrder=v.getLines().get(line1);
						if(v.getStopID()==lineTransferID) {
							isLine1=false;
							isLine2=true;
							tempOrder=0;
						}
					}
					
					if(currVertex.getStopID()==lineTransferID && v.getStopID()==walkLineID && isWalkToLine && !v.isVisited()) {
						if(v.getLines().containsKey(line2)) {
							tempOrder=0;
							int newDistance = (int) (currVertex.getDistance() + edge.getDistance());
							if(newDistance < v.getDistance()) {
								System.out.println(v.getStopID()+" "+v.getStopName());
								pq.remove(v);
								v.setDistance(newDistance);
								v.setPrevious(currVertex);
								pq.add(v);
								stopCount++;
								isWalkToLine=false;
							}
						}
					}
					if(v.getLines().containsKey(line2) && !v.isVisited() && v.getLines().get(line2)>tempOrder && v.getStopID()!=lineTransferID) {
						int newDistance = (int) (currVertex.getDistance() + edge.getDistance());
						if(newDistance < v.getDistance()) {
							System.out.println(v.getStopID()+" "+v.getStopName());
							pq.remove(v);
							v.setDistance(newDistance);
							v.setPrevious(currVertex);
							pq.add(v);
							stopCount++;
						}
						tempOrder=v.getLines().get(line2);
					}
					if(currVertex.getStopID()==walkTargetID && v.getStopID()==target && isWalkToTarget && !v.isVisited()) {
						int newDistance = (int) (currVertex.getDistance() + edge.getDistance());
						if(newDistance < v.getDistance()) {
							System.out.println(v.getStopID()+" "+v.getStopName());
							pq.remove(v);
							v.setDistance(newDistance);
							v.setPrevious(currVertex);
							pq.add(v);
							stopCount++;
							isWalkToTarget=false;
						}
					}
				}
				currVertex.setVisited(true);
		}
		System.out.println(g1.getVertex(target).getDistance());
		System.out.println(stopCount);
	}
	
	
	public List<String> getShortestPathTo(Vertex targetVertex){
		List<String> path = new ArrayList<>();
		Vertex v = targetVertex;
		while(v!=null) {
			path.add(v.getStopID()+ " " +v.getStopName());
			v=v.getPrevious();
		}
		Collections.reverse(path);
		return path;
	}
}
