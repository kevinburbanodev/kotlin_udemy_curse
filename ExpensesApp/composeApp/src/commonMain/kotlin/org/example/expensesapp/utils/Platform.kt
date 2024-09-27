package org.example.expensesapp.utils

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform