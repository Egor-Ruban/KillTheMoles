package ru.tsu.killthemole

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log

object Repository{
    const val COLLECTED = "COLLECTED"
    const val LAST_PASSED = "LAST_PASSED"
    const val ATTEMPTS = "ATTEMPTS"
    const val SUCCESS = "SUCCESS"
    const val BUSTED = "BUSTED"

    var isExisted = false
    private lateinit var prefs : SharedPreferences

    fun init(ctx : Context){
            prefs = ctx.getSharedPreferences("name", Context.MODE_PRIVATE)
            isExisted = true
    }

    fun putValue(pair : Pair<String,Any>) = with(prefs.edit()){
        val key = pair.first
        val value = pair.second

        when(value){
            is String -> putString(key,value)
            is Int -> putInt(key,value)
            is Boolean -> putBoolean(key,value)
            is Long -> putLong(key,value)
            is Float -> putFloat(key,value)
            else -> error("wrong type stored")
        }
    }.apply()

    fun getInt(key: String, defaultValue : Int) = prefs.getInt(key,defaultValue)

    fun add(key:String, toAdd : Int){
        val value = prefs.getInt(key, 0) + toAdd
        putValue(key to value)
    }

    fun reset(){
        putValue(COLLECTED to 0)
        putValue(LAST_PASSED to 0)
        putValue(ATTEMPTS to 0)
        putValue(SUCCESS to 0)
    }
}