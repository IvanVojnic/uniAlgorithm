package com.company;

public class Main {

    public static void main(String[] args) {
        double eps = 0.000001;
        double begVal = 0.8;
        double endVal = 0.5-Math.pow(begVal, 5.0)/4;
        while(Math.abs(endVal-begVal)>eps){
            begVal=endVal;
            endVal = 0.5-Math.pow(begVal, 5.0)/4;
        }
        System.out.println(endVal);
        System.out.println(Math.pow(endVal, 5.0)+4*endVal-2);
    }
}
