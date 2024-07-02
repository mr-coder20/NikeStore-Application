package first.app.nikestore

import android.app.Application
import android.os.Bundle
import com.facebook.drawee.backends.pipeline.Fresco
import first.app.nikestore.data.repo.CommentRepository
import first.app.nikestore.data.repo.CommentRepositoryImpl
import first.app.nikestore.data.repo.bannerRepository
import first.app.nikestore.data.repo.bannerRepositoryImpl
import first.app.nikestore.data.repo.productRepository
import first.app.nikestore.data.repo.productRepositoryImpl
import first.app.nikestore.data.source.CommentRemoteDataSource
import first.app.nikestore.data.source.bannerRemoteDataSource
import first.app.nikestore.data.source.productLocalDataSource
import first.app.nikestore.data.source.productRemoteDataSource
import first.app.nikestore.feature.product.ProductDetailViewModel
import first.app.nikestore.feature.main.ProductListAdapter
import first.app.nikestore.feature.main.mainViewModel
import first.app.nikestore.feature.product.comment.CommentListViewModel
import first.app.nikestore.services.ImageLoadingService
import first.app.nikestore.services.http.FrescoImageLoadingService
import first.app.nikestore.services.http.createApiServiceInstance
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber


class App : Application() {

    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)
        super.onCreate()
        val myModules = module {
            single { createApiServiceInstance() }
            single<ImageLoadingService> { FrescoImageLoadingService() }
            factory<productRepository> {
                productRepositoryImpl(
                    productRemoteDataSource(get()),
                    productLocalDataSource()

                )
            }
            factory { ProductListAdapter(get ()) }
            factory<CommentRepository> { CommentRepositoryImpl(CommentRemoteDataSource(get())) }
            factory <bannerRepository>{
                bannerRepositoryImpl(bannerRemoteDataSource(get()),
                )
            }
            viewModel { mainViewModel(get(),get()) }
            viewModel { (bundle: Bundle)-> ProductDetailViewModel(bundle,get()) }
            viewModel { (productId: Int)-> CommentListViewModel(productId,get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(myModules)

        }
    }

}