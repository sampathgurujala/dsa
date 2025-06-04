import java.util.*;
class Edge
{
    char n1;
    char n2;
    int weight;
    Edge(char n1,char n2, int weight)
    {
        this.n1=n1;
        this.n2=n2;
        this.weight = weight;
    }
}
public class Prims {
    public static Map<Character, List<Edge>> makeGraph()
    {
        Map<Character,List<Edge>> graph = new HashMap<>();
        graph.put('A',new ArrayList<>(List.of(new Edge('A','D',3), new Edge('A','C',3), new Edge('A','B',2))));
        graph.put('B',new ArrayList<>(List.of(new Edge('B','A',2), new Edge('B','C',4), new Edge('B','E',3))));
        graph.put('C',new ArrayList<>(List.of(new Edge('C','A',3), new Edge('C','D',5), new Edge('C','F',6), new Edge('C','E',1), new Edge('C','B',4))));
        graph.put('D',new ArrayList<>(List.of(new Edge('D','A',3), new Edge('D','C',5), new Edge('D','F',7))));
        graph.put('E',new ArrayList<>(List.of(new Edge('E','F',8), new Edge('E','C',1), new Edge('E','B',3))));
        graph.put('F',new ArrayList<>(List.of(new Edge('F','G',9), new Edge('F','E',8), new Edge('F','C',6),new Edge('F','D',7))));
        graph.put('G',new ArrayList<>(List.of(new Edge('G','F',9))));
        return graph;
    }
    public static List<Edge> prims(Map<Character,List<Edge>> graph, char start)
    {
        List<Edge> msp = new ArrayList<>();
        Comparator<Edge> cmp = (a,b)->{return Integer.compare(a.weight,b.weight);};
        PriorityQueue<Edge> pq= new PriorityQueue<>(cmp);
        int n = graph.size();
        Set<Character> visited = new HashSet<>();
        int totalCost=0;
        for(Edge e: graph.get(start))
        {
            pq.offer(e);
        }
        visited.add(start);
        while(!pq.isEmpty())
        {
            Edge e = pq.poll();
            if(visited.contains(e.n1) && visited.contains(e.n2))
                continue;
            msp.add(e);
            totalCost+=e.weight;
            char currNode=(!visited.contains(e.n1))?e.n1:e.n2;
            visited.add(currNode);
            for(Edge edge: graph.get(currNode))
            {
                if(!visited.contains(edge.n2))
                    pq.offer(edge);
            }
        }
        System.out.println("Total cost: "+ totalCost);
        return msp;

    }
    public static void printMST(List<Edge> mst)
    {
        for(Edge e: mst)
        {
            System.out.println(e.n1+"->"+e.n2+" | Weight: "+e.weight);
        }
    }
    public static void main(String[] args)
    {
        Map<Character, List<Edge>> graph = makeGraph();
        List<Edge> mst = prims(graph,'A');
        printMST(mst);
    }
}
/*
-Prim’s Algorithm is a greedy algorithm used to find the Minimum Spanning Tree (MST) for a connected, undirected, weighted graph.
i/p: A connected, undirected, weighted graph G(E,V) which may/may not have cycles.
o/p: Minimum Spanning Tree

 1. Spanning Tree
	•	A spanning tree of a graph is a subset of edges that:
	•	Connects all the vertices (i.e., it spans the graph),
	•	Contains no cycles (i.e., it’s a tree),
	•	Has exactly (V − 1) edges for a graph with V vertices.
	•   There can be multiple spanning trees in a graph.
   - No of Possible Spanning Trees for a G(E,V) : (|E|)C(|V|-1) - (number of cycles formed)
   - Spanning Tree Condition: S(E',V') where |E'|= |V|-1, V'=V

 2. Minimum Spanning Tree (MST)
	•	An MST is a spanning tree with the minimum possible total edge weight.
	•	It is used when edges have weights/costs, and you want to minimize the total cost of connecting all vertices.
	•	There can also be multiple MSTs if more than one tree has the same minimum weight.

Simply, Minimum Spanning Tree:  connect all vertices in the graph with the minimum total edge weight, without forming cycles.

Algo:
1.	Initialize:
	•	Start with any vertex (say A).
	•	Maintain a priority queue (min-heap) to get the edge with the minimum weight.
	•	Use a visited set to track which nodes are already included in the MST.
2.	Push all edges from the starting vertex into the priority queue.
3.	While the queue is not empty:
	•	Pop the edge with the smallest weight.
	•	If both endpoints of the edge are already visited, skip it (to avoid cycles).
	•	Otherwise, include the edge in the MST(this edge is the Smallest UnVisted Edge among all the adjacent edges of Visited Vertices).
	•	Mark the new node as visited.
	•	Add all edges from the new node to the queue (only if the destination is unvisited).
4.	Repeat until all vertices are included in the MST.

Data Structures Used:
	•	Priority Queue (Min-Heap) for edge selection
	•	Set for tracking visited nodes
	•	List to store MST edges

 */