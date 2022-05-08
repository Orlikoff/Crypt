package com.orlik.crypt.data.api

import android.content.Context

interface CypherRequest {
    fun getResultFromServer(data: String, mode: Boolean, context: Context): String
}