package common

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory

object RetrofitProvider {

    fun newRetrofit(host: String) = Retrofit.Builder()
            .baseUrl(host)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
}