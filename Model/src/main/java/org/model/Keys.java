package org.model;

public class Keys {

    private static int nr = 14;
    private static final byte[][] rCon = {
            {(byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x08, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x10, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x80, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x1b, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x36, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x6c, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0xd8, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0xab, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x4d, (byte) 0x00, (byte) 0x00, (byte) 0x00},
            {(byte) 0x9a, (byte) 0x00, (byte) 0x00, (byte) 0x00}
    };

    public static byte[][] keyExpansion(byte[] cipherKey) {
        byte[][] roundKeys = new byte[nr + 1][16];

        // Pierwszy klucz rundowy to po prostu klucz główny
        for (int i = 0; i < 32; i++) {
            roundKeys[i / 4][i % 4] = cipherKey[i];
        }
        for (int i = 1; i < (nr+1); i++) {
            byte[] temp = new byte[4];
            System.arraycopy(roundKeys[i - 1], 12, temp, 0, 4);
            temp = AES.subWord(AES.rotWord(temp));
            for (int j = 0; j < 4; j++) {
                temp[j] ^= rCon[i-1][j];
            }
            for (int j = 0; j < 4; j++) {
                roundKeys[i][j] = (byte) (roundKeys[i - 1][j] ^ temp[j]);
            }
            for (int j = 4; j < 16; j++) {
                roundKeys[i][j] = (byte) (roundKeys[i][j-4] ^ roundKeys[i - 1][j]);
            }
        }

        return roundKeys;
    }

}
