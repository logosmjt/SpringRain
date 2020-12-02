package com.jingtian.springrain.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*// ktlint-disable
import com.jingtian.springrain.data.Operation

@Dao
interface OperationDao {
    @Query("SELECT * FROM operation")
    fun observeOperations(): LiveData<List<Operation>>

    @Query("SELECT * FROM operation")
    suspend fun getOperations(): List<Operation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(operations: List<Operation>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(operation: Operation)

    @Query("DELETE FROM operation")
    suspend fun deleteOperations()

    @Transaction
    suspend fun replaceOperations(operations: List<Operation>) {
        deleteOperations()
        insertAll(operations)
    }
}
