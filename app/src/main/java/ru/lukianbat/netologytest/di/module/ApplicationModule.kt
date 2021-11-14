package ru.lukianbat.netologytest.di.module

import android.content.Context
import android.util.Log
import ru.lukianbat.architecture.network.NetworkLoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.lukianbat.architecture.mvvm.ErrorAdapter
import ru.lukianbat.coreutils.schedulers.BaseSchedulerProvider
import ru.lukianbat.coreutils.schedulers.SchedulerProvider
import ru.lukianbat.netologytest.DefaultErrorAdapter
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(
                NetworkLoggingInterceptor {
                    Log.i(NETWORK_LOG_TAG, it)
                }.apply { level = NetworkLoggingInterceptor.Level.BODY }
            )
            .build()

    }

    @Provides
    @Singleton
    fun provideErrorAdapter(context: Context): ErrorAdapter {
        return DefaultErrorAdapter(context)
    }

    @Provides
    fun provideBaseSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

    companion object {
        private const val NETWORK_LOG_TAG = "NETWORK_LOG"
        private const val CONNECT_TIMEOUT = 10000L
        private const val READ_WRITE_TIMEOUT = 30000L
    }
}
