package com.example.testclimatesmart.core

import android.util.Log
import java.io.IOException
import java.net.InetSocketAddress
import kotlinx.coroutines.coroutineScope
import java.net.Socket

object ConnectionInternet {

    /***
     * Metodo para checar la conexion a internet
     */
    suspend fun isInternetAvailable() = coroutineScope {
        return@coroutineScope try {
            val sock = Socket()
            val socketAdrres = InetSocketAddress("8.8.8.8", 53)
            sock.connect(socketAdrres, 3000)
            sock.close()
            true
        } catch (e: IOException) {
            Log.d("Error", e.message.toString())
            false
        }
    }
}