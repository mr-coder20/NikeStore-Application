package first.app.nikestore.data.repo

import first.app.nikestore.data.Comment
import first.app.nikestore.data.source.CommentDataSource
import io.reactivex.Single
import javax.sql.CommonDataSource

class CommentRepositoryImpl(val CommentDataSource: CommentDataSource):CommentRepository {
    override fun getAll(productId:Int): Single<List<Comment>> =CommentDataSource.getAll(productId)

    override fun insert(): Single<Comment> {
        TODO("Not yet implemented")
    }
}