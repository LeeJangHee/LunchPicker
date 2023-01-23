package com.devlee.launchpicker.presenter.fragment.ban

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devlee.launchpicker.databinding.ItemBanBinding
import com.devlee.launchpicker.util.Consts.TAG
import com.devlee.launchpicker.util.toPrettyJson
import okhttp3.internal.toImmutableList

class BanAdapter(
    private val selectedCallback: (List<String>) -> Unit
): ListAdapter<String, BanAdapter.BanViewHolder>(diffUtilCallback) {

    companion object {
        private val diffUtilCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }

    private val _selectedBanList: MutableList<String> = mutableListOf()
    val selectedBanList get() = _selectedBanList.toImmutableList()

    inner class BanViewHolder(val binding: ItemBanBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanViewHolder {
        return BanViewHolder(ItemBanBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BanViewHolder, position: Int) {
        getItem(position)?.let { title ->
            holder.binding.apply {
                banItemTitle.text = "${position + 1}. $title"
                banItemCheckBox.isChecked = selectedBanList.contains(title)
                root.setOnClickListener {
                    banItemCheckBox.isChecked = !banItemCheckBox.isChecked
                    if (banItemCheckBox.isChecked) {
                        _selectedBanList.add(title)
                    } else {
                        _selectedBanList.remove(title)
                    }
                    selectedCallback.invoke(selectedBanList)
                }
            }
        }
    }

    fun setIgnoreList(prevList: List<String>) {
        Log.d(TAG, "setIgnoreList() called with: prevList = ${prevList.toPrettyJson()}")
        _selectedBanList.clear()
        _selectedBanList.addAll(prevList)
    }
}