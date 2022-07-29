package com.example.test12

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_linkpage.*
import kotlinx.android.synthetic.main.callhistory.*

class LinkPage : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linkpage)

        setSupportActionBar(LinkPage_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val myintent1 = Intent(this,MainActivity::class.java)
        startActivity(myintent1)
        return true
    }
}