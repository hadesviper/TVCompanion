package com.prtd.serial.domain.use_cases

import com.prtd.serial.common.Resources
import com.prtd.serial.data.remote.dto.getMovieResults
import com.prtd.serial.domain.models.MoviesResult
import com.prtd.serial.domain.repository.RestApiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val restApiRepo: RestApiRepo
    ) {

    operator fun invoke(query:String, page:Int): Flow<Resources<MoviesResult>> = flow {
        try {
            emit(Resources.Loading())
            val data =restApiRepo.searchMovies(query,page)
            emit(Resources.Success(data.getMovieResults()))
        }
        catch (e: HttpException){
            emit( Resources.Error(message ="Couldn't Reach server check your internet connection !" ))
        }
        catch (e: IOException){
            emit( Resources.Error(message = e.localizedMessage ?: "An unexpected error has occurred"))
        }
    }
}