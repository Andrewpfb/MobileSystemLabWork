package com.example.frost.sqlite.ProductPackage;

import com.example.frost.sqlite.HelperPackage.DatabaseHelper;

/**
 * Created by frost on 14.10.2017.
 */

public class ControlToProduct {

    public static void AddProductToList(DatabaseHelper db, Product product){
        db.addProduct(product);
    }
    public static void DeleteProductFromList(DatabaseHelper db,Product product){
       db.deleteProduct(product);
    }
    public static Product SearchProductInList(DatabaseHelper db,String name){
        return db.getProductByName(name);
    }
    public static void UpdateProduct(DatabaseHelper db, Product product){
        db.updateProduct(product);
    }
    public static void DeleteAllProduct(DatabaseHelper db){
        db.deleteAllProducts();
    }
}
