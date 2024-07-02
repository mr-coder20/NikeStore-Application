package first.app.nikestore.feature.product

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.sevenlearn.nikestore.common.formatPrice
import first.app.nikestore.EXTRA_KEY_ID
import first.app.nikestore.R
import first.app.nikestore.common.nikeActivity
import first.app.nikestore.data.Comment
import first.app.nikestore.feature.product.comment.CommentListActivity
import first.app.nikestore.services.ImageLoadingService
import first.app.nikestore.view.NikeImageView
import first.app.nikestore.view.scroll.ObservableScrollView
import first.app.nikestore.view.scroll.ObservableScrollViewCallbacks
import first.app.nikestore.view.scroll.ScrollState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class ProductDetailActivity : nikeActivity() {



    val productDetailViewModel: ProductDetailViewModel by viewModel { parametersOf(intent.extras) }
    val imageLoadingService: ImageLoadingService by inject()
    val commentAdapter = CommentAdapter()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        initViews()



        productDetailViewModel.productLiveData.observe(this) {


            val productIv=findViewById<NikeImageView>(R.id.productIv)

            val titleTv=findViewById<TextView>(R.id.titleTv)
            val periviousPriceTv=findViewById<TextView>(R.id.previousPriceTv)
            val currentPriceTv=findViewById<TextView>(R.id.currentPriceTv)
            val toolbarTitleTv=findViewById<TextView>(R.id.toolbarTitleTv)

            imageLoadingService.load(productIv, it.image)
            titleTv.text = it.title
            periviousPriceTv.text = formatPrice(it.previous_price)
            periviousPriceTv.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG
            currentPriceTv.text = formatPrice(it.price)
            toolbarTitleTv.text = it.title
        }

        productDetailViewModel.progressBarLiveData.observe(this){
            setProgressIndicator(it)
        }

        productDetailViewModel.commentLiveData.observe(this) {
            Timber.i(it.toString())
            commentAdapter.comments = it as ArrayList<Comment>
            val viewAllCommentsBtn = findViewById<MaterialButton>(R.id.viewAllCommentsBtn)
            if(it.size>5){
                viewAllCommentsBtn.visibility=View.VISIBLE

                val viewAllCommentsBtn=findViewById<MaterialButton>(R.id.viewAllCommentsBtn)
                viewAllCommentsBtn.setOnClickListener{
                    startActivity(Intent(this,CommentListActivity::class.java).apply {
                        putExtra(EXTRA_KEY_ID,productDetailViewModel.productLiveData.value!!.id)
                        })
                }

            }else{
                viewAllCommentsBtn.visibility=View.GONE

            }


        }



    }

    fun initViews() {


        val commentsRv = findViewById<RecyclerView>(R.id.commentsRv)
        val toolbarView = findViewById<MaterialCardView>(R.id.toolbarView)
        val observableScrollView= findViewById<ObservableScrollView>(R.id.observableScrollView)
        val productIv=findViewById<NikeImageView>(R.id.productIv)

        commentsRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        commentsRv.adapter = commentAdapter
        commentsRv.isNestedScrollingEnabled = false



        productIv.post {
            val productIvHeight = productIv.height
            val toolbar = toolbarView
            val productImageView = productIv
            observableScrollView.addScrollViewCallbacks(object : ObservableScrollViewCallbacks {
                override fun onScrollChanged(
                    scrollY: Int, firstScroll: Boolean, dragging: Boolean
                ) {

                    toolbar.alpha = scrollY.toFloat() / productIvHeight.toFloat()
                    productImageView.translationY = scrollY.toFloat() / 2
                }

                override fun onDownMotionEvent() {
                }

                override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {
                }

            })
        }

      }
}