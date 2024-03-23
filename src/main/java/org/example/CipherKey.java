package org.example;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
public class CipherKey {

    private byte[] cipherKey;
    public CipherKey(int bits){
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(bits);
            SecretKey secretKey = keyGenerator.generateKey();
            cipherKey = secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e){
            System.out.println("Nie ma takiego algorytmu" + e);
        }
    }

    public byte[] getCipherKey() {
        return cipherKey;
    }

    public void showKey(){
        System.out.println("Klucz w postaci bajtów: " + bytesToHex(cipherKey));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }



}
