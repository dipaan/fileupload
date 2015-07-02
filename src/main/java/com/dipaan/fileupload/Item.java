package com.dipaan.fileupload;

import java.util.Date;

public class Item {
    private Date date;
    private Integer rowNumber;
    private String description;
    private Float amount;

    public Item(Date date, Integer rowNumber, String description, Float amount) {
        this.date = date;
        this.rowNumber = rowNumber;
        this.description = description;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Integer getRowNumber() {
        return rowNumber;
    }
    
    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Float getAmount() {
        return amount;
    }
    
    public void setAmount(Float amount) {
        this.amount = amount;
    }

}
