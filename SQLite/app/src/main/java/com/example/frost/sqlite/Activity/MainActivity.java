package com.example.frost.sqlite.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.frost.sqlite.HelperPackage.DatabaseHelper;
import com.example.frost.sqlite.HelperPackage.ProductAdapter;
import com.example.frost.sqlite.ProductPackage.Product;
import com.example.frost.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        products = db.getAllProducts();
        if(products==null) {
            products = new ArrayList<>();
        }
        ListView listView = (ListView) findViewById(R.id.MA_ProductsList);
        ProductAdapter productAdapter = new ProductAdapter(this,R.layout.item,products, db);
        listView.setAdapter(productAdapter);
    }

    public void CreateClick(View view){
        Intent intent = new Intent(this,AddCategoryAndName.class);
        startActivity(intent);
    }

    public void SearchClickAct(View view){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    public void FavoriteClick(View view){
        Intent intent = new Intent(this,FavoriteAct.class);
        startActivity(intent);
    }
}