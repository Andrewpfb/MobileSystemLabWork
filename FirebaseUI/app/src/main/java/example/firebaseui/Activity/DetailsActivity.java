package example.firebaseui.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.firebaseui.HelperPackage.BitmapHelper;
import example.firebaseui.R;

public class DetailsActivity extends AppCompatActivity {

    private TextView nameTV,categoryTV,priceTV,countTV;
    private ImageView imageView;
    private String bitmapBase64;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameTV = (TextView) findViewById(R.id.DA_nameTV);
        categoryTV = (TextView) findViewById(R.id.DA_categoryTV);
        priceTV = (TextView) findViewById(R.id.DA_priceTV);
        countTV = (TextView) findViewById(R.id.DA_countTV);
        imageView = (ImageView) findViewById(R.id.DA_imgView);

        Intent extras = getIntent();
        if(extras!=null){
            nameTV.setText(extras.getStringExtra("Name"));
            categoryTV.setText(extras.getStringExtra("Category"));
            priceTV.setText(extras.getStringExtra("Price"));
            countTV.setText(extras.getStringExtra("Count"));
            bitmapBase64 = extras.getStringExtra("Image");
            imageView.setImageBitmap(BitmapHelper.StringToBitMap(bitmapBase64));
        }
    }

    public void CancelClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
