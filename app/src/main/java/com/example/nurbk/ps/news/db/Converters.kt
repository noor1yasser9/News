package com.example.nurbk.ps.news.db

import androidx.room.TypeConverter
import com.example.nurbk.ps.news.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) =
        source.name

    @TypeConverter
    fun toSource(name: String) = Source(name, name)

}