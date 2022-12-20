package com.prtd.serial.domain.paging_data

import androidx.paging.PagingState
import com.prtd.serial.data.remote.dto.getSeriesPopular
import com.prtd.serial.domain.models.SeriesPopular
import com.prtd.serial.domain.repository.RestApiRepo
import retrofit2.HttpException
import java.io.IOException

class PagingData(
    private val restApiRepo: RestApiRepo
): androidx.paging.PagingSource<Int, SeriesPopular.Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeriesPopular.Result> {
        val position = params.key ?: 1
        return try {
            val response = restApiRepo.getPopularSeries(position).getSeriesPopular()
            val results = response.results

            LoadResult.Page(
                data = results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (results.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, SeriesPopular.Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}