package com.adi.veeraleaders.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adi.veeraleaders.MainActivity;
import com.adi.veeraleaders.R;
import com.adi.veeraleaders.model.UserResponce;
import com.adi.veeraleaders.retrofit.APIClient;
import com.adi.veeraleaders.utiles.CustPrograssbar;
import com.adi.veeraleaders.utiles.SessionManager;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity{

    LinearLayout loginLayout,signUpLayout;

    boolean isNewuser = true;
    SessionManager sessionManager;
    CustPrograssbar custPrograssbar;
    String mobile="mobile";
    String jsons="application/json";

    EditText lMobile,lPassword,smobile,spassword,sname,semail,scpassword,sUpi,sReferal;

    TextView aha,dha,loginBtn,signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpLayout=findViewById(R.id.signup_layout);
        loginLayout=findViewById(R.id.login_layout);

        aha=findViewById(R.id.already_have_account_btn);
        dha=findViewById(R.id.btn_register_now);

        lMobile=findViewById(R.id.l_mobile);
        lPassword=findViewById(R.id.l_password);

        smobile=findViewById(R.id.s_mobile);
        sname=findViewById(R.id.s_name);
        semail=findViewById(R.id.s_email);
        scpassword=findViewById(R.id.s_password);
        spassword=findViewById(R.id.sc_password);
        sUpi=findViewById(R.id.s_upi);
        sReferal=findViewById(R.id.s_referal);

        loginBtn=findViewById(R.id.btn_proceed);
        signUpBtn=findViewById(R.id.signup_btn);
        custPrograssbar = new CustPrograssbar();
        sessionManager = new SessionManager(LoginActivity.this);


        clickListeners();

        if (sessionManager.getUserDetails(SessionManager.login)!=null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void clickListeners() {

        aha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpLayout.setVisibility(View.GONE);
                loginLayout.setVisibility(View.VISIBLE);
            }
        });

        dha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginLayout.setVisibility(View.GONE);
                signUpLayout.setVisibility(View.VISIBLE);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=lMobile.getText().toString().trim();
                String password=lPassword.getText().toString().trim();
                if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(mobile,password);
                }

            }
        });



        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=smobile.getText().toString().trim();
                String name=sname.getText().toString().trim();
                String email=semail.getText().toString().trim();
                String password=spassword.getText().toString().trim();
                String confirmPassword=scpassword.getText().toString().trim();
                String myupi=sUpi.getText().toString().trim();
                String referal=""+sReferal.getText().toString().trim();
                if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(myupi)){
                    Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    if (confirmPassword.equals(password)){
                        registerUser(mobile,password,name,email,myupi,referal);
                    }else{
                        scpassword.setError("Password must be same");
                    }

                }

            }
        });
    }

    private void registerUser(String mobile, String password, String name, String email,String myUpi,String referal) {
        custPrograssbar.prograssCreate(LoginActivity.this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", mobile);
            jsonObject.put("password", password);
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("myupi", myUpi);
            jsonObject.put("ref_code", referal);

        } catch (Exception e) {
            e.printStackTrace();

        }
        RequestBody bodyRequest = RequestBody.create(MediaType.parse(jsons), jsonObject.toString());
        Call<UserResponce> call = APIClient.getInterface().getRegister(bodyRequest);


        call.enqueue(new Callback<UserResponce>() {
            @Override
            public void onResponse(Call<UserResponce> call, Response<UserResponce> response) {
                if(response.isSuccessful()){
                    UserResponce userResponce=response.body();
                    if (userResponce.getResponseCode().equals("200")){
                        Toast.makeText(LoginActivity.this, ""+response.body().getResponseMsg(), Toast.LENGTH_SHORT).show();
                        sessionManager.setUserDetails(SessionManager.login,userResponce.getUser());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, ""+response.body().getResponseMsg(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, ""+response.errorBody(), Toast.LENGTH_SHORT).show();
                    Log.d("seraj",""+response.errorBody());
                }
                custPrograssbar.closePrograssBar();

            }

            @Override
            public void onFailure(Call<UserResponce> call, Throwable t) {

            }
        });

    }


    private void chackUser() {
        custPrograssbar.prograssCreate(LoginActivity.this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(mobile, "mobile");
        } catch (Exception e) {
            e.printStackTrace();

        }



    }

    private void loginUser(String mobiles,String password) {
        custPrograssbar.prograssCreate(LoginActivity.this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", mobiles);
            jsonObject.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();

        }
        RequestBody bodyRequest = RequestBody.create(MediaType.parse(jsons), jsonObject.toString());
        Call<UserResponce> call = APIClient.getInterface().login(bodyRequest);

        call.enqueue(new Callback<UserResponce>() {
            @Override
            public void onResponse(Call<UserResponce> call, Response<UserResponce> response) {
                if(response.isSuccessful()){
                    UserResponce userResponce=response.body();
                    if (userResponce.getResponseCode().equals("200")){
                        Toast.makeText(LoginActivity.this, ""+response.body().getResponseMsg(), Toast.LENGTH_SHORT).show();
                        sessionManager.setUserDetails(SessionManager.login,userResponce.getUser());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, ""+response.body().getResponseMsg(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, ""+response.errorBody(), Toast.LENGTH_SHORT).show();
                    Log.d("seraj",""+response.errorBody());
                }
                custPrograssbar.closePrograssBar();
            }

            @Override
            public void onFailure(Call<UserResponce> call, Throwable t) {

            }
        });


    }



}