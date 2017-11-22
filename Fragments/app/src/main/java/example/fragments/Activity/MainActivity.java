package example.fragments.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import example.fragments.HelperPackage.ProductAdapter;
import example.fragments.ProductPackage.Product;
import example.fragments.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener {

    private static FirebaseAuth mAuth;
    private static DatabaseReference myRef;

    private ProductAdapter productAdapter;

    private List<Product> products;
    private ListView listView;

    public static List<Product> checkProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* if(products==null){
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
        registerForContextMenu(listView);*/
    }

    @Override
    public void onFragmentInteraction(Product selProduct) {
        DetailFragment fragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        if(fragment!=null && fragment.isInLayout()){
            fragment.setObject(selProduct);
        } else {
            Intent detailsIntent = new Intent(this,DetailsActivity.class);
            detailsIntent.putExtra("Name", selProduct.getName());
            detailsIntent.putExtra("Category",selProduct.getCategory());
            detailsIntent.putExtra("Price",selProduct.getPrice().toString());
            detailsIntent.putExtra("Count",selProduct.getCount().toString());
            detailsIntent.putExtra("Image",selProduct.getImagePath());
            startActivity(detailsIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.create_settings:
                startActivity(new Intent(this,AddCategoryAndName.class));
                return true;
            case R.id.edit_settings:
                Toast.makeText(this, "EDIT!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout_settings:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,Start.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

   /* @Override
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
                viewItem(product);
                return true;
            case R.id.edit:
                editItem(product);
                return true;
            case R.id.delete:
                deleteItem(product);
                return true;
            case R.id.copy:
                copyItem(product);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }*/

    private void copyItem(Product product) {
        ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("",product.toString());
        clipboard.setPrimaryClip(clip);
    }

    private void viewItem(Product product) {
        Boolean check = false;
        checkProducts = new ArrayList<>();
        for (Product tmp : products){
            if(tmp.getFavorite()==1){
                checkProducts.add(tmp);
                check=true;
            }
        }
        if(!check){
            checkProducts.add(product);
        }
        Intent intent = new Intent(this,FavoriteAct.class);
        startActivity(intent);
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
        Toast.makeText(this, "Del", Toast.LENGTH_SHORT).show();
        Boolean check = false;
        checkProducts = new ArrayList<>();
        for (Product tmp : products){
            if(tmp.getFavorite()==1){
                checkProducts.add(tmp);
                check=true;
            }
        }
        if(!check){
            SearchActivity.DeleteByName(product.getName());
        }
        else {
            SearchActivity.DeleteArray();
        }
        Intent tmp = getIntent();
        overridePendingTransition(0,0);
        tmp.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(tmp);
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