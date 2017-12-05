package example.webcontent.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import example.webcontent.Activities.MainActivity;
import example.webcontent.R;

public class ViewPageActivity extends AppCompatActivity {
    WebView webView;
    Context context;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        webView = (WebView) findViewById(R.id.webView);
        button = (Button) findViewById(R.id.button);
        button.setText("Load...");
        StringBuffer link =  new StringBuffer(getIntent().getStringExtra("link"));
        int insertPosition = link.indexOf("//");
        link.insert(insertPosition+2,"m.");
        context = this;
        new ProgressTask().execute(link.toString());
    }
    private class ProgressTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... path) {

            String content;
            try{
                content = getContent(path[0]);
            }
            catch (IOException ex){
                content = ex.getMessage();
            }

            return content;
        }
        @Override
        protected void onPostExecute(String content) {

            webView.loadData(content, "text/html; charset=utf-8", "utf-8");
            Toast.makeText(context, "Данные загружены", Toast.LENGTH_SHORT)
                    .show();
            button.setText("Back to list");
        }

        private String getContent(String path) throws IOException {
            BufferedReader reader=null;
            try {
                URL url=new URL(path);
                HttpsURLConnection c=(HttpsURLConnection)url.openConnection();
                c.setRequestMethod("GET");
                c.setReadTimeout(10000);
                c.connect();
                reader= new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder buf=new StringBuilder();
                String line=null;
                while ((line=reader.readLine()) != null) {
                    buf.append(line + "\n");
                }
                return(buf.toString());
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }
    public void BtnClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
