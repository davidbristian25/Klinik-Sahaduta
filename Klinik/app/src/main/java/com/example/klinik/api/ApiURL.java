package com.example.klinik.api;

public class ApiURL {
    public static String URL(String url_tambahan) {return root_url("api/"+url_tambahan);}
    public  static  String root_url(String url_tambahan){
        return "http://192.168.43.93/rental-api/"+url_tambahan;
    }
}
