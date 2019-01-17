package com.eddystudio.mutithreadingbm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

  lateinit var pgb1: ProgressBar
  lateinit var pgb2: ProgressBar
  lateinit var pgb3: ProgressBar
  lateinit var pgb4: ProgressBar
  lateinit var pgb5: ProgressBar
  lateinit var pgb6: ProgressBar
  lateinit var pgb7: ProgressBar
  lateinit var pgb8: ProgressBar
  lateinit var startBt: Button
  lateinit var logTv: TextView

  var startTime = 0L
  var endTime = 0L

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initUi()
  }


  private fun initUi() {
    pgb1 = findViewById(R.id.progress_bar1)
    pgb2 = findViewById(R.id.progress_bar2)
    pgb3 = findViewById(R.id.progress_bar3)
    pgb4 = findViewById(R.id.progress_bar4)
    pgb5 = findViewById(R.id.progress_bar5)
    pgb6 = findViewById(R.id.progress_bar6)
    pgb7 = findViewById(R.id.progress_bar7)
    pgb8 = findViewById(R.id.progress_bar8)
    startBt = findViewById(R.id.start_bt)
    logTv = findViewById(R.id.log_tv)
    logTv.text = "Log: \n"

    startBt.setOnClickListener {
      startTime = System.currentTimeMillis()
      logTv.text = "${logTv.text} Running... \n"
      runThread(pgb1, "1")
      runThread(pgb2, "2")
      runThread(pgb3, "3")
      runThread(pgb4, "4")
      runThread(pgb5, "5")
      runThread(pgb6, "6")
      runThread(pgb7, "7")
      runThread(pgb8, "8")
    }
  }

  private fun runThread(progressBar: ProgressBar, name: String) {
    val handler = Handler()
    Thread {
      for(j in 0..100) {
        val list = ArrayList<Int>()
        for(i in 1..20000) {
          list.add((1..Int.MAX_VALUE).random())
        }

        list.sort()
        handler.post {
          progressBar.setProgress(j, true)
        }
      }
      handler.post {
        endTime = System.currentTimeMillis()
        val timeuse = (endTime - startTime) / 1000.0
        logTv.text = "${logTv.text} Thread $name done, elapsed $timeuse s\n"

        if(allTestDone()) {
          logTv.text = "${logTv.text} All Threads Finished. Total time elasped: $timeuse s\n" +
              "---------------------------------------------------------------------------- \n"
        }
      }

    }.start()
  }


  private fun allTestDone(): Boolean {
    return pgb1.progress == 100 && pgb2.progress == 100 && pgb3.progress == 100 &&
        pgb4.progress == 100 && pgb5.progress == 100 && pgb6.progress == 100 &&
        pgb7.progress == 100 && pgb8.progress == 100
  }

}
