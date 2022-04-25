package com.valentine.challengechapter5.ORM

import android.os.Parcelable
import android.text.format.DateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "fullname") var fullname: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String?=null
) : Parcelable