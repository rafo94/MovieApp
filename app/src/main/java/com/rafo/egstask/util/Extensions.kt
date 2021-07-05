package com.rafo.egstask.util

import android.widget.ImageView
import androidx.lifecycle.LifecycleCoroutineScope
import coil.load
import com.rafo.egstask.BuildConfig.*
import com.rafo.egstask.R
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect


fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenStarted { this@launchWhenStarted.collect() }
}

fun <T> publishFlow(): MutableSharedFlow<T> = MutableSharedFlow(
    replay = 1, extraBufferCapacity = 0, onBufferOverflow = BufferOverflow.DROP_OLDEST
)

fun ImageView.load(imagePath: String) {
    this.load(IMAGE_BASE_URL.plus(imagePath))
}