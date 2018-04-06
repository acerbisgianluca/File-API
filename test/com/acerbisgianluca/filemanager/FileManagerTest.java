/*
 * The MIT License
 *
 * Copyright 2018 Acerbis Gianluca <www.rossimario.com>.
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

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Acerbis Gianluca <www.rossimario.com>
 */
public class FileManagerTest {
    
    public FileManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of objectToFile method, of class FileManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testObjectToFile() throws Exception {
        System.out.println("objectToFile");
        String fileName = "test.txt";
        TestObject test = new TestObject("mario", "rossi");
        FileManager.objectToFile(test, fileName);
    }

    /**
     * Test of fileToObject method, of class FileManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testFileToObject() throws Exception {
        System.out.println("fileToObject");
        String fileName = "test.txt";
        TestObject expResult = new TestObject("mario", "rossi");
        FileManager.objectToFile(expResult, fileName);
        TestObject result = (TestObject) FileManager.fileToObject(fileName);
        assertEquals(expResult, result);
    }

    /**
     * Test of objectToCSV method, of class FileManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testObjectToCSV() throws Exception {
        System.out.println("objectToCSV");
        TestObject obj = new TestObject("mario", "rossi");
        String expResult = "nome,cognome\n" +"mario,rossi\n" +"";
        String result = FileManager.objectToCSV(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of objectsToCSV method, of class FileManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testObjectsToCSV() throws Exception {
        System.out.println("objectsToCSV");
        TestObject[] obj = new TestObject[]{new TestObject("mario", "rossi"), new TestObject("gesu", "cristo")};
        String expResult = "nome,cognome\n" +"mario,rossi\n" +"gesu,cristo\n" +"";
        String result = FileManager.objectsToCSV(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToFile method, of class FileManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testStringToFile() throws Exception {
        System.out.println("stringToFile");
        String string = "test";
        String fileName = "string.txt";
        FileManager.stringToFile(string, fileName);
    }

    /**
     * Test of fileToString method, of class FileManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testFileToString() throws Exception {
        System.out.println("fileToString");
        String fileName = "string.txt";
        String string = "test";
        FileManager.stringToFile(string, fileName);
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("test\n");
        ArrayList<String> result = FileManager.fileToString(fileName);
        assertEquals(expResult, result);
    }
    
}
