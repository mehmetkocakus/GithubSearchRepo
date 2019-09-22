package com.example.mehmetkocakus.challenge.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Bu class sunucu ile bağlantı yapıp istek oluşturabilmek için kullanılmıştır
 */
public class APICient {

    private static Retrofit retrofit;
    private static String BASE_URL;

    public  APICient(String url){
        this.BASE_URL=url;
    }
    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
