package org.model;

public class AES {
    private static final byte[] sBox = {
            (byte)0x63, (byte)0x7c, (byte)0x77, (byte)0x7b, (byte)0xf2, (byte)0x6b, (byte)0x6f, (byte)0xc5, (byte)0x30, (byte)0x01, (byte)0x67, (byte)0x2b, (byte)0xfe, (byte)0xd7, (byte)0xab, (byte)0x76,
            (byte)0xca, (byte)0x82, (byte)0xc9, (byte)0x7d, (byte)0xfa, (byte)0x59, (byte)0x47, (byte)0xf0, (byte)0xad, (byte)0xd4, (byte)0xa2, (byte)0xaf, (byte)0x9c,(byte) 0xa4, (byte)0x72, (byte)0xc0,
            (byte)0xb7, (byte)0xfd, (byte)0x93, (byte)0x26, (byte)0x36, (byte)0x3f, (byte)0xf7, (byte)0xcc, (byte)0x34, (byte)0xa5, (byte)0xe5, (byte)0xf1, (byte)0x71, (byte)0xd8, (byte)0x31, (byte)0x15,
            (byte)0x04, (byte)0xc7, (byte)0x23, (byte)0xc3, (byte)0x18, (byte)0x96, (byte)0x05, (byte)0x9a, (byte)0x07, (byte)0x12, (byte)0x80, (byte)0xe2, (byte)0xeb, (byte)0x27, (byte)0xb2, (byte)0x75,
            (byte)0x09, (byte)0x83, (byte)0x2c, (byte)0x1a, (byte)0x1b, (byte)0x6e, (byte)0x5a, (byte)0xa0, (byte)0x52, (byte)0x3b, (byte)0xd6, (byte)0xb3, (byte)0x29, (byte)0xe3, (byte)0x2f, (byte)0x84,
            (byte)0x53, (byte)0xd1, (byte)0x00, (byte)0xed, (byte)0x20, (byte)0xfc, (byte)0xb1, (byte)0x5b, (byte)0x6a, (byte)0xcb, (byte)0xbe, (byte)0x39, (byte)0x4a, (byte)0x4c, (byte)0x58, (byte)0xcf,
            (byte)0xd0, (byte)0xef, (byte)0xaa, (byte)0xfb, (byte)0x43, (byte)0x4d, (byte)0x33, (byte)0x85, (byte)0x45, (byte)0xf9, (byte)0x02, (byte)0x7f, (byte)0x50, (byte)0x3c, (byte)0x9f, (byte)0xa8,
            (byte)0x51, (byte)0xa3, (byte)0x40, (byte)0x8f, (byte)0x92, (byte)0x9d, (byte)0x38, (byte)0xf5, (byte)0xbc, (byte)0xb6, (byte)0xda, (byte)0x21, (byte)0x10, (byte)0xff, (byte)0xf3, (byte)0xd2,
            (byte)0xcd, (byte)0x0c, (byte)0x13, (byte)0xec, (byte)0x5f, (byte)0x97, (byte)0x44, (byte)0x17, (byte)0xc4, (byte)0xa7, (byte)0x7e, (byte)0x3d, (byte)0x64, (byte)0x5d, (byte)0x19, (byte)0x73,
            (byte)0x60, (byte)0x81, (byte)0x4f, (byte)0xdc, (byte)0x22, (byte)0x2a, (byte)0x90, (byte)0x88, (byte)0x46, (byte)0xee, (byte)0xb8, (byte)0x14, (byte)0xde, (byte)0x5e, (byte)0x0b, (byte)0xdb,
            (byte)0xe0, (byte)0x32, (byte)0x3a, (byte)0x0a, (byte)0x49, (byte)0x06, (byte)0x24, (byte)0x5c, (byte)0xc2, (byte)0xd3, (byte)0xac, (byte)0x62, (byte)0x91, (byte)0x95, (byte)0xe4, (byte)0x79,
            (byte)0xe7, (byte)0xc8, (byte)0x37, (byte)0x6d, (byte)0x8d, (byte)0xd5, (byte)0x4e, (byte)0xa9, (byte)0x6c, (byte)0x56, (byte)0xf4, (byte)0xea, (byte)0x65, (byte)0x7a, (byte)0xae, (byte)0x08,
            (byte)0xba, (byte)0x78, (byte)0x25, (byte)0x2e, (byte)0x1c, (byte)0xa6, (byte)0xb4, (byte)0xc6, (byte)0xe8, (byte)0xdd, (byte)0x74, (byte)0x1f, (byte)0x4b, (byte)0xbd, (byte)0x8b, (byte)0x8a,
            (byte)0x70, (byte)0x3e, (byte)0xb5, (byte)0x66, (byte)0x48, (byte)0x03, (byte)0xf6, (byte)0x0e, (byte)0x61, (byte)0x35, (byte)0x57, (byte)0xb9, (byte)0x86, (byte)0xc1, (byte)0x1d, (byte)0x9e,
            (byte)0xe1, (byte)0xf8, (byte)0x98, (byte)0x11, (byte)0x69, (byte)0xd9, (byte)0x8e, (byte)0x94, (byte)0x9b, (byte)0x1e, (byte)0x87, (byte)0xe9, (byte)0xce, (byte)0x55, (byte)0x28, (byte)0xdf,
            (byte)0x8c, (byte)0xa1, (byte)0x89, (byte)0x0d, (byte)0xbf, (byte)0xe6, (byte)0x42, (byte)0x68, (byte)0x41, (byte)0x99, (byte)0x2d, (byte)0x0f, (byte)0xb0, (byte)0x54, (byte)0xbb, (byte)0x16
    };

