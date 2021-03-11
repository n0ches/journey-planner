A journey planner (or trip planner) is a specialized electronic search engine that finds one or 
more journey (trip) suggestions between an origin and a destination. This system assists 
travelers for planning their journey. 

Izmir is Turkey's the third largest city and has four existing public transport companies 
including ESHOT for bus transportation, Izmir Metro Inc. for metro, IZBAN Inc. for the rail 
system and IZDENIZ for maritime transport. All of these systems form a complete 
transportation network with 319 lines and 6708 stops/stations.

Dijkstra's algorithm forms the basis of modern journey planner search algorithms and 
provides an optimal solution to simple searches. While planning routes in such a combined 
network, some constraints as switching the mode of transportation frequently or unacceptable 
transfer counts must be considered. For instance, Table 1 shows the result path produced by the 
original Dijkstra’s algorithm for a query from the origin stop ‘10036-Konak’ to the destination 
stop ‘40120-Tınaztepe Kampüs Son Durak’. The path consists of 23 stops and 12 different lines, 
thus 11 transfers are required to complete the journey. Producing such an unefficient path 
should be avoided.


# Neighbor Stops
To make point to point queries in a transportation network, some sort of walk-distance edges 
are required, so any stage of the journey can be covered by walk or passengers may walk 
between the stops while transferring between two different lines. Walk-distance edges are also 
providing to link each of the transportation networks (bus, train, metro, ferry etc.). Two stops u
and v are labeled as neighbor stops by adding walk-distance edges between them, if a road 
segment is available to pedestrians and dist(u, v) is less than the maximum allowed walking 
distance.

# Representation of the Transportation Graph
In this assignment, you are expected to represent each stop (bus, train, metro, ferry) as a node 
and to represent each line connecting two consecutive stops in a certain direction as a directed 
edge to form a transportation graph

# Path Finding

    - Direct Routes:
        Direct paths start with the origin stop and reach to the destination stop with no transfer.
        
    - Routes Containing One Transfer:
        These paths start with an origin line (a line use the origin stop) and ends up in a destination line
        (a line use the destination stop). Origin and destination lines must be connected in a transfer 
        stop or a walk must exist between two lines as illustrated in Figure 2. Two consecutive walk is 
        not allowed. A path can contains max. three walks (at the beginning, in middle, and at the end 
        of the path).
       
    - Journey Planner Search Engine 
        Develop a modified Dijkstra's algorithm to find alternative journeys between the given origin 
        and destination stops. The alternative paths must include only direct routes and the routes 
        containing one transfer. You should modify the original Dijkstra's algorithm to limit transfers. 
        Several runs of the algorithm could be required to obtain enough alternative journeys. 
        You should consider two optimization criteria: fewer stops and minimum distance. In the first 
        case, you should use equal edge weights in the graph. In the second case, you should use the 
        given distance values (in meters) for the consecutive stops.
        
        
        
