package com.bawei.zhangjiafu1707b20200106.utlis;

import com.bawei.zhangjiafu1707b20200106.api.Api;
import com.blankj.utilcode.util.NetworkUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: 张家辅
 * @date: 2020/01/06
 */
public class NetUtlis {
    private static NetUtlis netUtlis;
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;

    private NetUtlis() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public <T>T create(Class<T> tClass){
        T t = retrofit.create(tClass);
        return t;
    }
    public Boolean isConnect(){
        return NetworkUtils.isConnected();
    }

    public static NetUtlis getInstance() {
        if(netUtlis==null){
            synchronized (NetUtlis.class){
                if(netUtlis==null){
                    netUtlis=new NetUtlis();
                }
            }
        }
        return netUtlis;
    }
}
