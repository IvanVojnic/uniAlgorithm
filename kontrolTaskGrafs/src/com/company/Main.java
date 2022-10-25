package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class Node {
    int n;
    String name;
    boolean visited;
    int flag;

    Node(int n, String name) {
        this.n = n;
        this.name = name;
        visited = false;
    }

    void visit() {
        visited = true;
    }

    public int getN(){
        return this.n;
    }

    public boolean isVisited() {
        return visited;
    }
}

class Graph {


    private HashMap<Node, LinkedList<Node>> adjacencyMap;
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        adjacencyMap = new HashMap<>();
    }
    public void addEdgeHelper(Node a, Node b) {
        LinkedList<Node> tmp = adjacencyMap.get(a);

        if (tmp != null) {
            tmp.remove(b);
        }
        else tmp = new LinkedList<>();
        tmp.add(b);
        adjacencyMap.put(a,tmp);
    }

    public void addEdge(Node source, Node destination) {

        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);

        if (!adjacencyMap.keySet().contains(destination))
            adjacencyMap.put(destination, null);

        addEdgeHelper(source, destination);

        if (!directed) {
            addEdgeHelper(destination, source);
        }
    }
    void breadthFirstSearch(Node node) {
        try {
            File fout = new File("output.txt");
            FileWriter myWriter = new FileWriter(fout);
            if (node == null)
                return;
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(node);

            while (!queue.isEmpty()) {
                Node currentFirst = queue.removeFirst();
                if (currentFirst.isVisited())
                    continue;
                currentFirst.visit();
                queue.getFirst().flag = Integer.parseInt(currentFirst.name)+1;
                myWriter.write(String.valueOf(Integer.parseInt(currentFirst.name)+1) + " ");
                LinkedList<Node> allNeighbors = adjacencyMap.get(currentFirst);

                if (allNeighbors == null)
                    continue;

                for (Node neighbor : allNeighbors) {
                    if (!neighbor.isVisited()) {
                        queue.add(neighbor);
                    }
                }
            }
            myWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    void print(){
        try {
            File fout = new File("output.txt");
            FileWriter myWriter = new FileWriter(fout);
            for(int i =0; i < this.adjacencyMap.size();i++){
                myWriter.write(String.valueOf(adjacencyMap.get(i)));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    void breadthFirstSearchModified(Node node) {
        breadthFirstSearch(node);

        for (Node n : adjacencyMap.keySet()) {
            if (!n.isVisited()) {
                breadthFirstSearch(n);
            }
        }
    }
}


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph(false);
        String infile1 = "input.txt";
        File fin1 = new File(infile1);
        Scanner myReader1 = new Scanner(fin1);
        int size = 0;
        if(myReader1.hasNext()){
            size = Integer.parseInt(myReader1.next());
        } else {
            return;
        }
        int[][] myMatrix = new int[size][size];
        Node[] NodesArr = new Node[size];
        for(int i = 0; i < size; i++){
            NodesArr[i] = new Node(i, String.valueOf(i));
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                myMatrix[i][j] = Integer.parseInt(myReader1.next());
            }
        }
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(myMatrix[i][j] == 1){
                    graph.addEdge(NodesArr[i],NodesArr[j]);
                }
            }
        }

        myReader1.close();
        graph.breadthFirstSearchModified(NodesArr[0]);

    }
}
