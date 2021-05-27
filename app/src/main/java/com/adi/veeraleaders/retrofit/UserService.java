package com.adi.veeraleaders.retrofit;


import com.adi.veeraleaders.model.DrawResponse;
import com.adi.veeraleaders.model.MyDrawResponse;
import com.adi.veeraleaders.model.Slider;
import com.adi.veeraleaders.model.TrasactionResponse;
import com.adi.veeraleaders.model.UserResponce;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserService {





    @POST("reg_user.php")
    Call<UserResponce> getRegister(@Body RequestBody requestBody);



    @POST("login_user.php")
    Call<UserResponce> login(@Body RequestBody requestBody);

    @POST("drawenroll.php")
    Call<JsonObject> getEnroll(@Body RequestBody requestBody);

    @POST("mydraws.php")
    Call<MyDrawResponse> getMydraws(@Body RequestBody requestBody);

    @POST("getTransactions.php")
    Call<TrasactionResponse> getTransactions(@Body RequestBody requestBody);

    @POST("update_user.php")
    Call<ResponseBody> updateUpi(@Body RequestBody requestBody);

    @POST("setdeviceid.php")
    Call<JsonObject> updateDeviceId(@Body RequestBody requestBody);





    @GET("getBanner.php")
    Call<Slider> getBanner();

    @POST("getdraws.php")
    Call<DrawResponse> getDrawList();



    @POST(APIClient.APPEND_URL + "p_forget_password.php")
    Call<JsonObject> forgotPassword(@Body RequestBody requestBody);

    @POST(APIClient.APPEND_URL + "pks_order.php")
    @Multipart
    Call<JsonObject> packData(@Part("uid") RequestBody uid,
                                @Part("p_method_id") RequestBody cid,
                                @Part("paddress") RequestBody paddress,
                                @Part("daddress") RequestBody daddress,
                                @Part("pmobile") RequestBody pmobile,
                                @Part("dmobile") RequestBody dmobile,
                                @Part("d_charge") RequestBody d_charge,
                                @Part("transaction_id") RequestBody transaction_id,
                                @Part("distance") RequestBody distance,
                                @Part("category") RequestBody category,
                                @Part("description") RequestBody description,
                                @Part("size") RequestBody size,
                                @Part List<MultipartBody.Part> parts);
}
