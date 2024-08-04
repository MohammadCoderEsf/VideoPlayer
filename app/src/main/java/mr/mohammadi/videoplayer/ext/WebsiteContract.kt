package mr.mohammadi.videoplayer.ext

import mr.mohammadi.videoplayer.recyclerView.DataProductsHome

interface WebsiteContract {
    interface View {
        fun showData(data: Array<DataProductsHome>)
    }

    interface Presenter {
        fun loadData()
    }
}
