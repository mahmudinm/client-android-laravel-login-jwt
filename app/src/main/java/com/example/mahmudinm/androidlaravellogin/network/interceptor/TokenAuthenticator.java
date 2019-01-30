package com.example.mahmudinm.androidlaravellogin.network.interceptor;

import com.example.mahmudinm.androidlaravellogin.MyApp;
import com.example.mahmudinm.androidlaravellogin.network.ApiClient;
import com.example.mahmudinm.androidlaravellogin.network.ApiInterface;
import com.example.mahmudinm.androidlaravellogin.network.response.UserResponse;
import com.example.mahmudinm.androidlaravellogin.utils.SharedPrefManager;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;

public class TokenAuthenticator implements Interceptor {

    ApiInterface apiInterface;
    SharedPrefManager sharedPrefManager;

    public TokenAuthenticator() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPrefManager = new SharedPrefManager(MyApp.getContext());
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response mainResponse = chain.proceed(chain.request());
        Request mainRequest = chain.request();

        if (mainResponse.code() == 401 || mainResponse.code() == 403 ) {
            String token = sharedPrefManager.getSPToken();
            retrofit2.Response<UserResponse> refreshToken = apiInterface.refreshToken(token)
                    .execute();
        } else if (mainResponse.code() == 500 ){
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        }

        return mainResponse;
    }
}
