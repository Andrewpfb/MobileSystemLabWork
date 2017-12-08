package example.mapslocation;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import example.mapslocation.Services.DownloadService;

public class DownloadActivity extends AppCompatActivity {

    private EditText filePath;
    private Button downButton, readButton;
    private TextView readTextView;
    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;
    DownloadService downloadService;
    private static final int REQUEST_PERMISSION_WRITE = 1001;
    private boolean permissionGranted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        intent = new Intent(getApplicationContext(),DownloadService.class);
        filePath = (EditText)findViewById(R.id.editText);
        downButton = (Button)findViewById(R.id.button2);
        readButton = (Button)findViewById(R.id.button3);
        readTextView = (TextView)findViewById(R.id.textView);
        sConn = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Toast.makeText(DownloadActivity.this, "MainActivity onServiceConnected", Toast.LENGTH_SHORT).show();
                downloadService = ((DownloadService.DownloadBinder) binder).getService();
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                Toast.makeText(DownloadActivity.this, "MainActivity onServiceDisconnected", Toast.LENGTH_SHORT).show();
                bound = false;
            }
        };
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(!permissionGranted){
                    checkPermissions();
                    return;
                }*/
                bindService(intent,sConn,BIND_AUTO_CREATE);
                startService(intent);
            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!permissionGranted){
               //     checkPermissions();
                 //   return;
              //  }

                FileInputStream fin = null;
                File file = getExternalPath();
                // если файл не существует, выход из метода
                if(!file.exists()) return;
                try {
                    fin =  new FileInputStream(file);
                    byte[] bytes = new byte[fin.available()];
                    fin.read(bytes);
                    String text = new String (bytes);
                    readTextView.setText(text);
                }
                catch(IOException ex) {

                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                finally{

                    try{
                        if(fin!=null)
                            fin.close();
                    }
                    catch(IOException ex){

                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private File getExternalPath() {
        return(new File(Environment.getExternalStorageDirectory(), "content.xml"));
    }
    // проверяем, доступно ли внешнее хранилище для чтения и записи
    public boolean isExternalStorageWriteable(){
        String state = Environment.getExternalStorageState();
        return  Environment.MEDIA_MOUNTED.equals(state);
    }
    // проверяем, доступно ли внешнее хранилище хотя бы только для чтения
    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        return  (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }

    private boolean checkPermissions(){

        if(!isExternalStorageReadable() || !isExternalStorageWriteable()){
            Toast.makeText(this, "Внешнее хранилище не доступно", Toast.LENGTH_LONG).show();
            return false;
        }
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE);
            return false;
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case REQUEST_PERMISSION_WRITE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    permissionGranted = true;
                    Toast.makeText(this, "Разрешения получены", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this, "Необходимо дать разрешения", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
