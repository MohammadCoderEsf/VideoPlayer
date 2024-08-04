package mr.mohammadi.videoplayer.fragment.typeHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mr.mohammadi.videoplayer.R
import mr.mohammadi.videoplayer.databinding.FragmentHomeMobileBinding
import mr.mohammadi.videoplayer.databinding.FragmentHomeWebsiteBinding
import mr.mohammadi.videoplayer.ext.MobileContract
import mr.mohammadi.videoplayer.presenter.MobilePresenter
import mr.mohammadi.videoplayer.recyclerView.DataProductsHome
import mr.mohammadi.videoplayer.recyclerView.RecyclerHome

class MobileFragment : Fragment(R.layout.fragment_home_mobile), MobileContract.View {
    private lateinit var binding: FragmentHomeMobileBinding
    private lateinit var presenter: MobileContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMobileBinding.inflate(inflater)
        presenter = MobilePresenter(this)
        presenter.loadData()
        return binding.root
    }

    override fun showData(data: Array<DataProductsHome>) {
        val adapter = RecyclerHome(requireActivity(), data)
        binding.recyclerViewMobile.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerViewMobile.adapter = adapter
    }
}
