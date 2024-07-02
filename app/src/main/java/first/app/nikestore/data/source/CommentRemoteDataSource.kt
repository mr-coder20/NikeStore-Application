package first.app.nikestore.data.source

import first.app.nikestore.data.Comment
import first.app.nikestore.services.http.apiService
import io.reactivex.Single

class CommentRemoteDataSource(val apiService: apiService):CommentDataSource {
    override fun getAll(productId:Int): Single<List<Comment>> =apiService.getComments(productId)

    override fun insert(): Single<Comment> {
        TODO("Not yet implemented")
    }
}