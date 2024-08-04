package mr.mohammadi.videoplayer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mr.mohammadi.videoplayer.R
import mr.mohammadi.videoplayer.databinding.FragmentHomeBinding
import mr.mohammadi.videoplayer.ext.OnItemClickListener
import mr.mohammadi.videoplayer.fragment.typeHome.AiFragment
import mr.mohammadi.videoplayer.fragment.typeHome.HtmlFragment
import mr.mohammadi.videoplayer.fragment.typeHome.MobileFragment
import mr.mohammadi.videoplayer.fragment.typeHome.WebsiteFragment
import mr.mohammadi.videoplayer.fragment.typeHome.WordpressFragment
import mr.mohammadi.videoplayer.recyclerView.DataProductsTop
import mr.mohammadi.videoplayer.recyclerView.RecyclerTop

class HomeFragment : Fragment(R.layout.fragment_home), OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding

    private val dataTop = arrayOf(
        DataProductsTop("وبسایت", R.drawable.ic_wensite),
        DataProductsTop("موبایل", R.drawable.ic_mobile),
        DataProductsTop("Ai", R.drawable.ic_ai),
        DataProductsTop("HTML", R.drawable.ic_wensite),
        DataProductsTop("وردپرس", R.drawable.ic_mobile)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        setRecyclerTop()
        selectDefaultFragment()
        return binding.root
    }

    private fun setRecyclerTop() {
        val adapter = RecyclerTop(requireActivity(), dataTop, this)
        binding.recyclerViewTop.layoutManager = LinearLayoutManager(
            requireActivity(),
            RecyclerView.HORIZONTAL,
            true
        )
        binding.recyclerViewTop.adapter = adapter
    }

    private fun selectDefaultFragment() {
        val defaultProduct = dataTop.first { it.title == "وبسایت" }
        onItemClick(defaultProduct)
    }

    override fun onItemClick(product: DataProductsTop) {
        val fragment = when (product.title) {
            "وبسایت" -> WebsiteFragment()
            "موبایل" -> MobileFragment()
            "Ai" -> AiFragment()
            "HTML" -> HtmlFragment()
            "وردپرس" -> WordpressFragment()
            else -> null
        }

        fragment?.let {
            val bundle = Bundle()
            bundle.putParcelable("product", product)
            it.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerViewHome, it)
                .addToBackStack(null)
                .commit()
        }
    }
}
