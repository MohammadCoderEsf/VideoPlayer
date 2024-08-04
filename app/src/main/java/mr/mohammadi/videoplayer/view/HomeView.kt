package mr.mohammadi.videoplayer.view

import androidx.fragment.app.Fragment
import mr.mohammadi.videoplayer.recyclerView.DataProductsTop

// HomeView.kt
interface HomeView {
    fun setupRecyclerView(dataTop: Array<DataProductsTop>)
    fun navigateToFragment(fragment: Fragment, product: DataProductsTop)
}
