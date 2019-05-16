package com.example.administrator.print;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yanzhenjie.permission.AndPermission;

public class MainActivity extends AppCompatActivity {


    PrintHelper printHelper ;
    private TextView tvConnState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvConnState = (TextView) findViewById(R.id.tv_connState);
        AndPermission.with(MainActivity.this)
                .permission(new String[]{Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_PRIVILEGED,Manifest.permission.BLUETOOTH_ADMIN,Manifest.permission.ACCESS_COARSE_LOCATION})
                .requestCode(14000)
                .send();

        printHelper = new PrintHelper(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    printHelper.onResultDeal(requestCode,resultCode,data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        printHelper.RegistrationOfRadio();
    }

    public void Blt(View view) {
        startActivityForResult(new Intent(this, BluetoothDeviceList.class), Constant.BLUETOOTH_REQUEST_CODE);
    }


    public void print(View view) {
        printHelper.sendReceiptWithResponse();
    }

    public void close(View view) {
        printHelper.close();
    }
}
