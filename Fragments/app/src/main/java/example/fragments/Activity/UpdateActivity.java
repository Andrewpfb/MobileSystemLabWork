package example.fragments.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import example.fragments.HelperPackage.BitmapHelper;
import example.fragments.HelperPackage.FirebaseHelper;
import example.fragments.ProductPackage.Product;
import example.fragments.R;

public class UpdateActivity extends AppCompatActivity {

    private TextView nameTV,categoryTV,priceTV,countTV;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameTV = (EditText) findViewById(R.id.UA_nameTV);
        categoryTV = (EditText) findViewById(R.id.UA_categoryTV);
        priceTV = (EditText) findViewById(R.id.UA_priceTV);
        countTV = (EditText) findViewById(R.id.UA_countTV);
        imgView = (ImageView) findViewById(R.id.UA_imgView);
        imgView.setDrawingCacheEnabled(true);

        nameTV.setText(getIntent().getStringExtra("Name"));
        categoryTV.setText(getIntent().getStringExtra("Category"));
        priceTV.setText(getIntent().getStringExtra("Price"));
        countTV.setText(getIntent().getStringExtra("Count"));
        imgView.setImageBitmap(BitmapHelper.StringToBitMap(getIntent().getStringExtra("Image")));

    }

    public void UpdateClick(View view){
        FirebaseHelper.Write(new Product(
                nameTV.getText().toString(),
                categoryTV.getText().toString(),
                Double.parseDouble(priceTV.getText().toString()),
                Integer.parseInt(countTV.getText().toString()),
                getIntent().getStringExtra("Image"),
                Integer.parseInt(getIntent().getStringExtra("Favorite"))
        ));
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void CancelClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
