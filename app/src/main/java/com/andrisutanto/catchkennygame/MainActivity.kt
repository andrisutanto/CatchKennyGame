package com.andrisutanto.catchkennygame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var score : Int = 0
    var imageArray = ArrayList<ImageView>()
    var handler : Handler = Handler()
    var runnable : Runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        score = 0
        imageArray = arrayListOf(imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9)
        hideImages()

        object : CountDownTimer(10000,1000) {
            override fun onFinish() {
                timeText.text = "Time's off"

                //setelah waktu habis, maka sembunyikan kenny yang ada
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
            }

            override fun onTick(p0: Long) {
                timeText.text = "Time: " + p0 / 1000
            }
        }.start()

    }

    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {
                //pertama, buat semua image dalam posisi invisible
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                //buat random, dari angka 0 s.d. 8, jika sudah image array yang ke-i tsb ditampilkan
                var random = Random()
                val index = random.nextInt(8 - 0)
                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)
            }
        }
        handler.post(runnable)
    }

    fun increaseScore(view: View) {
        score++
        scoreText.text = "Score: " + score
    }
}
