package com.example.klinik;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class popuppesan extends AppCompatActivity {

    Dialog mydialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popuppesan);
        mydialog=new Dialog(this);
    }


}
