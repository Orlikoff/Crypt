package com.orlik.crypt.data.api.cyphers

import com.orlik.crypt.data.api.CypherRequest
import com.orlik.crypt.data.api.Requester
import com.orlik.crypt.ui.synchronizer.Synchronizer
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.lang.Exception

class VigenereCypher: CypherRequest {
    override fun getResultFromServer(data: String, mode: Boolean): String {
        val key = Synchronizer.getCurrentProfile()?.code.hashCode()
        val client = OkHttpClient()

        val tempRequest = if (mode)
            Request.Builder().url("https://ciphers.p.rapidapi.com/vigenere/decipher/$data/$key") else
            Request.Builder().url("https://ciphers.p.rapidapi.com/vigenere/cipher/$data/$key")

        val request = tempRequest.get()
            .addHeader("X-RapidAPI-Host", "ciphers.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", Requester.KEY)
            .build()

        var response: Response? = null
        response = try{
            client.newCall(request).execute()
        } catch (exception: Exception){
            null
        }

        if (response == null || !response.isSuccessful){
            return ""
        }

        val json = JSONObject(response.body!!.string())

        return json.getString("result")
    }
}