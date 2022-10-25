package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Point
{
    int x, y;
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public double dist(Point p)
    {
        return Math.sqrt((this.x - p.x) * (this.x - p.x)
                + (this.y - p.y) * (this.y - p.y));
    }
}

class CalcMinTriang
{

    public static double task(ArrayList<Point> points, int i, int j)
    {
        if (j < i + 2)
        {
            return 0;
        }

        double cost = Double.MAX_VALUE;

        for (int k = i + 1; k <= j - 1; k++)
        {
            double weight = points.get(k).dist(points.get(i));
            cost = Double.min(cost,
                    weight + task(points, i, k)
                            + task(points, k, j));
        }
        return cost;
    }
}

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Point> points = new ArrayList<>();
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
            Point tmp = new Point(x,y);
            points.add(tmp);
        }
        myReader1.close();
        CalcMinTriang val = new CalcMinTriang();
        try {
            File fout = new File("output.txt");
            FileWriter myWriter = new FileWriter(fout);
            myWriter.write(String.valueOf(String.valueOf(val.task(points,
                    0, points.size() - 1))));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
