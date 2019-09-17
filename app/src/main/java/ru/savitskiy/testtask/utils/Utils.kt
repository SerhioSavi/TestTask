package ru.savitskiy.testtask.utils

import java.util.regex.Pattern

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