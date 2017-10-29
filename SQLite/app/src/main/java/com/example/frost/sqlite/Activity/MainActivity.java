package com.example.frost.sqlite.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.frost.sqlite.HelperPackage.ProductAdapter;
import com.example.frost.sqlite.ProductPackage.ControlToProduct;
import com.example.frost.sqlite.ProductPackage.Product;
import com.example.frost.sqlite.HelperPackage.JsonHelper;
import com.example.frost.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products;
    ListView listView;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        products = JsonHelper.importFromJSON(context);
        if(products==null) {
            products = new ArrayList<>();
        }
        ShowProducts();
    }

    public void CreateClick(View view){
        Intent intent = new Intent(this,AddCategoryAndName.class);
        startActivity(intent);
    }

    public void SortClick(View view){
        ControlToProduct.SortProductsList(products);
        ShowProducts();
    }

    public void SearchClickAct(View view){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    public void ShowProducts(){
        listView = (ListView) findViewById(R.id.MA_ProductsList);
        ProductAdapter productAdapter = new ProductAdapter(this,R.layout.item,products);
        listView.setAdapter(productAdapter);
    }
}