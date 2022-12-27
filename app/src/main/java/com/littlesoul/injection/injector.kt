package com.littlesoul.injection


import com.google.gson.GsonBuilder
import com.littlesoul.BuildConfig
import com.littlesoul.common.AppConstants.ACCESS_TOKEN
import com.littlesoul.common.AppConstants.API_KEY_VALUE
import com.littlesoul.common.AppConstants.LOGGED_IN_USER_ID
import com.littlesoul.common.AppConstants.STATIC_API_KEY
import com.littlesoul.common.utils.SharedPreferenceManager
import com.littlesoul.common.utils.ShowLogToast
import com.littlesoul.shareddata.endpoint.ApiEndPoint
import com.littlesoul.shareddata.repo.UserRepo
import com.littlesoul.shareddata.repo.UserRepository

import com.littlesoul.ui.login.apiModel.LoginApiModel

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    single<UserRepo> { UserRepository(get()) }
    viewModel { LoginApiModel(get()) }

}

val networkModule = module {
    single { provideHttpLogging() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

fun provideHttpLogging(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return OkHttpClient.Builder()
        .addNetworkInterceptor(AddHeaderInterceptor())
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addNetworkInterceptor(logging)
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    val gson = GsonBuilder().setLenient().create()
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
}

class AddHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val apikey: String? = SharedPreferenceManager.getString(API_KEY_VALUE, STATIC_API_KEY)

        val accessToken: String? = SharedPreferenceManager.getString(ACCESS_TOKEN, "")
        val userID: String = SharedPreferenceManager.getString(LOGGED_IN_USER_ID, "-1").toString()

        if (apikey == STATIC_API_KEY) builder.addHeader("xapikey", "" + apikey) else builder.addHeader("apikey", "" + apikey)

        if (accessToken != "") builder.addHeader("accesstoken", "" + accessToken)
        if (!userID.equals("-1")) builder.addHeader("userID", "" + userID)
        builder.addHeader("role", "User")
        builder.addHeader("deviceType", "android")
        ShowLogToast.ShowLog("http Apikey -->$apikey")
        ShowLogToast.ShowLog("http accesstoken -->$accessToken")
        ShowLogToast.ShowLog("http user_id -->$userID")
        return chain.proceed(builder.build())
    }
}

fun provideApiService(retrofit: Retrofit): ApiEndPoint = retrofit.create(ApiEndPoint::class.java)

val appModules = viewModelModule + networkModule