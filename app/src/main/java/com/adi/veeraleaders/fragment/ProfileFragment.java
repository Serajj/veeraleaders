package com.adi.veeraleaders.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.adi.veeraleaders.R;
import com.adi.veeraleaders.adapter.TransactionAdapter;
import com.adi.veeraleaders.model.MyWinHistory;
import com.adi.veeraleaders.model.TrasactionResponse;
import com.adi.veeraleaders.model.User;
import com.adi.veeraleaders.retrofit.APIClient;
import com.adi.veeraleaders.utiles.CustPrograssbar;
import com.adi.veeraleaders.utiles.SessionManager;

import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    TextView upiTv,cupiTv;
    EditText upiEt;
    Button updateBtn;
    RecyclerView recyclerView;
    TransactionAdapter adapter;

    CustPrograssbar custPrograssbar;

    SessionManager sessionManager;
    String jsons="application/json";
    User user;
  View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        upiTv=view.findViewById(R.id.tv_upi);
        cupiTv=view.findViewById(R.id.tv_cupi);
        upiEt=view.findViewById(R.id.et_upi);
        updateBtn=view.findViewById(R.id.uBtn);
        recyclerView=view.findViewById(R.id.t_rv);
        custPrograssbar=new CustPrograssbar();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetails(SessionManager.login);
        if (TextUtils.isEmpty(sessionManager.getStringData("upiid"))){
            cupiTv.setText(user.getBankIfsc().toString());
        }else{
            cupiTv.setText(sessionManager.getStringData("upiid"));
        }



        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upiid= upiEt.getText().toString();
                if (TextUtils.isEmpty(upiid)){
                    
                }else{
                    updateUpiNow(user.getId(),upiid);
                }
            }
        });

        getTransactions();

        return view;
    }

    private void updateUpiNow(String id, String upiid) {
        custPrograssbar.prograssCreate(getContext());


        JSONObject jsonObject = new JSONObject();
        try {
            Log.d("serajth",user.getId());
            jsonObject.put("uid",id);
            jsonObject.put("upi_id",upiid);


        } catch (Exception e) {
            e.printStackTrace();

        }

        Log.d("serajpres",jsonObject.toString());
        RequestBody bodyRequest = RequestBody.create(MediaType.parse(jsons), jsonObject.toString());
        Call<ResponseBody> call = APIClient.getInterface().updateUpi(bodyRequest);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    custPrograssbar.closePrograssBar();
                    sessionManager.setStringData("upiid",upiid);
                    cupiTv.setText(upiid);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void getTransactions() {

        custPrograssbar.prograssCreate(getContext());


        JSONObject jsonObject = new JSONObject();
        try {
            Log.d("serajth",user.getId());
            jsonObject.put("uid",user.getId());


        } catch (Exception e) {
            e.printStackTrace();

        }

        Log.d("serajpres",jsonObject.toString());
        RequestBody bodyRequest = RequestBody.create(MediaType.parse(jsons), jsonObject.toString());
        Call<TrasactionResponse> call = APIClient.getInterface().getTransactions(bodyRequest);

        call.enqueue(new Callback<TrasactionResponse>() {
            @Override
            public void onResponse(Call<TrasactionResponse> call, Response<TrasactionResponse> response) {
                if (response.isSuccessful()){
                    Log.d("serajth",response.body().getResponseMsg());
                    TrasactionResponse myTransactionresponse=response.body();
                    List<MyWinHistory> transaction=myTransactionresponse.getMyWinHistory();
                    if (transaction!=null){
                        adapter = new TransactionAdapter(getContext(),transaction);
                        recyclerView.setAdapter(adapter);
                    }
                    custPrograssbar.closePrograssBar();
                }
            }

            @Override
            public void onFailure(Call<TrasactionResponse> call, Throwable t) {

            }
        });
    }
}