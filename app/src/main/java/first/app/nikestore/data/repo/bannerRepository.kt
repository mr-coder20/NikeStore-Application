package first.app.nikestore.data.repo

import first.app.nikestore.data.banner
import io.reactivex.Single

interface bannerRepository {
    fun getBanners():Single<List<banner>>
}