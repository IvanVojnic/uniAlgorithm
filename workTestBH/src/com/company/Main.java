package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String infile1 = "input.txt";
        File fin1 = new File(infile1);
        Scanner myReader1 = new Scanner(fin1);
        int size = Integer.parseInt(myReader1.next());
        int[] arr = new int[size+1];
        int k = 1;
        while (myReader1.hasNext()) {
            String str = myReader1.next();
            arr[k] = Integer.parseInt(str);
            k++;
        }
        myReader1.close();
        int flag = 0;
        for (int i = 1; i <= size / 2; i++)
        {
            if ((2 * i <= size) && (arr[i] > arr[2 * i]))
            {
                flag = 1;
                break;
            }
            if ((2 * i + 1 <= size) && (arr[i] > arr[2 * i + 1]))
            {
                flag = 1;
                break;
            }
        }
        try {
            File fout = new File("output.txt");
            FileWriter myWriter = new FileWriter(fout);
            if (flag == 1) {
                myWriter.write("No");
                myWriter.write("\n");
            } else{
                myWriter.write("Yes");
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
