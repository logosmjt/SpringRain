package com.jingtian.springrain.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.jingtian.springrain.data.Operation
import com.jingtian.springrain.data.source.local.AppDatabase
import com.jingtian.springrain.helper.OPERATION_DATA_FILENAME
import kotlinx.coroutines.coroutineScope

class OperationDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            Log.i("JT", "doWork")
            applicationContext.assets.open(OPERATION_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<List<Operation>>() {}.type
                    val operations: List<Operation> = Gson().fromJson(jsonReader, type)
                    val database = AppDatabase.getInstance(applicationContext)
                    database.operationDao().insertAll(operations)
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = OperationDatabaseWorker::class.java.simpleName
    }
}
