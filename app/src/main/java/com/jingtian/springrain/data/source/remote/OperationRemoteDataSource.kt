package com.jingtian.springrain.data.source.remote

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.jingtian.springrain.data.Operation
import com.jingtian.springrain.data.Result
import com.jingtian.springrain.data.Result.Success
import com.jingtian.springrain.helper.OPERATION_DATA_FILENAME
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class OperationRemoteDataSource internal constructor(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getOperations(): Result<List<Operation>> = withContext(ioDispatcher) {
        try {
            val response = NetworkService.operationApi.getImage()
        } catch (e: Exception) {
            return@withContext Result.Error(IOException("Error getting stories", e))
        }
        // todo mock
        context.assets.open(OPERATION_DATA_FILENAME).use { inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->
                val type = object : TypeToken<List<Operation>>() {}.type
                val operations: List<Operation> = Gson().fromJson(jsonReader, type)
                return@withContext Success(operations)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: OperationRemoteDataSource? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: OperationRemoteDataSource(
                    context
                ).also { instance = it }
            }
    }
}
