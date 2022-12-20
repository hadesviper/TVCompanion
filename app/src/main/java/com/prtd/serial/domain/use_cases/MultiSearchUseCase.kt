package com.prtd.serial.domain.use_cases

import android.util.Log
import com.prtd.serial.common.Resources
import com.prtd.serial.data.remote.dto.getMultiResult
import com.prtd.serial.domain.models.MultiResult
import com.prtd.serial.domain.repository.RestApiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MultiSearchUseCase @Inject constructor(
    private val restApiRepo: RestApiRepo
    ) {

    operator fun invoke(query:String, page:Int): Flow<Resources<MultiResult>> = flow {
        try {
            emit(Resources.Loading())
            val data =restApiRepo.searchMulti(query,page)
            Log.i("TAG", "invoke: $data")
            emit(Resources.Success(data.getMultiResult()))
        }
        catch (e: HttpException){
            emit( Resources.Error(message ="Couldn't Reach server check your internet connection !" ))
        }
        catch (e: IOException){
            emit( Resources.Error(message = e.localizedMessage ?: "An unexpected error has occurred"))
        }
    }
}