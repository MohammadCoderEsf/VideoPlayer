package mr.mohammadi.videoplayer.presenter

import androidx.fragment.app.Fragment
import mr.mohammadi.videoplayer.fragment.typeHome.AiFragment
import mr.mohammadi.videoplayer.fragment.typeHome.HtmlFragment
import mr.mohammadi.videoplayer.fragment.typeHome.MobileFragment
import mr.mohammadi.videoplayer.fragment.typeHome.WebsiteFragment
import mr.mohammadi.videoplayer.fragment.typeHome.WordpressFragment
import mr.mohammadi.videoplayer.model.HomeModel
import mr.mohammadi.videoplayer.recyclerView.DataProductsTop
import mr.mohammadi.videoplayer.view.HomeView

// HomePresenter.kt
class HomePresenter(private val view: HomeView, private val model: HomeModel) {
    fun loadData() {
        val dataTop = model.getDataTop()
        view.setupRecyclerView(dataTop)
        selectDefaultFragment(dataTop)
    }

    private fun selectDefaultFragment(dataTop: Array<DataProductsTop>) {
        val defaultProduct = dataTop.first { it.title == "وبسایت" }
        view.navigateToFragment(createFragment(defaultProduct), defaultProduct)
    }

    private fun createFragment(product: DataProductsTop): Fragment {
        return when (product.title) {
            "وبسایت" -> WebsiteFragment()
            "موبایل" -> MobileFragment()
            "Ai" -> AiFragment()
            "HTML" -> HtmlFragment()
            else -> WordpressFragment()
        }
    }
}
