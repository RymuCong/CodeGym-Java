package com.t3h;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so phan tu cua mang: ");
        int n = sc.nextInt();
        System.out.println("Nhap so can tim:");
        int x = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap phan tu thu " + i + 1 + ": ");
            int value = sc.nextInt();
            arr.add(value);
        }
        boolean isExist = false;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == x) {
                System.out.println("Vi tri cua " + x + 1 + " la: " + i);
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            System.out.println(-1);
        }
    }
}