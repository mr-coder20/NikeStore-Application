package first.app.nikestore.data.source

import first.app.nikestore.data.Comment
import io.reactivex.Single

interface CommentDataSource {
    fun getAll(productId:Int):Single<List<Comment>>

    fun insert():Single<Comment>

}