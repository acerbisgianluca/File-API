/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acerbisgianluca.filemanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * An useful API for communicating with files.
 * 
 * @author Acerbis Gianluca
 * @version 1.0-beta
 * @since 12-21-2017
 */
public class FileManager {

    /**
     * Creates a new FileManager.
     */
    public FileManager() {
    }
    
    /**
     * Writes a given object to a file with a given name in the same directory of the app position.
     * @param obj Object to be written. 
     * @param fileName Name (with extension) of the file or the full path passed as a string, e.g "C:\\Users\\YourName\\Desktop\\FileName.someThing", where the object will be written to.
     * @throws IOException If an I/O error occurs. In particular, an IOException may be thrown if the output stream has been closed.
     */
    public void objectToFile (Object obj, String fileName) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName));
        stream.writeObject(obj);
        stream.close();
    }
    
    /**
     * Reads an object from a given file which is in the same directory of the app.
     * @param fileName File (with extension) to be read.
     * @return Object The general object read from the stream which must be casted into a specific type.
     * @throws IOException If an I/O error occurs. In particular, an IOException may be thrown if the output stream has been closed.
     * @throws ClassNotFoundException Class of a serialized object cannot be found.
     */
    public Object fileToObject (String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName));
        Object obj = (Object) stream.readObject();
        stream.close();
        return obj;
    }
}
