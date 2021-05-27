package com.adi.veeraleaders.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adi.veeraleaders.R;
import com.adi.veeraleaders.adapter.DrawAdapter;
import com.adi.veeraleaders.adapter.SliderAdapterExample;
import com.adi.veeraleaders.model.DrawResponse;
import com.adi.veeraleaders.model.LuckyDraw;
import com.adi.veeraleaders.model.Slider;
import com.adi.veeraleaders.model.SliderItem;
import com.adi.veeraleaders.retrofit.APIClient;
import com.adi.veeraleaders.utiles.CustPrograssbar;
import com.google.gson.JsonObject;
import com.smarteist.autoimageslider.SliderView;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    View view;
    SliderView sliderView;

    SliderAdapterExample sliderAdapterExample;

    CustPrograssbar custPrograssbar;
    RecyclerView recyclerView;
    DrawAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        sliderView = view.findViewById(R.id.imageSlider);
        custPrograssbar=new CustPrograssbar();
        sliderView.setAutoCycle(true);
        sliderView.setOffscreenPageLimit(4);

        recyclerView=view.findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadBanner();
        getDraws();
        return view;
    }

    private void getDraws() {
        custPrograssbar.prograssCreate(getContext());
        Call<DrawResponse> calldraw = APIClient.getInterface().getDrawList();

        calldraw.enqueue(new Callback<DrawResponse>() {
            @Override
            public void onResponse(Call<DrawResponse> call, Response<DrawResponse> response) {
                if (response.isSuccessful()){
                    DrawResponse drawResponse = response.body();
                    List<LuckyDraw> luckyDraws = drawResponse.getLuckyDraws();


                    adapter = new DrawAdapter(getContext(),luckyDraws);
                    recyclerView.setAdapter(adapter);
                    custPrograssbar.closePrograssBar();
                }
            }

            @Override
            public void onFailure(Call<DrawResponse> call, Throwable t) {

            }
        });
    }

    private void loadBanner() {
        Call<Slider> call = APIClient.getInterface().getBanner();
        call.enqueue(new Callback<Slider>() {
            @Override
            public void onResponse(Call<Slider> call, Response<Slider> response) {
                if (response.isSuccessful()){
                    Slider slider = response.body();

                    Log.d("seraj",slider.toString());

                    SliderAdapterExample sliderAdapterExample = new SliderAdapterExample(getContext(),slider.getSliderItem());

                    sliderView.setSliderAdapter(sliderAdapterExample);



                }else{
                    Log.d("seraj","response failed ");
                }
            }

            @Override
            public void onFailure(Call<Slider> call, Throwable t) {

            }
        });
    }


}