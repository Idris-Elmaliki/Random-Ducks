package com.example.randomduck_app.ui

import com.example.randomduck_app.R
import android.content.Context
import android.media.MediaPlayer

fun playDuckAudio(
    context : Context,
    soundRes : Int
) {
    val mediaPlayer = MediaPlayer.create(context, soundRes)
    mediaPlayer.start()

    mediaPlayer.setOnCompletionListener {
        mediaPlayer.release()
    }
}

fun chooseDuckAudio(
    randNum : Int
) : Int {
    val soundRes = when (randNum) {
        0 -> R.raw.rand_duck_quack_3
        1 -> R.raw.success_meme_duck_quack
        2 -> R.raw.error_duck_quack_spongebob
        else -> throw Exception("Invalid number")
    }
    return soundRes
}