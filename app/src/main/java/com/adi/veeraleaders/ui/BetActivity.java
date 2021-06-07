package com.adi.veeraleaders.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adi.veeraleaders.R;
import com.adi.veeraleaders.model.User;
import com.adi.veeraleaders.model.UserResponce;
import com.adi.veeraleaders.model.coupon.CouponResponse;
import com.adi.veeraleaders.retrofit.APIClient;
import com.adi.veeraleaders.utiles.SessionManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BetActivity extends AppCompatActivity {

    TextView optA,optB,optC,optD,optE,optF,selNum,titleTv;
    String number,id,amount,title,mttitle;
    Button payBtn;
    Dialog dialog;
    SessionManager sessionManager;
    User user;
    String jsons="application/json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);

        dialog = new Dialog(this);

        optA=findViewById(R.id.op1);
        optB=findViewById(R.id.op2);
        optC=findViewById(R.id.op3);
        optD=findViewById(R.id.op4);
        optE=findViewById(R.id.op5);
        optF=findViewById(R.id.op6);
        payBtn=findViewById(R.id.paybtn);
        titleTv=findViewById(R.id.title_bet);
        selNum=findViewById(R.id.selected_num);

        sessionManager = new SessionManager(this);
        user = sessionManager.getUserDetails(SessionManager.login);

        Intent i=getIntent();

        mttitle=i.getStringExtra("title");

        optA.setText(i.getStringExtra("optn1"));
        optB.setText(i.getStringExtra("optn2"));
        optC.setText(i.getStringExtra("optn3"));
        optD.setText(i.getStringExtra("optn4"));
        optE.setText(i.getStringExtra("optn5"));
        optF.setText(i.getStringExtra("optn6"));
        titleTv.setText(i.getStringExtra("title"));
        id = i.getStringExtra("did");
        amount=i.getStringExtra("amt");

        payBtn.setText(" ₹ "+amount);
        clickListeners();
    }

    private void clickListeners() {

        optA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                optA.setBackground(getResources().getDrawable(R.drawable.opt_background_filled));
                optB.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optC.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optD.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optE.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optF.setBackground(getResources().getDrawable(R.drawable.opt_background));
                number=optA.getText().toString();
                selNum.setText(number);



            }
        });
        optB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                optB.setBackground(getResources().getDrawable(R.drawable.opt_background_filled));
                optA.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optC.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optD.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optE.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optF.setBackground(getResources().getDrawable(R.drawable.opt_background));
                number=optB.getText().toString();
                selNum.setText(number);

            }
        });
        optC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                optC.setBackground(getResources().getDrawable(R.drawable.opt_background_filled));
                optB.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optA.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optD.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optE.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optF.setBackground(getResources().getDrawable(R.drawable.opt_background));

                number=optC.getText().toString();
                selNum.setText(number);

            }
        });

        optD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                optD.setBackground(getResources().getDrawable(R.drawable.opt_background_filled));
                optB.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optC.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optA.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optE.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optF.setBackground(getResources().getDrawable(R.drawable.opt_background));

                number=optD.getText().toString();
                selNum.setText(number);

            }
        });

        optE.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                optE.setBackground(getResources().getDrawable(R.drawable.opt_background_filled));
                optB.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optC.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optD.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optA.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optF.setBackground(getResources().getDrawable(R.drawable.opt_background));

                number=optE.getText().toString();
                selNum.setText(number);

            }
        });

        optF.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                optF.setBackground(getResources().getDrawable(R.drawable.opt_background_filled));
                optB.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optC.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optD.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optE.setBackground(getResources().getDrawable(R.drawable.opt_background));
                optA.setBackground(getResources().getDrawable(R.drawable.opt_background));

                number=optF.getText().toString();
                selNum.setText(number);

            }
        });


        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(BetActivity.this,PaymentActivity.class);
                i.putExtra("amount",amount);
                i.putExtra("qid",id);
                i.putExtra("option",number);
                i.putExtra("desc",mttitle);
                startActivity(i);
            }
        });
    }

    public void coupon(View view) {
        Toast.makeText(this, "Coupon", Toast.LENGTH_SHORT).show();

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.apply_coupun);

        EditText couponcode = dialog.findViewById(R.id.cccode);
        TextView showresult = dialog.findViewById(R.id.couponresult);
        TextView apply = dialog.findViewById(R.id.applybtn);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = couponcode.getText().toString();
                if (TextUtils.isEmpty(code))
                {
                    showresult.setText("Please enter code first");
                    showresult.setTextColor(Color.RED);
                }
                else
                {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("uid", user.getId());
                        jsonObject.put("ref_code", code);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    RequestBody bodyRequest = RequestBody.create(MediaType.parse(jsons), jsonObject.toString());
                    Call<CouponResponse> call = APIClient.getInterface().checkCoupon(bodyRequest);

                    call.enqueue(new Callback<CouponResponse>() {
                        @Override
                        public void onResponse(Call<CouponResponse> call, Response<CouponResponse> response) {
                            CouponResponse couponResponse= response.body();
                            if (couponResponse.getResult()){
                                showresult.setText(""+couponResponse.getResponseMsg());
                                showresult.setTextColor(Color.GREEN);
                            }else{
                                showresult.setText(""+couponResponse.getResponseMsg());
                                showresult.setTextColor(Color.RED);
                            }
                        }

                        @Override
                        public void onFailure(Call<CouponResponse> call, Throwable t) {

                        }
                    });


                }
            }
        });


        dialog.create();
        dialog.show();

    }
}