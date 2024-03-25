package org.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileWriter {

    private String path;
    private File file;
    public FileWriter(String path){
        this.path = path;
        file = new File(path);

    }

    public void write(byte[] bytesArray) throws Exception {
        try(FileOutputStream fis = new FileOutputStream(file)) {
            fis.write(bytesArray);
        }
    }

    public void setPath(String path) {
        this.path = path;
    }
}
