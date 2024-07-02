package first.app.nikestore.feature.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import first.app.nikestore.R
import first.app.nikestore.common.nikeFragment

class ProfileFragment:nikeFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile,container,false)
    }
}