package first.app.nikestore.data.repo

import first.app.nikestore.data.product
import io.reactivex.Completable
import io.reactivex.Single

interface productRepository {



    fun getProducts(sort:Int):Single<List<product>>

    fun getFavoriteProduct():Single<List<product>>

    fun addToFavorite():Completable

    fun deleteFromFavorite():Completable



}