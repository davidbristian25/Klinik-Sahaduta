package com.example.klinik.api;

import com.example.klinik.model.model_user.ResponseRegister;
import com.example.klinik.model.model_user.ResponseLogin;
import com.example.klinik.model.model_user.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Ujang Wahyu on 04/01/2018.
 */

public interface request{
  @FormUrlEncoded
  @POST("Api/auth")
  Call<ResponseLogin> auth(@Field("no_rm") String no_rm,
                           @Field("password") String password);

  @FormUrlEncoded
  @POST("Api/user")
  Call<ResponseRegister> userRegister(@Field("password")String password,
                                      @Field("nama_pasien")String nama_pasien,
                                      @Field("tgl_lahir")String tgl_lahir,
                                      @Field("alamat")String alamat,
                                      @Field("nama_kk")String nama_kk,
                                      @Field("agama")String agama,
                                      @Field("pendidikan")String pendidikan,
                                      @Field("pekerjaan")String pekerjaan,
                                      @Field("jenis_kelamin") String jenis_kelamin,
                                      @Field("no_hp")String no_hp,
                                      @Field("NIK")String NIK)

                                      /*@Field("group_user")Integer group_user*/;

  @FormUrlEncoded
  @PUT("Api/user")
  Call<ResponseRegister> userUpdate(@Field("nama_pasien")String nama_pasien,
                                    @Field("tgl_lahir")String tgl_lahir,
                                    @Field("alamat")String alamat,
                                    @Field("nama_kk")String nama_kk,
                                    @Field("agama")String agama,
                                    @Field("pendidikan")String pendidikan,
                                    @Field("pekerjaan")String pekerjaan,
                                    @Field("jenis_kelamin")Character jenis_kelamin,
                                    @Field("no_hp")String no_hp,
                                    @Field("NIK")String NIK,
                                    @Field("password")String password,
                                    @Field("group_user")Integer group_user,
                                    @Field("photo")String photo);

  @GET("Api/user/{group_user}/{no_rm}")
  Call<ResponseUser> dataUser(
          @Path("group_user") Integer group_user,
          @Path("no_rm") Integer no_rm
  );

  //Call<ResponseRegister> userRegister(String toString, String toString1, String toString2, String toString3, String toString4, String toString5, String toString6, Character jk, String toString7, String toString8, String toString9);


//    @FormUrlEncoded
//    @POST("Api/pesanan")
//    Call<ResponseRegisterTransaksi> checkout(@Body DataCarts data);
}
