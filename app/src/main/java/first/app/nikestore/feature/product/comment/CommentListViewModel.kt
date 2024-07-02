package first.app.nikestore.feature.product.comment

import android.database.Observable
import androidx.lifecycle.MutableLiveData
import com.sevenlearn.nikestore.common.asyncNetworkRequest
import first.app.nikestore.common.nikeSingleObserver
import first.app.nikestore.common.nikeViewModel
import first.app.nikestore.data.Comment
import first.app.nikestore.data.repo.CommentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommentListViewModel(productId: Int,commentRepository: CommentRepository):nikeViewModel() {

    val commentsLiveData=MutableLiveData<List<Comment>>()
init {
    progressBarLiveData.value=true
    commentRepository.getAll(productId)
        .asyncNetworkRequest()
        .doFinally { progressBarLiveData.value=false}
        .subscribe(object :nikeSingleObserver<List<Comment>>(CompositeDisposable){
            override fun onSuccess(t: List<Comment>) {
                commentsLiveData.value=t
            }
        })


}

}