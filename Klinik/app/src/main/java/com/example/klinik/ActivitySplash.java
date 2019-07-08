package com.example.klinik;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivitySplash extends AppCompatActivity {
    private TextView Textfont;
    private int waktu_loading=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Textfont=(TextView)findViewById(R.id.Textfont);
        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        Textfont.setTypeface(custom_fonts);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home = new Intent(ActivitySplash.this, ActivityLogin.class);
                startActivity(home);
                finish();

            }
        },waktu_loading);
    }
}
