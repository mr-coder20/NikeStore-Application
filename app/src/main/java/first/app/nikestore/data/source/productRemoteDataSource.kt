package first.app.nikestore.data.source

import first.app.nikestore.data.product
import first.app.nikestore.services.http.apiService
import io.reactivex.Completable
import io.reactivex.Single

class productRemoteDataSource(val apiService: apiService):productDataSource {
    override fun getProducts(sort:Int): Single<List<product>> =apiService.getProduct(sort.toString())

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