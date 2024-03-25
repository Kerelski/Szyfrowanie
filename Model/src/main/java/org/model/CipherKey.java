package org.model;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
public class CipherKey {

    private byte[] cipherKey;

    public void generate(int bits){
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

    public void setCipherKey(String input) {
        if(input.length() == 64){
            byte[] byteArray = new byte[input.length() / 2];
            for (int i = 0; i < byteArray.length; i++) {
                int index = i * 2;
                int num = Integer.parseInt(input.substring(index, index + 2), 16);
                byteArray[i] = (byte) num;
            }

        this.cipherKey = byteArray;
        }else {
            System.out.println("Nieprawidlowa dlugosc klucza");
        }

    }
}
