package com.example.klinik.model.model_rm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ujang Wahyu on 04/01/2018.
 */
public class DataRM {
    @SerializedName("no_rm")
    @Expose
    private String no_rm;

    public String getno_rm() {
        return no_rm;
    }

    public void setno_rm(String iDMOBIL) {
        this.no_rm = no_rm;
    }
}