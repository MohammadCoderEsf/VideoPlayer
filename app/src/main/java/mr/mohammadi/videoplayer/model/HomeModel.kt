package mr.mohammadi.videoplayer.model

import mr.mohammadi.videoplayer.R
import mr.mohammadi.videoplayer.recyclerView.DataProductsTop

// HomeModel.kt
class HomeModel {
    fun getDataTop(): Array<DataProductsTop> {
        return arrayOf(
            DataProductsTop("وبسایت", R.drawable.ic_wensite),
            DataProductsTop("موبایل", R.drawable.ic_mobile),
            DataProductsTop("Ai", R.drawable.ic_ai),
            DataProductsTop("HTML", R.drawable.ic_wensite),
            DataProductsTop("وردپرس", R.drawable.ic_mobile)
        )
    }
}
