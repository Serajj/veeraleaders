package com.adi.veeraleaders.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.adi.veeraleaders.R;
import com.adi.veeraleaders.model.User;
import com.adi.veeraleaders.utiles.SessionManager;

public class ReferFragment extends Fragment {

    TextView referal, copyReferal, refer;
    View view;
    SessionManager sessionManager;
    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       view =inflater.inflate(R.layout.fragment_refer, container, false);
        sessionManager = new SessionManager(getContext());
        user = sessionManager.getUserDetails(SessionManager.login);


        referal = view.findViewById(R.id.referal);
        referal.setText(user.getRid());
        refer = view.findViewById(R.id.refer);

        copyReferal = view.findViewById(R.id.copyReferal);
        copyReferal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Referal", referal.getText().toString());
                clipboard.setPrimaryClip(clipData);

                Toast.makeText(getContext(), "Copied", Toast.LENGTH_LONG).show();
            }
        });

        refer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Click here to download App https://play.google.com/store/apps/ & Use this Referral code "+user.getRid()+" to get Rs 50";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        return view;
    }

}