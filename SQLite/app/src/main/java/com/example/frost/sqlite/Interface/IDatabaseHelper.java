package com.example.frost.sqlite.Interface;

import com.example.frost.sqlite.ProductPackage.Product;

import java.util.List;

/**
 * Created by frost on 29.10.2017.
 */

public interface IDatabaseHelper {
    void addProduct(Product product);
    Product getProduct(int id);
    Product getProductByName(String name);
    List<Product> getAllProducts();
    List<Product> getFavoriteProducts();
    int getProductsCount();
    int updateProduct(Product product);
    void deleteProduct(Product product);
    void deleteAllProducts();
}
