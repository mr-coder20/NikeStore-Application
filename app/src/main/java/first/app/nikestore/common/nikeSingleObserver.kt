package first.app.nikestore.common

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class nikeSingleObserver<T>(val CompositeDisposable: CompositeDisposable):SingleObserver<T> {
    override fun onError(e: Throwable) {
        Timber.e(e)
    }

    override fun onSubscribe(d: Disposable) {
        CompositeDisposable.add(d)

    }
}