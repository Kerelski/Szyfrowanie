package org.example;

public class Main {

    public static void main(String[] args) throws Exception {

        AES aes = new AES();

        byte[][] box = {{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,0,0}};

        printBox(box);

        byte[][] chBox = aes.subBytes(box);

        printBox(chBox);

        chBox = aes.shiftRows(chBox);

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

        CipherKey secretKey = new CipherKey(256);
        secretKey.showKey();
        byte[][] roundKeys = Keys.keyExpansion(secretKey.getCipherKey());
        chBox = AES.addRoundKey(roundKeys[13], chBox);
        printBox(chBox);

        // Wypisz klucze rundowe
        for (int i = 0; i < 14 + 1; i++) {
            System.out.println("Round Key " + i + ": " + bytesToHex(roundKeys[i]));
        }

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
                System.out.print(Integer.toHexString(box[i][j]) +" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}