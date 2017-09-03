package com.philsoft.volumefix

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun isServiceEnabled(): Boolean {
        val settingValue = Settings.Secure.getString(contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
        return settingValue.contains(VolumeFixAccessibilityService::class.java.canonicalName, false)
    }

    override fun onResume() {
        super.onResume()

        val isEnabled = isServiceEnabled()
        if (isEnabled) {
            service_status.text = getString(R.string.status, "enabled")
            service_status.setTextColor(ContextCompat.getColor(this, R.color.green))
        } else {
            service_status.text = getString(R.string.status, "disabled")
            service_status.setTextColor(Color.RED)
        }
        main_description.text = getString(R.string.main_description)
        button_launch_accessibility.setOnClickListener { launchAccessibilitySettings() }
    }

    fun launchAccessibilitySettings() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
    }
}
