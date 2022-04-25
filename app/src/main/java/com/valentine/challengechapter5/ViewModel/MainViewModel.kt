package com.valentine.challengechapter5.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valentine.challengechapter5.API.MovieApi
import com.valentine.challengechapter5.model.ListMovie
import retrofit2.Call
import retrofit2.Response
import com.valentine.challengechapter5.model.Result

class MainViewModel: ViewModel() {

    private val movies : MutableLiveData<List<Result>> by lazy {
        MutableLiveData<List<Result>>().also {
            getAllMovies()
        }
    }


    fun getMovies(): LiveData<List<Result>> {
        return movies
    }
    private fun getAllMovies(){
        MovieApi.retrofitService.allMovies().enqueue(object : retrofit2.Callback<ListMovie>{
            override fun onResponse(
                call: Call<ListMovie>,
                response: Response<ListMovie>
            ) {
                movies.value = response.body()?.results
            }

            override fun onFailure(call: Call<ListMovie>, t: Throwable) {
                Log.d("Tag",t.message.toString())
            }

        })
    }
}
