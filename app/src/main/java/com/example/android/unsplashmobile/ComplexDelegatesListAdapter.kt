package com.example.android.unsplashmobile;

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ComplexDelegatesListAdapter() : AsyncListDifferDelegationAdapter<Any>(ComplexDiffCallback()) {

    init {
        delegatesManager
            .addDelegate(CardItemDelegate(::setItem))
            .addDelegate(ComplexItemDelegate())
            .addDelegate(PageLoadingAdapterDelegate())
    }

    class ComplexDiffCallback : DiffUtil.ItemCallback<Any>() {
        // сравниваем типы классов и если совпадают то сравниваем айдишники объекты если они разные то сравниваем изх содержимое
        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is CardItem -> first.uuid == (second as CardItem).uuid
                is ComplexItem -> first.uuid == (second as ComplexItem).uuid
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }

    private fun setItem(item: Any) {
        val newItems = items.toMutableList().apply {
            val position = indexOf(item)
            Log.d("like", "position = $position")
            set(position, item)
            notifyItemChanged(position)
        }
        items = newItems
    }
}
