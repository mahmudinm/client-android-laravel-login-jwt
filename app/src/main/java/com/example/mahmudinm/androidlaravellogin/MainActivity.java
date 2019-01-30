package com.example.mahmudinm.androidlaravellogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahmudinm.androidlaravellogin.model.User;
import com.example.mahmudinm.androidlaravellogin.network.ApiClient;
import com.example.mahmudinm.androidlaravellogin.network.ApiInterface;
import com.example.mahmudinm.androidlaravellogin.network.response.UserResponse;
import com.example.mahmudinm.androidlaravellogin.utils.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    ApiInterface apiInterface;
            
    @BindView(R.id.tvNama)
    TextView tvNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sharedPrefManager = new SharedPrefManager(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        
        tvNama.setText(sharedPrefManager.getSPNama());

    }

    @OnClick(R.id.btnLogout) void logout() {
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        startActivity(new Intent(MainActivity.this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @OnClick(R.id.btnCekAuth) void cekAuth() {
//        Toast.makeText(this, sharedPrefManager.getSPToken(), Toast.LENGTH_SHORT).show();
        Call<User> getUser = apiInterface.getUser(sharedPrefManager.getSPToken());
        getUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    Toast.makeText(MainActivity.this, response.body().getEmail(), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401 || response.code() == 403 ) {
                    Toast.makeText(MainActivity.this, "Token Expired", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    
    }

}
