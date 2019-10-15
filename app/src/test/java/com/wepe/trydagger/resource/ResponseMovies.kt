package com.wepe.trydagger.resource

import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.data.model.ResultsMovies

object ResponseMovies {
    val fakeMoviesResults : ArrayList<ResultsMovies> = getData()

    private fun getData(): ArrayList<ResultsMovies> {
        this.fakeMoviesResults.add(FAKE_MOVIES_RESULT)
        return fakeMoviesResults
    }

    val FAKE_MOVIES = ResponseMovies(
        page = 1,
        totalResults = 10000,
        totalPages = 500,
        results = fakeMoviesResults
    )

    val FAKE_MOVIES_RESULT = ResultsMovies(
        popularity= 697.866,
        voteCount= 2576,
        video= false,
        posterPath = "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
        id= 475557,
        adult= false,
        backdropPath =  "/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
        originalLanguage= "en",
        originalTitle= "Joker",
        title= "Joker",
        voteAverage= 8.7,
        overview= "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
        releaseDate= "2019-10-04"
    )

}