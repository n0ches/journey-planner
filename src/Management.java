import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Management {
	
	public void readStop(Graph g1) throws NumberFormatException, IOException {
		FileReader fileReader = new FileReader("Stop.txt");
		String line;
		BufferedReader br = new BufferedReader(fileReader);
		int stopID=0;
		String stopName=" ";
		double coordinateX=0.0;
		double coordinateY=0.0;
		int vehicleType=0;
		int k=0;
		ArrayList<String> tempEdges = new ArrayList<String>();
		ArrayList<Integer> tempVertices = new ArrayList<Integer>();
		while ((line = br.readLine()) != null) {
			if(k==0) {
				k++;
				continue;
			}
		   String[] splitted = line.split(";");
		   
		  
			stopID=Integer.parseInt(splitted[0]);
			
			stopName=splitted[1].toString();
			
			coordinateX=(double)Float.parseFloat(splitted[2].replace(",", "."));
			
			coordinateY=(double)Float.parseFloat(splitted[3].replace(",", "."));
			
			vehicleType=Integer.parseInt(splitted[4]);
			
			tempEdges.add(splitted[5]);
			
			tempVertices.add(stopID);
				
			Vertex v = new Vertex(stopID,stopName,coordinateX,coordinateY,vehicleType);
			
			g1.addVertex(v);
			
			g1.addEgde(v, v, 0, true);
	}
			   for (int i = 0; i < tempEdges.size(); i++) {
				   if(!tempEdges.get(i).equals("NULL")) {
					   String asd = tempEdges.get(i).toString().replace(".", ";");
					   String[] temp=asd.split(";");
					   for (int j = 0; j < temp.length; j++) {
						   String[] edge=temp[j].split(":");
						   int source = tempVertices.get(i);
						   int destination = Integer.parseInt(edge[0].toString());
						   int distance = Integer.parseInt(edge[1].toString());
						   g1.addEgde(g1.getVertex(source), g1.getVertex(destination), distance,true);
					}
			}
		}  
			   br.close();
	}
	
	public void readDistance(Graph g1) throws NumberFormatException, IOException {
		FileReader fileReader = new FileReader("Distance.txt");
		BufferedReader br = new BufferedReader(fileReader);
		  String line=null;
		  int k=0;
		   int sourceID;
		   int destinationID;
		   int distance;
		   while ((line = br.readLine()) != null) {
				if(k==0) {
					k++;
					continue;
				}
			
			String[] splitted1 = line.split(";");
			sourceID = Integer.parseInt(splitted1[0]);
			destinationID = Integer.parseInt(splitted1[1]);
			distance = Integer.parseInt(splitted1[2]);
			if(g1.getVertex(sourceID)==null) {
				Vertex v = new Vertex(sourceID);
				g1.addVertex(v);
			}
			if(g1.getVertex(destinationID)==null) {
				Vertex v = new Vertex(destinationID);
				g1.addVertex(v);
			}
			if(g1.getEdge(sourceID, destinationID)==null) {
				g1.addEgde(g1.getVertex(sourceID), g1.getVertex(destinationID), distance,false);
			}
			
		   }
		   br.close();
	}

	public void readLine(Graph g1, HashMap<Integer,Line> lineList, List<Integer> tripHelper) throws NumberFormatException, IOException {
		 FileReader lineReader = new FileReader("Line.txt");
		 BufferedReader lineRead = new BufferedReader(lineReader);
		 String sea;
		 int k=0;
		 int lineID = 0;
		 int lineNo;
		 String lineName;
		 int vehicleTypeID;
		 while ((sea = lineRead.readLine()) != null) {
					if(k==0) {
						k++;
						continue;
					}
					String[] splittedLine = sea.split(";");
					lineID = Integer.parseInt(splittedLine[0]);
					lineNo = Integer.parseInt(splittedLine[1]);
					lineName = splittedLine[2];
					vehicleTypeID = Integer.parseInt(splittedLine[3]);
					Line l1 = new Line(lineID,lineNo,lineName,vehicleTypeID);
					lineList.put(lineID,l1);
					tripHelper.add(lineID);
		   }
		 lineRead.close();
	}
	
	public void readTrip(Graph g1, HashMap<Integer,Line> lineList, List<Integer> tripHelper) throws NumberFormatException, IOException {
		int sourceID=0;
		   int destinationID=0;
		   FileReader TripReader = new FileReader("Trip.txt");
		   BufferedReader tripRead = new BufferedReader(TripReader);
		   String trip;
		   int k=0;
		   int ID;
		   int direction = 0;
		   int order;
		   int vertexID;
		   while ((trip = tripRead.readLine()) != null) {
					if(k==0) {
						k++;
						continue;
					}
					String[] splittedTrip = trip.split(";");
					ID = Integer.parseInt(splittedTrip[0]);
					direction = Integer.parseInt(splittedTrip[1]);
					order = Integer.parseInt(splittedTrip[2]);
					vertexID = Integer.parseInt(splittedTrip[3]);
					if(g1.getVertex(vertexID)==null) {
						Vertex v = new Vertex(vertexID);
						g1.addVertex(v);
					}
					g1.getVertex(vertexID).addLine(ID+";"+direction,order);
					lineList.get(ID).addStop(direction, vertexID);
				}
		   g1.addEgde(g1.getVertex(12272), g1.getVertex(1000314), 441, true);
		   for (int i = 0; i < tripHelper.size(); i++) {
			for (int j = 0; j < lineList.get(tripHelper.get(i)).getDirectZero().size()-1; j++) {
				sourceID=lineList.get(tripHelper.get(i)).getDirectZero().get(j);
				destinationID=lineList.get(tripHelper.get(i)).getDirectZero().get((j+1));
				if(g1.getVertex(sourceID)==null) {
					Vertex v = new Vertex(sourceID);
					g1.addVertex(v);
				}
				if(g1.getVertex(destinationID)==null) {
					Vertex v = new Vertex(destinationID);
					g1.addVertex(v);
				}
				if(g1.getEdge(sourceID, destinationID) == null) {
					g1.addEgde(g1.getVertex(sourceID), g1.getVertex(destinationID), 625,false);
					
				}
				g1.getEdge(sourceID, destinationID).setLineID(tripHelper.get(i));
				g1.getEdge(sourceID, destinationID).setDirection(0);
			}
				for (int m = 0; m < lineList.get(tripHelper.get(i)).getDirectOne().size()-1; m++) {
					sourceID=lineList.get(tripHelper.get(i)).getDirectOne().get(m);
					destinationID=lineList.get(tripHelper.get(i)).getDirectOne().get((m+1));
					if(g1.getVertex(sourceID)==null) {
						Vertex v = new Vertex(sourceID);
						g1.addVertex(v);
					}
					if(g1.getVertex(destinationID)==null) {
						Vertex v = new Vertex(destinationID);
						g1.addVertex(v);
					}
					if(g1.getEdge(sourceID, destinationID) == null) {
						g1.addEgde(g1.getVertex(sourceID), g1.getVertex(destinationID), 625,false);
					}
					g1.getEdge(sourceID, destinationID).setLineID(tripHelper.get(i));
					g1.getEdge(sourceID, destinationID).setDirection(1);
			}
		}
		   tripRead.close();
	}
	
	public void readTest_Stop(Graph g1 ,HashMap<Integer,Line> lineList, List<Integer> tripHelper) throws IOException {
		FileReader test_stopReader = new FileReader("test_stops.txt");
		BufferedReader test_stopRead = new BufferedReader(test_stopReader);
		String test_stop=null;
		int k=0;
		int sourceID=0;
		int destinationID = 0;
		int criteria=0;
		int vertexID;
		Scanner keyboard = new Scanner (System.in);
		String key=null;
		while ((test_stop = test_stopRead.readLine()) != null) {
				if(k==0) {
					k++;
					continue;
				}
				System.out.println();
				System.out.println("Press A for the results");
				key=keyboard.nextLine().toLowerCase();
				String[] splitted = test_stop.split(";");
				sourceID = Integer.parseInt(splitted[0]);
				destinationID = Integer.parseInt(splitted[1]);
				criteria = Integer.parseInt(splitted[4]);
				if(key.equals("a")) {
					System.out.println("-------  "+test_stop+"  -------");
					System.out.println();
					findShortestPath(g1,lineList,tripHelper,sourceID,destinationID,criteria);
				}
		   }
		test_stopRead.close();
	}
	
	public void initiate(Graph g1, HashMap<Integer,Line> lineList, List<Integer> tripHelper) throws NumberFormatException, IOException {
		readStop(g1);
	    readDistance(g1);
		readLine(g1, lineList, tripHelper);
		readTrip(g1, lineList, tripHelper);
	}
	
	public void findShortestPath(Graph g1, HashMap<Integer,Line> lineList, List<Integer> tripHelper, int source, int destination, int criteria) throws NumberFormatException, IOException {
		int transferCount=0;
	    String[][] transferStops = new String[2000][3];
	    String[] tempSourceLines = new String[50];
	    String[] tempTargetLines = new String[50];
	    int sourceID=source;
	    int targetID=destination;
	    for (int i = 0; i < g1.getVertex(sourceID).getEdges().size(); i++) {
			if(g1.getVertex(sourceID).getEdges().get(i).isNeighbourEdge()) {
				String[] tempNeighbourLines = new String[30];
				g1.getVertex(sourceID).getEdges().get(i).getDestination().getLines().keySet().toArray(tempNeighbourLines);
				for (int j = 0; j < tempNeighbourLines.length; j++) {
					if(tempNeighbourLines[j]==null) {
						break;
					}
					if(!g1.getVertex(sourceID).getLines().containsKey(tempNeighbourLines[j])) {
						g1.getVertex(sourceID).getLines().put(tempNeighbourLines[j]+";"+g1.getVertex(sourceID).getEdges().get(i).getDestination().getStopID()+";N", g1.getVertex(sourceID).getEdges().get(i).getDestination().getLines().get(tempNeighbourLines[j]));
					}
				}
			}
		}
	    for (int i = 0; i < g1.getVertex(targetID).getEdges().size(); i++) {
			if(g1.getVertex(targetID).getEdges().get(i).isNeighbourEdge()) {
				String[] tempNeighbourLines = new String[30];
				g1.getVertex(targetID).getEdges().get(i).getDestination().getLines().keySet().toArray(tempNeighbourLines);
				for (int j = 0; j < tempNeighbourLines.length; j++) {
					if(tempNeighbourLines[j]==null) {
						break;
					}
					if(!g1.getVertex(targetID).getLines().containsKey(tempNeighbourLines[j])) {
						g1.getVertex(targetID).getLines().put(tempNeighbourLines[j]+";"+g1.getVertex(targetID).getEdges().get(i).getDestination().getStopID()+";N", g1.getVertex(targetID).getEdges().get(i).getDestination().getLines().get(tempNeighbourLines[j]));
					}
				}
			}
		}
	    
	    boolean isNeighbourLineToSource=false;
	    boolean isNeighbourLineToTarget=false;
	    g1.getVertex(sourceID).getLines().keySet().toArray(tempSourceLines);
	    g1.getVertex(targetID).getLines().keySet().toArray(tempTargetLines);
	    for (int i = 0; i < tempSourceLines.length; i++) {
	    	isNeighbourLineToSource=false;
	    	if(tempSourceLines[i]==null) {
	    		break;
	    	}
	    	else {
	    		String[] splittedSourceLines = tempSourceLines[i].split(";");
	    		int sourceLine=Integer.parseInt(splittedSourceLines[0]);
				int sourceLineDirection=Integer.parseInt(splittedSourceLines[1]);
				String sourceNeighbourStop =null;
	    		if(splittedSourceLines.length>2) {
	    			sourceNeighbourStop=splittedSourceLines[2];
					isNeighbourLineToSource=true;
	    		}
	    		
				for (int j = 0; j < tempTargetLines.length; j++) {
					isNeighbourLineToTarget=false;
					if(tempTargetLines[j]==null) {
						break;
					}
					else {
						String[] splittedTargetLines = tempTargetLines[j].split(";");
						int targetLine=Integer.parseInt(splittedTargetLines[0]);
						int targetLineDirection=Integer.parseInt(splittedTargetLines[1]);
						String targetNeighbourStop =null;
						if(splittedTargetLines.length>2) {
							targetNeighbourStop=splittedTargetLines[2];
							isNeighbourLineToTarget=true;
			    		}
						if(sourceLine == targetLine && sourceLineDirection==targetLineDirection) {
							if(!isNeighbourLineToSource && !isNeighbourLineToTarget) {
								transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection;
								transferStops[transferCount][1]="Target;a";
								transferStops[transferCount][2]="source'tan direkt bindi, targetta direkt indi";
								transferCount++;
								break ;
							}
							else if(isNeighbourLineToSource && !isNeighbourLineToTarget) {
								transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection;
								transferStops[transferCount][1]="Target;a";
								transferStops[transferCount][2]="source'tan line'a yürüdü, line direkt targeta götürdü";
								transferCount++;
								break ;
							}
							else if(isNeighbourLineToTarget && !isNeighbourLineToSource) {
								transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection;
							    transferStops[transferCount][1]="Walk;"+targetNeighbourStop+";"+"Target";
								transferStops[transferCount][2]="source'tan direkt bindi, indiği yerden targeta yürüdü";
								transferCount++;
								break ;		
							}
							else if(isNeighbourLineToSource && isNeighbourLineToTarget) {
								
								transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection;
								transferStops[transferCount][1]="Walk;"+targetNeighbourStop+";"+"Target";
								transferStops[transferCount][2]="source'tan line'a yürüdü, indiği yerden target'a yürüdü";
								transferCount++;
								break ;
							}
						}
						else {
							if(sourceLineDirection == 0) {
								for (int k = 0; k < lineList.get(sourceLine).getDirectZero().size(); k++) {
									if(g1.getVertex(lineList.get(sourceLine).getDirectZero().get(k)).getLines().keySet().contains(targetLine+";"+targetLineDirection) && !lineList.get(sourceLine).getDirectZero().contains(targetID)) {
										if(g1.getVertex(lineList.get(sourceLine).getDirectZero().get(k)).getLines().get(targetLine+";"+targetLineDirection)< g1.getVertex(targetID).getLines().get(tempTargetLines[j])) {
											if(!isNeighbourLineToTarget && !isNeighbourLineToSource) {
												transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k).toString();
												transferStops[transferCount][1]=lineList.get(sourceLine).getDirectZero().get(k).toString()+";"+targetLine+";"+targetLineDirection+";"+"Target";
												transferStops[transferCount][2]="source'dan direkt line1'e bindi, line1'den line'ye geçti, line2 direkt target'a götürdü";
												transferCount++;
												break ;
											}
											if(isNeighbourLineToSource && !isNeighbourLineToTarget) {
												if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < k) {
													transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k).toString();
													transferStops[transferCount][1]=lineList.get(sourceLine).getDirectZero().get(k).toString()+";"+targetLine+";"+targetLineDirection+";Target";
													transferStops[transferCount][2]="source'tan line1'e yürüdü, line1'den line2'ye geçti, line2 direkt target'a götürdü";
													transferCount++;
													break ;
												}
											}
											else if(isNeighbourLineToTarget && !isNeighbourLineToSource) {
												transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k).toString();
												transferStops[transferCount][1]=lineList.get(sourceLine).getDirectZero().get(k).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
												transferStops[transferCount][2]="source'dan direkt line1'e bindi, line1'den line'ye geçti, line2'den target'a yürüdü";
												transferCount++;
												break ;
											}
											else if(isNeighbourLineToTarget && isNeighbourLineToSource) {
												if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < k) {
													transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k).toString();
													transferStops[transferCount][1]=lineList.get(sourceLine).getDirectZero().get(k).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
													transferStops[transferCount][2]="source'tan line1'e yürüdü, line1'den line2'ye geçti, line2'den target'a yürüdü";
													transferCount++;
													break ;
												}
											}
										}
									}
								}
									if(targetLineDirection==0) {
										for (int k2 = 0; k2 < lineList.get(sourceLine).getDirectZero().size(); k2++) {
											for (int l = 0; l < lineList.get(targetLine).getDirectZero().size(); l++) {
												if(g1.getEdge(lineList.get(sourceLine).getDirectZero().get(k2), lineList.get(targetLine).getDirectZero().get(l))!=null) {
													if(g1.getEdge(lineList.get(sourceLine).getDirectZero().get(k2), lineList.get(targetLine).getDirectZero().get(l)).isNeighbourEdge() && !lineList.get(sourceLine).getDirectZero().contains(targetID)) {
														if(g1.getVertex(lineList.get(targetLine).getDirectZero().get(l)).getLines().get(targetLine+";"+targetLineDirection) <g1.getVertex(targetID).getLines().get(tempTargetLines[j])){
															if(!isNeighbourLineToTarget && !isNeighbourLineToSource){
																transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k2).toString();
																transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectZero().get(l).toString()+";"+targetLine+";"+targetLineDirection+";"+"Target";
																transferStops[transferCount][2]="source'tan line1'e bindi, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den direkt target'a gitti";
																transferCount++;
																break ;
															}
															else if(isNeighbourLineToSource && !isNeighbourLineToTarget) {
																if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < l) {
																	transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k2).toString();
																	transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectZero().get(l).toString()+";"+targetLine+";"+targetLineDirection+";"+"Target";
																	transferStops[transferCount][2]="source'tan line'a yürüdü, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2 direkt target'a gitti.";
																	transferCount++;
																	break ;
																}
															}
															else if(isNeighbourLineToTarget && !isNeighbourLineToSource) {
																transferStops[transferCount][0]=sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k2).toString();
																transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectZero().get(l).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
																transferStops[transferCount][2]="source'tan line1'e bindi, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den targeta yürüdü";
																transferCount++;
																break ;
															}
															else if(isNeighbourLineToTarget && isNeighbourLineToSource) {
																if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < l) {
																	transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k2).toString();
																	transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectZero().get(l).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
																	transferStops[transferCount][2]="source'tan line1'e yürüdü, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den targeta yürüdü";
																	transferCount++;
																	break ;
																}
															}
														}
													}
												}
											}
										}
									}
									if(targetLineDirection==1) {
										for (int k2 = 0; k2 < lineList.get(sourceLine).getDirectZero().size(); k2++) {
											for (int l = 0; l < lineList.get(targetLine).getDirectOne().size(); l++) {
												if(g1.getEdge(lineList.get(sourceLine).getDirectZero().get(k2), lineList.get(targetLine).getDirectOne().get(l))!=null) {
													if(g1.getEdge(lineList.get(sourceLine).getDirectZero().get(k2), lineList.get(targetLine).getDirectOne().get(l)).isNeighbourEdge() && !lineList.get(sourceLine).getDirectZero().contains(targetID)) {
														if(g1.getVertex(lineList.get(targetLine).getDirectOne().get(l)).getLines().get(targetLine+";"+targetLineDirection) <g1.getVertex(targetID).getLines().get(tempTargetLines[j])){
															if(!isNeighbourLineToTarget && !isNeighbourLineToSource){
																transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k2).toString();
																transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectOne().get(l).toString()+";"+targetLine+";"+targetLineDirection+";"+"Target";
																transferStops[transferCount][2]="source'tan line1'e bindi, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den direkt target'a gitti";
																transferCount++;
																break ;
															}
															else if(isNeighbourLineToSource && !isNeighbourLineToTarget) {
																if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < l) {
																	transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k2).toString();
																	transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectOne().get(l).toString()+";"+targetLine+";"+targetLineDirection+";"+"Target";
																	transferStops[transferCount][2]="source'tan line'a yürüdü, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2 direkt target'a gitti.";
																	transferCount++;
																	break ;
																}
															}
															else if(isNeighbourLineToTarget && !isNeighbourLineToSource) {
																transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k2).toString();
																transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectOne().get(l).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
																transferStops[transferCount][2]="source'tan line1'e bindi, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den targeta yürüdü";
																transferCount++;
																break ;
															}
															else if(isNeighbourLineToTarget && isNeighbourLineToSource) {
																if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < l) {
																	transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectZero().get(k2).toString();
																	transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectOne().get(l).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
																	transferStops[transferCount][2]="source'tan line1'e yürüdü, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den targeta yürüdü";
																	transferCount++;
																	break ;
																}
															}
															
														}
													}
												}
												
											}
										}
									}
									
								}
							else if(sourceLineDirection == 1) {
								for (int m = 0; m < lineList.get(sourceLine).getDirectOne().size(); m++) {
									if(g1.getVertex(lineList.get(sourceLine).getDirectOne().get(m)).getLines().keySet().contains(targetLine+";"+targetLineDirection) && !lineList.get(sourceLine).getDirectOne().contains(targetID)) {
										if(g1.getVertex(lineList.get(sourceLine).getDirectOne().get(m)).getLines().get(sourceLine+";"+sourceLineDirection) < g1.getVertex(targetID).getLines().get(tempTargetLines[j])) {
											if(!isNeighbourLineToTarget && !isNeighbourLineToSource) {
												transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m).toString();
												transferStops[transferCount][1]=lineList.get(sourceLine).getDirectOne().get(m).toString()+";"+targetLine+";"+targetLineDirection+";"+"Target";
												transferStops[transferCount][2]="source'dan direkt line1'e bindi, line1'den line2'ye geçti, line2 direkt target'a götürdü";
												transferCount++;
												break ;
											}
											else if(isNeighbourLineToSource && !isNeighbourLineToTarget) {
												if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < m) {
													transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m).toString();
													transferStops[transferCount][1]=lineList.get(sourceLine).getDirectOne().get(m).toString()+";"+targetLine+";"+targetLineDirection+";Target";
													transferStops[transferCount][2]="source'tan line1'e yürüdü, line1'den line2'ye geçti, line2 direkt target'a götürdü";
													transferCount++;
													break ;
												}
												
											}
											else if(isNeighbourLineToTarget && !isNeighbourLineToSource) {
												transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m).toString();
												transferStops[transferCount][1]=lineList.get(sourceLine).getDirectOne().get(m).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
												transferStops[transferCount][2]="source'dan direkt line1'e bindi, line1'den line'ye geçti, line2'den target'a yürüdü";
												transferCount++;
												break ;
											}
											else if(isNeighbourLineToTarget && isNeighbourLineToSource) {
												if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < m) {
													transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m).toString();
													transferStops[transferCount][1]=lineList.get(sourceLine).getDirectOne().get(m).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
													transferStops[transferCount][2]="source'tan line1'e yürüdü, line1'den line2'ye geçti, line2'den target'a yürüdü";
													transferCount++;
													break ;
												}
												
											}
										}
									}
								}
									if(targetLineDirection==0) {
										
										for (int m2 = 0; m2 < lineList.get(sourceLine).getDirectOne().size(); m2++) {
											for (int n = 0; n < lineList.get(targetLine).getDirectZero().size(); n++) {
												if(g1.getEdge(lineList.get(sourceLine).getDirectOne().get(m2), lineList.get(targetLine).getDirectZero().get(n))!=null) {
													if(g1.getEdge(lineList.get(sourceLine).getDirectOne().get(m2), lineList.get(targetLine).getDirectZero().get(n)).isNeighbourEdge() && !lineList.get(sourceLine).getDirectOne().contains(targetID)) {
														if(g1.getVertex(lineList.get(targetLine).getDirectZero().get(n)).getLines().get(targetLine+";"+targetLineDirection) < g1.getVertex(targetID).getLines().get(tempTargetLines[j])){
															if(!isNeighbourLineToTarget && !isNeighbourLineToSource) {
																transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m2).toString();
																transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectZero().get(n).toString()+";"+targetLine+";"+targetLineDirection+";"+"Target";
																transferStops[transferCount][2]="source'tan line1'e bindi, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den direkt target'a gitti";
																transferCount++;
																break ;
															}
															else if(isNeighbourLineToSource && !isNeighbourLineToTarget) {
																if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < n) {
																	transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m2).toString();
																	transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectZero().get(n).toString()+";"+targetLine+";"+targetLineDirection+";"+"Target";
																	transferStops[transferCount][2]="source'tan line'a yürüdü, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2 direkt target'a gitti.";
																	transferCount++;
																	break ;
																}
															}
															else if(isNeighbourLineToTarget && !isNeighbourLineToSource) {
																transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m2).toString();
																transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectZero().get(n).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
																transferStops[transferCount][2]="source'tan line1'e bindi, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den targeta yürüdü";
																transferCount++;
																break ;
															}
															else if(isNeighbourLineToTarget && isNeighbourLineToSource) {
																if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < n) {
																	transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m2).toString();
																	transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectZero().get(n).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
																	transferStops[transferCount][2]="source'tan line1'e yürüdü, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den targeta yürüdü";
																	transferCount++;
																	break ;
																}
															}
															
														}
													}
												}
											}
										}
									}
									if(targetLineDirection==1) {
										for (int m2 = 0; m2 < lineList.get(sourceLine).getDirectOne().size(); m2++) {
											for (int n = 0; n < lineList.get(targetLine).getDirectOne().size(); n++) {
												if(g1.getEdge(lineList.get(sourceLine).getDirectOne().get(m2), lineList.get(targetLine).getDirectOne().get(n))!=null) {
													if(g1.getEdge(lineList.get(sourceLine).getDirectOne().get(m2), lineList.get(targetLine).getDirectOne().get(n)).isNeighbourEdge() && !lineList.get(sourceLine).getDirectOne().contains(targetID)) {
														if(g1.getVertex(lineList.get(targetLine).getDirectOne().get(n)).getLines().get(targetLine+";"+targetLineDirection) <g1.getVertex(targetID).getLines().get(tempTargetLines[j])){
															if(!isNeighbourLineToTarget && !isNeighbourLineToSource) {
																transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m2).toString();
																transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectOne().get(n).toString()+";"+targetLine+";"+targetLineDirection+";"+"Target";
																transferStops[transferCount][2]="source'tan line1'e bindi, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den direkt target'a gitti";
																transferCount++;
																break ; 
															}
															if(isNeighbourLineToSource && !isNeighbourLineToTarget) {
																if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < n) {
																	transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m2).toString();
																	transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectOne().get(n).toString()+";"+targetLine+";"+targetLineDirection+";"+"Target";
																	transferStops[transferCount][2]="source'tan line'a yürüdü, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2 direkt target'a gitti.";
																	transferCount++;
																	break ; 
																}
															}
															else if(isNeighbourLineToTarget && !isNeighbourLineToSource) {
																transferStops[transferCount][0]=sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m2).toString();
																transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectOne().get(n).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
																transferStops[transferCount][2]="source'tan line1'e bindi, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den targeta yürüdü";
																transferCount++;
																break ; 
															}
															else if(isNeighbourLineToTarget && isNeighbourLineToSource) {
																if(g1.getVertex(Integer.parseInt(sourceNeighbourStop)).getLines().get(sourceLine+";"+sourceLineDirection) < n) {
																	transferStops[transferCount][0]="Walk;"+sourceNeighbourStop+";"+sourceLine+";"+sourceLineDirection+";"+lineList.get(sourceLine).getDirectOne().get(m2).toString();
																	transferStops[transferCount][1]="Walk;"+lineList.get(targetLine).getDirectOne().get(n).toString()+";"+targetLine+";"+targetLineDirection+";Walk;"+targetNeighbourStop+";Target";
																	transferStops[transferCount][2]="source'tan line1'e yürüdü, line1'den araca bindi indiği duraktan line2'ye yürüdü, line2'den targeta yürüdü";
																	transferCount++;
																	break ; 
																}
															}
															
														}
													}
												}
											}
										}
									}
								}
							}
							
							}
						}
					}
	    	}
	    
	    int count=0;
	    for (int i = 0; i < transferStops.length; i++) {
	    	if(transferStops[i][0]==null || transferStops[i][1]==null) {
	    		break;
	    	}
	    	count++;
		}
	    String[][] lastLines= new String[5][3];
	    for (int i = 0; i < count; i++) {
	    	int distance=0;
	    	String[] splitSource = transferStops[i][0].split(";"); 
		  	String[] splitTarget = transferStops[i][1].split(";");
				boolean isWalkFromSource=false;
				int walkSourceID=-1;
				String line1=null;
		  		String line1ID=null;
		  		String line1Direction=null;
		  		boolean lineTransfer=false;
		  		int lineTransferID=-1;
		  		boolean isWalkToLine=false;
		  		int walkLineID=-1;
		  		String line2=null;
		  		String line2ID=null;
		  		String line2Direction=null;
		  		boolean isWalkToTarget=false;
		  		int walkTargetID=-1;
		  		
		  		if(splitSource.length==2) {
		  			line1=splitSource[0]+";"+splitSource[1];
		  		}
		  		else if(splitSource.length==3) {
		  			line1=splitSource[0]+";"+splitSource[1];
		  			lineTransferID=Integer.parseInt(splitSource[2]);
		  			lineTransfer=true;
		  		}
		  		else if(splitSource.length==4) {
		  			if(splitSource[0].equals("Walk")) {
		  				isWalkFromSource=true;
		  				walkSourceID=Integer.parseInt(splitSource[1]);
		  			}
		  			line1=splitSource[2]+";"+splitSource[3];
		  		}
		  		else if(splitSource.length==5) {
		  			if(splitSource[0].equals("Walk")) {
		  				isWalkFromSource=true;
		  				walkSourceID=Integer.parseInt(splitSource[1]);
		  			}
		  			line1=splitSource[2]+";"+splitSource[3];
		  			lineTransferID=Integer.parseInt(splitSource[4]);
		  			lineTransfer=true;
		  		}
		  		
		  		if(splitTarget.length==2) {
		  			isWalkToTarget=false;
		  		}
		  		if(splitTarget.length==3) {
		  			if(splitTarget[0].equals("Walk")) {
		  				isWalkToLine=true;
		  				walkTargetID=Integer.parseInt(splitTarget[1]);
		  			}
		  		}
		  		else if(splitTarget.length==4) {
		  			line2=splitTarget[1]+";"+splitTarget[2];
		  		}
		  		else if(splitTarget.length==5) {
		  			if(splitTarget[0].equals("Walk")) {
		  				isWalkToLine=true;
		  				walkLineID=Integer.parseInt(splitTarget[1]);
		  			}
		  			line2=splitTarget[2]+";"+splitTarget[3];
		  		}
		  		else if(splitTarget.length==6) {
		  			line2=splitTarget[1]+";"+splitTarget[2];
		  			if(splitTarget[3].equals("Walk")) {
		  				isWalkToTarget=true;
		  				walkTargetID=walkTargetID=Integer.parseInt(splitTarget[4]);
		  			}
		  		}
		  		else if(splitTarget.length==7) {
		  			if(splitTarget[0].equals("Walk")) {
		  				isWalkToLine=true;
		  				walkLineID=Integer.parseInt(splitTarget[1]);
		  			}
		  			line2=splitTarget[2]+";"+splitTarget[3];
		  			if(splitTarget[4].equals("Walk")) {
		  				isWalkToTarget=true;
		  				walkTargetID=Integer.parseInt(splitTarget[5]);
		  			}
		  		}
		  		Dijkstra shortestPath = new Dijkstra();
		  		distance=shortestPath.calculateDist(g1, sourceID, isWalkFromSource, walkSourceID, line1, lineTransferID, lineTransfer, isWalkToLine, walkLineID, line2, isWalkToTarget, walkTargetID, targetID,criteria);
		  		if(distance!=2147483647) {
		  			if(lastLines[0][2]==null || distance<Integer.parseInt(lastLines[0][2])) {
			  			lastLines[4][0]=lastLines[3][0]; lastLines[4][1]=lastLines[3][1]; lastLines[4][2]=lastLines[3][2];
			  			lastLines[3][0]=lastLines[2][0]; lastLines[3][1]=lastLines[2][1]; lastLines[3][2]=lastLines[2][2];
			  			lastLines[2][0]=lastLines[1][0]; lastLines[2][1]=lastLines[1][1]; lastLines[2][2]=lastLines[1][2];
			  			lastLines[1][0]=lastLines[0][0]; lastLines[1][1]=lastLines[0][1]; lastLines[1][2]=lastLines[0][2];
			  			lastLines[0][0]=transferStops[i][0]; lastLines[0][1]=transferStops[i][1]; lastLines[0][2]=String.valueOf(distance);
			  		}
			  		else if(lastLines[1][2]==null || distance<Integer.parseInt(lastLines[1][2])) {
			  			lastLines[4][0]=lastLines[3][0]; lastLines[4][1]=lastLines[3][1]; lastLines[4][2]=lastLines[3][2];
			  			lastLines[3][0]=lastLines[2][0]; lastLines[3][1]=lastLines[2][1]; lastLines[3][2]=lastLines[2][2];
			  			lastLines[2][0]=lastLines[1][0]; lastLines[2][1]=lastLines[1][1]; lastLines[2][2]=lastLines[1][2];
			  			lastLines[1][0]=transferStops[i][0]; lastLines[1][1]=transferStops[i][1]; lastLines[1][2]=String.valueOf(distance);
			  		}
			  		else if(lastLines[2][2]==null || distance<Integer.parseInt(lastLines[2][2])) {
			  			lastLines[4][0]=lastLines[3][0]; lastLines[4][1]=lastLines[3][1]; lastLines[4][2]=lastLines[3][2];
			  			lastLines[3][0]=lastLines[2][0]; lastLines[3][1]=lastLines[2][1]; lastLines[3][2]=lastLines[2][2];
			  			lastLines[2][0]=transferStops[i][0]; lastLines[2][1]=transferStops[i][1]; lastLines[2][2]=String.valueOf(distance);
			  		}
			  		else if(lastLines[3][2]==null || distance<Integer.parseInt(lastLines[3][2])) {
			  			lastLines[4][0]=lastLines[3][0]; lastLines[4][1]=lastLines[3][1]; lastLines[4][2]=lastLines[3][2];
			  			lastLines[3][0]=transferStops[i][0]; lastLines[3][1]=transferStops[i][1]; lastLines[3][2]=String.valueOf(distance);
			  		}
			  		else if(lastLines[4][2]==null || distance<Integer.parseInt(lastLines[4][2])) {
			  			lastLines[4][0]=transferStops[i][0]; lastLines[4][1]=transferStops[i][1]; lastLines[4][2]=String.valueOf(distance);
			  		}
		  		}
		  		
		}
	    for (int j = 0; j < 5; j++) {
	    	if(lastLines[j][0]==null && lastLines[j][1]==null && lastLines[j][2]==null ) {
	    		break;
	    	}
	    	String[] sourcePath = lastLines[j][0].split(";"); 
		  	String[] targetPath = lastLines[j][1].split(";");
		  	boolean isWalkFromSource=false;
			int walkSourceID=-1;
			String line1=null;
	  		String line1ID=null;
	  		String line1Direction=null;
	  		boolean lineTransfer=false;
	  		int lineTransferID=-1;
	  		boolean isWalkToLine=false;
	  		int walkLineID=-1;
	  		String line2=null;
	  		String line2ID=null;
	  		String line2Direction=null;
	  		boolean isWalkToTarget=false;
	  		int walkTargetID=-1;
		  	if(sourcePath.length==2) {
	  			line1=sourcePath[0]+";"+sourcePath[1];
	  			line1ID=sourcePath[0];
	  			line1Direction=sourcePath[1];
	  		}
	  		else if(sourcePath.length==3) {
	  			line1=sourcePath[0]+";"+sourcePath[1];
	  			line1ID=sourcePath[0];
	  			line1Direction=sourcePath[1];
	  			lineTransferID=Integer.parseInt(sourcePath[2]);
	  			lineTransfer=true;
	  		}
	  		else if(sourcePath.length==4) {
	  			if(sourcePath[0].equals("Walk")) {
	  				isWalkFromSource=true;
	  				walkSourceID=Integer.parseInt(sourcePath[1]);
	  			}
	  			line1=sourcePath[2]+";"+sourcePath[3];
	  			line1ID=sourcePath[2];
	  			line1Direction=sourcePath[3];
	  		}
	  		else if(sourcePath.length==5) {
	  			if(sourcePath[0].equals("Walk")) {
	  				isWalkFromSource=true;
	  				walkSourceID=Integer.parseInt(sourcePath[1]);
	  			}
	  			line1=sourcePath[2]+";"+sourcePath[3];
	  			line1ID=sourcePath[2];
	  			line1Direction=sourcePath[3];
	  			lineTransferID=Integer.parseInt(sourcePath[4]);
	  			lineTransfer=true;
	  		}
	  		
	  		if(targetPath.length==3) {
	  			if(targetPath[0].equals("Walk")) {
	  				isWalkToTarget=true;
	  				walkTargetID=Integer.parseInt(targetPath[1]);
	  			}
	  		}
	  		else if(targetPath.length==4) {
	  			line2=targetPath[1]+";"+targetPath[2];
	  			line2ID=targetPath[1];
	  			line2Direction=targetPath[2];
	  		}
	  		else if(targetPath.length==5) {
	  			if(targetPath[0].equals("Walk")) {
	  				isWalkToLine=true;
	  				walkLineID=Integer.parseInt(targetPath[1]);
	  			}
	  			line2=targetPath[2]+";"+targetPath[3];
	  			line2ID=targetPath[2];
	  			line2Direction=targetPath[3];
	  		}
	  		else if(targetPath.length==6) {
	  			line2=targetPath[1]+";"+targetPath[2];
	  			line2ID=targetPath[1];
	  			line2Direction=targetPath[2];
	  			if(targetPath[3].equals("Walk")) {
	  				isWalkToTarget=true;
	  				walkTargetID=walkTargetID=Integer.parseInt(targetPath[4]);
	  			}
	  		}
	  		else if(targetPath.length==7) {
	  			if(targetPath[0].equals("Walk")) {
	  				isWalkToLine=true;
	  				walkLineID=Integer.parseInt(targetPath[1]);
	  			}
	  			line2=targetPath[2]+";"+targetPath[3];
	  			line2Direction=targetPath[3];
	  			line2ID=targetPath[2];
	  			if(targetPath[4].equals("Walk")) {
	  				isWalkToTarget=true;
	  				walkTargetID=Integer.parseInt(targetPath[5]);
	  			}
	  		}
	  		Dijkstra shortestPath = new Dijkstra();
	  		shortestPath.calculateDist(g1, sourceID, isWalkFromSource, walkSourceID, line1, lineTransferID, lineTransfer, isWalkToLine, walkLineID, line2, isWalkToTarget, walkTargetID, targetID,criteria);
	  		if(!isWalkFromSource && !isWalkToTarget && !lineTransfer && !isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". " + line1ID+"   ######");
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
	  			System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
	  			System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
	  			if(criteria==1) {
	  				System.out.println("Stop count: " + g1.getVertex(targetID).getStopCountForReach());
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)g1.getVertex(targetID).getDistance());
	  			}
	  		}
	  		else if(isWalkFromSource && !isWalkToTarget && !lineTransfer && !isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". Walk -" + line1ID+"  ######");
	  			System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
	  			System.out.println("Destination Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(sourceID, walkSourceID).getDistance()+" m");
	  			System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
	  			System.out.println("Origin Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
	  			System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
	  			if(criteria==1) {
	  				System.out.println("Stop count: " + g1.getVertex(targetID).getStopCountForReach());
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(targetID).getDistance()-g1.getEdge(sourceID, walkSourceID).getDistance())+ " m");
	  				
	  			}
	  		}
	  		else if(!isWalkFromSource && isWalkToTarget && !lineTransfer && !isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". "+line1ID + " - Walk   ######" );
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
  				System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
  				System.out.println("Destination Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " + g1.getVertex(walkTargetID).getStopCountForReach());
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " +  (int)(g1.getVertex(walkTargetID).getDistance()) + " m");
	  				
	  			}
  				System.out.println("----------------------------------------------");
  				System.out.println("Line: Walk");
  				System.out.println("Origin Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
  				System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
  				System.out.println("Walk-distance: " + g1.getEdge(walkTargetID, targetID).getDistance()+" m");
	  		}
	  		else if(isWalkFromSource && isWalkToTarget && !lineTransfer && !isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". Walk - " + line1ID+" - Walk    ######");
	  			System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
	  			System.out.println("Destination Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(sourceID, walkSourceID).getDistance()+" m");
	  			System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
	  			System.out.println("Origin Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
	  			System.out.println("Destination Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
	  			if(criteria==1) {
	  				System.out.println("Stop count: " +  g1.getVertex(walkTargetID).getStopCountForReach());
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(walkTargetID).getDistance()-g1.getEdge(sourceID, walkSourceID).getDistance()) + " m");
	  				
	  			}
	  			System.out.println("----------------------------------------------");
	  			System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
	  			System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(walkTargetID, targetID).getDistance()+" m");
	  		
	  		}
	  		else if(!isWalkFromSource && !isWalkToTarget && lineTransfer && !isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". " + line1ID +" - "+ line2ID+"    ######");
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
  				System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
  				System.out.println("Destination Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  g1.getVertex(lineTransferID).getStopCountForReach());
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)g1.getVertex(lineTransferID).getDistance() + " m");
	  				
	  			}
  				System.out.println("----------------------------------------------");
  				System.out.println("Line: " + line2ID + "-"+ lineList.get(Integer.parseInt(line2ID)).getLineName() + " (Direction - " + line2Direction+")");
  				System.out.println("Origin Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(targetID).getStopCountForReach()- g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(targetID).getDistance()-g1.getVertex(lineTransferID).getDistance()) + " m");
	  				
	  			}
	  		}
	  		else if(isWalkFromSource && !isWalkToTarget && lineTransfer && !isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". Walk - " + line1ID +" - "+ line2ID+"    ######");
	  			System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
	  			System.out.println("Destination Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(sourceID, walkSourceID).getDistance()+" m");
	  			System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
  				System.out.println("Origin Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
  				System.out.println("Destination Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  g1.getVertex(lineTransferID).getStopCountForReach());
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(lineTransferID).getDistance()-g1.getEdge(sourceID, walkSourceID).getDistance()) + " m");
	  				
	  			}
  				System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line2ID + "-"+ lineList.get(Integer.parseInt(line2ID)).getLineName() + " (Direction - " + line2Direction+")");
  				System.out.println("Origin Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(targetID).getStopCountForReach()-g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(targetID).getDistance()-g1.getVertex(lineTransferID).getDistance()-g1.getEdge(sourceID, walkSourceID).getDistance()) + " m");
	  				
	  			}
	  		}
	  		else if(!isWalkFromSource && isWalkToTarget && lineTransfer && !isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". " + line1ID +" - "+ line2ID+" - Walk    ######");
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
  				System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
  				System.out.println("Destination Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  g1.getVertex(lineTransferID).getStopCountForReach());
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(lineTransferID).getDistance()) + " m");
	  			}
  				System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line2ID + "-"+ lineList.get(Integer.parseInt(line2ID)).getLineName() + " (Direction - " + line2Direction+")");
  				System.out.println("Origin Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				System.out.println("Destination Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(walkTargetID).getStopCountForReach()-g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(walkTargetID).getDistance()-g1.getVertex(lineTransferID).getDistance()) + " m");
	  				
	  			}
  				System.out.println("----------------------------------------------");
  				System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
	  			System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(walkTargetID, targetID).getDistance()+" m");
	  		}
	  		else if(isWalkFromSource && isWalkToTarget && lineTransfer && !isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". Walk - " + line1ID +" - "+ line2ID+" - Walk    ######");
	  			System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
	  			System.out.println("Destination Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(sourceID, walkSourceID).getDistance()+" m");
	  			System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
  				System.out.println("Origin Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
  				System.out.println("Destination Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  g1.getVertex(lineTransferID).getStopCountForReach());
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(lineTransferID).getDistance()-g1.getEdge(sourceID, walkSourceID).getDistance()) + " m");
	  				
	  			}
  				System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line2ID + "-"+ lineList.get(Integer.parseInt(line2ID)).getLineName() + " (Direction - " + line2Direction+")");
  				System.out.println("Origin Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				System.out.println("Destination Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(walkTargetID).getStopCountForReach()-g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(walkTargetID).getDistance()-g1.getVertex(lineTransferID).getDistance()-g1.getEdge(sourceID, walkSourceID).getDistance()) + " m");
	  			}
  				System.out.println("----------------------------------------------");
  				System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
	  			System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(walkTargetID, targetID).getDistance()+" m");
	  		}
	  		else if(!isWalkFromSource && !isWalkToTarget && lineTransfer && isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". " + line1ID +" - Walk - "+ line2ID+"   ######");
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
  				System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
  				System.out.println("Destination Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(lineTransferID).getDistance()) + " m");
	  			}
  				System.out.println("----------------------------------------------");
  				System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
	  			System.out.println("Destination Stop: " + walkLineID + " - " + g1.getVertex(walkLineID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(lineTransferID, walkLineID).getDistance()+" m");
	  			System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line2ID + "-"+ lineList.get(Integer.parseInt(line2ID)).getLineName() + " (Direction - " + line2Direction+")");
  				System.out.println("Origin Stop: " + walkLineID + " - " + g1.getVertex(walkLineID).getStopName());
  				System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(targetID).getStopCountForReach()-g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(targetID).getDistance()-g1.getVertex(lineTransferID).getDistance()-g1.getEdge(lineTransferID, walkLineID).getDistance()) + " m");
	  			}
	  		}
	  		else if(isWalkFromSource && !isWalkToTarget && lineTransfer && isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". Walk - " + line1ID +" - Walk - "+ line2ID+"   ######");
	  			System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
	  			System.out.println("Destination Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(sourceID, walkSourceID).getDistance()+" m");
	  			System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
  				System.out.println("Origin Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
  				System.out.println("Destination Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(lineTransferID).getDistance()-g1.getEdge(sourceID, walkSourceID).getDistance()) + " m");
	  			}
  				System.out.println("----------------------------------------------");
  				System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
	  			System.out.println("Destination Stop: " + walkLineID + " - " + g1.getVertex(walkLineID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(lineTransferID, walkLineID).getDistance()+" m");
	  			System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line2ID + "-"+ lineList.get(Integer.parseInt(line2ID)).getLineName() + " (Direction - " + line2Direction+")");
  				System.out.println("Origin Stop: " + walkLineID + " - " + g1.getVertex(walkLineID).getStopName());
  				System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(targetID).getStopCountForReach()-g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(targetID).getDistance()-g1.getVertex(lineTransferID).getDistance()-g1.getEdge(sourceID, walkSourceID).getDistance()-g1.getEdge(lineTransferID, walkLineID).getDistance()) + " m");
	  			}
	  		}
	  		else if(!isWalkFromSource && isWalkToTarget && lineTransfer && isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". " + line1ID +" - Walk - "+ line2ID+" - Walk -   ######");
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
  				System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
  				System.out.println("Destination Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(lineTransferID).getDistance()) + " m");
	  			}
  				System.out.println("----------------------------------------------");
  				System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
	  			System.out.println("Destination Stop: " + walkLineID + " - " + g1.getVertex(walkLineID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(lineTransferID, walkLineID).getDistance()+" m");
	  			System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line2ID + "-"+ lineList.get(Integer.parseInt(line2ID)).getLineName() + " (Direction - " + line2Direction+")");
  				System.out.println("Origin Stop: " + walkLineID + " - " + g1.getVertex(walkLineID).getStopName());
  				System.out.println("Destination Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(walkTargetID).getStopCountForReach()-g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(walkTargetID).getDistance()-g1.getVertex(lineTransferID).getDistance()-g1.getEdge(lineTransferID, walkLineID).getDistance()-g1.getEdge(lineTransferID, walkLineID).getDistance()) + " m");
	  			}
  				System.out.println("----------------------------------------------");
  				System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
	  			System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(walkTargetID, targetID).getDistance()+" m");
	  		}
	  		else if(isWalkFromSource && isWalkToTarget && lineTransfer && isWalkToLine) {
	  			System.out.println("### PATH "+ (j+1) + ". Walk - " + line1ID +" - Walk - "+ line2ID+" - Walk -   ######");
	  			System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + sourceID + " - " + g1.getVertex(sourceID).getStopName());
	  			System.out.println("Destination Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(sourceID, walkSourceID).getDistance()+" m");
	  			System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line1ID + "-"+ lineList.get(Integer.parseInt(line1ID)).getLineName() + " (Direction - " + line1Direction+")");
  				System.out.println("Origin Stop: " + walkSourceID + " - " + g1.getVertex(walkSourceID).getStopName());
  				System.out.println("Destination Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(lineTransferID).getDistance()-g1.getEdge(sourceID, walkSourceID).getDistance()) + " m");
	  			}
  				System.out.println("----------------------------------------------");
  				System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + lineTransferID + " - " + g1.getVertex(lineTransferID).getStopName());
	  			System.out.println("Destination Stop: " + walkLineID + " - " + g1.getVertex(walkLineID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(lineTransferID, walkLineID).getDistance()+" m");
	  			System.out.println("----------------------------------------------");
	  			System.out.println("Line: " + line2ID + "-"+ lineList.get(Integer.parseInt(line2ID)).getLineName() + " (Direction - " + line2Direction+")");
  				System.out.println("Origin Stop: " + walkLineID + " - " + g1.getVertex(walkLineID).getStopName());
  				System.out.println("Destination Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
  				if(criteria==1) {
	  				System.out.println("Stop count: " +  (g1.getVertex(walkTargetID).getStopCountForReach()-g1.getVertex(lineTransferID).getStopCountForReach()));
	  			}
	  			else if(criteria==2) {
	  				System.out.println("Distance: " + (int)(g1.getVertex(walkTargetID).getDistance()-g1.getVertex(lineTransferID).getDistance()-g1.getEdge(sourceID, walkSourceID).getDistance()-g1.getEdge(lineTransferID, walkLineID).getDistance()) + " m");
	  			}
  				System.out.println("----------------------------------------------");
  				System.out.println("Line: Walk");
	  			System.out.println("Origin Stop: " + walkTargetID + " - " + g1.getVertex(walkTargetID).getStopName());
	  			System.out.println("Destination Stop: " + targetID + " - " + g1.getVertex(targetID).getStopName());
	  			System.out.println("Walk-distance: " + g1.getEdge(walkTargetID, targetID).getDistance()+" m");
	  		}
	  		System.out.println();
	  		System.out.println(shortestPath.getShortestPathTo(g1.getVertex(targetID)));
	  		System.out.println();
		}
	}
}
