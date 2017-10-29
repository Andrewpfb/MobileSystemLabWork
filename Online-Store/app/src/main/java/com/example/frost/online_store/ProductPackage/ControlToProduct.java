package com.example.frost.online_store.ProductPackage;

import android.content.Context;

import com.example.frost.online_store.HelperPackage.JsonHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by frost on 14.10.2017.
 */

public class ControlToProduct {

    private static List<Product> products;
    public static void AddProductToList(Product product,Context context){
        products = JsonHelper.importFromJSON(context);
        if(products == null){
            products = new ArrayList<>();
        }
        products.add(product);
        JsonHelper.exportToJSON(context,products);
    }
    public static void DeleteProductFromList(String productName,Context context){
        products = JsonHelper.importFromJSON(context);
        for(Product product:products){
            if(product.getName().compareTo(productName)==0){
                products.remove(product);
            }
        }
        JsonHelper.exportToJSON(context,products);
    }
    public static Product SearchProductInList(String name,List<Product> productLists){
        Product tmpProduct = null;
        for(Product product:productLists){
            if(product.getName().compareTo(name)==0){
                tmpProduct=product;
            }
        }
        return tmpProduct;
    }
    public static void SortProductsList(List<Product> productLists){
        ProductSort ps = new ProductSort();
        Collections.sort(productLists,ps);
    }
}
