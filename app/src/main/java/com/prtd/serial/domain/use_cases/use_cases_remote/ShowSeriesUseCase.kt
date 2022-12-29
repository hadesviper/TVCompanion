package com.prtd.serial.domain.use_cases.use_cases_remote

import android.content.Context
import com.prtd.serial.R
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.Series
import com.prtd.serial.domain.repository.RestApiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ShowSeriesUseCase @Inject constructor(
    private val restApiRepo: RestApiRepo,
    private val context: Context
) {

    operator fun invoke(id:Int): Flow<Resources<Series>> = flow {
        try {
            emit(Resources.Loading())
            val data =restApiRepo.getSeries(id)
            emit(Resources.Success(data.toSeries()))
        }
        catch (e: HttpException){
            emit(Resources.Error(message = context.getString(R.string.http_exception_error_msg)))
        }
        catch (e: IOException){
            emit(Resources.Error(message = context.getString(R.string.io_exception_error_msg)))
        }
    }
}