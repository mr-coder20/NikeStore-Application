package first.app.nikestore.services.http

import com.facebook.drawee.view.SimpleDraweeView
import first.app.nikestore.services.ImageLoadingService
import first.app.nikestore.view.NikeImageView
import java.lang.IllegalStateException

class FrescoImageLoadingService:ImageLoadingService {
    override fun load(imageView: NikeImageView, imageUrl: String) {
        if (imageView is SimpleDraweeView)
            imageView.setImageURI(imageUrl)
        else
            throw IllegalStateException("ImageView Must Be Instance Of SimpleDraweeView")
    }

}