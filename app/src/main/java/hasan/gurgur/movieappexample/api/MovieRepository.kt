package hasan.gurgur.movieappexample.api

import hasan.gurgur.movieappexample.di.AppModule.API_KEY
import hasan.gurgur.movieappexample.model.UpcomingResponseModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MovieRepository @Inject constructor(val apiService: ApiService) {

    fun getUpcomingMovies(page : Int): Single<UpcomingResponseModel> {
        return apiService.getUpcomingMovies(API_KEY, page)
    }




}