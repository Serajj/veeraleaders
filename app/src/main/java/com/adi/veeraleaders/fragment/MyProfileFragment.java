package com.adi.veeraleaders.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adi.veeraleaders.R;
import com.adi.veeraleaders.model.User;
import com.adi.veeraleaders.utiles.SessionManager;


public class MyProfileFragment extends Fragment {

    SessionManager sessionManager;
    User user;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_my_profile, container, false);
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetails(SessionManager.login);
        return view;
    }
}