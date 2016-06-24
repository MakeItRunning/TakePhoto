package com.example.administrator.takephoto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void takePhoto(View view){
        startActivity(new Intent(MainActivity.this,PhotoActivity.class));
    }

    public void doScan(View view){
        startActivity(new Intent(MainActivity.this,ScanActivity.class));
    }
}
