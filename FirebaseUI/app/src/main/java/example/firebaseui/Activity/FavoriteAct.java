package example.firebaseui.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import example.firebaseui.HelperPackage.FavoriteProductAdapter;
import example.firebaseui.HelperPackage.ProductAdapter;
import example.firebaseui.ProductPackage.Product;
import example.firebaseui.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_item);
        ListView favoriteListView = (ListView) findViewById(R.id.FI_ProductsList);
        if(MainActivity.checkProducts!=null) {
            FavoriteProductAdapter favoriteAdapter = new FavoriteProductAdapter(this,R.layout.f_item,MainActivity.checkProducts);
            favoriteListView.setAdapter(favoriteAdapter);
        }
    }

    public void CancelClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
