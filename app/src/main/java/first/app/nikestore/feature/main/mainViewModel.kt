package first.app.nikestore.feature.main

import androidx.lifecycle.MutableLiveData
import first.app.nikestore.common.nikeSingleObserver
import first.app.nikestore.common.nikeViewModel
import first.app.nikestore.data.SORT_LATEST
import first.app.nikestore.data.SORT_POPULAR
import first.app.nikestore.data.banner
import first.app.nikestore.data.product
import first.app.nikestore.data.repo.bannerRepository
import first.app.nikestore.data.repo.productRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class mainViewModel(productRepository: productRepository, val bannerRepository: bannerRepository) :
    nikeViewModel() {
    val latestproductLiveData = MutableLiveData<List<product>>()
    val popularproductLiveData = MutableLiveData<List<product>>()
    val bannersLiveData = MutableLiveData<List<banner>>()



    init {
        progressBarLiveData.value = true
        productRepository.getProducts(SORT_LATEST)

            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : nikeSingleObserver<List<product>>(CompositeDisposable) {
                override fun onSuccess(t: List<product>) {
                    latestproductLiveData.value = t
                }

            })






        bannerRepository.getBanners().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : nikeSingleObserver<List<banner>>(CompositeDisposable) {
                override fun onSuccess(t: List<banner>) {
                    bannersLiveData.value = t

                }
            })


        productRepository.getProducts(SORT_POPULAR)

            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : nikeSingleObserver<List<product>>(CompositeDisposable) {
                override fun onSuccess(t: List<product>) {
                    popularproductLiveData.value = t
                }

            })



    }
}