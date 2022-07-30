package com.example.test12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.callhistory.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        button.setOnClickListener {
            val intent = Intent(this, RaceActivity::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent2 = Intent(this,CallHistoryActivity::class.java)
            startActivity(intent2)
        }
        imageButton.setOnClickListener {
            val intent3 = Intent(this,AlarmService::class.java)
            startActivity(intent3)
        }
        search_button.setOnClickListener {
            val intent4 = Intent(this,LinkPage::class.java)
            startActivity(intent4)
        }
        setting_button.setOnClickListener {
            val intent5 = Intent(this,SettingPage::class.java)
            startActivity(intent5)
        }

    }

}