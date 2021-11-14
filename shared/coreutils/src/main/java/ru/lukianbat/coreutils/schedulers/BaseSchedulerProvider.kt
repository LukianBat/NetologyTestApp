package ru.lukianbat.coreutils.schedulers

import io.reactivex.Scheduler

interface BaseSchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

    fun analytics(): Scheduler

    fun db(): Scheduler

    fun singleThread(): Scheduler
}
