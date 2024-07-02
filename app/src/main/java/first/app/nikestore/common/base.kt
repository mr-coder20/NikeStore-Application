package first.app.nikestore.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import first.app.nikestore.R
import io.reactivex.disposables.CompositeDisposable

abstract class nikeFragment : nikeView, Fragment() {
    override val rootView: CoordinatorLayout?
        get() =view as CoordinatorLayout

    override val viewContext: Context?
        get() = context
}

abstract class nikeActivity : nikeView, AppCompatActivity() {
    override val rootView: CoordinatorLayout?
        get() {
            val viewGroup: ViewGroup = window.decorView.findViewById(android.R.id.content)
            if (viewGroup !is CoordinatorLayout) {
                viewGroup.children.forEach {
                    if (it is CoordinatorLayout)
                        return it
                }
                throw IllegalStateException("RootView must be instance of CoordinatorLayout")
            } else
                return viewGroup
        }
    override val viewContext: Context?
        get() = this
}

interface nikeView {
    val rootView: CoordinatorLayout?
    val viewContext: Context?
    fun setProgressIndicator(muatShow: Boolean) {

            rootView?.let {
                viewContext?.let {context->
                var loadingView = it.findViewById<View>(R.id.loadingView)
                if (loadingView == null && muatShow) {
                    loadingView = LayoutInflater.from(context).inflate(R.layout.view_loading,it,false)
                    it.addView(loadingView)
                }
                    loadingView?.visibility=if (muatShow) View.VISIBLE else View.GONE
            }
        }
    }
}

abstract class nikeViewModel : ViewModel() {
    val CompositeDisposable = CompositeDisposable()
    val progressBarLiveData = MutableLiveData<Boolean>()
    override fun onCleared() {
        CompositeDisposable.clear()
        super.onCleared()
    }
}