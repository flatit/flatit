package com.github.flatit.di

import android.app.Application
import com.github.flatit.data.*
import com.github.flatit.ui.finances.FinancesDebtViewModel
import com.github.flatit.ui.finances.FinancesExpenseViewModel
import com.github.flatit.ui.shoppinglist.ShoppingListViewModel
import com.github.flatit.ui.todos.TodosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

    single<ShoppingListRemoteDataSource> { ShoppingListFirebaseDataSource() }
    single<ShoppingListRepository> { ShoppingListRepositoryImpl(get()) }

    single<TodosRemoteDataSource> { TodosFirebaseDataSource() }
    single<TodosRepository> {TodosRepositoryImpl(get()) }

    single<FinancesRemoteDataSource> {FinancesFirebaseDataSource() }
    single<FinancesRepository> {FinancesRepositoryImpl(get()) }

    viewModel { ShoppingListViewModel(get()) }
    viewModel { TodosViewModel(get()) }
    viewModel { FinancesExpenseViewModel(get()) }
    viewModel { FinancesDebtViewModel(get()) }
}

class FlatIt : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}