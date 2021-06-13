package com.github.psm.moviedb.db

import com.github.psm.moviedb.db.model.Movie
import com.github.psm.moviedb.db.model.detail.MovieDetail
import com.github.psm.moviedb.db.model.genre.Genre
import io.objectbox.Box

interface BoxStore {
    val movie: Box<Movie>
    val movieDetail: Box<MovieDetail>
    val genre: Box<Genre>
}