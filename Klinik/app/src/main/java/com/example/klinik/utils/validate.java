package com.example.klinik.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Ujang Wahyu on 04/01/2018.
 */
public class validate {

    public static boolean cek(EditText et) {
            View focusView = null;
            Boolean cancel=false;
            if (TextUtils.isEmpty(et.getText().toString().trim())) {
                et.setError("Inputan Harus Di Isi");
                focusView = et;
                cancel = true;
            }
            if (cancel) {
                focusView.requestFocus();
            }
            return cancel;
    }

    public static boolean cek(Spinner et) {
        View focusView = null;
        Boolean cancel=false;
        if (TextUtils.isEmpty(et.getSelectedItem().toString().trim())) {
            et.setSelected(Boolean.parseBoolean("Inputan Harus Di Isi"));
            focusView = et;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        }
        return cancel;
    }

    public static boolean cekPassword(EditText et,String Password,String ConfirmPassword){
        View focusView = null;
        Boolean cancel=false;
        if (!Password.equals(ConfirmPassword)) {
            et.setError("Password tidak sama");
            focusView = et;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        }
        return cancel;
    }
}
