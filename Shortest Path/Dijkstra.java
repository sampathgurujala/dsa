import java.util.*;

public class Dijkstra
{
	public static int[] dijkstra(int[][] edges, int source, int n)
	{
		Map<Integer,List<int[]>> adj = new HashMap<>();
		for(int[] edge: edges)
		{
			List<int[]> neighbours= adj.getOrDefault(edge[0],new ArrayList<int[]>());
			neighbours.add(new int[]{edge[1],edge[2]});
			adj.put(edge[0], neighbours);
		}
		int[] dist =new int[n+1];
		int[] parent =new int[n+1];
		for(int i=1;i<=n;i++) {
			dist[i] = Integer.MAX_VALUE;
			parent[i] = i;
		}
		dist[source] = 0;

		boolean[] visited= new boolean[n+1];
		Comparator<int[]> cmp = (a,b)->{return Integer.compare(a[1],b[1]);};
		PriorityQueue<int[]> pq=new PriorityQueue<>(cmp);

		pq.offer(new int[]{source,0});
		while(!pq.isEmpty())
		{
			int[] edge = pq.poll();
			int curr=edge[0],currDist=edge[1];
			if(visited[curr])
				continue;
			visited[curr] = true;

			List<int[]> reachables = adj.getOrDefault(curr, new ArrayList<int[]>());
			for(int[] neigh: reachables)
			{
				int v = neigh[0];
				int distToV = neigh[1];
				if(!visited[v] && dist[v] > currDist + distToV )
				{
					dist[v] = currDist + distToV;
					parent[v] = curr;
					pq.offer(new int[]{v,dist[v]});
				}
			}
		}
		return dist;
	}
	public static void main(String[] args)
	{
		int[][] edges= new int[3][3];
		edges[0] = new int[]{2,1,1};
		edges[1] = new int[]{2,3,1};
		edges[2] = new int[]{3,4,1};
		int n= 4;
		int[] dist = dijkstra(edges,2,n);
		for(int i=1;i<=n;i++)
		{
			System.out.println("Vertex : "+i+" | Shortest Distance :" + dist[i]);
		}
 	}
}



/*
 Dijkstra’s Algorithm Overview
	•	Dijkstra finds the shortest path from a source node to all other nodes in a graph with non-negative edge weights.
	•	It works for both connected and disconnected graphs, but:
        •	In a connected graph, it will find the shortest path to every node.
        •	In a disconnected graph, it will only find shortest paths to reachable nodes. The unreachable ones will stay at infinite (∞) distance.
input:- Directed/Undirected Graph(E,V) with +ve edge weights, source vertex U
output: Distance array with shortest distances to reach a vertex 'v' from source 'u' , where v belongs to |V|
Algo:
 function Dijkstra(Graph, source):
    dist[] ← array of size |V|, all initialized to ∞
    dist[source] ← 0
    visited[] ← array of size |V|, all false
    priorityQueue ← min-heap with (source, 0)

    while priorityQueue is not empty:
        (u, d) ← priorityQueue.poll()
        if visited[u]: continue
        visited[u] ← true

        for each neighbor v of u:
            if not visited[v] and dist[u] + weight(u, v) < dist[v]:
                dist[v] ← dist[u] + weight(u, v)
                priorityQueue.add((v, dist[v]))

    return dist
Time Complexity: O(|V|^2) , can be O(|E| + |V|logV) if fiboncacci Queue is used
Space Complexity: O(|V|)
 */