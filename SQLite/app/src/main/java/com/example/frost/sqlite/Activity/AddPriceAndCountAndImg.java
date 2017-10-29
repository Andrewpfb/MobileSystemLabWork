package com.example.frost.sqlite.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.frost.sqlite.HelperPackage.BitmapHelper;
import com.example.frost.sqlite.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddPriceAndCountAndImg extends AppCompatActivity {
    static final int GALLERY_REQUEST = 1;
    private EditText priceEditText, countEditText;
    private ImageView imageView;
    private String bitmapBase64;
    private boolean checkImgFlag=false;
    final static String textViewCountKey = "TEXTVIEW_COUNT_KEY";
    final static String textViewPriceKey = "TEXTVIEW_PRICE_KEY";
    final static String textViewImageKey = "TEXTVIEW_IMAGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_price_and_count_and_image);
        priceEditText = (EditText) findViewById(R.id.APACAI_priceTV);
        countEditText = (EditText) findViewById(R.id.APACAI_countTV);
        imageView = (ImageView) findViewById(R.id.APACAI_imgView);
        Intent extras = getIntent();
        if(extras!=null){
            priceEditText.setText(extras.getStringExtra("Price"));
            countEditText.setText(extras.getStringExtra("Count"));
            imageView.setImageBitmap(BitmapHelper.StringToBitMap(extras.getStringExtra("Image")));
        }
    }

    public void AddImgClick(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    public void CancelClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void BackClick(View view) {
        Intent intent = new Intent(this, AddCategoryAndName.class);
        intent.putExtra("Category", getIntent().getStringExtra("Category"));
        intent.putExtra("Name", getIntent().getStringExtra("Name"));
        startActivity(intent);
    }

    public void NextClick(View view) {
        if(priceEditText.getText().toString().isEmpty()
                & countEditText.getText().toString().isEmpty()
                & checkImgFlag==false){
            Toast.makeText(getApplicationContext(),"Enter the field",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("Category", getIntent().getStringExtra("Category"));
            intent.putExtra("Name", getIntent().getStringExtra("Name"));
            intent.putExtra("Price", priceEditText.getText().toString());
            intent.putExtra("Count", countEditText.getText().toString());
            intent.putExtra("Image", bitmapBase64);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(selectedImage);
                        bitmapBase64 = BitmapHelper.BitMapToString(selectedImage);
                        checkImgFlag=true;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        checkImgFlag=false;
                    }
                }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        priceEditText = (EditText) findViewById(R.id.APACAI_priceTV);
        countEditText = (EditText) findViewById(R.id.APACAI_countTV);
        outState.putString(textViewCountKey,countEditText.getText().toString());
        outState.putString(textViewPriceKey,priceEditText.getText().toString());
        outState.putString(textViewImageKey,bitmapBase64);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        priceEditText = (EditText) findViewById(R.id.APACAI_priceTV);
        countEditText = (EditText) findViewById(R.id.APACAI_countTV);
        imageView = (ImageView) findViewById(R.id.APACAI_imgView);
        countEditText.setText(savedInstanceState.getString(textViewCountKey));
        priceEditText.setText(savedInstanceState.getString(textViewPriceKey));
        imageView.setImageBitmap(BitmapHelper.StringToBitMap(savedInstanceState.getString(textViewImageKey)));
    }
}
