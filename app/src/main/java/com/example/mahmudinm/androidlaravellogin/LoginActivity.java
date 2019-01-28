    package com.example.mahmudinm.androidlaravellogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.mahmudinm.androidlaravellogin.network.ApiInterface;
import com.example.mahmudinm.androidlaravellogin.utils.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

    public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;

    Context mContext;
    ApiInterface apiInterface;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        ButterKnife.bind(this);

    }

}
