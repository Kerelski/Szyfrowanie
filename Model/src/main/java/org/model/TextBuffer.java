package org.model;

import java.util.Arrays;
import java.util.Base64;

public class TextBuffer {

    private byte[] bytes;

    public TextBuffer(byte[] bytes){
        this.bytes = bytes;
        int ogLength = bytes.length;
        int additionBytes = 16 - (ogLength%16);
        if(additionBytes != 16){
            this.bytes = Arrays.copyOf(bytes, ogLength+additionBytes);
            this.bytes[ogLength+additionBytes-1] = (byte) additionBytes;
        }
    }

    public byte[] getBytes() {
        return bytes;
    }

}
