package com.company;
// Java implementation of Dijkstra's Algorithm
// using Priority Queue
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Class to represent a node in the graph
class Node implements Comparator<Node> {
    public int node;
    public int cost;

    public Node()
    {
    }

    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}
class DPQ {
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V; // Number of vertices
    List<List<Node>> adj;

    public DPQ(int V)
    {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    public int getElem(int i){
        return this.dist[i];
    }

    public int getDistSize(){
        return this.V;
    }

    public void dijkstra(List<List<Node> > adj, int src)
    {
        this.adj = adj;

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        pq.add(new Node(src, 0));

        dist[src] = 0;
        while (settled.size() != V) {
            if(pq.isEmpty())
                return ;

            int u = pq.remove().node;
            settled.add(u);
            e_Neighbours(u);
        }
    }

    private void e_Neighbours(int u)
    {
        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;

                // Add the current node to the queue
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

}

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int source = 1;

        List<List<Node> > adj = new ArrayList<List<Node> >();


        String infile1 = "input.txt";
        File fin1 = new File(infile1);
        Scanner myReader1 = new Scanner(fin1);
        int size = Integer.parseInt(myReader1.next())+1;
        for (int i = 0; i < size; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }
        int countOfEdge = Integer.parseInt(myReader1.next());
        myReader1.nextLine();
        while (myReader1.hasNext()){
            //StringTokenizer tmp = new StringTokenizer(myReader1.nextLine(), " ");
            int val1 = Integer.parseInt(myReader1.next());
            int val2 = Integer.parseInt(myReader1.next());
            int val3 = Integer.parseInt(myReader1.next());
            adj.get(val1).add(new Node(val2,val3));
           // adj.get(Integer.parseInt(tmp.nextToken())).add(new Node(Integer.parseInt(tmp.nextToken()),Integer.parseInt(tmp.nextToken())));
        }
        myReader1.close();

        /*adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));*/

        DPQ dpq = new DPQ(size);
        dpq.dijkstra(adj, source);

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.getDistSize(); i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.getElem(i));
    }
}
