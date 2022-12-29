package com.prtd.serial.domain.use_cases.use_cases_remote

import android.content.Context
import android.util.Log
import com.prtd.serial.R
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.MultiResult
import com.prtd.serial.domain.repository.RestApiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MultiSearchUseCase @Inject constructor(
    private val restApiRepo: RestApiRepo,
    private val context: Context

) {

    operator fun invoke(query: String, page: Int): Flow<Resources<MultiResult>> = flow {
        try {
            emit(Resources.Loading())
            val data = restApiRepo.searchMulti(query, page)
            Log.i("TAG", "invoke: $data")
            emit(Resources.Success(data.toMultiResult()))
        } catch (e: HttpException) {
            emit(Resources.Error(message = context.getString(R.string.http_exception_error_msg)))
        }
        catch (e: IOException){
            emit(Resources.Error(message = context.getString(R.string.io_exception_error_msg)))
        }
    }
}