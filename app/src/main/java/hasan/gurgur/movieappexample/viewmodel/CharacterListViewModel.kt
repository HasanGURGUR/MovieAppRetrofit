package hasan.gurgur.movieappexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hasan.gurgur.movieappexample.api.MovieRepository
import hasan.gurgur.movieappexample.model.UpcomingResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val upcomingMoviesModel = MutableLiveData<UpcomingResponseModel>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun fetchDataFromRemoteApi(page : Int) {
        disposable.add(
            repository.getUpcomingMovies(page)
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