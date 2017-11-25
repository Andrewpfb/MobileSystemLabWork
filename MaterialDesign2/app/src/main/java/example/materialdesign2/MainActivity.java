package example.materialdesign2;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Phone> phones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        // создаем адаптер
        DataAdapter adapter = new DataAdapter(this, phones);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData(){

        phones.add(new Phone ("Huawei P10", "Huawei", R.drawable.mate8));
        phones.add(new Phone ("Elite z3", "HP", R.drawable.lumia950));
        phones.add(new Phone ("Galaxy S8", "Samsung", R.drawable.galaxys6));
        phones.add(new Phone ("LG G 5", "LG", R.drawable.nexus5x));
    }
}
