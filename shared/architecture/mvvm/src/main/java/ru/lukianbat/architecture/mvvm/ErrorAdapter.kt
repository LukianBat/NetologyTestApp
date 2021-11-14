package ru.lukianbat.architecture.mvvm

import android.content.res.Resources
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

interface ErrorAdapter {

    companion object {
        val DEFAULT = object : ErrorAdapter {
            override fun adapt(error: Throwable): String? {
                return error.message
            }
        }

        fun connection(resources: Resources) = object : ErrorAdapter {
            override fun adapt(error: Throwable): String? = when (error) {
                is SocketException,
                is SocketTimeoutException,
                is UnknownHostException,
                is SSLException -> resources.getString(R.string.rxview_errors_network)
                else -> null
            }
        }

        fun composite(vararg adapters: ErrorAdapter) = object : ErrorAdapter {
            override fun adapt(error: Throwable): String? {
                return adapters
                    .asSequence()
                    .mapNotNull { it.adapt(error) }
                    .firstOrNull()
            }
        }
    }

    fun adapt(error: Throwable): String?
}
