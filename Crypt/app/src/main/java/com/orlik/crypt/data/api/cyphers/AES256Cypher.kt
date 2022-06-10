package com.orlik.crypt.data.api.cyphers

import android.widget.Toast
import com.orlik.crypt.data.api.CypherRequest
import com.orlik.crypt.data.api.Requester
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.lang.Exception

class AES256Cypher: CypherRequest {
    override fun getResultFromServer(data: String, mode: Boolean): String {
        val client = OkHttpClient()

        val mediaType = "application/json".toMediaTypeOrNull()
        val body = "{\n\"data\": \"$data\"\n}".toRequestBody(mediaType)
        val tempRequest = if (mode)
            Request.Builder().url("https://encrypt-decrypt-hash.p.rapidapi.com/aes256/decrypt") else
            Request.Builder().url("https://encrypt-decrypt-hash.p.rapidapi.com/aes256/encrypt")

        val request = tempRequest
            .post(body)
            .addHeader("content-type", "application/json")
            .addHeader("X-RapidAPI-Host", "encrypt-decrypt-hash.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", Requester.KEY)
            .build()

        var response: Response? = null
        response = try{
            client.newCall(request).execute()
        } catch (exception: Exception){
            null
        }

        if (response == null){
            return ""
        }

        val json = JSONObject(response.body!!.string())

        return if (mode) json.getString("decryptedData")
        else json.getString("encryptedData")
    }
}