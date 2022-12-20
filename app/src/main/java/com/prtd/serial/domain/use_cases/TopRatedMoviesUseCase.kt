package com.prtd.serial.domain.use_cases

import com.prtd.serial.common.Resources
import com.prtd.serial.data.remote.dto.getMoviesTopRated
import com.prtd.serial.domain.models.MoviesTopRated
import com.prtd.serial.domain.repository.RestApiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TopRatedMoviesUseCase @Inject constructor(
    private val restApiRepo: RestApiRepo
    ) {

    operator fun invoke(): Flow<Resources<MoviesTopRated>> = flow {
        try {
            emit(Resources.Loading())
            val data =restApiRepo.getTopRatedMovies()
            emit(Resources.Success(data.getMoviesTopRated()))
        }
        catch (e: HttpException){
            emit( Resources.Error(message ="Couldn't Reach server check your internet connection !" ))
        }
        catch (e: IOException){
            emit( Resources.Error(message = e.localizedMessage ?: "An unexpected error has occurred"))
        }
    }
}