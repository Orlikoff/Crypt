package com.orlik.crypt.data.api

import com.orlik.crypt.ui.synchronizer.Synchronizer
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class Requester {

    // TODO: API requests for cyphers

    companion object {
        fun performRequest(cypher: String, data: String, mode: Boolean): String {
            val shifts = Synchronizer.getCurrentProfile()?.code.hashCode()
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://ciphers.p.rapidapi.com/caesar/cipher/$data/$shifts")
                .get()
                .addHeader("X-RapidAPI-Host", "ciphers.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "0c06b30ce8msh1e6d98b375c3b17p1c05c9jsn813d55c11df7")
                .build()

            val response = client.newCall(request).execute()
            val json = JSONObject(response.body!!.string())

            return json.getString("result")
        }

    }
}