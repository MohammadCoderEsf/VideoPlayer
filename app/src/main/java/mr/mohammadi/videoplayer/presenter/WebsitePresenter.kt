package mr.mohammadi.videoplayer.presenter

import mr.mohammadi.videoplayer.R
import mr.mohammadi.videoplayer.ext.WebsiteContract
import mr.mohammadi.videoplayer.recyclerView.DataProductsHome

class WebsitePresenter(private val view: WebsiteContract.View) : WebsiteContract.Presenter {
    private val dataWebsite = arrayOf(
        DataProductsHome("جامع ترین دوره آموزش برنامه نویسی اندروید (۱۵۰ ساعت با پشتیبانی ۲۴ ساعته)", R.drawable.img_curses_android),
        DataProductsHome("دوره آموزش Git و GitHub جامع با نسخه 2024", R.drawable.img_curses_git),
        DataProductsHome("دوره ی مستر تدوینگر آموزش پریمیر با بیش از ۲۰ پروژه عملی ویژه بازار کار", R.drawable.img_curses_premere),
        DataProductsHome("دوره طلایی آموزش بازی سازی با یونیتی عملی و پروژه محور", R.drawable.img_curses_unity)
    )

    override fun loadData() {
        // داده‌ها به View ارسال می‌شود
        view.showData(dataWebsite)
    }
}
