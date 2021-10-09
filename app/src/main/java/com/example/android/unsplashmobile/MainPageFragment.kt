package com.example.android.unsplashmobile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android.unsplashmobile.databinding.FragmentMainPageBinding
import java.util.*

class MainPageFragment : Fragment(R.layout.fragment_main_page) {
    private val binding by viewBinding (FragmentMainPageBinding::bind)
    private var complexAdapter: ComplexDelegatesListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        loadMoreItems()
    }

    private fun initList() {
        complexAdapter = ComplexDelegatesListAdapter()
        with(binding.list) {
            val orientation = RecyclerView.VERTICAL
            adapter = complexAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            // Pagination
            addOnScrollListener(
                PaginationScrollListener(
                    layoutManager = layoutManager as LinearLayoutManager,
                    requestNextItems = ::loadMoreItems,
                    visibilityThreshold = 0
                )
            )

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun getDefaultItems() = List(6) {
        val randomUUID = UUID.randomUUID()
        when ((1..2).random()) {
            1 -> CardItem(
                author = getString(R.string.author),
                title = getString(R.string.simpleTitle),
                votes = 55,
                uuid = randomUUID
            )
            2 -> ComplexItem(
                author = getString(R.string.author),
                title = getString(R.string.complexTitle),
                uuid = randomUUID
            )
            else -> error("Wrong random number")
        }
    }

    private fun loadMoreItems() {
        val newItems = complexAdapter.items.toMutableList().apply {
            if (lastOrNull() is LoadingItem) {
                removeLastOrNull()
            }
        } + getDefaultItems() + LoadingItem()
        complexAdapter.items = newItems
        Log.d("Pagination", newItems.size.toString())
    }
}