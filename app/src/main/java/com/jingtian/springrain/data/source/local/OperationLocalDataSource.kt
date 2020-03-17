package com.jingtian.springrain.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.jingtian.springrain.data.Operation
import com.jingtian.springrain.data.Result
import com.jingtian.springrain.data.Result.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OperationLocalDataSource internal constructor(
    private val operationDao: OperationDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun observeOperations(): LiveData<List<Operation>> {
        return operationDao.observeOperations()
    }

    suspend fun getOperations(): Result<List<Operation>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(operationDao.getOperations())
        } catch (e: Exception) {
            Error(e)
        }
    }

    suspend fun insert(operation: Operation) = withContext(ioDispatcher) {
        operationDao.insert(operation)
    }

    suspend fun replaceOperations(operations: List<Operation>) = withContext(ioDispatcher) {
        operationDao.replaceOperations(operations)
    }

    companion object {
        @Volatile
        private var instance: OperationLocalDataSource? = null

        fun getInstance(operationDao: OperationDao) =
            instance ?: synchronized(this) {
                instance ?: OperationLocalDataSource(
                    operationDao
                ).also { instance = it }
            }
    }
}