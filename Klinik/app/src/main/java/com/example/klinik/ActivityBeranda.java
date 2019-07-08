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
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.klinik.model.mode_antrian.AntrianSekarang;
import com.example.klinik.myinterface.InitComponent;
import com.example.klinik.utils.move;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityBeranda extends AppCompatActivity implements InitComponent, View.OnClickListener{

  private TextView fonthome;
  private Button Bantuan;
  private Button Profil;
  private Button Riwayat_Pemesanan;
  private Button Pemesanan;
  private Context mContext;

  Dialog mydialog;
  ViewFlipper v_flipper;


  private AntrianSekarang AntriSek;
  private TextView AntrianNow;

  private RequestQueue requestQueue;
  private StringRequest stringRequest;

  ArrayList<HashMap<String, String>> list_data;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_beranda);
    mydialog = new Dialog(this);
    mContext = this;
    startInit();

    fonthome = (TextView) findViewById(R.id.fonthome);
    Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
    fonthome.setTypeface(custom_fonts);

    int images[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

    v_flipper = (ViewFlipper) findViewById(R.id.v_flipper);

    // for loop
        /*for (int i = 0; i < images.length; i++){
            flipperImage(images[i]);
        }*/

    //but I prefer foreach

    for (int image : images) {
      flipperImages(image);
    }

      /**ANTRIAN**/
      String url = "http://192.168.43.93/sahaduta/getAntrianBaru.php"; //sesuaikan dengan ip pc anda
      AntrianNow = (TextView)findViewById(R.id.AntrianNow);
      requestQueue = Volley.newRequestQueue(ActivityBeranda.this);

      list_data = new ArrayList<HashMap<String, String>>();

      stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
              try {
                  JSONObject jsonResponse = new JSONObject(response);
                  JSONArray jsonArray = jsonResponse.getJSONArray("no_antrian");
                  for (int a = 0; a < jsonArray.length(); a ++){
                      JSONObject json = jsonArray.getJSONObject(a);
                      HashMap<String, String> map  = new HashMap<String, String>();
                      map.put("no_antrian", json.getString("no_antrian"));
                      list_data.add(map);
                  }
                  AntrianNow.setText(list_data.get(0).get("no_antrian"));
              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Toast.makeText(ActivityBeranda.this, error.getMessage(), Toast.LENGTH_LONG).show();
          }
      });

      requestQueue.add(stringRequest);


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
    Riwayat_Pemesanan=(Button) findViewById(R.id.Riwayat_Pemesanan);
    Pemesanan = (Button) findViewById(R.id.Pemesanan);
  }

  @Override
  public void initValue() {

  }

  @Override
  public void initEvent() {
    Bantuan.setOnClickListener(this);
    Profil.setOnClickListener(this);
    Riwayat_Pemesanan.setOnClickListener(this);
    Pemesanan.setOnClickListener(this);
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

      case R.id.Riwayat_Pemesanan:
        move.moveActivity(mContext,HistoryActivity.class);
        break;

      case R.id.Pemesanan:
         move.moveActivity(mContext,ActivityPemesanan.class);
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

  public void popuppesan (){



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
