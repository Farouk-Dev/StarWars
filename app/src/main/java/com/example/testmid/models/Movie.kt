package com.example.testmid.models

import android.os.Parcel
import android.os.Parcelable

class Movie(
        val images: ArrayList<String>?,
        val title: String?,
        val intro: String?,
        val year: String?,
        val text: String?) : Parcelable {


    constructor(source: Parcel) : this(
            source.createStringArrayList(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeStringList(images)
        writeString(title)
        writeString(intro)
        writeString(year)
        writeString(text)
    }

    override fun toString(): String {
        return "Movie(images=$images, title=$title, intro=$intro, year=$year, text=$text)"
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}