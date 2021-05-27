package com.adi.veeraleaders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.adi.veeraleaders.fragment.HomeFragment;
import com.adi.veeraleaders.fragment.MyDrawFragment;
import com.adi.veeraleaders.fragment.ProfileFragment;
import com.adi.veeraleaders.model.User;
import com.adi.veeraleaders.retrofit.APIClient;
import com.adi.veeraleaders.ui.LoginActivity;
import com.adi.veeraleaders.ui.PaymentActivity;
import com.adi.veeraleaders.utiles.SessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;
    Button menuBtn;
    SessionManager sessionManager;

    boolean home=true;
    String jsons="application/json";
    User user;
    FrameLayout frameLayout;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*---------------------Hooks------------------------*/
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        sessionManager = new SessionManager(MainActivity.this);
        user = sessionManager.getUserDetails(SessionManager.login);
        toolbar=findViewById(R.id.toolbar);
        menuBtn=findViewById(R.id.menu_btn);

        frameLayout=findViewById(R.id.main_frame);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.d("serajtoken", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        token = task.getResult().getToken();
                        Log.d("serajtoken",""+token);
                        updateToken(token);

                    }
                });

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new
                ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:

                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.main_frame, new HomeFragment()).commit();

                        break;

                    case R.id.nav_history:

                        FragmentManager managerwh = getSupportFragmentManager();
                        FragmentTransaction transactionwh = managerwh.beginTransaction();
                        transactionwh.replace(R.id.main_frame, new ProfileFragment()).commit();

                        break;

                    case R.id.nav_logout:
                        new SessionManager(MainActivity.this).logoutUser();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        finishAffinity();
                        break;
                    case R.id.nav_mydraws:
                        home=false;
                        FragmentManager manager1 = getSupportFragmentManager();
                        FragmentTransaction transaction1 = manager1.beginTransaction();
                        transaction1.replace(R.id.main_frame, new MyDrawFragment()).commit();


//                        menu.findItem(R.id.nav_logout).setVisible(false);
//                        menu.findItem(R.id.nav_profile).setVisible(false);
//                        menu.findItem(R.id.nav_login).setVisible(true);
                        break;
                    case R.id.nav_view: Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show(); break;
                }
                drawerLayout.closeDrawer(GravityCompat.START); return true;

            }
        });

        navigationView.setCheckedItem(R.id.nav_home);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_frame, new HomeFragment()).commit();

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               drawerLayout.openDrawer(GravityCompat.START);

            }
        });

    }

    private void updateToken(String token) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", user.getId());
            jsonObject.put("device_id", token);
        } catch (Exception e) {
            e.printStackTrace();

        }

        Log.d("serajpres",jsonObject.toString());
        RequestBody bodyRequest = RequestBody.create(MediaType.parse(jsons), jsonObject.toString());
        Call<JsonObject> call = APIClient.getInterface().updateDeviceId(bodyRequest);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    Log.d("serajtoken",user.getId()+" "+response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            if (home){
                super.onBackPressed();
                finish();
            }else{
                home=true;
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.main_frame, new HomeFragment()).commit();

            }

        }
    }



}