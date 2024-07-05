package com.example.todo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var title :String,
)
