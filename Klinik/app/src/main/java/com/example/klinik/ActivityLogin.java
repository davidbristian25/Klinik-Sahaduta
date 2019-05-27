package com.example.klinik;

import android.app.ProgressDialog;
import android.content.Context;
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
        mContext=this;
        startInit();

    }

    @Override
    public void startInit() {
        if (Prefs.getInt(SPref.getGroupUser(),0)==1){
            move.moveActivity(mContext,ActivityBeranda.class);
            finish();
        }
        if (Prefs.getInt(SPref.getGroupUser(),0)==2){
            move.moveActivity(mContext,ActivityBeranda.class);
            finish();
        }
        //initToolbar();
        initUI();
        initValue();
        initEvent();
    }

    /*@Override
    public void initToolbar() {
        getSupportActionBar().hide();
    }*/

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
                move.moveActivity(mContext,ActivityBantuan.class);
                break;

            case R.id.txt_bantuan:
                    move.moveActivity(mContext,ActivityBeranda.class);
                    break;
        }
    }

    public boolean validate_login(){
        return (!validate.cek(no_rm)&&!validate.cek(password)) ? true : false;
    }

    public void login(){
        pDialog = new ProgressDialog(this);
        //  pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setMessage("Loading");
        pDialog.setCancelable(false);
        // pDialog.setIndeterminate(false);
        pDialog.show();

        Call<ResponseLogin> user=client.getApi().auth(no_rm.getText().toString(),password.getText().toString());
        user.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                pDialog.hide();
                if (response.isSuccessful()){
                    if (response.body().getStatus()){
                        userData=response.body().getData();
                        Toasty.success(mContext,"login berhasil", Toast.LENGTH_LONG).show();
                        Log.d("data user",userData.toString());
                        setPreference(userData);
                        if (userData.equals(1))
                            move.moveActivity(mContext,ActivityBeranda.class);
                        else
                            move.moveActivity(mContext,ActivityBeranda.class);
                        finish();
                    }else{
                        Toasty.error(mContext,"Username dan password salah",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toasty.error(mContext,"Username dan password salah",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                pDialog.hide();
//                new ProgressDialog(mContext)
//                        .setTitle("Oops...")
//                        .d("Koneksi bermasalah!")
//                        .show();
//                pDialog = new ProgressDialog(ActivityLogin.this);
//                //  pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//                pDialog.setMessage("Tidak ada koneksi");
//                pDialog.show();
                Toasty.success(mContext,"Koneksi Tidak ada",Toast.LENGTH_LONG).show();

                if (pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
    }

    private void setPreference(DataUser du){
        Prefs.putInt(SPref.getNo_rm(),du.getNo_rm());
        Prefs.putString(SPref.getNik(),du.getNik());
        Prefs.putString(SPref.getTglLahir(),du.getTglLahir());
        Prefs.putString(SPref.getNamaPasien(),du.getNamaPasien());

        Prefs.putString(SPref.getNamaKK(),du.getNamaKK());
        Prefs.putString(SPref.getPendidikan(),du.getPendidikan());
        Prefs.putString(SPref.getPekerjaan(),du.getPekerjaan());
        Prefs.putString(SPref.getNo_hp(),du.getNo_hp());
        Prefs.putString(SPref.getAlamat(),du.getAlamat());
        Prefs.putString(SPref.getPhoto(),du.getPhoto());
        Prefs.putString(SPref.getJenisKelamin(),du.getJenis_kelamin().toString());
        Prefs.putInt(SPref.getGroupUser(),du.getGroup_user());
        Prefs.putString(SPref.getPassword(),du.getPassword().toString());

    }




}
