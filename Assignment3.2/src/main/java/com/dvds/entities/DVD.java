package com.dvds.entities;

import java.io.Serializable;

public class DVD implements Serializable {

    private String title;
    private Integer year;
    private Integer length;

    public DVD(String title, Integer year, Integer length) {
        this.title = title;
        this.year = year;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", length=" + length +
                '}';
    }
}
