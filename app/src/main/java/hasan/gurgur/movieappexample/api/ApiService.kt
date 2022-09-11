package hasan.gurgur.movieappexample.api

import hasan.gurgur.movieappexample.model.UpcomingResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") api_key : String,
                      @Query("page") page : Int)
    : Single<UpcomingResponseModel>
}