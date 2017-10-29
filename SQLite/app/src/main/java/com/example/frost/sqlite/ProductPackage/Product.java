package com.example.frost.sqlite.ProductPackage;

/**
 * Created by frost on 05.10.2017.
 */

public class Product {

    private int id;
    private String name;
    private String category;
    private Double price;
    private Integer count;
    private String imagePath;
    private Integer favorite;

    public Product(){}
    public Product(int id,String name,String category, Double price ,Integer count, String imagePath){
        this.id = id;
        this.category=category;
        this.price=price;
        this.name=name;
        this.imagePath=imagePath;
        this.count=count;
        this.favorite = 0;
    }
    public Product(String name,String category, Double price ,Integer count, String imagePath){
        this.category=category;
        this.price=price;
        this.name=name;
        this.imagePath=imagePath;
        this.count=count;
        this.favorite = 0;
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
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
    public Integer getFavorite() {return favorite;}
    public void setFavorite(Integer favorite){this.favorite = favorite>0?1:0;}

    @Override
    public String toString() {
        return this.category + "\n" + this.name + "\n" + this.price + "\n" + this.count + "\n" + this.imagePath;
    }
}
