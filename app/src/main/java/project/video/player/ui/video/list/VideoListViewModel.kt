package project.video.player.ui.video.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import project.video.player.common.Event
import project.video.player.di.VIDEO_SEARCH_DEBOUNCE_TIME_MS
import project.video.player.di.VIDEO_SEARCH_PHRASE_MIN_LENGTH
import project.video.player.video.interactors.GetVideoListUseCase
import project.video.player.video.model.Video
import java.util.concurrent.TimeUnit


class VideoListViewModel(
    private val getVideoListUserCase: GetVideoListUseCase,
) : ViewModel() {

    private val videoListPublishSubject = PublishSubject.create<String>()
    private val compositeDisposable = CompositeDisposable()

    private val videoListLiveData = MutableLiveData<List<Video>>()
    private val videoListErrorLiveData = MutableLiveData<Event<Throwable>>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        videoListPublishSubject.onComplete()
    }

    fun onSearchPhraseChanged(searchPhrase: String?) {
        if (searchPhrase == null
            || searchPhrase.length < VIDEO_SEARCH_PHRASE_MIN_LENGTH) {
            return
        }

        fetchVideoList(searchPhrase)
    }

    fun configureVideoListSubject() {
        val disposable = videoListPublishSubject
            .debounce(VIDEO_SEARCH_DEBOUNCE_TIME_MS, TimeUnit.MILLISECONDS)
            .switchMap { mapStringToVideoListObservable(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::onFetchVideoListSuccess,
                this::onFetchVideoListFailed
            )

        compositeDisposable.add(disposable)
    }

    fun getVideoListLiveData(): LiveData<List<Video>> = videoListLiveData
    fun getVideoListErrorLiveData(): LiveData<Event<Throwable>> = videoListErrorLiveData

    private fun mapStringToVideoListObservable(searchPhrase: String): Observable<List<Video>> {
        return getVideoListUserCase(searchPhrase)
            .onErrorReturn {
                onFetchVideoListFailed(it)
                emptyList()
            }
    }

    private fun fetchVideoList(searchPhrase: String) {
        videoListPublishSubject.onNext(searchPhrase)
    }

    private fun onFetchVideoListSuccess(videoList: List<Video>) {
        videoListLiveData.postValue(videoList)
    }

    private fun onFetchVideoListFailed(throwable: Throwable) {
        videoListErrorLiveData.postValue(Event(throwable))
    }

}