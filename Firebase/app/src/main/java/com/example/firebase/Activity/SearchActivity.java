package com.example.firebase.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firebase.HelperPackage.BitmapHelper;
import com.example.firebase.HelperPackage.FirebaseHelper;
import com.example.firebase.ProductPackage.Product;
import com.example.firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends AppCompatActivity {
    private EditText searchName,nameTV,categoryTV,countTV,priceTV;
    private ImageView imageView;
    private Product product;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private DatabaseReference delRef;
    private Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        searchName = (EditText) findViewById(R.id.SL_nameET);
        nameTV = (EditText) findViewById(R.id.SL_nameTV);
        categoryTV = (EditText) findViewById(R.id.SL_categoryTV);
        priceTV = (EditText) findViewById(R.id.SL_priceTV);
        countTV = (EditText) findViewById(R.id.SL_countTV);
        imageView = (ImageView) findViewById(R.id.SL_imgView);
    }

    public void SearchClick(View view){
        user = mAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("Products");
        query= myRef.orderByChild("name").equalTo(searchName.getText().toString()).limitToFirst(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    product = snapshot.getValue(Product.class);
                    nameTV.setText(product.getName());
                    categoryTV.setText(product.getCategory());
                    priceTV.setText(product.getPrice().toString());
                    countTV.setText(product.getCount().toString());
                    imageView.setImageBitmap(BitmapHelper.StringToBitMap(product.getImagePath()));
                    delRef = snapshot.getRef();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Product doesn't find", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void DeleteBeforeUpdate(){
        if(product!=null) {
            delRef.removeValue();
        }
    }
    public void DeleteClick(View view){
        if(product!=null){
            delRef.removeValue();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    public void CancelClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void UpdateClick(View view){
        if(product!=null) {
            DeleteBeforeUpdate();
        }
        imageView.setDrawingCacheEnabled(true);
        Product newProduct = new Product(
                nameTV.getText().toString(),
                categoryTV.getText().toString(),
                Double.parseDouble(priceTV.getText().toString()),
                Integer.parseInt(countTV.getText().toString()),
                product.getImagePath(),
                product.getFavorite()
        );
        FirebaseHelper.Write(newProduct);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
