package org.example;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {

        byte[][] box = {
                {(byte) 0x01,(byte) 0x00,(byte) 0x00,(byte) 0xbf},
                {(byte) 0x01,(byte) 0x00,(byte) 0x00,(byte) 0x00},
                {(byte) 0x00,(byte) 0x04,(byte) 0x00,(byte) 0xFF},
                {(byte) 0x00,(byte) 0x00,(byte) 0xa1,(byte) 0x00}};

        CipherKey secretKey = new CipherKey(256);
        secretKey.showKey();

        printBox(box);

        box = AES.encode(box, secretKey.getCipherKey());

        printBox(box);

        box = AES.decode(box, secretKey.getCipherKey());

        printBox(box);




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


//        chBox = AES.addRoundKey(roundKeys[0], chBox);
//        printBox(chBox);

//        chBox = AES.addRoundKey(roundKeys[0], chBox);
//        printBox(chBox);
//
//        byte[][] roundKeys = Keys.keyExpansion(secretKey.getCipherKey());
//
//        // Wypisz klucze rundowe
//        for (int i = 0; i < 14 + 1; i++) {
//            System.out.println("Round Key " + i + ": " + bytesToHex(roundKeys[i]));
//        }

    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }

    private static void printBox(byte[][] box) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(Integer.toHexString(box[i][j] & 0xFF)  +" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}