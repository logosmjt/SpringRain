package com.jingtian.springrain.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import com.jingtian.springrain.data.Operation
import com.jingtian.springrain.data.source.local.OperationLocalDataSource
import com.jingtian.springrain.data.source.remote.OperationRemoteDataSource
import com.jingtian.springrain.data.Result.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class OperationRepository(
    private val operationLocalDataSource: OperationLocalDataSource,
    private val operationRemoteDataSource: OperationRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun refresh() {
        val result = operationRemoteDataSource.getOperations()
        if (result is Success) {
            operationLocalDataSource.replaceOperations(result.data)
        } else if (result is Error) {
            Log.i("OperationRepository","result.exception:${result.exception}")
        }
    }

    fun observeTasks(): LiveData<List<Operation>> {
        return operationLocalDataSource.observeOperations()
    }

    suspend fun getOperations() {
        withContext(ioDispatcher){
            operationLocalDataSource.getOperations()
        }
    }

    suspend fun insert(operation: Operation) {
        withContext(ioDispatcher){
            operationLocalDataSource.insert(operation)
        }
    }

    companion object {
        @Volatile
        private var instance: OperationRepository? = null
        fun getInstance(localDataSource: OperationLocalDataSource, remoteDataSource
            : OperationRemoteDataSource) =
            instance ?: synchronized(this) {
                instance ?: OperationRepository(localDataSource, remoteDataSource)
                    .also { instance = it }
            }
    }
}

