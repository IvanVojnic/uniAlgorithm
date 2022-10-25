package com.company;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Point{
    private int x;
    private int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    Point(){

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Square{

    private int height;
    private boolean visited;
    private int flag;
    private Point S_point;
    public Square(int height, boolean visited, int x, int y){
        this.height = height;
        this.visited = visited;
        this.S_point = new Point(x,y);
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Point getS_point(){
        return this.S_point;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        String infile1 = "input.txt";
        File fin1 = new File(infile1);
        Scanner myReader1 = new Scanner(fin1);
        StringTokenizer taskInfo;
        if(myReader1.hasNext()){
            taskInfo = new StringTokenizer(myReader1.nextLine(), " ");
        } else {
            return;
        }
        int size = Integer.parseInt(taskInfo.nextToken());
        Point a = new Point(Integer.parseInt(taskInfo.nextToken())-1,Integer.parseInt(taskInfo.nextToken())-1);

        Square[][] myMatrix = new Square[size][size];
        int i = 0;
        while (myReader1.hasNext()){
            StringTokenizer tmp = new StringTokenizer(myReader1.nextLine(), " ");
            int j = 0;
            while(tmp.hasMoreTokens()){
                myMatrix[i][j] = new Square(Integer.parseInt(tmp.nextToken()), false, i, j);
                j++;
            }
            i++;
        }
        myReader1.close();
        Queue<Point> coord = new ArrayDeque<Point>();
        coord.offer(a);
        myMatrix[a.getX()][a.getY()].setFlag( myMatrix[a.getX()][a.getY()].getHeight());
        myMatrix[a.getX()][a.getY()].setVisited(true);
        while (!coord.isEmpty()){
            Point tmp = coord.remove();
            int tX = tmp.getX();
            int tY = tmp.getY();
            //   myMatrix[tX][tY].visited = true;
            if(tX!=0){
                if(!myMatrix[tX-1][tY].isVisited()){
                    int tmpHeight = myMatrix[tX-1][tY].getHeight();
                    if( tmpHeight >= myMatrix[tX][tY].getHeight()){
                        myMatrix[tX-1][tY].setFlag(0);
                    } else {
                        myMatrix[tX-1][tY].setFlag(tmpHeight);
                        myMatrix[tX-1][tY].setVisited(true);
                        coord.offer(myMatrix[tX-1][tY].getS_point());
                    }
                }
            }
            if(tY!=0){
                if(!myMatrix[tX][tY-1].isVisited()){
                    int tmpHeight = myMatrix[tX][tY-1].getHeight();
                    if( tmpHeight >= myMatrix[tX][tY].getHeight()){
                        myMatrix[tX][tY-1].setFlag(0);
                    } else {
                        myMatrix[tX][tY-1].setFlag(tmpHeight);
                        myMatrix[tX][tY-1].setVisited(true);
                        coord.offer(myMatrix[tX][tY-1].getS_point());
                    }
                }
            }
            if(tX != size-1){
                if(!myMatrix[tX+1][tY].isVisited()){
                    int tmpHeight = myMatrix[tX+1][tY].getHeight();
                    if( tmpHeight >= myMatrix[tX][tY].getHeight()){
                        myMatrix[tX+1][tY].setFlag(0);
                    } else {
                        myMatrix[tX+1][tY].setFlag(tmpHeight);
                        myMatrix[tX+1][tY].setVisited(true);
                        coord.offer(myMatrix[tX+1][tY].getS_point());
                    }
                }
            }
            if(tY != size-1){
                if(!myMatrix[tX][tY+1].isVisited()){
                    int tmpHeight = myMatrix[tX][tY+1].getHeight();
                    if( tmpHeight >= myMatrix[tX][tY].getHeight()){
                        myMatrix[tX][tY+1].setFlag(0);
                    } else {
                        myMatrix[tX][tY+1].setFlag(tmpHeight);
                        myMatrix[tX][tY+1].setVisited(true);
                        coord.offer(myMatrix[tX][tY+1].getS_point());
                    }
                }
            }
        }
        File file = new File("out.txt");
        FileWriter myFileWriter = null;
        myFileWriter = new FileWriter(file);
        BufferedWriter myWriter = new BufferedWriter(myFileWriter);
        for (i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                    if(j != (size-1)){
                        myWriter.write(myMatrix[i][j].getFlag() + " ");
                    } else {
                        myWriter.write(myMatrix[i][j].getFlag() + "");
                    }
            }
            if( i != size -1){
                myWriter.write("\n");
            }
        }
        myWriter.flush();

    }

}
