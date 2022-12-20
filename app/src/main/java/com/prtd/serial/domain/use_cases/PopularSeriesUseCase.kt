package com.prtd.serial.domain.use_cases

import com.prtd.serial.common.Resources
import com.prtd.serial.data.remote.dto.getSeriesPopular
import com.prtd.serial.domain.models.SeriesPopular
import com.prtd.serial.domain.repository.RestApiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopularSeriesUseCase @Inject constructor(
    private val restApiRepo: RestApiRepo
    ) {

    operator fun invoke(page:Int): Flow<Resources<SeriesPopular>> = flow {
        try {
            emit(Resources.Loading())
            val data =restApiRepo.getPopularSeries(page)
            emit(Resources.Success(data.getSeriesPopular()))
        }
        catch (e: HttpException){
            emit( Resources.Error(message ="Couldn't Reach server check your internet connection !" ))
        }
        catch (e: IOException){
            emit( Resources.Error(message = e.localizedMessage ?: "An unexpected error has occurred"))
        }
    }
}