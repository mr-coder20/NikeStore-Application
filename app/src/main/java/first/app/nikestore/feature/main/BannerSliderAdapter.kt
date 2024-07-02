package first.app.nikestore.feature.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import first.app.nikestore.data.banner

class BannerSliderAdapter(fragment: Fragment,val banners:List<banner>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int =banners.size

    override fun createFragment(position: Int): Fragment =BannerFragment.newInstance(banners[position])
}