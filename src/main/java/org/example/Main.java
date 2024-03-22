package org.example;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        int[][] box = {{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,0,0}};

        printBox(box);

        int[][] chBox = AES.subBytes(box);

        printBox(chBox);

        chBox = AES.shiftRows(chBox);

        printBox(chBox);

        chBox = AES.mixColumns(chBox);

        printBox(chBox);

        chBox = AES.invMixColumns(chBox);

        printBox(chBox);

//        FileReader fr = new FileReader("sample.pdf");
//
//        FileOutputStream fos = new FileOutputStream("coded.pdf");
//        fr.read();
//        byte[] b = fr.getBytes();
//
//        System.out.println(b.length);
//        System.out.println();
//        for(int i = 0; i < b.length; i++) {
//            System.out.println(Integer.toBinaryString(b[i] & 0xFFFF));
//        }
    }

    private static void printBox(int[][] box) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(Integer.toHexString(box[i][j]) +" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}