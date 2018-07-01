package com.mooyz.birdcalls

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonTapped(view: View) {
        val id = view.id
        val ourId = view.resources.getResourceEntryName(id)

        val resourceId = resources.getIdentifier(ourId, "raw", packageName)

        val mplayer = MediaPlayer.create(this, resourceId)
        mplayer.start()

        Log.i("button tapped", ourId)
    }
}
