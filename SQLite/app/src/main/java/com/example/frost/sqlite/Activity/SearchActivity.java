package com.example.frost.sqlite.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frost.sqlite.HelperPackage.BitmapHelper;
import com.example.frost.sqlite.HelperPackage.DatabaseHelper;
import com.example.frost.sqlite.ProductPackage.ControlToProduct;
import com.example.frost.sqlite.ProductPackage.Product;
import com.example.frost.sqlite.R;

public class SearchActivity extends AppCompatActivity {
    private EditText searchName;
    private TextView nameTV,categoryTV,countTV,priceTV;
    private ImageView imageView;
    private Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        searchName = (EditText) findViewById(R.id.SL_nameET);
        nameTV = (TextView) findViewById(R.id.SL_nameTV);
        categoryTV = (TextView) findViewById(R.id.SL_categoryTV);
        priceTV = (TextView) findViewById(R.id.SL_priceTV);
        countTV = (TextView) findViewById(R.id.SL_countTV);
        imageView = (ImageView) findViewById(R.id.SL_imgView);
    }

    public void SearchClick(View view){
        product = ControlToProduct.SearchProductInList(new DatabaseHelper(this),searchName.getText().toString());
        if(product!=null) {
            nameTV.setText(product.getName());
            categoryTV.setText(product.getCategory());
            priceTV.setText(product.getPrice().toString());
            countTV.setText(product.getCount().toString());
            imageView.setImageBitmap(BitmapHelper.StringToBitMap(product.getImagePath()));
        }
        else {
            Toast.makeText(getApplicationContext(),"Product doesn't find",Toast.LENGTH_LONG).show();
        }
    }
    public void DeleteClick(View view){
        if(product!=null){
            ControlToProduct.DeleteProductFromList(new DatabaseHelper(this), product);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
