package example.mapslocation.Services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class DownloadService extends Service {

    DownloadBinder binder = new DownloadBinder();
    String url1;
    String fileName="content5.xml";

    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service Create", Toast.LENGTH_SHORT).show();
        new ProgressTask().execute(url1);
    }

    public IBinder onBind(Intent arg0) {
        Toast.makeText(this, "Service Bind", Toast.LENGTH_SHORT).show();
        url1 = arg0.getStringExtra("url");
        return binder;
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        url1 = intent.getStringExtra("url");
        Toast.makeText(this, "Service Rebind", Toast.LENGTH_SHORT).show();
    }

    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "Service Unbind", Toast.LENGTH_SHORT).show();
        url1 = intent.getStringExtra("url");
        return super.onUnbind(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroy", Toast.LENGTH_SHORT).show();
    }

    public class DownloadBinder extends Binder {
        public DownloadService getService() {
            return DownloadService.this;
        }
    }
    private File getExternalPath() {
        return(new File(Environment.getExternalStorageDirectory(), fileName));
    }
     public void setUrl(String url){
         this.url1 = url;
     }
    private class ProgressTask extends AsyncTask<String, Void, String> {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";
        @Override
        protected String doInBackground(String... params) {
            try {
               // URL url = new URL(params[0]);
                URL url = new URL(url1);

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
            Toast.makeText(getApplicationContext(), "Данные загружены", Toast.LENGTH_SHORT)
                    .show();
            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(getExternalPath());
                fos.write(strJson.getBytes());
                Toast.makeText(getApplicationContext(), "Файл сохранен", Toast.LENGTH_SHORT).show();
            }catch(IOException ex) {

                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
            finally {
                try {
                    if (fos != null)
                        fos.close();
                    onDestroy();
                } catch (IOException ex) {

                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}