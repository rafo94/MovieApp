package com.rafo.egstask.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.rafo.egstask.R
import com.rafo.egstask.databinding.ActivityMainBinding
import com.rafo.egstask.detail.DetailActivity
import com.rafo.egstask.detail.DetailActivity.Companion.DETAIL_MOVIE_ID
import com.rafo.egstask.listeners.PageListener
import com.rafo.egstask.main.adapter.MovieAdapter
import com.rafo.egstask.main.viewmodel.MainViewModel
import com.rafo.egstask.util.PaginationUtils.initPagination
import com.rafo.egstask.util.PaginationUtils.resetPage
import com.rafo.egstask.util.launchWhenStarted
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), PageListener {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.getPopularMovieList()
        resetPage()
        initView()
        initData()
    }

    private fun initView() {
        val linearLayoutManager = GridLayoutManager(this, 2)
        movieAdapter = MovieAdapter(ArrayList()) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DETAIL_MOVIE_ID, it.id)
            startActivity(intent)
        }
        binding.movieList.apply {
            adapter = movieAdapter
            layoutManager = linearLayoutManager
        }
        initPagination(binding.movieList, linearLayoutManager, this)
    }

    private fun initData() {
        viewModel.movieList.onEach { content ->
            movieAdapter.setData(content.results)
        }.launchWhenStarted(lifecycleScope)
        viewModel.movieListError.onEach { error ->
            if (error.isNotEmpty())
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }.launchWhenStarted(lifecycleScope)
    }

    override fun onPagination(page: Int) {
        viewModel.getPopularMovieList(page)
    }
}