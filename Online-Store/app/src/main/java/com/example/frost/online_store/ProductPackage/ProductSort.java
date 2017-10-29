package com.example.frost.online_store.ProductPackage;

/**
 * Created by frost on 28.10.2017.
 */

import java.util.Comparator;

public class ProductSort implements Comparator<Product> {
    public int compare(Product one, Product two){
        return one.getName().compareTo(two.getName());
    }
}
