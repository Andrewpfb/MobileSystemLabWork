package com.example.frost.sqlite.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.frost.sqlite.HelperPackage.DatabaseHelper;
import com.example.frost.sqlite.HelperPackage.ProductAdapter;
import com.example.frost.sqlite.ProductPackage.Product;
import com.example.frost.sqlite.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private List<Product> products;
    private ProgressBar progressBar;
    private ListView listView;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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