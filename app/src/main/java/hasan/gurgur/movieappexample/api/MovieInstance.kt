package hasan.gurgur.movieappexample.api

import hasan.gurgur.movieappexample.model.UpcomingResponseModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieInstance {

    private val BASE_URL = "https://api.themoviedb.org/3/"
    private val API_KEY = "d8d1b3f9e6a3db84e1c6584fbf8ca8f4"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ApiService::class.java)

    fun getUpcomingMovies(page : Int): Single<UpcomingResponseModel> {
        return api.getUpcomingMovies(API_KEY, page)
    }
}