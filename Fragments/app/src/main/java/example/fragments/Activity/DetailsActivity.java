package example.fragments.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.fragments.HelperPackage.BitmapHelper;
import example.fragments.ProductPackage.Product;
import example.fragments.R;


public class DetailsActivity extends AppCompatActivity {

    /*private TextView nameTV,categoryTV,priceTV,countTV;
    private ImageView imageView;
    private String bitmapBase64;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_detail);
        /*nameTV = (TextView) findViewById(R.id.DA_nameTV);
        categoryTV = (TextView) findViewById(R.id.DA_categoryTV);
        priceTV = (TextView) findViewById(R.id.DA_priceTV);
        countTV = (TextView) findViewById(R.id.DA_countTV);
        imageView = (ImageView) findViewById(R.id.DA_imgView);*/

        Intent extras = getIntent();
        if (extras != null) {
            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
            detailFragment.setObject(new Product(
                    extras.getStringExtra("Name"),
                    extras.getStringExtra("Category"),
                    Double.parseDouble(extras.getStringExtra("Price")),
                    Integer.parseInt(extras.getStringExtra("Count")),
                    extras.getStringExtra("Image"),
                    0
            ));
            /*nameTV.setText(extras.getStringExtra("Name"));
            categoryTV.setText(extras.getStringExtra("Category"));
            priceTV.setText(extras.getStringExtra("Price"));
            countTV.setText(extras.getStringExtra("Count"));
            bitmapBase64 = extras.getStringExtra("Image");
            imageView.setImageBitmap(BitmapHelper.StringToBitMap(bitmapBase64));*/
        }
    }

    public void CancelClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
