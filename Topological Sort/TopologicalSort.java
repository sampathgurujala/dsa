import java.util.*;

public class TopologicalSort
{   //Leetcode Course Scheduler 2 Problem
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] outDegree= new int[numCourses];
        Map<Integer,List<Integer>> dependants=new HashMap<>();
        for(int[] prereq: prerequisites)
        {
            List<Integer> list =dependants.getOrDefault(prereq[1], new ArrayList<Integer>());
            list.add(prereq[0]);
            dependants.put(prereq[1],list);
            outDegree[prereq[0]]++;
        }
        Queue<Integer> q=new LinkedList();
        for(int i=0;i<numCourses;i++)
        {
            if(outDegree[i]==0)
                q.offer(i);
        }
        int canDo = 0;
        int[] order = new int[numCourses];
        while(!q.isEmpty())
        {
            int curr = q.poll();
            order[canDo] = curr;
            canDo++;
            if(dependants.containsKey(curr)){
                List<Integer> dep = dependants.get(curr);
                for(int course : dep)
                {
                    outDegree[course]--;
                    if(outDegree[course]==0)
                        q.offer(course);
                }
            }

        }
        if(canDo!=numCourses)
            return new int[0];

        return order;
    }
    public static void main(String[] args)
    {
        TopologicalSort ts = new TopologicalSort();

        int numCourses = 4;
        int[][] prerequisites = {
                {1, 0},     // Course 1 is dependent on Course 0. Similarly, for below inputs
                {2, 0},
                {3, 1},
                {3, 2}
//                         0
//                         ^
//                        / \
//                       1   2
//                       ^   ^
//                        \ /
//                         3
        };

        int[] order = ts.findOrder(numCourses, prerequisites);

        if (order.length == 0) {
            System.out.println("No valid course order exists.");
        } else {
            System.out.print("Course Order: ");
            for (int course : order) {
                System.out.print(course + " ");
            }
            System.out.println();
        }
        return;
    }
}
/*
function topologicalSort(V, prerequisites):
    indegree = [0] * V
    adj = [[] for _ in range(V)]

    for (u, v) in prerequisites:
        adj[v].append(u)
        indegree[u] += 1

    queue = []
    for i in range(V):
        if indegree[i] == 0:
            queue.append(i)

    topo_order = []
    while queue:
        node = queue.pop(0)
        topo_order.append(node)
        for neighbor in adj[node]:
            indegree[neighbor] -= 1
            if indegree[neighbor] == 0:
                queue.append(neighbor)

    if len(topo_order) == V:
        return topo_order
    else:
        return []  // Cycle exists

Time Complexity: O(V + E)
    Explanation:
        •	Building the graph (adjacency list + out-degree array):
            O(E) — for each prerequisite pair.
	    •	Initializing the queue and processing nodes:
            O(V) — we process each node (course) once.
        •	Processing all edges from each node:
            O(E) — each edge (dependency) is processed once.

Space Complexity: O(V+E)
    Explanation:
        •	Adjacency list (dependants) stores up to E edges.
        •	Out-degree array takes O(V) space.
        •	Queue and result array order take O(V) space.
*/