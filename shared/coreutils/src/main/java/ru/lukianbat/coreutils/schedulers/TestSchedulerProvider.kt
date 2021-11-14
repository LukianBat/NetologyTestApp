package ru.lukianbat.coreutils.schedulers

import io.reactivex.schedulers.Schedulers

/**
 * Шедулер для тестов
 */
class TestSchedulerProvider : BaseSchedulerProvider {

    override fun computation() = Schedulers.trampoline()

    override fun analytics() = Schedulers.trampoline()

    override fun io() = Schedulers.trampoline()

    override fun ui() = Schedulers.trampoline()

    override fun db() = Schedulers.trampoline()

    override fun singleThread() = Schedulers.trampoline()
}
