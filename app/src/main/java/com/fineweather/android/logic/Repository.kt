package com.fineweather.android.logic


import androidx.lifecycle.liveData
import com.fineweather.android.logic.dao.PlaceDao
import com.fineweather.android.logic.model.Place
import com.fineweather.android.logic.model.Weather
import com.fineweather.android.logic.network.FineWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext


/*
 * =====================================================================================
 * Summary: liveData 构建块用作协程
 * Author: LinLinLin
 * Create: 2020/5/12 0:27
 * =====================================================================================
 */object Repository {
    fun searchPlaced(query: String) = fire(Dispatchers.IO) {
        val placeResponse =
            FineWeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }

    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async {
                FineWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                FineWeatherNetwork.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()

            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather =
                    Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)//包装
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)//每次调用 emit() 或 emitSource() 都会移除之前添加的来源。
            //每次 emit() 调用都会暂停执行代码块，直到在主线程上设置 LiveData 值。
        }

    /**
     * 应该开启一个线程，然后通过LiveData取回数据
     */
    fun savePlace(place: Place) = PlaceDao.savePlace(place)
    fun getSavedPlace() = PlaceDao.getSavePlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSaved()
}