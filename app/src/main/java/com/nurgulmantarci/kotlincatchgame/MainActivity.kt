package com.nurgulmantarci.kotlincatchgame

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var score:Int=0
    var userScore=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        sharedPreferences = getSharedPreferences("com.nurgulmantarci.kotlincatchgame", Context.MODE_PRIVATE)
        score = sharedPreferences.getInt("score", 0)
        txtScor.text = "Best Score: $score"
    }


    override fun onResume() {
        super.onResume()
        userScore = sharedPreferences.getInt("user_score", 0)
        score = sharedPreferences.getInt("score", 0)
        txtScor.text = "Best Score: $score"
        txtUserScore.text="Score: $userScore"
    }



    fun buttonStart(view: View){
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }
}