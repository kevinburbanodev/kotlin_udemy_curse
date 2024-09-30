package previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.expensesapp.data.ExpenseManager
import org.example.expensesapp.model.Expense
import org.example.expensesapp.model.ExpensesCategory
import org.example.expensesapp.presentation.ExpensesUiState
import org.example.expensesapp.ui.AllExpensesHeader
import org.example.expensesapp.ui.ExpensesItems
import org.example.expensesapp.ui.ExpensesScreen
import org.example.expensesapp.ui.ExpensesTotalHeader

@Preview
@Composable
private fun ExpensesTotalHeaderPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        ExpensesTotalHeader(1028.8)
    }
}

@Preview(showBackground = true)
@Composable
private fun AllExpensesHeaderPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        AllExpensesHeader()
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpensesItemsPreview() {
    Box(modifier = Modifier.padding(8.dp)) {
        ExpensesItems(expense = ExpenseManager.fakeExpenseList[0], onExpenseClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpensesScreenPreview() {
    ExpensesScreen(
        uiState = ExpensesUiState(
            expenses = ExpenseManager.fakeExpenseList,
            total = 1052.0
        ), onExpenseClick = {}
    )
}