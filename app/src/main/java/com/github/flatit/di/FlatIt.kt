package com.github.flatit.di

import android.app.Application
import com.github.flatit.data.*
import com.github.flatit.ui.shoppinglist.ShoppingListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

    single<ShoppingListRemoteDataSource> { ShoppingListFirebaseDataSource() }
    single<ShoppingListRepository> { ShoppingListRepositoryImpl(get()) }

    viewModel { ShoppingListViewModel(get()) }

}

class FlatIt : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}