package com.example.administrator.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class ScanActivity extends AppCompatActivity {

    private TextView tv_text;
    private EditText msg_text;
    private ImageView result_img;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        tv_text = (TextView) findViewById(R.id.tv_text);
        msg_text = (EditText) findViewById(R.id.msg_text);
        result_img = (ImageView) findViewById(R.id.result_img);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
    }

    public void doScan(View view){

        startActivityForResult(new Intent(ScanActivity.this,CaptureActivity.class),0);
    }

    public void doScanQC(View view){
        String msg = msg_text.getText().toString();
        if(msg.equals("")){
            Toast.makeText(this,"输入不能为空！",Toast.LENGTH_LONG).show();

        }else{
            Bitmap bitmap = EncodingUtils.createQRCode(msg,500,500,checkBox.isChecked()? BitmapFactory.decodeResource(getResources(),R.drawable.game):null);
            result_img.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode ==RESULT_OK){
            Bundle bundle = data.getExtras();
            String str = bundle.getString("result");
            tv_text.setText(str);
        }
    }
}
