package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class MinWeightTriang {
    public static double sum = 0.0;
    public static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double dist(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x)
                + (p1.y - p2.y) * (p1.y - p2.y));
    }

    public static double weight(ArrayList<MinWeightTriang.Point> points, int i, int j, int k) {
        Point p1 = points.get(i), p2 = points.get(j),
                p3 = points.get(k);
        return  dist(p1, p3) + dist(p3, p2);
    }

    public static void Reconstructing(int[][] S, int i, int j, ArrayList<Point> myArr){
        int k = S[i][j];
        if(i < k - 1){
            sum += dist(myArr.get(i), myArr.get(k));
            Reconstructing(S, i, k, myArr);
        }
        if(k < j - 1){
            sum += dist(myArr.get(k), myArr.get(j));
            Reconstructing(S, k, j, myArr);
        }
    }

    public static double SearchMinTriang(ArrayList<MinWeightTriang.Point> points, int n) {
        if (n < 3)
            return 0;
        double[][] L = new double[n][n];
        int[][] S = new int[n][n];
        for (int l = 3; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                L[i][j] = Double.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    double val = L[i][k] + L[k][j] + weight(points, i, j, k);
                    if (val < L[i][j]) {
                        L[i][j] = val;
                        S[i][j] = k;
                    }
                }
            }
        }
        Reconstructing(S, 0, n-1, points);
        return sum;
    }
}


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<MinWeightTriang.Point> points1 = new ArrayList<>();
        String infile1 = "input.txt";
        File fin1 = new File(infile1);
        Scanner myReader1 = new Scanner(fin1);
        int size = 0;
        if(myReader1.hasNext()){
            size = Integer.parseInt(myReader1.next());
        } else {
            return;
        }

        for(int i = 0; i < size; i++){
            int x = Integer.parseInt(myReader1.next());
            int y = Integer.parseInt(myReader1.next());
            MinWeightTriang.Point tmp = new MinWeightTriang.Point(x,y);
            points1.add(tmp);
        }
        myReader1.close();
        try {
            File fout = new File("output.txt");
            FileWriter myWriter = new FileWriter(fout);
            myWriter.write(String.valueOf(MinWeightTriang.SearchMinTriang(points1, size)));
            myWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
