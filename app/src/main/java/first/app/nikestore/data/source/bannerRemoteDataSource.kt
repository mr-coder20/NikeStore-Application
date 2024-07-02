package first.app.nikestore.data.source

import first.app.nikestore.data.banner
import first.app.nikestore.services.http.apiService
import io.reactivex.Single

class bannerRemoteDataSource(val apiService: apiService):bannerDataSource {
    override fun getBanners(): Single<List<banner>> =apiService.getBanner()
}