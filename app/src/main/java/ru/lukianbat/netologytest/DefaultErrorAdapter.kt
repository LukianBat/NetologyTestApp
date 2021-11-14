package ru.lukianbat.netologytest

import android.content.Context
import retrofit2.HttpException
import ru.lukianbat.architecture.mvvm.ErrorAdapter
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLException

class DefaultErrorAdapter @Inject constructor(private val context: Context) : ErrorAdapter {

    override fun adapt(error: Throwable): String? = when (error) {
        is SocketException,
        is SocketTimeoutException,
        is UnknownHostException,
        is SSLException -> context.getString(R.string.error_network)
        is HttpException -> context.getString(R.string.error_server)
        else -> error.message
    }
}
