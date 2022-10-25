package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

class Node{
    private boolean visited = false;
    private int vertex;
    private int flag = -1;

    Node(int vertex, boolean visited, int flag){
        this.flag = flag; this.visited = false; this.vertex = vertex;
    }
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}

class Graph {
    private  LinkedList<Integer> nodes[];
    private int countOfVert = 0;

    public Graph(int countVert) {
        this.countOfVert = countVert;
        nodes = new LinkedList[countVert];
        for(int i = 0; i < countVert; i++){
            nodes[i] = new LinkedList<>();
        }
    }
    void addEdge(int v, int w)
    {
        nodes[v].add(w);
    }

    int DFSUtil(int v, Node visited[], int iter)
    {
        visited[v].setVisited(true);
        visited[v].setFlag(iter);

        Iterator<Integer> i = nodes[v].listIterator();

        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n].isVisited()){
                iter++;
                iter = DFSUtil(n, visited, iter);
            }
        }
        return iter;

    }

    void DFS(int countOfVert)
    {
        boolean visited[] = new boolean[this.countOfVert];
        Node[] visitedNodes = new Node[this.countOfVert];
        for(int i = 0; i < this.countOfVert; i++){
            visitedNodes[i] = new Node(i, false, -1);
        }
        DFSUtil(countOfVert, visitedNodes, 0);
        try {
            File fout = new File("output.txt");
            FileWriter myWriter = new FileWriter(fout);
            for(int i = 0; i < this.countOfVert; i++){
                if(visitedNodes[i].getFlag()!=-1){
                    myWriter.write(String.valueOf(visitedNodes[i].getFlag() + 1) + " ");
                } else {
                    myWriter.write(String.valueOf(i+1));
                }
            }

            myWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String infile1 = "input.txt";
        File fin1 = new File(infile1);
        Scanner myReader1 = new Scanner(fin1);
        int size = 0;
        if(myReader1.hasNext()){
            size = Integer.parseInt(myReader1.next());
        } else {
            return;
        }
        Graph graph = new Graph(size);
        int[][] myMatrix = new int[size][size];
        int[] NodesArr = new int[size];
        for(int i = 0; i < size; i++){
            NodesArr[i] = i;
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
        graph.DFS(0);
    }
}
