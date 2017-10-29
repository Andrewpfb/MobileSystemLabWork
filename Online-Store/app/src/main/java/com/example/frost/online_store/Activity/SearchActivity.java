package com.example.frost.online_store.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frost.online_store.HelperPackage.BitmapHelper;
import com.example.frost.online_store.HelperPackage.JsonHelper;
import com.example.frost.online_store.ProductPackage.ControlToProduct;
import com.example.frost.online_store.ProductPackage.Product;
import com.example.frost.online_store.R;


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
        if(searchName.getText().toString()!=null) {
            product = ControlToProduct.SearchProductInList(searchName.getText().toString(),
                    JsonHelper.importFromJSON(getApplicationContext()));
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
        else {
            Toast.makeText(getApplicationContext(),"Enter the name",Toast.LENGTH_LONG).show();
        }
    }
    public void DeleteClick(View view){
        if(product!=null){
            ControlToProduct.DeleteProductFromList(product.getName(),getApplicationContext());
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
