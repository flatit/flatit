package com.github.flatit.data

import androidx.lifecycle.LiveData
import com.github.flatit.data.model.Flatmate

interface FlatmateRepository {
    fun getFlatmates() : LiveData<List<Flatmate>>
    fun addFlatmate(item: Flatmate)
    fun updateFlatmate(item: Flatmate)
    fun deleteFlatmate(item: Flatmate)
}

class FlatmateRepositoryImpl(
    private val dataSource: FlatmateRemoteDataSource
) : FlatmateRepository {
    override fun getFlatmates(): LiveData<List<Flatmate>> {
        return dataSource.getFlatmates()
    }

    override fun addFlatmate(item: Flatmate) {
        dataSource.addFlatmate(item)
    }

    override fun updateFlatmate(item: Flatmate) {
        dataSource.updateFlatmate(item)
    }

    override fun deleteFlatmate(item: Flatmate) {
        dataSource.deleteFlatmate(item)
    }
}