package example.webcontent.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import example.webcontent.Adapters.CurAdapter;
import example.webcontent.Adapters.DataAdapter;
import example.webcontent.Models.Currencies;
import example.webcontent.R;
import example.webcontent.RSS.SaxFeedParser;
import example.webcontent.Utils.StaticLinkStorage;

public class Currency extends AppCompatActivity {

    List<Currencies> currencies;
    TextView progressText;
    RecyclerView recyclerView;
    ListView tmp;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        recyclerView = (RecyclerView)findViewById(R.id.cur_list);
        //tmp = (ListView) findViewById(R.id.cur_list);
        progressText = (TextView) findViewById(R.id.cur_progressText);
        context = this;
        new ProgressTask().execute("http://www.nbrb.by/API/ExRates/Rates?Periodicity=0");
    }
    private class ProgressTask extends AsyncTask<String, Void, String> {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";
        @Override
        protected String doInBackground(String... params) {

            if(currencies==null){
                currencies = new ArrayList<>();
            }
            try {
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();
            }catch (Exception e){}
            return resultJson;
        }
        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            progressText.setVisibility(View.INVISIBLE);
            Toast.makeText(context, "Данные загружены", Toast.LENGTH_SHORT)
                    .show();
            JSONObject jsonObject;
            if(currencies==null){
                currencies=new ArrayList<>();
            }
            try{
                JSONArray jsonArray = new JSONArray(strJson);
                for(int i =0; i<jsonArray.length();i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    int Cur_ID = jsonObject.getInt("Cur_ID");
                    String Date = jsonObject.getString("Date");
                    String Cur_Abbreviation = jsonObject.getString("Cur_Abbreviation");
                    int Cur_Scale = jsonObject.getInt("Cur_Scale");
                    String Cur_Name = jsonObject.getString("Cur_Name");
                    Double Cur_OfficialRate = jsonObject.getDouble("Cur_OfficialRate");
                    Currencies tmpCur = new Currencies(
                            Cur_ID,
                            Date,
                            Cur_Abbreviation,
                            Cur_Scale,
                            Cur_Name,
                            Cur_OfficialRate
                    );
                    currencies.add(tmpCur);
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
            //ArrayAdapter<Currencies> adapter = new ArrayAdapter<Currencies>(context,android.R.layout.simple_list_item_1,currencies);
            //tmp.setAdapter(adapter);
            CurAdapter dataAdapter = new CurAdapter(context,currencies);
            recyclerView.setAdapter(dataAdapter);
        }
    }
}
