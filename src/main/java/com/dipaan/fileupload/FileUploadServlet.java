package com.dipaan.fileupload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadServlet extends HttpServlet {
    
    static final String view = "/view.jsp";
    static final String filePartName = "file";
    static final String fileDatePattern = "MM/dd/yyyy'T'HH:mm:ss'Z'";
    static final String servletInfo = "Handles POST requests that upload a"
            + " file by parsing the file and forwards the data object to the"
            + " view layer.";
    static final String emptyString = "";
    static final Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    request.getInputStream()));

            // create a map of item objects uniquely identified by date and
            // description; if an item exists for that key, update the amount
            // for that existing item
            Map<String, Item> items = new HashMap<String, Item>();
            for (String line; (line = reader.readLine()) != null;) {
                // convert the line to an Item object
                Item item = parseLine(line);
                if (item == null) {
                    continue;
                }
                
                updateItems(items, item);
            }
            request.setAttribute("items", items);
            
        } catch (Exception e) {
            String message = "Failed to read uploaded file.";
            logger.error(message, e);
            request.setAttribute("error", message + ": " + e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return servletInfo;
    }

    /**
     * parses an input line of comma-separated fields; builds and returns
     * an Item object with corresponding fields populated from input values.
     * A null value is returned for a non-existing field. If the method fails
     * to parse a field, a null Item object is returned.
     * 
     * @param line
     * @return 
     */
    protected Item parseLine(String line) {
        String fields[] = line.split(",");
        // there should be at least three fields (last field may be non-existent)
        if (fields == null || fields.length < 3) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(fileDatePattern);
        Date date = null;
        try {
            if (!fields[0].equals(emptyString)) {
                date = dateFormat.parse(fields[0]);
            }
        } catch (ParseException pe) {
            logger.error("Failed to parse date " + fields[0], pe);
            return null;
        }

        Integer rowNumber = null;
        try {
            if (!fields[1].equals(emptyString)) {
                rowNumber = Integer.parseInt(fields[1]);
            }
        } catch (NumberFormatException nfe) {
            logger.error("Failed to parse row number " + fields[1], nfe);
            return null;
        }
        
        String description = "";
        if (!fields[2].equals(emptyString)) {
            description = fields[2];
        }
        
        Float value = null;
        try {
            if (fields.length > 3 && !fields[3].equals(emptyString)) {
                value = Float.parseFloat(fields[3]);
            }
        } catch (NumberFormatException nfe) {
            logger.error("Failed to parse value " + fields[3], nfe);
            return null;
        }

        Item item = new Item(date, rowNumber, description, value);
        return item;
    }

    protected void updateItems(Map<String, Item> items, Item item) {
        // identify the unique key based on date and description
        String key = "";
        if (item.getDate() != null) {
            DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
            String date = dateFormat.format(item.getDate());
            key += date;
        }
        if (item.getDescription() != null) {
            key += item.getDescription();
        }

        Item existingItem = items.get(key);
        if (existingItem != null) {
            // add new amount to existing amount if item exists
            Float amount = null;
            if (existingItem.getAmount() != null) {
                amount = existingItem.getAmount();
            }
            if (item.getAmount() != null) {
                amount += item.getAmount();
            }
            existingItem.setAmount(amount);
        } else {
            items.put(key, item);
        }
    }
}
