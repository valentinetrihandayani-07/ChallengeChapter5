package com.valentine.challengechapter5.API

import com.valentine.challengechapter5.model.ListMovie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.themoviedb.org/"
private const val API_KEY = "14e84366df6954a1e3048857dc4c9648"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService{

    @GET("3/movie/634649/recommendations?api_key=14e84366df6954a1e3048857dc4c9648&language=en-US&page=1")
    fun allMovies(): Call<ListMovie>


}

object MovieApi{
    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val instance: MovieApiService by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(MovieApiService::class.java)
    }
    val retrofitService:MovieApiService by lazy{retrofit.create(MovieApiService::class.java)}
}