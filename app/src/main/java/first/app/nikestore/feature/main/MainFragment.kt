package first.app.nikestore.feature.main
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sevenlearn.nikestore.common.convertDpToPixel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import first.app.nikestore.EXTRA_KEY_DATA
import first.app.nikestore.R
import first.app.nikestore.common.nikeFragment
import first.app.nikestore.data.product
import first.app.nikestore.feature.product.ProductDetailActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : nikeFragment(),ProductOnClickListener {
    val mainViewModel: mainViewModel by viewModel()
    val latestproductListAdapter: ProductListAdapter by inject()
    val popularproductListAdapter: ProductListAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val latestProductsRv = view.findViewById<RecyclerView>(R.id.latestProductsRv)
        val popularProductsRv = view.findViewById<RecyclerView>(R.id.popularProductsRv)

        latestProductsRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        popularProductsRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        latestProductsRv.adapter = latestproductListAdapter
        popularProductsRv.adapter = popularproductListAdapter
        popularproductListAdapter.popularproductLiveData=this
        latestproductListAdapter.latestproductLiveData=this
        mainViewModel.latestproductLiveData.observe(viewLifecycleOwner) {

            latestproductListAdapter.products = it as ArrayList<product>
        }
        mainViewModel.popularproductLiveData.observe(viewLifecycleOwner) {

            popularproductListAdapter.products = it as ArrayList<product>

            mainViewModel.progressBarLiveData.observe(viewLifecycleOwner) {
                setProgressIndicator(it)

            }
            mainViewModel.bannersLiveData.observe(viewLifecycleOwner) {

                val BannerSliderAdapter = BannerSliderAdapter(this, it)
                val BannerSliderViewPager =
                    view.findViewById<ViewPager2>(R.id.BannerSliderViewPager)
                BannerSliderViewPager.post {
                    BannerSliderViewPager.adapter = BannerSliderAdapter
                    val viewPAgerHeight = (((BannerSliderViewPager.measuredWidth - convertDpToPixel(
                        32f,
                        requireContext()
                    )) * 173) / 328).toInt()
                    val layoutParams = BannerSliderViewPager.layoutParams
                    layoutParams.height = viewPAgerHeight
                    BannerSliderViewPager.layoutParams = layoutParams

                    val sliderIndicator = view.findViewById<DotsIndicator>(R.id.sliderIndicator)
                    sliderIndicator.attachTo(BannerSliderViewPager)
                }
            }
        }



    }

    override fun onProductClick(product: product) {
        startActivity(Intent(requireContext(), ProductDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA,product)
        })
    }


}