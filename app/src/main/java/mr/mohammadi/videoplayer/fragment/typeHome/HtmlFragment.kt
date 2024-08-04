package mr.mohammadi.videoplayer.fragment.typeHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mr.mohammadi.videoplayer.R
import mr.mohammadi.videoplayer.databinding.FragmentHomeHtmlBinding
import mr.mohammadi.videoplayer.databinding.FragmentHomeWebsiteBinding
import mr.mohammadi.videoplayer.presenter.HtmlPresenter
import mr.mohammadi.videoplayer.recyclerView.DataProductsHome
import mr.mohammadi.videoplayer.recyclerView.RecyclerHome
import mr.mohammadi.videoplayer.view.HtmlView

class HtmlFragment : Fragment(R.layout.fragment_home_html), HtmlView {

    private lateinit var binding: FragmentHomeHtmlBinding
    private lateinit var presenter: HtmlPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeHtmlBinding.inflate(inflater)
        presenter = HtmlPresenter(this)
        presenter.loadProducts()
        return binding.root
    }

    override fun displayProducts(products: Array<DataProductsHome>) {
        val adapter = RecyclerHome(requireActivity(), products)
        binding.recyclerViewHtml.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerViewHtml.adapter = adapter
    }
}
