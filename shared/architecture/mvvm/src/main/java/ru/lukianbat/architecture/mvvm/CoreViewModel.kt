package ru.lukianbat.architecture.mvvm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Базовая ViewModel фрагментов, от которой должны наследоваться все новые вью модели,
 * по надобности выносим сюда функционал который может быть переиспользован.
 *
 */
abstract class CoreViewModel(
    private val errorAdapter: ErrorAdapter = ErrorAdapter.DEFAULT
) : ViewModel() {

    /**
     * Лайвдата с текстом ошибки для отображения на ui
     */
    val errorMessageLiveData = SingleLiveEvent<String>()

    private var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    /**
     * Базовая обработка ошибок
     */
    open fun onError(throwable: Throwable) {
        errorMessageLiveData.postValue(errorAdapter.adapt(throwable))
    }

    protected fun Disposable.addDisposable() = also { compositeDisposable.add(it) }
}
