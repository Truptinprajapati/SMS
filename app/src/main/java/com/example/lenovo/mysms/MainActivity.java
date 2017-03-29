package com.example.lenovo.mysms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

private static final int My_SMs=0;
    String mno;
    String smsmsg;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case My_SMs:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(mno,null,smsmsg,null,null);
                    Toast.makeText(getApplicationContext(),"Sms sent",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Sms faild,please try again",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

    }









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText mobno=(EditText)findViewById(R.id.mobno);
        final EditText sms=(EditText)findViewById(R.id.msg);
        Button ok=(Button)findViewById(R.id.sendsms);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendSMSMessage();
            }

            protected void sendSMSMessage() {

                 mno=mobno.getText().toString();
                 smsmsg=sms.getText().toString();


                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED);
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.SEND_SMS))
                {
                }
                else
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},My_SMs);
                }


            }
        });




    }
}













