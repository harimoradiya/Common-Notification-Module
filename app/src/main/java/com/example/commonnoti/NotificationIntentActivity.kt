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
import com.example.commonnoti.databinding.ActivityNotificationIntentBinding
import com.example.commonnoti.domain.NotificationManagerHelper
import com.example.commonnoti.utils.NotificationUtils
import org.koin.android.ext.android.inject

class NotificationIntentActivity : AppCompatActivity() {



    private lateinit var binding: ActivityNotificationIntentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNotificationIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}