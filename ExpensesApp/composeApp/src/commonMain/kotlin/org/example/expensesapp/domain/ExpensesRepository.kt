package org.example.expensesapp.domain

import org.example.expensesapp.model.Expense
import org.example.expensesapp.model.ExpensesCategory

interface ExpensesRepository {
    fun getAllExpenses(): List<Expense>
    fun addExpense(expense: Expense): Unit
    fun editExpense(expense: Expense): Unit
    fun deleteExpense(expense: Expense): List<Expense>
    fun getCategories(): List<ExpensesCategory>
}