    private static final byte[] invSBox = {
            (byte)0x52, (byte)0x09, (byte)0x6a, (byte)0xd5, (byte)0x30, (byte)0x36, (byte)0xa5, (byte)0x38, (byte)0xbf, (byte)0x40, (byte)0xa3, (byte)0x9e, (byte)0x81, (byte)0xf3, (byte)0xd7, (byte)0xfb,
            (byte)0x7c, (byte)0xe3, (byte)0x39, (byte)0x82, (byte)0x9b, (byte)0x2f, (byte)0xff, (byte)0x87, (byte)0x34, (byte)0x8e, (byte)0x43, (byte)0x44, (byte)0xc4, (byte)0xde, (byte)0xe9, (byte)0xcb,
            (byte)0x54, (byte)0x7b, (byte)0x94, (byte)0x32, (byte)0xa6, (byte)0xc2, (byte)0x23, (byte)0x3d, (byte)0xee, (byte)0x4c, (byte)0x95, (byte)0x0b, (byte)0x42, (byte)0xfa, (byte)0xc3, (byte)0x4e,
            (byte)0x08, (byte)0x2e, (byte)0xa1, (byte)0x66, (byte)0x28, (byte)0xd9, (byte)0x24, (byte)0xb2, (byte)0x76, (byte)0x5b, (byte)0xa2, (byte)0x49, (byte)0x6d, (byte)0x8b, (byte)0xd1, (byte)0x25,
            (byte)0x72, (byte)0xf8, (byte)0xf6, (byte)0x64, (byte)0x86, (byte)0x68, (byte)0x98, (byte)0x16, (byte)0xd4, (byte)0xa4, (byte)0x5c, (byte)0xcc, (byte)0x5d, (byte)0x65, (byte)0xb6, (byte)0x92,
            (byte)0x6c, (byte)0x70, (byte)0x48, (byte)0x50, (byte)0xfd, (byte)0xed, (byte)0xb9, (byte)0xda, (byte)0x5e, (byte)0x15, (byte)0x46, (byte)0x57, (byte)0xa7, (byte)0x8d, (byte)0x9d, (byte)0x84,
            (byte)0x90, (byte)0xd8, (byte)0xab, (byte)0x00, (byte)0x8c, (byte)0xbc, (byte)0xd3, (byte)0x0a, (byte)0xf7, (byte)0xe4, (byte)0x58, (byte)0x05, (byte)0xb8, (byte)0xb3, (byte)0x45, (byte)0x06,
            (byte)0xd0, (byte)0x2c, (byte)0x1e, (byte)0x8f, (byte)0xca, (byte)0x3f, (byte)0x0f, (byte)0x02, (byte)0xc1, (byte)0xaf, (byte)0xbd, (byte)0x03, (byte)0x01, (byte)0x13, (byte)0x8a, (byte)0x6b,
            (byte)0x3a, (byte)0x91, (byte)0x11, (byte)0x41, (byte)0x4f, (byte)0x67, (byte)0xdc, (byte)0xea, (byte)0x97, (byte)0xf2, (byte)0xcf, (byte)0xce, (byte)0xf0, (byte)0xb4, (byte)0xe6, (byte)0x73,
            (byte)0x96, (byte)0xac, (byte)0x74, (byte)0x22, (byte)0xe7, (byte)0xad, (byte)0x35, (byte)0x85, (byte)0xe2, (byte)0xf9, (byte)0x37, (byte)0xe8, (byte)0x1c, (byte)0x75, (byte)0xdf, (byte)0x6e,
            (byte)0x47, (byte)0xf1, (byte)0x1a, (byte)0x71, (byte)0x1d, (byte)0x29, (byte)0xc5, (byte)0x89, (byte)0x6f, (byte)0xb7, (byte)0x62, (byte)0x0e, (byte)0xaa, (byte)0x18, (byte)0xbe, (byte)0x1b,
            (byte)0xfc, (byte)0x56, (byte)0x3e, (byte)0x4b, (byte)0xc6, (byte)0xd2, (byte)0x79, (byte)0x20, (byte)0x9a, (byte)0xdb, (byte)0xc0, (byte)0xfe, (byte)0x78, (byte)0xcd, (byte)0x5a, (byte)0xf4,
            (byte)0x1f, (byte)0xdd, (byte)0xa8, (byte)0x33, (byte)0x88, (byte)0x07, (byte)0xc7, (byte)0x31, (byte)0xb1, (byte)0x12, (byte)0x10, (byte)0x59, (byte)0x27, (byte)0x80, (byte)0xec, (byte)0x5f,
            (byte)0x60, (byte)0x51, (byte)0x7f, (byte)0xa9, (byte)0x19, (byte)0xb5, (byte)0x4a, (byte)0x0d, (byte)0x2d, (byte)0xe5, (byte)0x7a, (byte)0x9f, (byte)0x93, (byte)0xc9, (byte)0x9c, (byte)0xef,
            (byte)0xa0, (byte)0xe0, (byte)0x3b, (byte)0x4d, (byte)0xae, (byte)0x2a, (byte)0xf5, (byte)0xb0, (byte)0xc8, (byte)0xeb, (byte)0xbb, (byte)0x3c, (byte)0x83, (byte)0x53, (byte)0x99, (byte)0x61,
            (byte)0x17, (byte)0x2b, (byte)0x04, (byte)0x7e, (byte)0xba, (byte)0x77, (byte)0xd6, (byte)0x26, (byte)0xe1, (byte)0x69, (byte)0x14, (byte)0x63, (byte)0x55, (byte)0x21, (byte)0x0c, (byte)0x7d
    };

