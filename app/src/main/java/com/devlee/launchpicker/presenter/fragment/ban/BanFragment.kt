package com.devlee.launchpicker.presenter.fragment.ban

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.devlee.launchpicker.R
import com.devlee.launchpicker.databinding.FragmentBanBinding
import com.devlee.launchpicker.util.Consts.STORE_LIST
import com.devlee.launchpicker.util.Consts.TAG
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
        binding.selectAllCheck.isChecked = it.size == banAdapter.itemCount
        pref.setIgnoreStore(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBanBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.banRecyclerView.init()

        banAdapter.submitList(storeList) {
            binding.selectAllCheck.isChecked = banAdapter.selectedBanList.size == banAdapter.itemCount
        }

        binding.selectAllBody.setOnClickListener {
            val checked = !binding.selectAllCheck.isChecked
            binding.selectAllCheck.isChecked = checked
            banAdapter.setSelectedAll(checked) {
                pref.setIgnoreStore(it)
            }
        }
    }

    private fun RecyclerView.init() {
        adapter = banAdapter
        itemAnimator = null
        addItemDecoration(
            CustomDecoration(
                height = 1.toDp(),
                color = ContextCompat.getColor(requireContext(), R.color.space)
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}