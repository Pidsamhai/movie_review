package com.github.psm.movie.review.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.github.psm.movie.review.db.ObjectBox
import com.github.psm.movie.review.db.model.Movie
import com.github.psm.movie.review.db.model.Movie_
import com.github.psm.movie.review.db.model.genre.Genre
import com.github.psm.movie.review.repository.TMDBRepository
import com.github.psm.movie.review.utils.ObjectBoxLiveData
import com.github.psm.movie.review.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: TMDBRepository) : ViewModel() {
    private val genreBox: Box<Genre> = ObjectBox.store.boxFor()
    private val movieBox: Box<Movie> = ObjectBox.store.boxFor()

    val movieResponse: LiveData<List<Movie>>
    get() =  movieBox
        .query()
        .orderDesc(Movie_.popularity)
        .orderDesc(Movie_.releaseDate)
        .orderDesc(Movie_.voteCount)
        .orderDesc(Movie_.voteAverage)
        .asLiveData()
        .map {
            it.take(20)
        }

    val genres: ObjectBoxLiveData<Genre> = genreBox.query().asLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGenreNormal()
            repository.getPopular()
        }
    }
}
