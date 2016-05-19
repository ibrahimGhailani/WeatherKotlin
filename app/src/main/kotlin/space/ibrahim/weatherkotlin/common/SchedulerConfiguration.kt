package space.ibrahim.weatherkotlin.common

import rx.Scheduler

data class SchedulerConfiguration (
        val subscriberScheduler: Scheduler,
        val observerScheduler: Scheduler
)