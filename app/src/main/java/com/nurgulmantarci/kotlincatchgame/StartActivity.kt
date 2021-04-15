package com.nurgulmantarci.kotlincatchgame

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_start.*
import java.util.*
import kotlin.collections.ArrayList

class StartActivity : AppCompatActivity() {

    var clicked=0
    var score:Int=0
    var userScore=0
    lateinit var sharedPreferences: SharedPreferences
    var imageViewArray=ArrayList<ImageView>()
    var handler = Handler()
    var runnable= Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        sharedPreferences = getSharedPreferences("com.nurgulmantarci.kotlincatchgame", Context.MODE_PRIVATE)
        score=sharedPreferences.getInt("score", 0)

        setImageViewArray()

        hideImageViewArray()

        //Countdown
        object : CountDownTimer(16000,1000){
            override fun onTick(millisUntilFinished: Long) {
                txtTime.text="Time: " + millisUntilFinished/1000
            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)
                gridLayout2.visibility=View.GONE

                txtTime.text="Time: 0"
                var alert =AlertDialog.Builder(this@StartActivity)

                userScore=clicked
                if(userScore>=score){
                    alert.setTitle("you won!")
                    sharedPreferences.edit().putInt("score",userScore).apply()
                }else{
                    alert.setTitle("you lost!")
                }


                alert.setMessage("Do you want to try again?")
                alert.setPositiveButton("Yes"){dialog, which ->
                    val intentRestart=intent
                    finish()
                    startActivity(intentRestart)
                }

                alert.setNegativeButton("No"){dialog, which ->
                    sharedPreferences.edit().putInt("user_score",userScore).apply()
                    finish()
                }

                alert.setCancelable(false)


                alert.show()


            }
        }.start()
    }

    fun increaseScore(view: View){
        clicked++
        txtCatch.text="Catch: $clicked"

    }

    fun setImageViewArray(){
        imageViewArray.add(imageView1)
        imageViewArray.add(imageView2)
        imageViewArray.add(imageView3)
        imageViewArray.add(imageView4)
        imageViewArray.add(imageView5)
        imageViewArray.add(imageView6)
        imageViewArray.add(imageView7)
        imageViewArray.add(imageView8)
        imageViewArray.add(imageView9)
    }

    fun hideImageViewArray(){

        runnable= object : Runnable{
            override fun run() {
                for (image_view in imageViewArray ){
                    image_view.visibility=View.INVISIBLE
                }
                val random=  Random()
                val randomIndex=random.nextInt(9)
                imageViewArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,400)
            }
        }

        handler.post(runnable)

    }
}