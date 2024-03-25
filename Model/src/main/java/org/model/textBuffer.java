package org.model;

import java.util.Arrays;

public class textBuffer {

    private byte[] bytes;

    public textBuffer(byte[] bytes){
        this.bytes = bytes;
        int ogLength = bytes.length;
        int additionBytes = 16 - (ogLength%16);
        if(additionBytes != 16){
            this.bytes = Arrays.copyOf(bytes, ogLength+additionBytes);
        }
    }

    public byte[] getBytes() {
        return bytes;
    }
}
