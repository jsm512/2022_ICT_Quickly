package com.example.test12

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_linkpage.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.callhistory.*
class LinkPage : AppCompatActivity(){
    val DataList = arrayListOf(
        Data(R.drawable.user,"0번"),
        Data(R.drawable.user,"1번"),
        Data(R.drawable.user,"2번"),
        Data(R.drawable.user,"3번")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linkpage)

        setSupportActionBar(LinkPage_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        LinkPage_toolbar.title = "보호자 찾기"

        user_recyclerView.layoutManager = LinearLayoutManager(this)
        user_recyclerView.adapter = CustomAdapter(DataList)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
            R.id.action_search ->{
                Toast.makeText(applicationContext,"검색 이벤트 실행",Toast.LENGTH_LONG).show()
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_toolbar_menu,menu)
        return true
    }
}