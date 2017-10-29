package com.example.frost.online_store.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.frost.online_store.Activity.AddCategoryAndName;
import com.example.frost.online_store.HelperPackage.DatabaseHelper;
import com.example.frost.online_store.HelperPackage.JsonHelper;
import com.example.frost.online_store.HelperPackage.ProductAdapter;
import com.example.frost.online_store.ProductPackage.ControlToProduct;
import com.example.frost.online_store.ProductPackage.Product;
import com.example.frost.online_store.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products;
    ListView listView;
    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        /*products = JsonHelper.importFromJSON(context);
        if(products==null) {
            products = new ArrayList<>();
        }*/
        databaseHelper = new DatabaseHelper(context);
       // ShowProducts();
    }

    @Override
    public void onResume(){
        super.onResume();
        db = databaseHelper.getReadableDatabase();

        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_YEAR};
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        listView.setAdapter(userAdapter);
        /*userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
        String[] headers = new String[]{
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_CATEGORY,
                DatabaseHelper.COLUMN_PRICE,
                DatabaseHelper.COLUMN_COUNT
        };
        userAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                userCursor,
                headers,
                new int[]{android.R.id.text1, android.R.id.text2},
                0
        );
        listView.setAdapter(userAdapter);*/
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
        userCursor.close();
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
