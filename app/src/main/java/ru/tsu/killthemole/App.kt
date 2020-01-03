package ru.tsu.killthemole

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate

class App : Application(){
    init {
        instance = this
    }

    companion object{
        private var instance : App? = null

        fun applicationContext() : Context{
            Log.d("M_App", "fasfas")
            return  instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}