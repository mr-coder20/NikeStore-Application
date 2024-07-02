package first.app.nikestore.data.source
import first.app.nikestore.data.product
import io.reactivex.Completable
import io.reactivex.Single



class productLocalDataSource:productDataSource {

    override fun getProducts(sort:Int): Single<List<product>> {
        TODO("Not yet implemented")
    }


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