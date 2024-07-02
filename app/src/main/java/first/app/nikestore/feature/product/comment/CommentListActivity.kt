package first.app.nikestore.feature.product.comment

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import first.app.nikestore.EXTRA_KEY_DATA
import first.app.nikestore.EXTRA_KEY_ID
import first.app.nikestore.R
import first.app.nikestore.common.nikeActivity
import first.app.nikestore.data.Comment
import first.app.nikestore.feature.product.CommentAdapter
import first.app.nikestore.view.NikeToolbar
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CommentListActivity : nikeActivity() {
    val viewModel:CommentListViewModel by inject { parametersOf(intent.extras!!.getInt(EXTRA_KEY_ID)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_list)

        val commentListToolbar=findViewById<NikeToolbar>(R.id.commentListToolbar)
        commentListToolbar.onBackButtonClickListener= View.OnClickListener {
            finish()
        }
            viewModel.progressBarLiveData.observe(this){
                setProgressIndicator(it)
            }
        viewModel.commentsLiveData.observe(this){
            val commentsRv=findViewById<RecyclerView>(R.id.commentsRv)
            val adapter=CommentAdapter(true)
            commentsRv.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            adapter.comments= it as ArrayList<Comment>
            commentsRv.adapter=adapter

        }

    }
}