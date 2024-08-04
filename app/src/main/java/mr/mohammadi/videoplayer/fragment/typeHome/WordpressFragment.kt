package mr.mohammadi.videoplayer.fragment.typeHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mr.mohammadi.videoplayer.R
import mr.mohammadi.videoplayer.databinding.FragmentHomeWordpressBinding
import mr.mohammadi.videoplayer.ext.WordpressContract
import mr.mohammadi.videoplayer.presenter.WordpressPresenter
import mr.mohammadi.videoplayer.recyclerView.DataProductsHome
import mr.mohammadi.videoplayer.recyclerView.RecyclerHome

class WordpressFragment : Fragment(R.layout.fragment_home_wordpress), WordpressContract.View {
    private lateinit var binding: FragmentHomeWordpressBinding
    private lateinit var presenter: WordpressContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeWordpressBinding.inflate(inflater)
        presenter = WordpressPresenter(this)
        presenter.loadData()
        return binding.root
    }

    override fun showData(data: Array<DataProductsHome>) {
        val adapter = RecyclerHome(requireActivity(), data)
        binding.recyclerViewWordpress.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerViewWordpress.adapter = adapter
    }
}
