package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static  String down1url= "";
    public static String down2url= "";
    public static  String down3url= "";
    public static  String down4url= "";
    public static String down5url= "";
    public static final int request_code=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button down1 = findViewById(R.id.down1);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},request_code);
            }
        }

        down1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                down1url=((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString();
                down2url=((EditText)findViewById(R.id.editTextTextPersonName2)).getText().toString();
                down3url=((EditText)findViewById(R.id.editTextTextPersonName3)).getText().toString();
                down4url=((EditText)findViewById(R.id.editTextTextPersonName4)).getText().toString();
                down5url=((EditText)findViewById(R.id.editTextTextPersonName5)).getText().toString();
                downloadfile(down1url);
                downloadfile(down2url);
                downloadfile(down3url);
                downloadfile(down4url);
                downloadfile(down5url);

            }
        });
    }
    public void downloadfile(String downurl){
        String filename= URLUtil.guessFileName(downurl,null,null);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downurl));
        request.setTitle(filename);
        request.setDescription("Downloading please wait....");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,filename);
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }


}