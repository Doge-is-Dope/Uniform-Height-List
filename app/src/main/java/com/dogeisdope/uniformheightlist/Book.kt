package com.dogeisdope.uniformheightlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(val id: Long, val title: String) : Parcelable
