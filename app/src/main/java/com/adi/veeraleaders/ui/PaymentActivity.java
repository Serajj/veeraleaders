package com.adi.veeraleaders.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.adi.veeraleaders.MainActivity;
import com.adi.veeraleaders.R;
import com.adi.veeraleaders.model.User;
import com.adi.veeraleaders.model.UserResponce;
import com.adi.veeraleaders.retrofit.APIClient;
import com.adi.veeraleaders.utiles.CustPrograssbar;
import com.adi.veeraleaders.utiles.SessionManager;
import com.google.gson.JsonObject;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    String amounts,qid,option,title;
    SessionManager sessionManager;
    String jsons="application/json";
    CustPrograssbar custPrograssbar;
    User user;

    LinearLayout ps_layout,pf_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        sessionManager = new SessionManager(PaymentActivity.this);
        custPrograssbar=new CustPrograssbar();

        ps_layout=findViewById(R.id.payment_success_layout);
        pf_layout=findViewById(R.id.payment_failed_layout);

        Intent i = getIntent();

        amounts=i.getStringExtra("amount");
        qid=i.getStringExtra("qid");
        option=i.getStringExtra("option");
        title=i.getStringExtra("desc");
         user = sessionManager.getUserDetails(SessionManager.login);

        startPyment(amounts);
    }

    private void startPyment(String samount) {



        // rounding off the amount.
        int amount = Math.round(Float.parseFloat(samount) * 100);

        // initialize Razorpay account.
        Checkout checkout = new Checkout();

        // set your id as below
        checkout.setKeyID("rzp_live_p6UjpqGRKdHdZt");

        //test key rzp_test_eKjgh4H8ZcSquy

        // set image
        checkout.setImage(R.mipmap.ic_launcher_main);

        // initialize json object
        JSONObject object = new JSONObject();
        try {
            // to put name
            object.put("name", "Veeraleaders");

            // put description
            object.put("description", title);

            // to set theme color
            object.put("theme.color", Color.RED);

            // put the currency
            object.put("currency", "INR");

            // put amount
            object.put("amount", amount);

            // put mobile number
            object.put("prefill.contact", user.getMobile());

            // put email
            object.put("prefill.email", "veeraleaders@gmail.com");

            // open razorpay to checkout activity
            checkout.open(PaymentActivity.this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        custPrograssbar.prograssCreate(PaymentActivity.this);
        Toast.makeText(this, "Payment Sucessfully !", Toast.LENGTH_SHORT).show();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", user.getId());
            jsonObject.put("qid", qid);
            jsonObject.put("ans", option);
            jsonObject.put("amt", amounts);

        } catch (Exception e) {
            e.printStackTrace();

        }

        Log.d("serajpres",jsonObject.toString());
        RequestBody bodyRequest = RequestBody.create(MediaType.parse(jsons), jsonObject.toString());
        Call<JsonObject> call = APIClient.getInterface().getEnroll(bodyRequest);


        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    JsonObject object=response.body();
                    Log.d("serajpres",object.toString());
                    custPrograssbar.closePrograssBar();
                    ps_layout.setVisibility(View.VISIBLE);

                }else{
                    Log.d("serajpres",response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed !", Toast.LENGTH_SHORT).show();
        pf_layout.setVisibility(View.VISIBLE);
    }


    public void goHome(View view) {
        startActivity(new Intent(PaymentActivity.this, MainActivity.class));
        finish();
    }
}