package com.philsoft.volumefix

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.media.AudioManager
import android.util.Log
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent


class VolumeFixAccessibilityService : AccessibilityService() {

    override fun onInterrupt() {
    }

    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
    }

    override fun onKeyEvent(event: KeyEvent?): Boolean {
        Log.d("asdf", "" + event)
        if (event?.keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            val wasVUpPressed = event?.action == KeyEvent.ACTION_UP
            if (wasVUpPressed) {
                adjustVolumeUp()
            }
            return true
        } else if (event?.keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            val wasVDownPressed = event?.action == KeyEvent.ACTION_UP
            if (wasVDownPressed) {
                adjustVolumeDown()
            }
            return true
        }
        return false
    }

    private fun adjustVolumeUp() {
        val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI)
    }

    private fun adjustVolumeDown() {
        val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI)
    }
}
