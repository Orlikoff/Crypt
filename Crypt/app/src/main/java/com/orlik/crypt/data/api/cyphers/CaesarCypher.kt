package com.orlik.crypt.data.api.cyphers

import android.widget.Toast
import com.orlik.crypt.data.api.CypherRequest
import com.orlik.crypt.data.api.Requester
import com.orlik.crypt.ui.synchronizer.Synchronizer
import okhttp3.*
import org.json.JSONObject
import java.lang.Exception

class CaesarCypher: CypherRequest {
    override fun getResultFromServer(data: String, mode: Boolean): String {
        val shifts = Synchronizer.getCurrentProfile()?.code.hashCode()
        val client = OkHttpClient()

        val tempRequest = if (mode)
            Request.Builder().url("https://ciphers.p.rapidapi.com/caesar/decipher/$data/$shifts") else
            Request.Builder().url("https://ciphers.p.rapidapi.com/caesar/cipher/$data/$shifts")

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

        if (response == null){
            return ""
        }

        val json = JSONObject(response.body!!.string())

        return json.getString("result")
    }
}