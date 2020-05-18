package com.fineweather.android.logic.model

/*
 * =====================================================================================
 * Summary: 
 * Author: LinLinLin
 * Create: 2020/5/15 16:25
 * =====================================================================================
 */
data class Weather(val realtime: RealtimeResponse.Realtime,val daily: DailyResponse.Daily)