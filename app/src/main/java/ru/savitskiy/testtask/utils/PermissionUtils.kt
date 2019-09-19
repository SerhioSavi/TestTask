package ru.savitskiy.testtask.utils

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun AppCompatActivity.checkPerms(permission: String, code: Int,
                                      onPermissionGrantedYet: (() -> Unit)) {
    if (ContextCompat.checkSelfPermission(
            this,
            permission
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        val permissions = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        ActivityCompat.requestPermissions(this, permissions, code)
    } else {
        onPermissionGrantedYet.invoke()
    }
}

fun AppCompatActivity.checkPermsResult(grantResults: IntArray, onPermissionGranted:  (() -> Unit)) {
    if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
        // denied
    } else {
        onPermissionGranted.invoke()
    }
}