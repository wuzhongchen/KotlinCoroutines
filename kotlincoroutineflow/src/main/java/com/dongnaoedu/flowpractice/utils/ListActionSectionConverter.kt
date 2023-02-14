package com.dongnaoedu.flowpractice.utils

import androidx.room.TypeConverter
import com.dongnaoedu.flowpractice.db.User.ActionSectionBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListActionSectionConverter {
    private val gson = Gson()
    private val type: Type = object : TypeToken<List<ActionSectionBean>>() {}.type

    @TypeConverter
    fun fromString(json: String?): List<ActionSectionBean> {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromList(list: List<ActionSectionBean?>?): String {
        return gson.toJson(list, type)
    }
}
