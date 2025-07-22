package com.example.qrcodescanner.models;

public class ProductItem {
    private String name;
    private String price;
    private int imageResId;
    private int quantity;

    public ProductItem(String name, String price, int imageResId, int quantity) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return Double.parseDouble(price.replace("$", "")) * quantity; // Assuming price is in the format "$10.00"
    }
}