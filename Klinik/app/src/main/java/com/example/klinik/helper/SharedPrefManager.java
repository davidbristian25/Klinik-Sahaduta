package com.example.klinik.helper;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
    public static final String SP_PASIEN_APP = "spPasienApp";

    public static final String SP_NO_RM = "no_rm";
    public static final String SP_PASSWORD = "sppassword";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_PASIEN_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();

    }



    public void saveSPString(String keySP, String value) {
        spEditor.putString(SP_NO_RM, null);
        spEditor.commit();

    }
    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNo_rm(){
        return sp.getString(SP_NO_RM, "");
    }

    public String getSPPassword(){
        return sp.getString(SP_PASSWORD, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
