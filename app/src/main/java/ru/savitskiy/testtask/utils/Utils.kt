package ru.savitskiy.testtask.utils

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AlertDialog
import java.util.regex.Pattern
import kotlin.math.roundToInt

fun String.isEmailValid(): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isPassValid(): Boolean {
    val expression = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}\$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun Double.toCelsius(): String {
    return "${(this - 273).roundToInt()} °C"
}

/**
 * Показ алерта
 */
fun Context.alert(message: String, title: String? = null): Dialog =
    AlertDialog.Builder(this).apply {
        if (title != null) setTitle(title)
        setMessage(message)
        setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
    }.show()