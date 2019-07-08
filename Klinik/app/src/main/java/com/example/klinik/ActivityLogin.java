package com.example.klinik;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.klinik.SPreferenced.SPref;
import com.example.klinik.api.client;
import com.example.klinik.helper.SharedPrefManager;
import com.example.klinik.model.model_user.DataUser;
import com.example.klinik.model.model_user.ResponseLogin;
import com.example.klinik.myinterface.InitComponent;
import com.example.klinik.utils.move;
import com.example.klinik.utils.validate;
import com.pixplicity.easyprefs.library.Prefs;

import customfonts.MyEditText;
import customfonts.MyTextView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.rental_apps.android.rental_apps.admin.AdminMain;
//import com.rental_apps.android.rental_apps.user.SplashActivity;
//import com.rental_apps.android.rental_apps.user.UserMain;

public class ActivityLogin extends AppCompatActivity implements InitComponent, View.OnClickListener{

  //declare componenr
  private MyEditText no_rm;
  private MyEditText password;
  private MyTextView btn_login;
  private MyTextView txt_register;
  private MyTextView txt_bantuan;
  private TextView logofont;
  private CoordinatorLayout coordinatorlayout;
  private SharedPrefManager sharedPrefManager;
  //declare context
  private Context mContext;

  //declate variable
  private DataUser userData;

  //declare sweet alert
  //   private SweetAlertDialog pDialog;
  private ProgressDialog pDialog;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    sharedPrefManager = new SharedPrefManager(this);
    mContext=this;

      if (sharedPrefManager.getSPSudahLogin()){
          startActivity(new Intent(ActivityLogin.this, ActivityBeranda.class)
                  .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
          finish();
      }

    startInit();

  }

  @Override
  public void startInit() {

//        initToolbar();
    initUI();
    initValue();
    initEvent();
  }

//    @Override
//    public void initToolbar() { getSupportActionBar().hide();
//    }

  @Override
  public void initUI() {
    password=(MyEditText)findViewById(R.id.password);
    btn_login=(MyTextView)findViewById(R.id.btn_login);
    txt_register=(MyTextView) findViewById(R.id.txt_register);
    txt_bantuan=(MyTextView) findViewById(R.id.txt_bantuan);
    logofont=(TextView)findViewById(R.id.logofont);
    Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
    no_rm=(MyEditText)findViewById(R.id.no_rm);
    logofont.setTypeface(custom_fonts);

  }



  @Override
  public void initValue() {

  }

  @Override
  public void initEvent() {
    btn_login.setOnClickListener(this);
    txt_register.setOnClickListener(this);
    txt_bantuan.setOnClickListener(this);
  }


  @Override
  public void onClick(View view) {
    switch (view.getId()){

      case R.id.btn_login:
        if (validate_login())
          login();
        break;

      case R.id.txt_register:
        move.moveActivity(mContext,ActivityRegister.class);
        break;

      case R.id.txt_bantuan:
        move.moveActivity(mContext,ActivityBantuan.class);
        break;
    }
  }

  public boolean validate_login(){
    return (!validate.cek(no_rm)&&!validate.cek(password)) ? true : false;
  }


  public void login(){
    pDialog = new ProgressDialog(this);
    pDialog.setMessage("Loading");
    pDialog.setCancelable(false);
    pDialog.show();

    Call<ResponseLogin>
            user = client.getApi().auth(
                    no_rm.getText().toString(),
                    password.getText().toString());
    //Log.e("response login",no_rm.getText().toString()+" "+password.getText().toString());
    user.enqueue(new Callback<ResponseLogin>() {
      @Override
      public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
        pDialog.hide();
        Log.e("Response",response.message());
        if (response.isSuccessful()){
          if (response.body().getStatus()){
            userData = response.body().getData();
            //pDialog.dismiss();
            //try {
                //JSONObject jsonRESULTS = new JSONObject(response.body().string());
                //if (jsonRESULTS.getString("error").equals("false")){
                    Toasty.success(mContext,"login berhasil",Toast.LENGTH_LONG).show();
                    String no_rm = userData.getNo_rm();
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NO_RM, no_rm);
                    // Shared Pref ini berfungsi untuk menjadi trigger session login
                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                    startActivity(new Intent(mContext, ActivityBeranda.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                //} else {
                    // Jika login gagal
                    //String error_message = jsonRESULTS.getString("error_msg");
                    //Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
               // }
            //} catch (JSONException e) {
              //  e.printStackTrace();
           // } catch (IOException e) {
               // e.printStackTrace();
           // }
            //Log.d("data user",userData.toString());
            //setPreference(userData);
            //Object no_rm = null;
            //move.moveActivity(mContext,ActivityBeranda.class);
                        //if (userData.getNo_rm().equals("false"))
                            //move.moveActivity(mContext,ActivityBeranda.class);
                        //
                          // }else{
                            //move.moveActivity(mContext,ActivityBeranda.class);
                            //  Log.e("else",response.message());
            //finish();
          }else{
            Toasty.error(mContext,"Username dan password salah",Toast.LENGTH_LONG).show();
          }
        }else{
          Toasty.error(mContext,"Username dan password salah",Toast.LENGTH_LONG).show();
        }

      }
      @Override
      public void onFailure(Call<ResponseLogin> call, Throwable t) {
        //Log.e("on Failure ",call.toString());
        pDialog.hide();
        Toasty.success(mContext,"Koneksi Tidak ada",Toast.LENGTH_LONG).show();
        move.moveActivity(mContext,ActivityBeranda.class);

        if (pDialog.isShowing())
          pDialog.dismiss();
      }
    });
  }

  private void setPreference(DataUser du){
    Prefs.putString(SPref.getNo_rm(),du.getNo_rm());
    Prefs.putString(SPref.getPassword(),du.getPassword());
    Prefs.putString(SPref.getNamaPasien(),du.getNamaPasien());


  }




}
