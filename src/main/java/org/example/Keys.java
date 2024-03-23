package org.example;

import java.util.Arrays;

public class Keys {

    private static int nr = 14;
    private static int nb = 4;
    private static int nk = 8;
    private static final int[] rCon = {
            0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36
    };

    public static byte[][] keyExpansion(byte[] cipherKey){
        byte[][] roundKeys = new byte[nr + 1][4 * nb];

        // Pierwszy klucz rundowy to po prostu klucz główny
        for (int i = 0; i < nk; i++) {
            roundKeys[i] = new byte[]{cipherKey[4*i], cipherKey[4*i+1], cipherKey[4*i+2], cipherKey[4*i+3]};
        }
        for (int i = nk; i < (nr+1); i++) {
            byte[] temp = Arrays.copyOf(roundKeys[i-1], 4);
            if (i % nk == 0) {
                temp = AES.subWord(AES.rotWord(temp));

                for (int j = 0; j < 4; j++) {
                    temp[j] ^= rCon[i/nk - 1];
                }
            } else if (nk > 6 && i % nk == 4) {
                temp = AES.subWord(temp);
            }
            for (int j = 0; j < 4; j++) {
                roundKeys[i][j] = (byte) (roundKeys[i-nk][j] ^ temp[j]);
            }

        }

        return roundKeys;
    }


}
