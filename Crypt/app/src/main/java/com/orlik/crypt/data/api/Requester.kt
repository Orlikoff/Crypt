package com.orlik.crypt.data.api

import android.content.Context
import com.orlik.crypt.data.api.cyphers.AES192Cypher
import com.orlik.crypt.data.api.cyphers.AES256Cypher
import com.orlik.crypt.data.api.cyphers.CaesarCypher
import com.orlik.crypt.data.api.cyphers.VigenereCypher

class Requester {

    companion object {
        const val KEY = "0c06b30ce8msh1e6d98b375c3b17p1c05c9jsn813d55c11df7"

        fun performRequest(cypher: String, data: String, mode: Boolean, context: Context): String {
            val cypherClass = when (cypher){
                "CAESAR" -> CaesarCypher()
                "VIGENERE" -> VigenereCypher()
                "AES256" -> AES256Cypher()
                else -> AES192Cypher()
            }
            return cypherClass.getResultFromServer(data, mode, context)
        }
    }

}