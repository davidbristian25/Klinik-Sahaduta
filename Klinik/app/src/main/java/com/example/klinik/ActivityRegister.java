package com.example.klinik;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.klinik.api.client;
import com.example.klinik.model.model_user.DataUser;
import com.example.klinik.model.model_user.ResponseRegister;
import com.example.klinik.myinterface.InitComponent;
import com.example.klinik.utils.move;
import com.example.klinik.utils.validate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityRegister extends AppCompatActivity  implements InitComponent, View.OnClickListener {

    private EditText Nama;
    private EditText NIK;
    private EditText birth;
    private EditText Alamat;
    private EditText KK;
    private Spinner Agama;
    private Spinner Pendidikan;
    private Spinner Pekerjaan;
    private EditText no_hp;
    private EditText Password;
    private EditText ConPassword;
    private String JK;
    private RadioButton rbl;
    private RadioButton rbp;
    private Button button_regist;
    private CoordinatorLayout coordinatorLayout;

    Calendar myCalendar = Calendar.getInstance();

    //declare context
    private Context mContext;

    //declare variable
    private DataUser userData;

    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext=this;
        startInit();
        initUI();
        initValue();
        initEvent();
        //AndroidNetworking.initialize(this);
    }

    /*@Override
    public void startInit() {


    }

    @Override
    public void initToolbar() {
        getSupportActionBar().hide();
    }*/


    @Override
    public void startInit() {

    }

    /*@Override
    public void initToolbar() {

    }*/

    @Override
    public void initUI() {
        Nama=(EditText)findViewById(R.id.Nama);
        NIK=(EditText)findViewById(R.id.NIK);
        birth=(EditText)findViewById(R.id.birth);
        Alamat=(EditText)findViewById(R.id.Alamat);
        KK=(EditText)findViewById(R.id.KK);
        Agama=(Spinner)findViewById(R.id.Agama);
        Pendidikan=(Spinner) findViewById(R.id.Pendidikan);
        Pekerjaan=(Spinner) findViewById(R.id.Pekerjaan);
        no_hp=(EditText)findViewById(R.id.no_hp);
        Password=(EditText)findViewById(R.id.Password);
        ConPassword=(EditText)findViewById(R.id.ConPassword);
        rbl=(RadioButton)findViewById(R.id.jkl);
        rbp=(RadioButton)findViewById(R.id.jkp);
        button_regist=(Button)findViewById(R.id.button_regist);
        birth = findViewById(R.id.birth);

        birth.setFocusableInTouchMode(false);
        birth.setFocusable(false);
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ActivityRegister.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }


            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    setBirth();
                }

            };

            private void setBirth() {
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                birth.setText(sdf.format(myCalendar.getTime()));
            }


        });
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {
        button_regist.setOnClickListener(this);
        rbl.setOnClickListener(this);
        rbp.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_regist:
                if (validasi())
                    register();
                break;
            case R.id.jkl:
                JK = "Laki-laki";
                rbp.setChecked(false);
                break;
            case R.id.jkp:
                JK = "Perempuan";
                rbl.setChecked(false);
                break;
        }

    }

    public void register(){
        pDialog = new ProgressDialog(this);
        // pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setMessage("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        /*AndroidNetworking.post("http://192.168.43.93/sahaduta_api/index.php/api/user/register")
                .addBodyParameter("Nama", Nama.getText().toString())
                .addBodyParameter("birth", birth.getText().toString())
                .addBodyParameter("Alamat", Alamat.getText().toString())
                .addBodyParameter("KK", KK.getText().toString())
                .addBodyParameter("Agama", Agama.getSelectedItem().toString())
                .addBodyParameter("Pendidikan", Pendidikan.getSelectedItem().toString())
                .addBodyParameter("Pekerjaan", Pekerjaan.getSelectedItem().toString())
                .addBodyParameter("no_hp", no_hp.getText().toString())
                .addBodyParameter("NIK", NIK.getText().toString())
                .addBodyParameter("Password", Password.getText().toString())
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Boolean status = response.getBoolean("status");
                            String message = response.getString("message");
                                showMessage(message);
                            if(status){
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });*/

        Call register;
        register = client.getApi().userRegister
                                (Password.getText().toString(),
                                Nama.getText().toString(),
                                birth.getText().toString(),
                                Alamat.getText().toString(),
                                KK.getText().toString(),
                                Agama.getSelectedItem().toString(),
                                Pendidikan.getSelectedItem().toString(),
                                Pekerjaan.getSelectedItem().toString(),
                                JK,
                                no_hp.getText().toString(),
                                NIK.getText().toString()
                                );

        register.enqueue(new Callback<ResponseRegister>() {

            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                pDialog.hide();
                if (response.isSuccessful()){
                    if (response.body().getStatus()) {
                        Toasty.success(mContext,"Berhasil Dibuat", Toast.LENGTH_LONG).show();
                        move.moveActivity(mContext,ActivityRekam.class);
                    }else {
                        Toasty.success(mContext,"Gagal dibuat", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toasty.error(mContext,"Gagal dibuat", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                pDialog.hide();
                Toasty.success(mContext,"Berhasil Dibuat", Toast.LENGTH_LONG).show();
                move.moveActivity(mContext,ActivityRekam.class);
            }
        });
    }



    private Boolean validasi(){
        if (!validate.cek(Nama)
                &&!validate.cek(birth)
                &&!validate.cek(Alamat)
                &&!validate.cek(KK)
                &&!validate.cek(Agama)
                &&!validate.cek(Pendidikan)
                &&!validate.cek(Pekerjaan)
                &&!validate.cek(no_hp)
                &&!validate.cek(NIK)
                &&!validate.cek(Password)
                &&!validate.cek(ConPassword)) {
            if (validate.cekPassword(ConPassword,Password.getText().toString(),ConPassword.getText().toString())){
                return false;
            }else{
                return true;
            }
        } else{ return false; }
    }
}
