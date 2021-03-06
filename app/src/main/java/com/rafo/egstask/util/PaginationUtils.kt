package com.rafo.egstask.util

import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafo.egstask.listeners.PageListener

object PaginationUtils {
    private var isScrolling = false
    private var page = 1

    fun initPagination(
        mRecycler: RecyclerView, mLayoutManager: LinearLayoutManager,
        pageListener: PageListener
    ) {
        mRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentItems = mLayoutManager.childCount
                val totalItems = mLayoutManager.itemCount
                val scrolledOutItems = mLayoutManager.findFirstVisibleItemPosition()

                if (isScrolling && (currentItems + scrolledOutItems == totalItems)) {
                    isScrolling = false
                    pageListener.onPagination(++page)
                }
            }
        })
    }

    fun resetPage() {
        page = 1
    }
}