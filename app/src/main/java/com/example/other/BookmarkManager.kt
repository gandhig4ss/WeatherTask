package com.example.other

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.model.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookmarkManager(private val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("Bookmarks", Context.MODE_PRIVATE)
    }

    fun addBookmark(location: Location) {
        val bookmarks = getAllBookmarks().toMutableSet()
        bookmarks.add(location)
        saveBookmarks(bookmarks)
    }

    fun removeBookmark(location: Location) {
        val bookmarks = getAllBookmarks().toMutableSet()
        bookmarks.remove(location)
        saveBookmarks(bookmarks)
    }

    fun getAllBookmarks(): Set<Location> {
        val bookmarksJson = sharedPreferences.getString("bookmarks", "[]")
        val typeToken = object : TypeToken<Set<Location>>() {}.type
        return Gson().fromJson(bookmarksJson, typeToken) ?: emptySet()
    }

    private fun saveBookmarks(bookmarks: Set<Location>) {
        val editor = sharedPreferences.edit()
        editor.putString("bookmarks", Gson().toJson(bookmarks))
        editor.apply()
    }
}