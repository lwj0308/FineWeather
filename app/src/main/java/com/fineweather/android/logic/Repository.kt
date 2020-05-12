package com.fineweather.android.logic


import androidx.lifecycle.liveData
import com.fineweather.android.logic.model.Place
import com.fineweather.android.logic.network.FineWeatherNetwork
import kotlinx.coroutines.Dispatchers


/*
 * =====================================================================================
 * Summary: 
 * Author: LinLinLin
 * Create: 2020/5/12 0:27
 * =====================================================================================
 */object Repository {
    fun searchPlaced(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse =
                FineWeatherNetwork.searchPlaces(
                    query
                )
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<com.fineweather.android.logic.model.Place>>(e)
        }
        emit(result as Result<List<Place>>)
    }
}