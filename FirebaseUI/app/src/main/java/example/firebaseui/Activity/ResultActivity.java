package example.firebaseui.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import example.firebaseui.HelperPackage.BitmapHelper;
import example.firebaseui.HelperPackage.FirebaseHelper;
import example.firebaseui.ProductPackage.Product;
import example.firebaseui.R;

public class ResultActivity extends AppCompatActivity {
    private TextView name, category, price, count;
    private String bitmapBase64;
    private AlertDialog.Builder confirmDialog;
    private String title = "Confirm the create";
    private String message = "Are you sure?";
    private String yesBtn = "Yes";
    private String noBtn = "No";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        name = (TextView) findViewById(R.id.RL_nameTV);
        category = (TextView) findViewById(R.id.RL_categoryTV);
        price = (TextView) findViewById(R.id.RL_priceTV);
        count = (TextView) findViewById(R.id.RL_countTV);
        ImageView imageView = (ImageView) findViewById(R.id.RL_imgView);

        Intent extras = getIntent();
        if (extras != null) {
            name.setText(extras.getStringExtra("Name"));
            category.setText(extras.getStringExtra("Category"));
            price.setText(extras.getStringExtra("Price"));
            count.setText(extras.getStringExtra("Count"));
            bitmapBase64 = extras.getStringExtra("Image");
            imageView.setImageBitmap(BitmapHelper.StringToBitMap(bitmapBase64));
        }
    }

    public void CancelClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void BackClick(View view) {
        Intent intent = new Intent(this, AddPriceAndCountAndImg.class);
        Intent extras = getIntent();
        intent.putExtra("Category", extras.getStringExtra("Category"));
        intent.putExtra("Name", extras.getStringExtra("Name"));
        intent.putExtra("Price", extras.getStringExtra("Price"));
        intent.putExtra("Count", extras.getStringExtra("Count"));
        intent.putExtra("Image", bitmapBase64);
        startActivity(intent);
    }

    public void SaveClick(View view) {
        confirmDialog = new AlertDialog.Builder(this);
        confirmDialog.setTitle(title);
        confirmDialog.setMessage(message);
        confirmDialog.setPositiveButton(yesBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ResultActivity.this, "Product was created", Toast.LENGTH_SHORT).show();
                Product product = new Product(name.getText().toString(), category.getText().toString(),
                        Double.parseDouble(price.getText().toString()), Integer.parseInt(count.getText().toString()), bitmapBase64, 0);
                FirebaseHelper.Write(product);
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        confirmDialog.setNegativeButton(noBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ResultActivity.this, "No", Toast.LENGTH_SHORT).show();
            }
        });
        confirmDialog.setCancelable(true);
        confirmDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(ResultActivity.this, "Press button", Toast.LENGTH_SHORT).show();
            }
        });
        confirmDialog.show();
    }
}