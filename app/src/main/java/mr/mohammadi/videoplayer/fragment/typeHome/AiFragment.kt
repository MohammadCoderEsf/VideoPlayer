package mr.mohammadi.videoplayer.fragment.typeHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mr.mohammadi.videoplayer.R
import mr.mohammadi.videoplayer.databinding.FragmentHomeAiBinding
import mr.mohammadi.videoplayer.databinding.FragmentHomeWebsiteBinding
import mr.mohammadi.videoplayer.presenter.AiPresenter
import mr.mohammadi.videoplayer.recyclerView.DataProductsHome
import mr.mohammadi.videoplayer.recyclerView.RecyclerHome
import mr.mohammadi.videoplayer.view.AiView


class AiFragment : Fragment(R.layout.fragment_home_ai), AiView {

    private lateinit var binding: FragmentHomeAiBinding
    private lateinit var presenter: AiPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeAiBinding.inflate(inflater)
        presenter = AiPresenter(this)
        presenter.loadProducts()
        return binding.root
    }

    override fun displayProducts(products: Array<DataProductsHome>) {
        val adapter = RecyclerHome(requireActivity(), products)
        binding.recyclerViewAi.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerViewAi.adapter = adapter
    }
}
