package com.example.frost.online_store.ProductPackage;

/**
 * Created by frost on 05.10.2017.
 */

public class Product {

    private String name;
    private String category;
    private Double price;
    private Integer count;
    private String imagePath;

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public Integer getCount(){return count;}
    public void setCount(Integer count) {
        this.count = count;
    }

    public Product(String name,String category, Double price ,Integer count, String imagePath){
        this.category=category;
        this.price=price;
        this.name=name;
        this.imagePath=imagePath;
        this.count=count;
    }

    @Override
    public String toString() {
        return this.category + "\n" + this.name + "\n" + this.price + "\n" + this.count + "\n" + this.imagePath;
    }
}
