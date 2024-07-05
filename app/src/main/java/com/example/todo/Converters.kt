package com.example.todo

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromDate(date:Date) : Long {
        return date.time
    }

    @TypeConverter
    fun toDate(time:Long) : Date{
        return Date(time)
    }
}