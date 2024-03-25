package org.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class FileReader {

    private String path;
    private byte[] bytes;
    private File file;

    public FileReader(String path){
        this.path = path;
        file = new File(path);
        bytes = new byte[(int) file.length()];
    }

    public void read() throws Exception {
        try(FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getBytes() {
        return bytes;
    }


}
