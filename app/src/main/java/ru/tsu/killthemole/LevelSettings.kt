package ru.tsu.killthemole

data class LevelSettings(
    var difficulty : Int = 4,
    var time : Long = 30000L,
    var speed : Long = 2000L,
    var holes : Int = 8
)