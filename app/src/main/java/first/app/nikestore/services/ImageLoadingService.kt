package first.app.nikestore.services

import first.app.nikestore.view.NikeImageView

interface ImageLoadingService {
    fun load(imageView: NikeImageView,imageUrl: String)
}