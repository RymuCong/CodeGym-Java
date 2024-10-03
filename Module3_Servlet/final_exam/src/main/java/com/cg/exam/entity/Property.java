package com.cg.exam.entity;

import java.util.Date;

public class Property {
    private String propertyId;
    private String status;
    private double area;
    private int floor;
    private String type;
    private double price;
    private Date startDate;
    private Date endDate;

    public Property() {}

    public Property(String propertyId, String status, double area, int floor, String type, double price, Date startDate, Date endDate) {
        this.propertyId = propertyId;
        this.status = status;
        this.area = area;
        this.floor = floor;
        this.type = type;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId='" + propertyId + '\'' +
                ", status='" + status + '\'' +
                ", area=" + area +
                ", floor=" + floor +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}