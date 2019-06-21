package com.example.klinik;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.klinik.myinterface.InitComponent;
import com.example.klinik.utils.move;

public class ActivityBeranda extends AppCompatActivity implements InitComponent, View.OnClickListener{

  private TextView fonthome;
  private Button Bantuan;
  private Button Profil;
  private Context mContext;

    Dialog mydialog;
  ViewFlipper v_flipper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_beranda);
    mydialog=new Dialog(this);
    mContext=this;
    startInit();

    fonthome=(TextView)findViewById(R.id.fonthome);
    Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
    fonthome.setTypeface(custom_fonts);

    int images[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

    v_flipper = (ViewFlipper) findViewById(R.id.v_flipper);

    // for loop
        /*for (int i = 0; i < images.length; i++){
            flipperImage(images[i]);
        }*/

    //but I prefer foreach

    for (int image: images){
      flipperImages(image);
    }
  }

  @Override
  public void startInit() {
    //initToolbar();
    initUI();
    initValue();
    initEvent();
  }

  @Override
  public void initUI() {
    Bantuan=(Button) findViewById(R.id.Bantuan);
    Profil=(Button) findViewById(R.id.Profil);
  }

  @Override
  public void initValue() {

  }

  @Override
  public void initEvent() {
    Bantuan.setOnClickListener(this);
    Profil.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()){

      case R.id.Bantuan:
        move.moveActivity(mContext,ActivityBantuan.class);
        break;

      case R.id.Profil:
        move.moveActivity(mContext,KomentarActivity.class);
        break;
    }
  }


  public void flipperImages(int image){
    ImageView imageView = new ImageView(this);
    imageView.setBackgroundResource(image);

    v_flipper.addView(imageView);
    v_flipper.setFlipInterval(4000); //4sec
    v_flipper.setAutoStart(true);

    //animation
    v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
    v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
  }
    public void Pemesananpopup (View v){
        Button btncancel;
        Button btnconfir;
        mydialog.setContentView(R.layout.popuppesan);
        btncancel=(Button)mydialog.findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.dismiss();

            }

        });
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mydialog.show();


    }
}
