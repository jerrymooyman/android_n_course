package com.mooyz.sounddemo

import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.util.Log
import android.view.View
import android.widget.SeekBar
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() { //, SeekBar.OnSeekBarChangeListener {

    var mplayer:  MediaPlayer? = null
    var audioManager : AudioManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mplayer = MediaPlayer.create(this, R.raw.birds)
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val maxVol = audioManager!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val curVol = audioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC)

        val volumeController: SeekBar = bind(R.id.volumeController)
        volumeController.max = maxVol
        volumeController.progress = curVol

        volumeController.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(p0: SeekBar?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                audioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
            }
        })


        val fileDuration: Int = mplayer!!.duration
        val scrubController: SeekBar = bind(R.id.scrubber)
        scrubController.max = fileDuration

        Timer().scheduleAtFixedRate(timerTask {
           scrubController.progress = mplayer!!.currentPosition
        }, 0, 100)

        scrubController.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Log.i("seekbar value: ", progress.toString())
                mplayer!!.seekTo(progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun play(view: View) {
        mplayer!!.start()
    }

    fun pause(view: View) {
        mplayer!!.pause()
    }
}


fun <T : View> Activity.bind(@IdRes res : Int) : T {
    @Suppress("UNCHECKED_CAST")
    return findViewById(res) as T
}
