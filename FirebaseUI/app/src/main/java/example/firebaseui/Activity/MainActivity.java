package example.firebaseui.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import example.firebaseui.HelperPackage.ProductAdapter;
import example.firebaseui.ProductPackage.Product;
import example.firebaseui.R;
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

    private ProductAdapter productAdapter;

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
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ShowFunction();

        final Intent detailsIntent = new Intent(this,DetailsActivity.class);
       AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener(){
           @Override
           public void onItemClick(AdapterView<?> parent, View v, int position, long id){
              Product selProduct = (Product)parent.getItemAtPosition(position);
               detailsIntent.putExtra("Name", selProduct.getName());
               detailsIntent.putExtra("Category",selProduct.getCategory());
               detailsIntent.putExtra("Price",selProduct.getPrice().toString());
               detailsIntent.putExtra("Count",selProduct.getCount().toString());
               detailsIntent.putExtra("Image",selProduct.getImagePath());
               startActivity(detailsIntent);
           }
       };
       listView.setOnItemClickListener(itemListener);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Product product = productAdapter.getItem(info.position);
        switch (item.getItemId()){
            case R.id.view:
                viewItem();
                return true;
            case R.id.edit:
                editItem(product);
                return true;
            case R.id.delete:
                deleteItem(product);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void viewItem() {
        Toast.makeText(this, "View", Toast.LENGTH_SHORT).show();
    }

    private void editItem(Product product) {
        Intent intent = new Intent(this,UpdateActivity.class);
        intent.putExtra("Name",product.getName());
        intent.putExtra("Category",product.getCategory());
        intent.putExtra("Price",product.getPrice().toString());
        intent.putExtra("Count",product.getCount().toString());
        intent.putExtra("Image",product.getImagePath());
        intent.putExtra("Favorite",product.getFavorite().toString());
        startActivity(intent);
    }

    private void deleteItem(Product product) {
        Toast.makeText(this, "Del" + product.toString(), Toast.LENGTH_SHORT).show();
        SearchActivity.DeleteByName(product.getName());
        //onCreate();
        //products.remove(product);
        //updateUI();
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
                //Toast.makeText(MainActivity.this, "Removed", Toast.LENGTH_SHORT).show();
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
        productAdapter = new ProductAdapter(getApplicationContext(),R.layout.item,products);
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