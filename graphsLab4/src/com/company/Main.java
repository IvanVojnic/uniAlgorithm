package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

class Node{
    private int NodeVal;

    public Node(int NodeVal){
        this.NodeVal = NodeVal;
    }

    public int getNodeVal() {
        return NodeVal;
    }

    public void setNodeVal(int nodeVal) {
        NodeVal = nodeVal;
    }
}

class Graph{
    private Node[][] myGraph;
    private int GraphSize;
    public Graph(int size){
        this.myGraph = new Node[size][size];
        this.GraphSize = size;
        initialize();
    }
    public void initialize(){
        for(int i = 0; i < this.GraphSize; i++){
            for( int j = 0; j < this.GraphSize; j++){
                myGraph[i][j] = new Node(0);
            }
        }
    }
    public void addNodeGraph(int parentNode, StringTokenizer childNodes){
        while (childNodes.hasMoreTokens()){
            int tmp = Integer.parseInt(childNodes.nextToken());
            if(tmp != 0){
                this.myGraph[parentNode][tmp-1] = new Node(1);
            }
        }

    }
    public void print(){
        for(int i = 0; i < this.GraphSize; i++){
            for(int j = 0; j < this.GraphSize; j++){
                System.out.print(this.myGraph[i][j].getNodeVal() + " ");
            }
            System.out.println();
        }
    }

    boolean bfs(Node Graph[][], int s, int t, int p[]) {
        boolean visited[] = new boolean[this.GraphSize];
        for (int i = 0; i < this.GraphSize; ++i) {
            visited[i] = false;
        }

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        p[s] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < this.GraphSize; v++) {
                if (visited[v] == false && Graph[u][v].getNodeVal() > 0) {
                    queue.add(v);
                    p[v] = u;
                    visited[v] = true;
                }
            }
        }

        return (visited[t] == true);
    }

    String fordFulkerson(int s, int t) {
        int i, j;
        Node[][] resGraph = new Node[this.GraphSize][this.GraphSize];
        for(int u = 0; u < this.GraphSize; u++){
            for( int k = 0; k < this.GraphSize; k++){
                resGraph[u][k] = new Node(0);
            }
        }
        for( i = 0; i < this.GraphSize; i++){
            for(j = 0; j < this.GraphSize; j++){
                resGraph[i][j].setNodeVal(this.myGraph[i][j].getNodeVal());
            }
        }
        int p[] = new int[this.GraphSize];

        int max_flow = 0;


        while (bfs(resGraph, s, t, p)) {
            int path_flow = Integer.MAX_VALUE;
            for (j = t; j != s; j = p[j]) {
                i = p[j];
                path_flow = Math.min(path_flow, resGraph[i][j].getNodeVal());
            }

            for (j = t; j != s; j = p[j]) {
                i = p[j];
                resGraph[i][j].setNodeVal(resGraph[i][j].getNodeVal()-path_flow);
                resGraph[j][i].setNodeVal(resGraph[j][i].getNodeVal()+path_flow);
            }

            max_flow += path_flow;
        }

        return String.valueOf(max_flow);
    }
}


public class Main {

    public static void main(String[] args) throws IOException {
        String infile1 = "input.in";
        File fin1 = new File(infile1);
        Scanner myReader1 = new Scanner(fin1);
        int size = 0;
        size = Integer.parseInt(myReader1.next());
        Graph graph = new Graph(size);
        myReader1.nextLine();
        for(int i = 0; i < size; i++) {
            StringTokenizer tmp = new StringTokenizer(myReader1.nextLine(), " ");
            graph.addNodeGraph(i, tmp);
        }
        graph.print();

        File file = new File("output.out");
        FileWriter myFileWriter = null;
        myFileWriter = new FileWriter(file, false);
        BufferedWriter myWriter = new BufferedWriter(myFileWriter);
        myWriter.write(graph.fordFulkerson(Integer.parseInt(myReader1.next())-1,Integer.parseInt(myReader1.next())-1));
        myWriter.flush();
    }
}
