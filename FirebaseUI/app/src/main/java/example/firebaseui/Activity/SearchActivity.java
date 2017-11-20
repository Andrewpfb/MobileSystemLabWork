package example.firebaseui.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import example.firebaseui.HelperPackage.BitmapHelper;
import example.firebaseui.HelperPackage.FirebaseHelper;
import example.firebaseui.ProductPackage.Product;
import example.firebaseui.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends AppCompatActivity {
    private static EditText searchName;
    private static TextView nameTV, categoryTV, countTV, priceTV;
    private static ImageView imageView;
    private static Product product;
    private static FirebaseUser user;
    private static FirebaseAuth mAuth;
    private static DatabaseReference myRef;
    private static DatabaseReference delRef;
    private static Query query;
    private static Boolean check=false;
    private AlertDialog.Builder confirmDialog;
    private String title = "Confirm the delete";
    private String message = "Are you sure?";
    private String yesBtn = "Yes";
    private String noBtn = "No";

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

    public static void Search(String searchName) {
        user = mAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("Products");
        query = myRef.orderByChild("name").equalTo(searchName).limitToFirst(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    product = snapshot.getValue(Product.class);
                    nameTV.setText(product.getName());
                    categoryTV.setText(product.getCategory());
                    priceTV.setText(product.getPrice().toString());
                    countTV.setText(product.getCount().toString());
                    imageView.setImageBitmap(BitmapHelper.StringToBitMap(product.getImagePath()));
                    if(check) {
                        snapshot.getRef().removeValue();
                    } else{
                        delRef = snapshot.getRef();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void Update() {
        if (product != null) {
            Delete();
            Intent intent = new Intent(this, UpdateActivity.class);
            intent.putExtra("Name", product.getName());
            intent.putExtra("Category", product.getCategory());
            intent.putExtra("Price", product.getPrice().toString());
            intent.putExtra("Count", product.getCount().toString());
            intent.putExtra("Image", product.getImagePath());
            intent.putExtra("Favorite", product.getFavorite().toString());
            startActivity(intent);
        }
    }

    static public void DeleteArray() {
        check=true;
        for (Product tmp : MainActivity.checkProducts) {
            Search(tmp.getName());
            Delete();
        }
        check=false;
    }

    static public void DeleteByName(String name) {
        Search(name);
        Delete();
    }

    private static void Delete() {
        if (product != null) {
            delRef.removeValue();
        }
    }


    public void SearchClick(View view) {
        Search(searchName.getText().toString());
    }

    public void DeleteClick(View view) {
        confirmDialog = new AlertDialog.Builder(this);
        confirmDialog.setTitle(title);
        confirmDialog.setMessage(message);
        confirmDialog.setPositiveButton(yesBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SearchActivity.this, "Product was deleted", Toast.LENGTH_SHORT).show();
                Delete();
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        confirmDialog.setNegativeButton(noBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SearchActivity.this, "No", Toast.LENGTH_SHORT).show();
            }
        });
        confirmDialog.setCancelable(true);
        confirmDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(SearchActivity.this, "Press button", Toast.LENGTH_SHORT).show();
            }
        });
        confirmDialog.show();
    }

    public void CancelClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void UpdateClick(View view) {
        Update();
    }
}
