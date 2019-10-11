package wob.sdc.timetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.os.SystemClock



class MainActivity : AppCompatActivity() {

    private lateinit var chronometer: Chronometer

    private var todaysHours: Int = 0
    private var todaysMinutes: Int = 0

    private var runningHours: Int = 0
    private var runningMinutes: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.chronometer = findViewById(R.id.timer)
        this.chronometer.text = "00:00:00"
           this.chronometer.setOnChronometerTickListener { chrono -> onChronometerTick(chrono) }

        findViewById<Button>(R.id.startTimer).setOnClickListener { startTimer() }
        findViewById<Button>(R.id.stopTimer).setOnClickListener { stopTimer() }

    }

    fun onChronometerTick(chrono: Chronometer) {
        val time = SystemClock.elapsedRealtime() - chrono.base
        val h = (time / 3600000).toInt()
        val m = (time - h * 3600000).toInt() / 60000
        val s = (time - (h * 3600000).toLong() - (m * 60000).toLong()).toInt() / 1000
        val hh = if (h < 10) "0$h" else h.toString() + ""
        val mm = if (m < 10) "0$m" else m.toString() + ""
        val ss = if (s < 10) "0$s" else s.toString() + ""
        chrono.text = "$hh:$mm:$ss"

        this.runningHours = Integer.parseInt(hh)
        this.runningMinutes = Integer.parseInt(mm)

        printTodaysWork()
    }


    fun startTimer(){
        this.chronometer.base = SystemClock.elapsedRealtime()
        this.chronometer.start()
    }

    fun stopTimer(){
        this.chronometer.stop()
        this.todaysHours += this.runningHours
        this.todaysMinutes += this.runningMinutes
    }

    fun printTodaysWork(){
        val todaysWork = findViewById<TextView>(R.id.todaysWork)
        val actualHours = this.todaysHours + this.runningHours
        val actualMinutes = this.todaysMinutes + this.runningMinutes
        todaysWork.setText("Todays work is " + actualHours + " hours and " + actualMinutes + " minutes")


    }
}
