package com.example.administrator.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PhotoActivity extends AppCompatActivity {

    private String filePath;
    private final static int REQUEST1= 1;
    private final static int REQUEST= 0;
    private ImageView id_imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        id_imageview = (ImageView) findViewById(R.id.id_imageview);

        /**
         * 获取SD卡的路径
         */
        filePath = Environment.getExternalStorageDirectory().getPath();
        //路径拼接
        filePath = filePath+"/" +"temp.png";

    }

    public void begintoTakePhoto(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    public void begintoTakePhoto2(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoUri = Uri.fromFile(new File(filePath));
        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
        startActivityForResult(intent,REQUEST1);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST){
            if(resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                id_imageview.setImageBitmap(bitmap);
            }
        }else if(requestCode ==REQUEST1){
            if(resultCode == RESULT_OK){
                FileInputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(filePath);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    id_imageview.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    if(inputStream!=null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }
}
