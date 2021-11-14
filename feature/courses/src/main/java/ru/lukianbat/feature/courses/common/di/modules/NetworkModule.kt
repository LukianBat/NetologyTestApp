package ru.lukianbat.feature.courses.common.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.lukianbat.feature.courses.common.data.remote.CoursesApi
import ru.lukianbat.feature.courses.common.di.CoursesFlowScope

@Module
class NetworkModule {

    @CoursesFlowScope
    @Provides
    fun provideCoursesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/netology-code/rn-task/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @CoursesFlowScope
    @Provides
    fun provideCoursesApi(retrofit: Retrofit): CoursesApi =
        retrofit.create(CoursesApi::class.java)
}