    public static byte[][] encode(byte[][] block, byte [] secretKey) {
        byte[][] roundKeys = Keys.keyExpansion(secretKey);

        block = AES.addRoundKey(roundKeys[0],block);

        for(int i = 1; i<=13;i++) {
            block = AES.subBytes(block);
            block = AES.shiftRows(block);
            block = AES.mixColumns(block);
            block = AES.addRoundKey(roundKeys[i],block);
        }

        block = AES.subBytes(block);
        block = AES.shiftRows(block);
        block = AES.addRoundKey(roundKeys[14],block);

        return block;
    }

    public static byte[][] decode(byte[][] block, byte [] secretKey) {
        byte[][] roundKeys = Keys.keyExpansion(secretKey);

        block = AES.addRoundKey(roundKeys[14],block);
        block = AES.invShiftRows(block);
        block = AES.invSubBytes(block);

        for(int i = 13; i>=1;i--) {
            block = AES.addRoundKey(roundKeys[i],block);
            block = AES.invMixColumns(block);
            block = AES.invShiftRows(block);
            block = AES.invSubBytes(block);
        }

        block = AES.addRoundKey(roundKeys[0],block);

        return block;
    }


    private static byte[][] subBytes(byte[][] block) {

        byte[][] chBlock = new byte[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                chBlock[i] = subWord(block[i]);
            }
        }
        return chBlock;
    }
    private static byte[][] invSubBytes(byte[][] block) {

        byte[][] chBlock = new byte[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                chBlock[i] = invSubWord(block[i]);
            }
        }
        return chBlock;
    }

    public static byte[] subWord(byte[] word) {
        byte[] result = new byte[word.length];
        for(int i = 0; i< word.length; i++){
            result[i] = sBox[word[i] & 0xFF];
        }
        return result;
    }

    private static byte[] invSubWord(byte[] word) {
        byte[] result = new byte[word.length];
        for(int i = 0; i< word.length; i++){
            result[i] = invSBox[word[i] & 0xFF];
        }
        return result;
    }

    private static byte[][] shiftRows(byte[][] block) {
        byte[][] chBlock = block;
        for(int i = 1; i < 4; i++)  {
            for(int j =0; j<i; j++){
                chBlock[i] = rotWord(chBlock[i]);
            }
        }
        return chBlock;
    }

    private static byte[][] invShiftRows(byte[][] block) {
        byte[][] chBlock = block;
        for(int i = 1; i < 4; i++)  {
            for(int j =0; j<i; j++){
                chBlock[i] = invRotWord(chBlock[i]);
            }
        }
        return chBlock;
    }
    public static byte[] rotWord(byte[] word){
        byte[] result = new byte[word.length];
        byte temp = word[0];
        result[0] = word[1];
        result[1] = word[2];
        result[2] = word[3];
        result[3] = temp;
        return result;
    }

    private static byte[] invRotWord(byte[] word){
        byte[] result = new byte[word.length];
        byte temp = word[3];
        result[3] = word[2];
        result[2] = word[1];
        result[1] = word[0];
        result[0] = temp;
        return result;
    }

    private static byte[][] mixColumns(byte[][] block) {
        byte[][] chBlock = new byte[4][4];

        for (int col = 0; col < 4; col++) {
            chBlock[0][col] = (byte) ((multiply(block[0][col], 2) ^ multiply(block[1][col], 3) ^ block[2][col] ^ block[3][col]) & 0xFF);
            chBlock[1][col] = (byte) ((block[0][col] ^ multiply(block[1][col], 2) ^ multiply(block[2][col], 3) ^ block[3][col]) & 0xFF);
            chBlock[2][col] = (byte) ((block[0][col] ^ block[1][col] ^ multiply(block[2][col], 2) ^ multiply(block[3][col], 3)) & 0xFF);
            chBlock[3][col] = (byte) ((multiply(block[0][col], 3) ^ block[1][col] ^ block[2][col] ^ multiply(block[3][col], 2)) & 0xFF);
        }

        return chBlock;
    }
    private static byte[][] invMixColumns(byte[][] block) {
        byte[][] chBlock = new byte[4][4];

        for (int col = 0; col < 4; col++) {
            chBlock[0][col] = (byte) ((multiply(block[0][col], 14) ^ multiply(block[1][col], 11) ^ multiply(block[2][col], 13) ^ multiply(block[3][col], 9)) & 0xFF);
            chBlock[1][col] = (byte) ((multiply(block[0][col], 9) ^ multiply(block[1][col], 14) ^ multiply(block[2][col], 11) ^ multiply(block[3][col], 13)) & 0xFF);
            chBlock[2][col] = (byte) ((multiply(block[0][col], 13) ^ multiply(block[1][col], 9) ^ multiply(block[2][col], 14) ^ multiply(block[3][col], 11)) & 0xFF);
            chBlock[3][col] = (byte) ((multiply(block[0][col], 11) ^ multiply(block[1][col], 13) ^ multiply(block[2][col], 9) ^ multiply(block[3][col], 14)) & 0xFF);
        }

        return chBlock;
    }
    private static byte multiply(int a, int b) {
        int result = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                result ^= a;
            }
            boolean highBitSet = (a & 0x80) != 0;
            a <<= 1;
            if (highBitSet) {
                a ^= 0x1B;
            }
            b >>= 1;
        }
        return (byte)result;
    }

    private static byte[][] addRoundKey(byte[] key, byte[][] block){
        byte[][] chBlock = new byte[4][4];

        int index = 0;
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                chBlock[i][j] = (byte) (block[i][j] ^ key[index]);
                index++;
            }
        }
        return chBlock;
    }

}