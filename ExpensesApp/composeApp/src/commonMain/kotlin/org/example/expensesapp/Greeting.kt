package org.example.expensesapp

import org.example.expensesapp.utils.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}