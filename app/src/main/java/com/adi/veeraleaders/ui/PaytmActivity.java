package com.adi.veeraleaders.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adi.veeraleaders.MainActivity;
import com.adi.veeraleaders.R;
import com.adi.veeraleaders.model.Token_Res;

import com.adi.veeraleaders.model.User;
import com.adi.veeraleaders.retrofit.APIClient;
import com.adi.veeraleaders.utiles.CustPrograssbar;
import com.adi.veeraleaders.utiles.SessionManager;
import com.google.gson.JsonObject;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.paytm.pgsdk.TransactionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaytmActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback{
    String jsons="application/json";
    private static final String TAG ="serajpaytm" ;
    private ProgressBar progressBar;
    private EditText txnAmount;
    private String midString ="NfqODq67906240943450", txnAmountString, orderIdString="", txnTokenString="";
    private Button btnPayNow;
    private Integer ActivityRequestCode = 2;


    String amounts,qid,option,title;
    SessionManager sessionManager;

    CustPrograssbar custPrograssbar;
    User user;

    LinearLayout ps_layout,pf_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm);

        Log.d("serajpaytm","starting payments done ");

        sessionManager = new SessionManager(PaytmActivity.this);
        custPrograssbar=new CustPrograssbar();

        ps_layout=findViewById(R.id.payment_success_layout);
        pf_layout=findViewById(R.id.payment_failed_layout);

        Intent i = getIntent();

        amounts=i.getStringExtra("amount");
        txnAmountString=amounts;
        qid=i.getStringExtra("qid");
        option=i.getStringExtra("option");
        title=i.getStringExtra("desc");
        user = sessionManager.getUserDetails(SessionManager.login);




        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String date = df.format(c.getTime());
        Random rand = new Random();
        int min =1000, max= 9999;
// nextInt as provided by Random is exclusive of the top value so you need to add 1
        int randomNum = rand.nextInt((max - min) + 1) + min;
        orderIdString =  date+String.valueOf(randomNum);


        getToken();

    }

    private void getToken() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", "12345");
            jsonObject.put("MID", midString);
            jsonObject.put("ORDER_ID", orderIdString);
            jsonObject.put("AMOUNT", txnAmountString);

        } catch (Exception e) {
            e.printStackTrace();

        }

        Log.d("serajpres",jsonObject.toString());
        RequestBody bodyRequest = RequestBody.create(MediaType.parse(jsons), jsonObject.toString());





        Log.d("serajpaytm","Calling api");
        Call<Token_Res> call = APIClient.getInterface().generateTokenCall(bodyRequest);

        call.enqueue(new Callback<Token_Res>() {
            @Override
            public void onResponse(Call<Token_Res> call, Response<Token_Res> response) {
                Log.d("serajpaytm","Responce recieved");
                Log.d(TAG,response.isSuccessful()+"");
                Log.d("serajpaytm","token "+response.body().getTxnToken());
                if (TextUtils.isEmpty(response.body().getTxnToken())){
                    Log.d("serajpaytm","Some error occured");
                    Log.d("serajpaytm","r msg"+response.body().getResponseMsg());
                }else{
                    Log.d("serajpaytm","Stsrting transactions ");
                    startPaytmPayment(response.body().getTxnToken());
                }


            }

            @Override
            public void onFailure(Call<Token_Res> call, Throwable t) {
                Log.d("serajpaytm","failed "+t.getLocalizedMessage());
            }
        });
    }

    private void startPaytmPayment(String token) {
        Log.d("serajpaytm","starting final payments ");
        txnTokenString = token;
        // for test mode use it
        // String host = "https://securegw-stage.paytm.in/";
        // for production mode use it
        String host = "https://securegw.paytm.in/";
        String orderDetails = "MID: " + midString + ", OrderId: " + orderIdString + ", TxnToken: " + txnTokenString
                + ", Amount: " + txnAmountString;
        //Log.e(TAG, "order details "+ orderDetails);
        String callBackUrl = host + "theia/paytmCallback?ORDER_ID="+orderIdString;
        Log.e("serajpaytm", " callback URL "+callBackUrl);
        PaytmOrder paytmOrder = new PaytmOrder(orderIdString, midString, txnTokenString, txnAmountString,callBackUrl);

        TransactionManager transactionManager = new TransactionManager(paytmOrder,this);
        transactionManager.setAppInvokeEnabled(false);
        transactionManager.setShowPaymentUrl(host + "theia/api/v1/showPaymentPage");
        transactionManager.startTransaction(this, ActivityRequestCode);
    }

    @Override
    public void onTransactionResponse(@Nullable Bundle bundle) {
        Log.d("serajpaytm","Transaction is successfull "+bundle.get("TXN_SUCCESS"));

        custPrograssbar.prograssCreate(PaytmActivity.this);
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
                pf_layout.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void networkNotAvailable() {
        Log.d("serajpaytm","Network Failure");
        pf_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorProceed(String s) {
        Log.d("serajpaytm","proceed error "+s);
        pf_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void clientAuthenticationFailed(String s) {
        Log.d("serajpaytm","Client auth fail "+s);
        pf_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void someUIErrorOccurred(String s) {
        Log.d("serajpaytm","ui error "+s);
        pf_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Log.d("serajpaytm","err loding wpge"+s);
        pf_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressedCancelTransaction() {
        Log.d("serajpaytm","back press cancell transaction");
        pf_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Log.d("serajpaytm","transaction cancelled"+bundle);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ActivityRequestCode && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    Log.e(TAG, key + " : " + (bundle.get(key) != null ? bundle.get(key) : "NULL"));
                }
            }
            Log.e(TAG, " data "+  data.getStringExtra("nativeSdkForMerchantMessage"));
            Log.e(TAG, " data response - "+data.getStringExtra("response"));
/*
 data response - {"BANKNAME":"WALLET","BANKTXNID":"1395841115",
 "CHECKSUMHASH":"7jRCFIk6mrep+IhnmQrlrL43KSCSXrmM+VHP5pH0hekXaaxjt3MEgd1N9mLtWyu4VwpWexHOILCTAhybOo5EVDmAEV33rg2VAS/p0PXdk\u003d",
 "CURRENCY":"INR","GATEWAYNAME":"WALLET","MID":"EAc0553138556","ORDERID":"100620202152",
 "PAYMENTMODE":"PPI","RESPCODE":"01","RESPMSG":"Txn Success","STATUS":"TXN_SUCCESS",
 "TXNAMOUNT":"2.00","TXNDATE":"2020-06-10 16:57:45.0","TXNID":"20200610111212800110168328631290118"}
  */
            Toast.makeText(this, data.getStringExtra("nativeSdkForMerchantMessage")
                    + data.getStringExtra("response"), Toast.LENGTH_SHORT).show();
        }else{
            Log.e(TAG, " payment failed");
        }
    }

    public void goHome(View view) {

        startActivity(new Intent(PaytmActivity.this, MainActivity.class));
        finish();
    }
}