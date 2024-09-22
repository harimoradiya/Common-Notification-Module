package com.example.commonnoti

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.commonnoti.databinding.ActivityMainBinding
import com.example.commonnoti.domain.NotificationManagerHelper
import com.example.commonnoti.utils.NotificationUtils
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {


    private val notificationManagerHelper : NotificationManagerHelper by inject()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowNotification.setOnClickListener {
            checkAndRequestNotificationPermission {
                showSimpleNotificagtion()
            }

        }

        binding.btnCancelNotification.setOnClickListener {
            checkAndRequestNotificationPermission {
                cancelCurrentNotification()
            }
        }



    }

    private fun checkAndRequestNotificationPermission(onGranted: () -> Unit){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED){
                onGranted()
            }
            else{
                requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }

        }
        else{
            onGranted()
        }
    }

    private val requestNotificationPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted -> Boolean
        if (isGranted){
            showSimpleNotificagtion()
        }
        else{
            Toast.makeText(this@MainActivity,"Please allow notification permission",Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSimpleNotificagtion(){
        // Build the notification using Big Text Style
        val notification = NotificationUtils.buildBigTextNotification(
            context = this,
            title = "Practicing Koin",
            message = "This is a Test notification.",
            channelId = "test_channel_id"
        )
      notificationManagerHelper.showNotification(
          notification
      )
    }

    private fun cancelCurrentNotification(){
        notificationManagerHelper.cancelNotification(NotificationUtils.NOTIFICATION_ID)
    }
}