package com.example.frost.sqlite.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frost.sqlite.HelperPackage.BitmapHelper;
import com.example.frost.sqlite.HelperPackage.DatabaseHelper;
import com.example.frost.sqlite.ProductPackage.ControlToProduct;
import com.example.frost.sqlite.ProductPackage.Product;
import com.example.frost.sqlite.R;

public class ResultActivity extends AppCompatActivity {
    private TextView name,category,price,count;
    private String bitmapBase64;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        name = (TextView) findViewById(R.id.RL_nameTV);
        category = (TextView) findViewById(R.id.RL_categoryTV);
        price = (TextView) findViewById(R.id.RL_priceTV);
        count = (TextView) findViewById(R.id.RL_countTV);
        ImageView imageView = (ImageView) findViewById(R.id.RL_imgView);

        Intent extras = getIntent();
        if(extras!=null){
            name.setText(extras.getStringExtra("Name"));
            category.setText(extras.getStringExtra("Category"));
            price.setText(extras.getStringExtra("Price"));
            count.setText(extras.getStringExtra("Count"));
            bitmapBase64 = extras.getStringExtra("Image");
            imageView.setImageBitmap(BitmapHelper.StringToBitMap(bitmapBase64));
        }
    }

    public void CancelClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void BackClick(View view){
        Intent intent = new Intent(this,AddPriceAndCountAndImg.class);
        Intent extras = getIntent();
        intent.putExtra("Category", extras.getStringExtra("Category"));
        intent.putExtra("Name", extras.getStringExtra("Name"));
        intent.putExtra("Price", extras.getStringExtra("Price"));
        intent.putExtra("Count", extras.getStringExtra("Count"));
        intent.putExtra("Image", bitmapBase64);
        startActivity(intent);
    }
    public void SaveClick(View view){
        Product product = new Product(name.getText().toString(),category.getText().toString(),
                Double.parseDouble(price.getText().toString()),Integer.parseInt(count.getText().toString()),bitmapBase64,0);
        ControlToProduct.AddProductToList(new DatabaseHelper(this),product);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}