package com.company;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String infile1 = "input.txt";
        File fin1 = new File(infile1);
        Scanner myReader1 = new Scanner(fin1);
        String myStr = "";
        while (myReader1.hasNext()) {
            myStr = myReader1.nextLine();
        }
        myReader1.close();
        int strLen = myStr.length();

        char []strArr = myStr.toCharArray();
        String [][]myMatrix = new String[strLen+1][strLen+1];
        for(int i = 0; i < strLen+1; i++){
            for(int j = 0; j < strLen+1;j++){
                myMatrix[i][j] = "";
            }
        }
        for(int i = 1; i < strLen+1; i++){
            myMatrix[0][i] = String.valueOf(strArr[i-1]);
            myMatrix[i][0] = String.valueOf(strArr[strLen - (i)]);
        }
        for(int i = 0; i < strLen+1; i++){
            for(int j= 0; j < strLen+1;j++){
                System.out.print(myMatrix[i][j]+"  ");
            }
            System.out.println("\n");
        }
        for(int i = 0; i < strLen;i++){
            for(int j = 0; j < strLen; j++){
                if(!myMatrix[i+1][0].equals(myMatrix[0][j+1])){
                    if(myMatrix[i][j+1].length()>myMatrix[i+1][j].length()){
                        myMatrix[i+1][j+1] = myMatrix[i][j+1];
                    } else {
                        myMatrix[i+1][j+1] = myMatrix[i+1][j];
                    }
                } else {
                    myMatrix[i+1][j+1] = myMatrix[i][j] + myMatrix[i+1][0];
                }
                System.out.print(myMatrix[i+1][j+1] + " ");
            }
            System.out.println("\n");
        }
        System.out.println(String.valueOf(myMatrix[strLen][strLen].length()));
      System.out.println(myMatrix[strLen][strLen]);
        try {
            File fout = new File("output.txt");
            FileWriter myWriter = new FileWriter(fout);
            myWriter.write(String.valueOf(myMatrix[strLen][strLen].length()));
            myWriter.write("\n");
            myWriter.write(myMatrix[strLen][strLen]);
            myWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
