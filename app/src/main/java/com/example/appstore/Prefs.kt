package com.example.appstore

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {


    private val preferences: SharedPreferences = context.getSharedPreferences("AppPrefs",Context.MODE_PRIVATE)

    fun setIntPrefs(key:String,value:Int){
        preferences.edit().putInt(key, value).apply()
    }

    fun getIntPrefs(key:String) : Int{
        return preferences.getInt(key,-1)
    }

    fun setStringPrefs(key:String,value:String){
        preferences.edit().putString(key, value).apply()
    }

    fun getStringPrefs(key:String) : String {
        return preferences.getString(key, "") ?:""
    }

    fun setBooleanPrefs(key:String,value:Boolean){
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getBooleanPrefs(key:String) : Boolean {
        return preferences.getBoolean(key,false)
    }


}