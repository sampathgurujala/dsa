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

public class Kruskal
{
   static Map<Character,Character> parent = new HashMap<>();
   static Map<Character,Integer> rank = new HashMap<>();
   static Set<String> seen = new HashSet<>();

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
    public static char find(char v)
    {
       if(parent.get(v)!=v) //path compression
       {
           parent.put(v,find(parent.get(v)));
       }
        return parent.get(v);
    }
    public static void union(char n1,char n2)
    {
        char x= find(n1);
        char y= find(n2);
        if(x==y)
            return;
        if (rank.get(x) > rank.get(y)) {
            parent.put(y, x);
        } else if (rank.get(x) < rank.get(y)) {
            parent.put(x, y);
        } else {
            parent.put(x, y);
            rank.put(y, rank.get(y) + 1); // y becomes parent
        }
        return;
    }
    public static List<Edge> kruskal(List<Edge> edgeList)
    {

        Collections.sort(edgeList,(a,b)->Integer.compare(a.weight,b.weight)); // ElogE
        List<Edge> mst= new ArrayList<>();
        int totalCost = 0;

        for(Edge e: edgeList)
        {

            if(find(e.n1) != find(e.n2))
            {
                mst.add(e);
                totalCost+=e.weight;
                union(e.n1,e.n2);
            }
        }
        System.out.println("Total Cost: "+totalCost);
        return mst;

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
        List<Edge> edgeList = new ArrayList<>();
        Set<Character> vertices = graph.keySet();

        for(char v: vertices )
        {
            for(Edge e: graph.get(v))
            {
                String key = e.n1 < e.n2 ? "" + e.n1 + e.n2 : "" + e.n2 + e.n1;
                if (!seen.contains(key)) {
                    seen.add(key);
                    edgeList.add(e);
                }
            }
            parent.put(v,v);        // initializing parent of each vertex with parent as itself
            rank.put(v,0);          // initializing rank of each vertex to be zero
        }

        List<Edge> mst = kruskal(edgeList);
        printMST(mst);
//        System.out.println(parent);
//        System.out.println(rank);
    }
}

/*
Algo for kruskal(G, w):
Input:    A connected undirected graph G = (V, E) with edge weights we
Output:   A minimum spanning tree defined by the edges X

for all u ∈ V:
    makeset(u)

X = {}
Sort the edges E by weight
for all edges {u,v} ∈ E, in increasing order of weight:
    if find(u) ≠ find(v):
        add edge {u,v} to X
        union(u,v)

----------------------------------------------------

procedure makeset(x)
π(x) = x    //holds  parent of x
rank(x) = 0 //

----------------------------------------------------

function find(x)
while x ≠ π(x): x = π(x)
return x

----------------------------------------------------

procedure union(x,y)
rx = find(x)
ry = find(y)
if rx = ry: return
if rank(rx) > rank(ry):
    π(ry) = rx
else:
    π(rx) = ry
    if rank(rx) = rank(ry): rank(ry) = rank(ry) + 1

Time Complexity: O(ElogE)   // Sorting of all edges takes nlogn , and Traversing through all Edges  take |E|=|V|
Space Complexity: O(E)      // edgelist holds all edges and A
 */