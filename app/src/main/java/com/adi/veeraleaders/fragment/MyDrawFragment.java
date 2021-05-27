package com.adi.veeraleaders.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adi.veeraleaders.R;
import com.adi.veeraleaders.adapter.DrawAdapter;
import com.adi.veeraleaders.adapter.MyDrawAdapter;
import com.adi.veeraleaders.model.DrawResponse;
import com.adi.veeraleaders.model.LuckyDraw;
import com.adi.veeraleaders.model.MyDraw;
import com.adi.veeraleaders.model.MyDrawResponse;
import com.adi.veeraleaders.model.User;
import com.adi.veeraleaders.retrofit.APIClient;
import com.adi.veeraleaders.ui.PaymentActivity;
import com.adi.veeraleaders.utiles.CustPrograssbar;
import com.adi.veeraleaders.utiles.SessionManager;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyDrawFragment extends Fragment {

    View view;

    RecyclerView recyclerView;
    MyDrawAdapter adapter;

    CustPrograssbar custPrograssbar;

    SessionManager sessionManager;
    String jsons="application/json";
    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_my_draw, container, false);
      custPrograssbar=new CustPrograssbar();
        recyclerView=view.findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetails(SessionManager.login);
        getMyDraws();
        return view;
    }

    private void getMyDraws() {

        custPrograssbar.prograssCreate(getContext());


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", user.getId());


        } catch (Exception e) {
            e.printStackTrace();

        }

        Log.d("serajpres",jsonObject.toString());
        RequestBody bodyRequest = RequestBody.create(MediaType.parse(jsons), jsonObject.toString());
        Call<MyDrawResponse> call = APIClient.getInterface().getMydraws(bodyRequest);

        call.enqueue(new Callback<MyDrawResponse>() {
            @Override
            public void onResponse(Call<MyDrawResponse> call, Response<MyDrawResponse> response) {
                if (response.isSuccessful()){
                    MyDrawResponse myDrawResponse=response.body();
                    List<MyDraw> luckyDraws = myDrawResponse.getMyDraws();


                    adapter = new MyDrawAdapter(getContext(),luckyDraws);
                    recyclerView.setAdapter(adapter);
                    custPrograssbar.closePrograssBar();
                }
            }

            @Override
            public void onFailure(Call<MyDrawResponse> call, Throwable t) {

            }
        });
    }
}