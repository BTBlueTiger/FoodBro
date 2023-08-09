package com.bluetiger.foodbrocompose.database.room

import androidx.room.Ignore
import androidx.room.Transaction

class TransactionRunnerDao : TransactionRunner {
    @Transaction
    protected open suspend fun runInTransaction(tx: suspend () -> Unit) = tx()

    @Ignore
    override suspend fun invoke(tx: suspend () -> Unit) {
        runInTransaction(tx)
    }
}

interface TransactionRunner {
    suspend operator fun invoke(tx: suspend () -> Unit)
}