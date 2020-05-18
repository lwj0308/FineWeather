package com.fineweather.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.fineweather.android.FineWeatherApplication
import com.fineweather.android.logic.model.Place
import com.google.gson.Gson

/*
 * =====================================================================================
 * Summary: 
 * Author: LinLinLin
 * Create: 2020/5/18 11:51
 * =====================================================================================
 */object PlaceDao {
    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavePlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        FineWeatherApplication.context.getSharedPreferences("fine_weather", Context.MODE_PRIVATE)
}