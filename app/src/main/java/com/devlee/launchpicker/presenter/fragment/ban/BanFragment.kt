package com.devlee.launchpicker.presenter.fragment.ban

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.devlee.launchpicker.databinding.FragmentBanBinding
import com.devlee.launchpicker.util.Consts.STORE_LIST
import com.devlee.launchpicker.util.CustomDecoration
import com.devlee.launchpicker.util.PreferenceUtil
import com.devlee.launchpicker.util.toDp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class BanFragment : Fragment() {

    private var _binding: FragmentBanBinding? = null
    private val binding get() = _binding!!

    private val banAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BanAdapter(selectedCallback).apply {
            setIgnoreList(pref.getIgnoreStore())
        }
    }

    @Inject
    @Named(STORE_LIST)
    lateinit var storeList: List<String>

    @Inject
    lateinit var pref: PreferenceUtil

    private val selectedCallback: (List<String>) -> Unit = {
        pref.setIgnoreStore(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBanBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.banRecyclerView.init()

        banAdapter.submitList(storeList)
    }

    private fun RecyclerView.init() {
        adapter = banAdapter
        itemAnimator = null
        addItemDecoration(
            CustomDecoration(
                height = 1.toDp(),
                color = Color.parseColor("#e0e0e0")
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}