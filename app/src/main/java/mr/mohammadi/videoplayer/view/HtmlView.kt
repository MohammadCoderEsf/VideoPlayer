package mr.mohammadi.videoplayer.view

import mr.mohammadi.videoplayer.recyclerView.DataProductsHome

interface HtmlView {
    fun displayProducts(products: Array<DataProductsHome>)
}
