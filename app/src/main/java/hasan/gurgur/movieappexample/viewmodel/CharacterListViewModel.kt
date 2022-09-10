package hasan.gurgur.movieappexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hasan.gurgur.movieappexample.api.MovieInstance
import hasan.gurgur.movieappexample.model.UpcomingResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel : ViewModel() {

    val upcomingMoviesModel = MutableLiveData<UpcomingResponseModel>()

    private val movieService: MovieInstance = MovieInstance()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun fetchDataFromRemoteApi(page : String) {
        disposable.add(
            movieService.getUpcomingMovies(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UpcomingResponseModel>() {
                    override fun onSuccess(response:UpcomingResponseModel) {
                        upcomingMoviesModel.value = response
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

}