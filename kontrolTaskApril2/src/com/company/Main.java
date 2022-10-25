package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class myNode implements Comparable<myNode> {
    public int child;
    public int parent;

    myNode(int parent, int child) {
        this.child = child;
        this.parent = parent;
    }

    @Override
    public int compareTo(myNode o) {
        if (this.child > o.child) {
            return 1;
        } else if(this.child == o.child){
            return 0;
        } else {
            return -1;
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
            ArrayList<myNode> myNodes = new ArrayList<>();
            while (myReader1.hasNext()){
                int val1 = Integer.parseInt(myReader1.next());
                int val2 = Integer.parseInt(myReader1.next());
                myNodes.add(new myNode(val1,val2));
            }

            Collections.sort(myNodes);
            myReader1.close();
            try {
                File fout = new File("output.txt");
                FileWriter myWriter = new FileWriter(fout);
                for(int i = 0; i < myNodes.size(); i++){
                    myWriter.write(String.valueOf(myNodes.get(i).parent));
                }
                myWriter.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
}
