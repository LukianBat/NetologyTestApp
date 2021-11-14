package ru.lukianbat.coreutils.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class SchedulerProvider @Inject constructor() : BaseSchedulerProvider {

    private val analyticsExecutor: Executor = Executors.newSingleThreadExecutor()
    private val databaseExecutor: Executor = Executors.newSingleThreadExecutor()

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun analytics(): Scheduler {
        return Schedulers.from(analyticsExecutor)
    }

    override fun db(): Scheduler {
        return Schedulers.from(databaseExecutor)
    }

    override fun singleThread(): Scheduler {
        return Schedulers.single()
    }
}
