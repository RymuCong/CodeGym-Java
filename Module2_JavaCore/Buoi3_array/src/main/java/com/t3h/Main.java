package com.t3h;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            float[][] arr = new float[3][3];

            Scanner sc = new Scanner(System.in);

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.printf("Nhập phần tử thứ [" + (i+1) + "][" + (j+1) + "]: ");
                    arr[i][j] = sc.nextFloat();
                }
            }

            System.out.println("Mảng vừa nhập là: ");
            for (float[] floats : arr) {
                for (float aFloat : floats) {
                    System.out.printf("%.2f\t", aFloat);
                }
                System.out.println();
            }
    }
}