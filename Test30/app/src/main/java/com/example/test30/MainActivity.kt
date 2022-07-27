package com.example.test30

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf("apple", "banana","kiwifruit")
        for (index in items.indices){
            println("인덱스 : $index, 값 : ${items[index]}")
        }


    }

}