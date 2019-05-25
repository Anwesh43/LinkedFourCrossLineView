package com.anwesh.uiprojects.linkedfourcrossboxview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.fourcrossboxview.FourCrossBoxView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FourCrossBoxView.create(this)
    }
}
