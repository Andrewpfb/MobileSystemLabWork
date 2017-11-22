package example.fragments.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import example.fragments.R;

public class AddCategoryAndName extends AppCompatActivity {
    Spinner categoryList;
    EditText nameEditText;
    String[] categories = { "Phone", "Table", "Notebook", "PC"};
    final static String textViewCategoryKey = "TEXTVIEW_CATEGORY_KEY";
    final static String textViewNameKey = "TEXTVIEW_NAME_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category_and_name);
        categoryList = (Spinner) findViewById(R.id.ACAN_categorySpinner);
        nameEditText = (EditText) findViewById(R.id.ACAN_nameTV);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categories);
        categoryList.setAdapter(adapter);
        Intent extras = getIntent();
        if(extras!=null){
            nameEditText.setText(extras.getStringExtra("Name"));
            int listKey = adapter.getPosition(extras.getStringExtra("Category"));
            categoryList.setSelection(listKey);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        categoryList = (Spinner) findViewById(R.id.ACAN_categorySpinner);
        nameEditText = (EditText) findViewById(R.id.ACAN_nameTV);
        outState.putInt(textViewCategoryKey,categoryList.getSelectedItemPosition());
        outState.putString(textViewNameKey,nameEditText.getText().toString());
        categoryList.onSaveInstanceState();

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        categoryList = (Spinner) findViewById(R.id.ACAN_categorySpinner);
        nameEditText = (EditText) findViewById(R.id.ACAN_nameTV);
        categoryList.setSelection(savedInstanceState.getInt(textViewCategoryKey));
        nameEditText.setText(savedInstanceState.getString(textViewNameKey));
    }

    public void CancelClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void NextClick(View view){
        if(nameEditText.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Enter the name",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, AddPriceAndCountAndImg.class);
            intent.putExtra("Category", categoryList.getSelectedItem().toString());
            intent.putExtra("Name", nameEditText.getText().toString());
            startActivity(intent);
        }
    }
}
