package com.company;
import java.util.LinkedList;
import java.io.*;
import java.lang.*;
import java.util.*;

class MaxFlow {
    private int V = 6;

    public void setV(int v) {
        V = v;
    }

    boolean bfs(int rGraph[][], int s, int t, int parent[])
    {

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue
                = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false
                        && rGraph[u][v] > 0) {
                    // If we find a connection to the sink
                    // node, then there is no point in BFS
                    // anymore We just have to set its parent
                    // and can return true
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // We didn't reach sink in BFS starting from source,
        // so return false
        return false;
    }

    // Returns the maximum flow from s to t in the given
    // graph
    int fordFulkerson(int graph[][], int s, int t)
    {
        int u, v;

        // Create a residual graph and fill the residual
        // graph with given capacities in the original graph
        // as residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0; // There is no flow initially

        // Augment the flow while there is path from source
        // to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow
                        = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }

    // Driver program to test above functions
    public static void main(String[] args)
            throws java.lang.Exception
    {
        // Let us create a graph shown in the above example

    }
}
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String infile1 = "input.txt";
        File fin1 = new File(infile1);
        Scanner myReader1 = new Scanner(fin1);
        int size = 0;
        size = Integer.parseInt(myReader1.next());
        int graph[][] = new int[size][size];
        myReader1.nextLine();
        for(int i = 0; i < size; i++) {
            StringTokenizer tmp = new StringTokenizer(myReader1.nextLine(), " ");
            while (tmp.hasMoreTokens()){
                int node = Integer.parseInt(tmp.nextToken());
                if(node != 0){
                    graph[i][node-1] = 1;
                }
            }
        }

        /*int graph[][] = new int[][] {
                { 0, 16, 13, 0, 0, 0 }, { 0, 0, 10, 12, 0, 0 },
                { 0, 4, 0, 0, 14, 0 },  { 0, 0, 9, 0, 0, 20 },
                { 0, 0, 0, 7, 0, 4 },   { 0, 0, 0, 0, 0, 0 }
        };*/
        MaxFlow m = new MaxFlow();
        m.setV(size);
        System.out.println("The maximum possible flow is "
                + m.fordFulkerson(graph, Integer.parseInt(myReader1.next())-1, Integer.parseInt(myReader1.next())-1));
        myReader1.close();
    }
}
