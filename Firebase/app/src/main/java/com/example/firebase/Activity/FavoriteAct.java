package com.example.firebase.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.firebase.HelperPackage.ProductAdapter;
import com.example.firebase.ProductPackage.Product;
import com.example.firebase.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_item);
        ListView favoriteListView = (ListView) findViewById(R.id.FI_ProductsList);
        List<Product> products;
    }
}
