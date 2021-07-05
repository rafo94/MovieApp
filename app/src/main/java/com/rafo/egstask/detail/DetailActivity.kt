package com.rafo.egstask.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rafo.egstask.R
import com.rafo.egstask.databinding.ActivityDetailBinding
import com.rafo.egstask.detail.adapter.ProductionAdapter
import com.rafo.egstask.detail.viewmodel.DetailViewModel
import com.rafo.egstask.util.launchWhenStarted
import com.rafo.egstask.util.load
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initDate()
    }

    private fun initView() {
        supportActionBar?.run { setDisplayHomeAsUpEnabled(true) }
        intent?.let { movieId = it.getIntExtra(DETAIL_MOVIE_ID, 0) }
        if (movieId != 0) {
            viewModel.getMovieDetail(movieId)
        } else {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initDate() {
        viewModel.movieDetail.onEach { detail ->
            binding.run {
                detail.backdropPath?.also { moviePoster.load(it) } ?: run {
                    moviePoster.setImageResource(R.drawable.ic_broken_image)
                }
                detail.posterPath?.also { movieImg.load(it) } ?: run {
                    movieImg.setImageResource(R.drawable.ic_broken_image)
                }
                detail.originalTitle?.let { movieName.text = it }
                detail.popularity?.let { moviePopularity.text = it.toString() }
                detail.releaseDate?.let { movieReleaseDate.text = it }
                detail.voteAverage?.let { movieRating.rating = it.toFloat() }
                detail.voteCount?.let { movieRateCount.text = "($it)" }
                detail.overview?.let { movieDesc.text = it }
                detail.genres?.let { genresList ->
                    if (genresList.isNotEmpty()) {
                        val joinToString = genresList.joinToString { it.name }
                        movieGenres.text = joinToString
                    }
                }
                detail.budget?.let { movieBudget.text = "$it $" }
                detail.revenue?.let { movieRevenue.text = "$it $" }
                detail.productionCompanies?.let {
                    val productionAdapter = ProductionAdapter(it)
                    productionList.adapter = productionAdapter
                }
                movieName.isSelected = true
                movieGenres.isSelected = true
            }
        }.launchWhenStarted(lifecycleScope)
        viewModel.movieDetailError.onEach { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }.launchWhenStarted(lifecycleScope)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return false
    }

    companion object {
        const val DETAIL_MOVIE_ID = "DETAIL_MOVIE_ID"
    }
}