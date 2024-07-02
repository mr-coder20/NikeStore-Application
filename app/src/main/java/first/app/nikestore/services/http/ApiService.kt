package first.app.nikestore.services.http

import first.app.nikestore.data.Comment
import first.app.nikestore.data.banner
import first.app.nikestore.data.product
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface apiService {
    @GET("product/list")
    fun getProduct(@Query("sort")sort:String):Single<List<product>>

    @GET("banner/slider")
    fun getBanner():Single<List<banner>>

    @GET("comment/list")
    fun getComments(@Query("product_id") productId: Int): Single<List<Comment>>
}
fun createApiServiceInstance():apiService{
        val retrofit=Retrofit.Builder()
        .baseUrl("http://expertdevelopers.ir/api/v1/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        return retrofit.create(apiService::class.java)

}