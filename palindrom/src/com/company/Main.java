package com.company;

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
        String finStr = "";
        while (myReader1.hasNext()) {
            finStr = myReader1.nextLine();
        }
        myReader1.close();
        String revStr = reverseString(finStr);
        int strLen = finStr.length();
        char[] myArr = finStr.toCharArray();
        String[][] myMatr = new String[strLen+1][strLen+1];
        for(int i=1;i<strLen;i++){
            for(int j=1;j<strLen;j++){
                if(finStr.charAt(i-1)==revStr.charAt(j-1)){
                    myMatr[i][j]=myMatr[i-1][j-1]+revStr.charAt(j-1);
                }
                else{
                    if(myMatr[i-1][j].length()>myMatr[i][j-1].length()){
                        myMatr[i][j]=myMatr[i-1][j];
                    }
                    else{
                        myMatr[i][j]=myMatr[i][j-1];
                    }
                }
            }
        }
        System.out.println(myMatr[strLen-1][strLen-1]);
        try {
            File fout = new File("output.txt");
            FileWriter myWriter = new FileWriter(fout);
            int i = 0;
            for(int j=strLen; i < strLen/2; i++, j--){

            }
            myWriter.write("\n");
            myWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    //private static
    public static String reverseString(String str) {
        if (str.length() <= 1) {
            return str;
        }
        return reverseString(str.substring(1)) + str.charAt(0);
    }
}
