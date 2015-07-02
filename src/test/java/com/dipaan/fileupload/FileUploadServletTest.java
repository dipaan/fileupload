/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dipaan.fileupload;

import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

/**
 *
 * @author bdipan
 */
public class FileUploadServletTest {

    @Before
    public void setUp() {
    }
    
    /**
     * Test of parseLine method, of class FileUploadServlet.
     */
    @org.junit.Test
    public void testParseLine() {
        FileUploadServlet instance = new FileUploadServlet();
        Calendar cal = Calendar.getInstance();
        cal.set(2006, 3, 2, 14, 23, 47);
        cal.set(Calendar.MILLISECOND, 0); 
        Date date = cal.getTime();

        Item expResult1 = new Item(date, 2, "BMart Bubbles", 5.5f);
        String line1 = "04/02/2006T14:23:47Z,2,BMart Bubbles,5.5";
        Item result1 = instance.parseLine(line1);
        System.out.println(expResult1.getDate());
        System.out.println(result1.getDate());
        assertTrue(expResult1.getDate().compareTo(result1.getDate()) == 0);
        assertEquals(expResult1.getRowNumber(), result1.getRowNumber());
        assertEquals(expResult1.getDescription(), result1.getDescription());
        assertEquals(expResult1.getAmount(), result1.getAmount());

        Item expResult2 = new Item(null, 2, "BMart Bubbles", 5.5f);
        String line2 = ",2,BMart Bubbles,5.5";
        Item result2 = instance.parseLine(line2);
        assertEquals(expResult2.getDate(), result2.getDate());

        Item expResult3 = new Item(date, null, "BMart Bubbles", 5.5f);
        String line3 = "04/02/2006T14:23:47Z,,BMart Bubbles,5.5";
        Item result3 = instance.parseLine(line3);
        assertEquals(expResult3.getRowNumber(), result3.getRowNumber());

        Item expResult4 = new Item(date, 2, "", 5.5f);
        String line4 = "04/02/2006T14:23:47Z,2,,5.5";
        Item result4 = instance.parseLine(line4);
        assertEquals(expResult4.getDescription(), result4.getDescription());

        Item expResult5 = new Item(date, 2, "BMart Bubbles", null);
        String line5 = "04/02/2006T14:23:47Z,2,BMart Bubbles,";
        Item result5 = instance.parseLine(line5);
        assertEquals(expResult5.getAmount(), result5.getAmount());
    }

}
