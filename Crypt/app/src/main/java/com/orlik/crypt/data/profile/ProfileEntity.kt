package com.orlik.crypt.data.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "desc") val desc: String,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "hex") val hex: String
)