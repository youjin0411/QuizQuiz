package com.example.quizquiz

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.DropBoxManager
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.drawer_nav_view)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.quiz_solve -> Log.d("mytag", "퀴즈 풀기")
                R.id.quiz_manage -> Log.d("mytag", "퀴즈 관리")
            }

            drawerLayout.closeDrawers()  //메뉴를 누른 후 네브바가 수동으로 닫히는 것

            true
        }

        drawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        ){}
        // isDrawerIndicatorEnabled 속성을 true로 설정해 액션바의 왼쪽 상단에 위치한 햄버거 아이콘을 통해 내비게이션 드로어를 표시하고 숨길 수 있도록 합니다.
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        // setDisplayHomeAsUpEnabled 메서드를 호출해서 햄버거 아이콘을 표시하고 해당 아이콘을 클릭해 내비게이션 드로어를 열고 닫을 수 있도록 설
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}