package first.app.nikestore.data.source

import first.app.nikestore.data.banner
import io.reactivex.Single

interface bannerDataSource {
    fun getBanners():Single<List<banner>>
}