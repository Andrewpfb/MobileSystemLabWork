package com.example.firebase.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.firebase.HelperPackage.DatabaseHelper;
import com.example.firebase.HelperPackage.ProductAdapter;
import com.example.firebase.ProductPackage.Product;
import com.example.firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private List<Product> products;
    private ProgressBar progressBar;
    private ListView listView;
    private FirebaseAuth mFireBaseAuth;
    private FirebaseUser mFireBaseUser;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFireBaseAuth = FirebaseAuth.getInstance();
        mFireBaseUser = mFireBaseAuth.getCurrentUser();
        if(mFireBaseUser==null){
            startActivity(new Intent(this,SignInActivity.class));
            finish();
            return;
        }else {
            Toast.makeText(this, "Hello, " + mFireBaseUser.getDisplayName(), Toast.LENGTH_LONG).show();
        }
        db = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.MA_ProductsList);
        progressBar = (ProgressBar) findViewById(R.id.MA_progressBar);
        /*try {
            ShowFunction();
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        ListTask listTask = new ListTask();
        listTask.execute();
    }

    private void ShowFunction() throws InterruptedException {
        products=db.getAllProducts();
        if(products==null){
            products = new ArrayList<>();
        }
        ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(),R.layout.item,products,db);
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

    class ListTask extends AsyncTask<Void,Integer,List<Product>>{

        @Override
        protected void onPostExecute(List<Product> result){
            ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(),R.layout.item,products,db);
            listView.setAdapter(productAdapter);
        }
        @Override
        protected List<Product> doInBackground(Void... params) {
            try {
                int counter = 0;
                for(int i=0;i<5;i++) {
                    TimeUnit.SECONDS.sleep(1);
                    counter+=20;
                    publishProgress(counter);
                }
                products = db.getAllProducts();
                if (products == null) {
                    products = new ArrayList<>();
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return products;
        }
        @Override
        protected void onProgressUpdate(Integer... values){
            progressBar.setProgress(values[0]);
        }
    }
}