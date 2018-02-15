/*
 * The MIT License
 *
 * Copyright 2017 Acerbis Gianluca <acerbisgianluca.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.acerbisgianluca.filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * An useful API for interacting with files.
 * 
 * @author Acerbis Gianluca
 * @version 1.0
 * @since 12-21-2017
 */
public class FileManager {

    /**
     * Creates a new FileManager.
     */
    public FileManager() {
    }
    
    /**
     * Writes a given object to a file with a given name.
     * @param obj Object to be written. 
     * @param fileName Name (with extension) of the file or the full path passed as a string, e.g "C:\\Users\\YourName\\Desktop\\FileName.someThing", where the object will be written to.
     * @throws IOException If an I/O error occurs. In particular, an IOException may be thrown if the output stream has been closed.
     * @throws java.io.NotSerializableException Thrown when an instance is required to have a Serializable interface.
     */
    public void objectToFile (Object obj, String fileName) throws IOException, NotSerializableException {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            stream.writeObject(obj);
            stream.close();
        }
    }
    
    /**
     * Reads an object from a given file.
     * @param fileName Name (with extension) of the file or the full path passed as a string, e.g "C:\\Users\\YourName\\Desktop\\FileName.someThing", where the object will be read from.
     * @return Object The general object read from the stream which must be casted into a specific type.
     * @throws IOException If an I/O error occurs. In particular, an IOException may be thrown if the input stream has been closed.
     * @throws ClassNotFoundException Class of a serialized object cannot be found.
     * @throws java.io.NotSerializableException Thrown when an instance is required to have a Serializable interface.
     */
    public Object fileToObject (String fileName) throws IOException, ClassNotFoundException, NotSerializableException {
        Object obj;
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName))) {
            obj = (Object) stream.readObject();
            stream.close();
        }
        return obj;
    }
    
    /**
     * Creates a strings which contains all the attributes of a given object separated by comma.
     * @param obj Object whose attributes will be extracted.
     * @return A string composed of all the attribute values separated by comma. The first line contains attribute names.
     * @throws IllegalAccessException Application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    public String objectToCSV (Object obj) throws IllegalAccessException {
        Class<?> c = obj.getClass();
        String out = "";
        Field[] attr = c.getDeclaredFields();
        for(int i = 0; i < attr.length; i++) {
            if(i != attr.length - 1)
                out += attr[i].getName() + ",";
            else
                out += attr[i].getName() + "\n";
        }
        
        for(int i = 0; i < attr.length; i++) {
            attr[i].setAccessible(true);
            if(i != attr.length - 1)
                out += attr[i].get(obj) + ",";
            else
                out += attr[i].get(obj) + "\n";
        }

        return out;
    }
    
    /**
     * Creates a strings which contains all the attributes of each object in the array separated by comma.
     * @param obj Object array whose attributes will be extracted.
     * @return A string composed of all the attribute values separated by comma. The first line contains attribute names.
     * @throws IllegalAccessException Application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    public String objectsToCSV (Object[] obj) throws IllegalAccessException {
        Class<?> c = obj[0].getClass();
        String out = "";
        Field[] attr = c.getDeclaredFields();
        for(int i = 0; i < attr.length; i++) {
            if(i != attr.length - 1)
                out += attr[i].getName() + ",";
            else
                out += attr[i].getName() + "\n";
        }
        
        for (Object obj1 : obj) {
            for (int j = 0; j < attr.length; j++) {
                attr[j].setAccessible(true);
                if (j != attr.length - 1) {
                    out += attr[j].get(obj1) + ",";
                } else {
                    out += attr[j].get(obj1) + "\n";
                }
            }
        }

        return out;
    }
    
    /**
     * Writes a given string to a file with a given name.
     * @param string String to be written.
     * @param fileName Name (with extension) of the file or the full path passed as a string, e.g "C:\\Users\\YourName\\Desktop\\FileName.someThing", where the string will be written to.
     * @throws IOException If an I/O error occurs. In particular, an IOException may be thrown if the output stream has been closed.
     */
    public void stringToFile (String string, String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter (new FileWriter(fileName))) {
            bw.write(string);
            bw.close();
        }
    }
    
    /**
     * Reads every line contained in a given file and return an ArrayList (1 item = 1 line).
     * @param fileName Name (with extension) of the file or the full path passed as a string, e.g "C:\\Users\\YourName\\Desktop\\FileName.someThing", where the string will be read from.
     * @return An ArrayList which contains an item for each line in the given file.
     * @throws IOException If an I/O error occurs. In particular, an IOException may be thrown if the input stream has been closed.
     */
    public ArrayList<String> fileToString (String fileName) throws IOException {
        ArrayList<String> out = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader (new FileReader(fileName))) {
            while((line = br.readLine()) != null)
                out.add(line.concat("\n"));
            br.close();
        }
               
        return out;
    }
}
