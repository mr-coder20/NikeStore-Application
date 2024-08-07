package first.app.nikestore.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class banner(
    val id: Int,
    val image: String,
    val link_type: Int,
    val link_value: String
) : Parcelable