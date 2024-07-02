package first.app.nikestore.data.repo

import first.app.nikestore.data.banner
import first.app.nikestore.data.source.bannerDataSource
import first.app.nikestore.data.source.bannerRemoteDataSource
import first.app.nikestore.data.source.productRemoteDataSource
import io.reactivex.Single

class bannerRepositoryImpl(val bannerRemoteDataSource: bannerDataSource):bannerRepository {
    override fun getBanners(): Single<List<banner>> =bannerRemoteDataSource.getBanners()

}