package com.github.psm.moviedb.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.psm.moviedb.db.Resource
import com.github.psm.moviedb.db.model.tv.credits.TvCredit
import com.github.psm.moviedb.db.model.tv.detail.TvDetail
import com.github.psm.moviedb.ui.bookmark.BookmarkVM

@Composable
fun TvDetailPage(
    navigateBack: () -> Unit,
    viewModel: TvDetailViewModel = hiltViewModel(),
    bookmarkViewModel: BookmarkVM = hiltViewModel(),
    navigateToPerson: (personId: Long) -> Unit
) {
    val detailResource: Resource<TvDetail> by viewModel.detail.collectAsState(initial = Resource.Loading)
    val creditResource: Resource<TvCredit> by viewModel.credit.collectAsState(initial = Resource.Loading)
    val isBooked by viewModel.isBooked.observeAsState(initial = false)
    var detail: TvDetail? = null
    var credit: TvCredit? = null

    when (detailResource) {
        is Resource.Success -> {
            detail = (detailResource as Resource.Success<TvDetail>).data
        }
        is Resource.Error -> {
            detail = (detailResource as Resource.Error<TvDetail>).data
        }
    }

    when (creditResource) {
        is Resource.Success -> {
            credit = (creditResource as Resource.Success<TvCredit>).data
        }
        is Resource.Error -> {
            credit = (creditResource as Resource.Error<TvCredit>).data
        }
    }

    DetailContent(
        appBarTitle = "Tv",
        title = detail?.name,
        posterPath = detail?.posterPath,
        backDropPath = detail?.backdropPath,
        voteAverage = detail?.voteAverage,
        runtime = detail?.episodeRunTime?.getOrNull(0),
        overview = detail?.overview,
        genres = detail?.genres,
        credit = credit,
        isBooked = isBooked,
        navigateBack = navigateBack,
        onBookMarkClick = { booked ->
            val id = detail?.id ?: return@DetailContent
            when {
                booked -> bookmarkViewModel.booking(id, false)
                else -> bookmarkViewModel.unBook(id)
            }
        },
        navigateToPerson = navigateToPerson
    )
}