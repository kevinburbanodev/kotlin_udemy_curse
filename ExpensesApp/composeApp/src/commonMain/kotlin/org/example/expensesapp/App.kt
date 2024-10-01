package org.example.expensesapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.viewmodel.viewModel
import org.example.expensesapp.data.ExpenseManager
import org.example.expensesapp.data.ExpenseRepositoryImpl
import org.example.expensesapp.data.TitleTopBarTypes
import org.example.expensesapp.navigation.Navigation
import org.example.expensesapp.presentation.ExpensesViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PreComposeApp() {
        val colors = getColorsTheme()
        val viewModel = viewModel(modelClass = ExpensesViewModel::class) { savedStateHolder ->
            ExpensesViewModel(
                ExpenseRepositoryImpl(ExpenseManager)
            )
        }
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        AppTheme {
            val navigator = rememberNavigator()
            val titleTopBar = getTitleTopAppBar(navigator)
            val isEditOrAddExpenses = titleTopBar != TitleTopBarTypes.DASHBOARD.value
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = titleTopBar,
                                fontSize = 25.sp,
                                color = colors.textColor

                            )
                        },
                        navigationIcon = {
                            if (isEditOrAddExpenses) {
                                IconButton(
                                    onClick = {
                                        navigator.popBackStack()
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(start = 16.dp),
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        tint = colors.textColor,
                                        contentDescription = "Back Arrow"
                                    )
                                }
                            } else {
                                Icon(
                                    modifier = Modifier.padding(start = 16.dp),
                                    imageVector = Icons.Default.Apps,
                                    tint = colors.textColor,
                                    contentDescription = "Dashboard Icon"
                                )
                            }

                        },
                        elevation = 0.dp,
                        backgroundColor = Color.White,
                    )
                },
                floatingActionButton = {
                    if(!isEditOrAddExpenses) {
                        FloatingActionButton(
                            modifier = Modifier.padding(8.dp),
                            onClick = {
                                navigator.navigate("/addExpenses")
                            },
                            shape = RoundedCornerShape(50),
                            backgroundColor = colors.addIconColor,
                            contentColor = Color.White,
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    tint = Color.White,
                                    contentDescription = "Floating Icon"
                                )
                            }
                        )
                    }
                }
            ) { _ ->
                Navigation(navigator)
            }
        }

    }
}

@Composable
fun getTitleTopAppBar(navigator: Navigator): String {
    var titleTopBar = TitleTopBarTypes.DASHBOARD

    val isOnAddExpenses =
        navigator.currentEntry.collectAsState(null).value?.route?.route.equals("/addExpenses/{id}?")
    if (isOnAddExpenses) {
        titleTopBar = TitleTopBarTypes.ADD
    }

    val isOnEditExpenses = navigator.currentEntry.collectAsState(null).value?.path<Long>("id")
    isOnEditExpenses?.let {
        titleTopBar = TitleTopBarTypes.EDIT
    }

    return titleTopBar.value
}
