package first.app.nikestore.data.repo

import first.app.nikestore.data.product
import first.app.nikestore.data.source.productDataSource
import first.app.nikestore.data.source.productLocalDataSource
import io.reactivex.Completable
import io.reactivex.Single

class productRepositoryImpl(
  val  remoteDataSource: productDataSource,
  val  localDataSource: productLocalDataSource
) :productRepository {
    override fun getProducts(sort:Int): Single<List<product>> = remoteDataSource.getProducts(sort)

    override fun getFavoriteProduct(): Single<List<product>> {
        TODO("Not yet implemented")
    }

    override fun addToFavorite(): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteFromFavorite(): Completable {
        TODO("Not yet implemented")
    }
}