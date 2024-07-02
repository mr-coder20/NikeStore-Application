package first.app.nikestore.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import first.app.nikestore.EXTRA_KEY_DATA
import first.app.nikestore.R
import first.app.nikestore.data.banner
import first.app.nikestore.services.ImageLoadingService
import first.app.nikestore.view.NikeImageView
import org.koin.android.ext.android.inject
import java.lang.IllegalStateException

class BannerFragment:Fragment() {
    val ImageLoadingService:ImageLoadingService by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val imageView=inflater.inflate(R.layout.fragment_banner,container,false) as NikeImageView
        val banner=requireArguments().getParcelable<banner>(EXTRA_KEY_DATA)?:throw IllegalStateException("Banner Can Not Be Null")
        ImageLoadingService.load(imageView,banner.image)
        return imageView
    }
    companion object{
        fun newInstance(banner: banner):BannerFragment {

            return BannerFragment().apply {
                arguments=Bundle().apply {
                    putParcelable(EXTRA_KEY_DATA,banner)
            }

            }
        }
    }
}