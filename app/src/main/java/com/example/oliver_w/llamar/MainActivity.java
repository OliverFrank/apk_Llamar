package com.example.oliver_w.llamar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private  static  final  int MI_PERMISO_CALL_PHONE =1;
    Button bt_llamar;
    private Button button;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.bt_Mensaje);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });


        number =(EditText) findViewById(R.id.txtNumber);
        bt_llamar = (Button) findViewById(R.id.bt_llamar);


        bt_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String a1 = "7736830523";
                String a1 = number.getText().toString();
                Intent callintent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+a1));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CALL_PHONE)) {

                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Atencion")
                                .setContentText("debe otorgar permisos para realizar la llamada")
                                .setConfirmText("solicitar permisos")
                                .setCancelText("cancelar")
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.cancel();
                                    }
                                })
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.cancel();
                                        ActivityCompat.requestPermissions(MainActivity.this,
                                                new String[]{Manifest.permission.CALL_PHONE},
                                                MI_PERMISO_CALL_PHONE);
                                    }
                                })
                                .show();
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                MI_PERMISO_CALL_PHONE);
                    }

                } else {

                    startActivity(callintent);
                }


            }

        });
    }
    public  void  openActivity () {
        Intent intent = new Intent(this, EncDecSMS.class);
        startActivity(intent);
    }
}