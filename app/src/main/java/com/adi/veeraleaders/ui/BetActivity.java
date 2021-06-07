package com.adi.veeraleaders.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adi.veeraleaders.R;
import com.adi.veeraleaders.model.User;
import com.adi.veeraleaders.model.coupon.CouponResponse;
import com.adi.veeraleaders.retrofit.APIClient;
import com.adi.veeraleaders.utiles.SessionManager;

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
        initDialog();
    }

    private void initDialog() {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.apply_coupun);

        EditText couponcode = dialog.findViewById(R.id.cccode);
        TextView showresult = dialog.findViewById(R.id.couponresult);
        TextView apply = dialog.findViewById(R.id.applybtn);
        TextView closebtn = dialog.findViewById(R.id.closebtn);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BetActivity.this, "Checking..", Toast.LENGTH_SHORT).show();
                String code = couponcode.getText().toString();
                if (TextUtils.isEmpty(code))
                {
                    showresult.setVisibility(View.VISIBLE);
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
                                showresult.setVisibility(View.VISIBLE);
                                showresult.setText(""+couponResponse.getResponseMsg());
                                showresult.setTextColor(Color.GREEN);
                                int saved=(int) (Double.parseDouble(amount) * Integer.parseInt(couponResponse.getDiscount())/100);
                                int newamt = Integer.parseInt(amount) - saved;
                                payBtn.setText(" ₹ "+ newamt );
                                showresult.setText(""+couponResponse.getResponseMsg()+" You saved "+ saved);
                                showresult.setTextColor(Color.GREEN);
                                amount=newamt+"";

                            }else{
                                showresult.setVisibility(View.VISIBLE);
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

        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });

        dialog.create();
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
        dialog.show();
    }
}