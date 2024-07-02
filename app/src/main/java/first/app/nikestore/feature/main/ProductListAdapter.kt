package first.app.nikestore.feature.main

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sevenlearn.nikestore.common.formatPrice
import com.sevenlearn.nikestore.common.implementSpringAnimationTrait
import first.app.nikestore.R
import first.app.nikestore.data.product
import first.app.nikestore.services.ImageLoadingService
import first.app.nikestore.view.NikeImageView

class ProductListAdapter(val imageLoadingService: ImageLoadingService):RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    var popularproductLiveData:ProductOnClickListener?=null
    var latestproductLiveData:ProductOnClickListener?=null
    var products=ArrayList<product>()

    set(value)  {
        field=value
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productIv:NikeImageView=itemView.findViewById(R.id.productIv)
        val titleTv:TextView=itemView.findViewById(R.id.productTitleTv)
        val currentPriceTv:TextView=itemView.findViewById(R.id.currentPriceTv)
        val periviousPriceTv:TextView=itemView.findViewById(R.id.periviousPriceTv)

        fun BindProduct(product: product) {

        imageLoadingService.load(productIv,product.image)
            titleTv.text=product.title
            currentPriceTv.text= formatPrice(product.price)
            periviousPriceTv.text= formatPrice(product.previous_price)
            periviousPriceTv.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
            itemView.implementSpringAnimationTrait()
            itemView.setOnClickListener{
                popularproductLiveData?.onProductClick(product)
                latestproductLiveData?.onProductClick(product)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false))
    }

    override fun getItemCount(): Int =products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =holder.BindProduct(products[position])
}
interface ProductOnClickListener{
    fun onProductClick(product: product)

}