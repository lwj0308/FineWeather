package com.fineweather.android.logic.network

import com.fineweather.android.FineWeatherApplication
import com.fineweather.android.logic.model.DailyResponse
import com.fineweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/*
 * =====================================================================================
 * Summary: 
 * Author: LinLinLin
 * Create: 2020/5/15 16:28
 * =====================================================================================
 */
interface WeatherService{
    @GET("v2.5/${FineWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>

    @GET("v2.5/${FineWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}
