package com.dogeisdope.uniformheightlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogeisdope.uniformheightlist.Utils.dpToPx
import com.dogeisdope.uniformheightlist.Utils.getMeasurements
import com.dogeisdope.uniformheightlist.databinding.ListItemBookBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    fun getBooks(layoutInflater: LayoutInflater, viewGroup: ViewGroup) {
        viewModelScope.launch {
            val books = DUMMY_COUNT.let { count ->
                (0 until count).map { index ->
                    val randomLength = getRandomLength(index)
                    Timber.tag("test").d("Random length: $randomLength")
                    Book(id = index.toLong(), title = getDummyRandomText(randomLength))
                }
            }


            val maxHeight = getMaxHeight(layoutInflater, viewGroup, books)
            Timber.tag("test").d("Max height: $maxHeight")
            _books.value = books.map { it.copy(maxHeight = maxHeight) }
        }
    }

    private suspend fun getDummyRandomText(randomNumber: Int): String =
        withContext(Dispatchers.Default) { DUMMY_TEXT.repeat(randomNumber) }

    private fun getMaxHeight(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup,
        items: List<Book>,
    ): Int {
        val listItemBinding = ListItemBookBinding.inflate(layoutInflater, viewGroup, false)

        var maxHeight = 0
        items.forEach { book ->
            listItemBinding.title.text = book.title
            val (width, height) = listItemBinding.root.getMeasurements(120.dpToPx)
            Timber.tag("test").i("Title length: ${book.title.length}")
            Timber.tag("test").d("Current width: $width, height: $height")
            maxHeight = maxOf(maxHeight, height)
        }
        return maxHeight
    }

    private fun getRandomLength(seed: Int): Int =
        Random(System.currentTimeMillis() + seed).nextInt(1, 100)

    companion object {
        private const val DUMMY_COUNT = 20
        private const val DUMMY_TEXT = "A"
    }
}