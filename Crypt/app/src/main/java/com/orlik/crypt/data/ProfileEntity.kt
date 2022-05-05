package com.orlik.crypt.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "short_description") val short_desc: String?,
    @ColumnInfo(name = "profile_color") val hex_color: String?
)