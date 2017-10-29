package com.example.frost.sqlite.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.frost.sqlite.HelperPackage.DatabaseHelper;
import com.example.frost.sqlite.HelperPackage.ProductAdapter;
import com.example.frost.sqlite.ProductPackage.Product;
import com.example.frost.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_item);
        ListView favoriteListView = (ListView) findViewById(R.id.FI_ProductsList);
        List<Product> products;
        DatabaseHelper db = new DatabaseHelper(this);
        //products = db.getFavoriteProducts();
       /* if(products==null){
            products = new ArrayList<>();
        }
        ProductAdapter productAdapter = new ProductAdapter(this,R.layout.item,products);
        favoriteListView.setAdapter(productAdapter);*/
    }
}
