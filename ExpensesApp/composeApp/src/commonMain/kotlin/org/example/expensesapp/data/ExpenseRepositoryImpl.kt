package org.example.expensesapp.data

import org.example.expensesapp.domain.ExpensesRepository
import org.example.expensesapp.model.Expense
import org.example.expensesapp.model.ExpensesCategory

class ExpenseRepositoryImpl(private val expenseManager: ExpenseManager) : ExpensesRepository {
    override fun getAllExpenses(): List<Expense> {
        return expenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        expenseManager.addNewExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        expenseManager.editExpense(expense)
    }

    override fun deleteExpense(expense: Expense): List<Expense> {
        expenseManager.deleteExpense(expense)
        return expenseManager.fakeExpenseList
    }


    override fun getCategories(): List<ExpensesCategory> {
        return expenseManager.getCategories()
    }
}