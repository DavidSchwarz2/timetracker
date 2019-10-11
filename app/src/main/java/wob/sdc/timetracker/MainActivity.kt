package wob.sdc.timetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.startTimer).setOnClickListener { startTimer() }
        findViewById<Button>(R.id.stopTimer).setOnClickListener { stopTimer() }

    }



    fun startTimer(){
        findViewById<Chronometer>(R.id.timer).start()
    }

    fun stopTimer(){
        findViewById<Chronometer>(R.id.timer).stop()
    }
}
