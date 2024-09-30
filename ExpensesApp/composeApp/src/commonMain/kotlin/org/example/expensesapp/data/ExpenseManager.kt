package org.example.expensesapp.data

import org.example.expensesapp.model.Expense
import org.example.expensesapp.model.ExpensesCategory

object ExpenseManager {
    private var currentId = 1L
    val fakeExpenseList = mutableListOf(
        Expense(
            id = currentId++,
            amount = 70.0,
            category = ExpensesCategory.GROCERIES,
            description = "Weekly Buy"
        ),
        Expense(
            id = currentId++,
            amount = 10.2,
            category = ExpensesCategory.SNACKS,
            description = "Homies"
        ),
        Expense(
            id = currentId++,
            amount = 21000.0,
            category = ExpensesCategory.CAR,
            description = "Audi A1"
        ),
        Expense(
            id = currentId++,
            amount = 120.0,
            category = ExpensesCategory.PARTY,
            description = "Weekend party"
        ),
        Expense(
            id = currentId++,
            amount = 25.0,
            category = ExpensesCategory.HOUSE,
            description = "Cleaning"
        ),
        Expense(
            id = currentId++,
            amount = 120.0,
            category = ExpensesCategory.OTHER,
            description = "Services"
        )
    )
    fun addNewExpense(expense: Expense) {
        fakeExpenseList.add(expense.copy(id = currentId++))
    }

    fun editExpense(expense: Expense) {
        val index = fakeExpenseList.indexOfFirst {
            it.id == expense.id
        }
        if(index != -1) {
            fakeExpenseList[index] = fakeExpenseList[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description
            )
        }
    }

    fun deleteExpense(expense: Expense) {
        val index = fakeExpenseList.indexOfFirst {
            it.id == expense.id
        }

        fakeExpenseList.removeAt(index)
    }

    fun getCategories(): List<ExpensesCategory> {
        return listOf(
            ExpensesCategory.GROCERIES,
            ExpensesCategory.PARTY,
            ExpensesCategory.SNACKS,
            ExpensesCategory.COFFEE,
            ExpensesCategory.CAR,
            ExpensesCategory.HOUSE,
            ExpensesCategory.OTHER,
        )
    }
}