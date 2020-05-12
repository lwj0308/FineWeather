package com.fineweather.android.logic.network

import com.fineweather.android.FineWeatherApplication
import com.fineweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * =====================================================================================
 * Summary: 
 * Author: LinLinLin
 * Create: 2020/5/11 23:53
 * =====================================================================================
 */interface PlaceService {
    @GET("v2/place?token=${FineWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}