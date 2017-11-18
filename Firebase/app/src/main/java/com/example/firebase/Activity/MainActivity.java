package com.example.firebase.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.firebase.HelperPackage.ProductAdapter;
import com.example.firebase.ProductPackage.Product;
import com.example.firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static FirebaseAuth mAuth;
    private static DatabaseReference myRef;


    private List<Product> products;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(products==null){
            products = new ArrayList<>();
        }
        listView = (ListView) findViewById(R.id.MA_ProductsList);
        ShowFunction();
    }

    private void ShowFunction() {
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("Products");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                products.add(dataSnapshot.getValue(Product.class));
                updateUI();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addChildEventListener(childEventListener);
    }

    private void updateUI() {
        ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(),R.layout.item,products);
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