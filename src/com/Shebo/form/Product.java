/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Shebo.form;

/**
 *
 * @author mm729
 */
public class Product {

    private int id, quantity;
    private double price;
    private String name, srcImage;

    public Product(int id, int quantity, double price, String name, String srcImage) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.srcImage = srcImage;
    }

    public Product() {
    }

    public void reset() {
        id = quantity = 0;
        price = 0.0;
        name = "";
        srcImage = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(String srcImage) {
        this.srcImage = srcImage;
    }

}
