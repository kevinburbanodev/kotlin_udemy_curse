package org.example.expensesapp.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import org.example.expensesapp.DarkModeColors
import org.example.expensesapp.data.ExpenseManager
import org.example.expensesapp.data.ExpenseRepositoryImpl
import org.example.expensesapp.getColorsTheme
import org.example.expensesapp.presentation.ExpensesViewModel
import org.example.expensesapp.ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator) {
    val colors: DarkModeColors = getColorsTheme()
    val viewModel = viewModel(modelClass = ExpensesViewModel::class) { _ ->
        ExpensesViewModel(
            ExpenseRepositoryImpl(ExpenseManager)
        )
    }

    NavHost(
        modifier = Modifier.background(colors.backgroundColor),
        navigator = navigator,
        initialRoute = "/home"
    ) {
        scene(route = "/home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState) { expense ->
                navigator.navigate("/addExpenses/${expense.id}")
            }
        }

        scene(route = "/addExpenses/{id}") { backStackEntry ->
            val idFromPath = backStackEntry.path<Long>("id")
            val isAddExpense = idFromPath?.let { id -> viewModel.getExpenseWithId(id) }
        }
    }
}