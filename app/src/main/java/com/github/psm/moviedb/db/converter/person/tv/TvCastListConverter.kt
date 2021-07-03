package com.github.psm.moviedb.db.converter.person.tv

import com.github.psm.moviedb.db.model.person.tv.TvCast
import com.github.psm.moviedb.utils.JsonX
import io.objectbox.converter.PropertyConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

class TvCastListConverter : PropertyConverter<List<TvCast>, String> {
    override fun convertToEntityProperty(databaseValue: String?): List<TvCast> {
        return JsonX.decodeFromString(databaseValue ?: return emptyList())
    }

    override fun convertToDatabaseValue(entityProperty: List<TvCast>?): String {
        return JsonX.encodeToString(entityProperty)
    }
}