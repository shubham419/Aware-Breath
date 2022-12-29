package com.shubham.awarebreath.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shubham.awarebreath.util.JsonParser
import java.lang.reflect.Type


class Converters() {

    @TypeConverter
    fun analyticListToJson(analyticListData: AnalyticListData): String {

        val gson = Gson()
        val jsonString = gson.toJson(analyticListData)
        return jsonString

//        return jsonParser.toJson(
//            analyticListData,
//            object : TypeToken<AnalyticListData>() {}.type
//        ) ?: "[]"
    }

    @TypeConverter
    fun jsonToAnalyticList(jsonString: String): AnalyticListData{

        val listType: Type = object : TypeToken<AnalyticListData?>() {}.type
        val list: AnalyticListData = Gson().fromJson(jsonString, listType)

        return list

//        val analyticListData = AnalyticListData(0,0,"null")
//        return jsonParser.fromJson<AnalyticListData>(
//            json,
//            object: TypeToken<AnalyticListData>(){}.type
//        ) ?: analyticListData
    }
}