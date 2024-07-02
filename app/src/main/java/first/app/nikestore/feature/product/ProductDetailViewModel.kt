package first.app.nikestore.feature.product

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.sevenlearn.nikestore.common.asyncNetworkRequest
import first.app.nikestore.EXTRA_KEY_DATA
import first.app.nikestore.common.nikeSingleObserver
import first.app.nikestore.common.nikeViewModel
import first.app.nikestore.data.Comment
import first.app.nikestore.data.product
import first.app.nikestore.data.repo.CommentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailViewModel(bundle: Bundle, commentRepository: CommentRepository ): nikeViewModel() {

val productLiveData=MutableLiveData<product>()
    val commentLiveData=MutableLiveData<List<Comment>>()
    init {
        productLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
        progressBarLiveData.value = true

        commentRepository.getAll(productLiveData.value!!.id)
            .asyncNetworkRequest()
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object :nikeSingleObserver<List<Comment>>(CompositeDisposable){
                override fun onSuccess(t: List<Comment>) {
                    commentLiveData.value = t

                }

            })
    }
}