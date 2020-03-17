package com.jingtian.springrain.helper

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.jingtian.springrain.data.source.OperationRepository
import com.jingtian.springrain.data.source.local.AppDatabase
import com.jingtian.springrain.data.source.local.OperationLocalDataSource
import com.jingtian.springrain.data.source.remote.OperationRemoteDataSource
import com.jingtian.springrain.ui.home.HomeViewModel

class HomeViewModelFactory(
    private val repository: OperationRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return HomeViewModel(repository, handle) as T
    }
}

private fun getOperationLocalDataSource(context: Context): OperationLocalDataSource {
    return OperationLocalDataSource.getInstance(
        AppDatabase.getInstance(context.applicationContext).operationDao())
}

private fun getOperationRemoteDataSource(context: Context): OperationRemoteDataSource {
    return OperationRemoteDataSource.getInstance(context.applicationContext)
}

private fun getOperationRepository(context: Context):OperationRepository {
    return OperationRepository.getInstance(getOperationLocalDataSource(context),
        getOperationRemoteDataSource(context))
}

fun provideHomeViewModelFactory(fragment: Fragment): HomeViewModelFactory {
    val repository = getOperationRepository(fragment.requireContext())
    return HomeViewModelFactory(repository, fragment)
}