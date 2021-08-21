package com.jacaceresf.service

import com.jacaceresf.configuration.PropertiesConfiguration
import com.jacaceresf.model.HelloWorld
import jakarta.inject.Inject
import java.time.LocalDateTime
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll


class LoadSimulator {

    @Inject
    private lateinit var propertiesConfiguration: PropertiesConfiguration


    suspend fun processLoad(): HelloWorld {

        ///each routine will perform some calculations and put itself into hibernation
        val currentStartTime = LocalDateTime.now()
        val threadDuration = propertiesConfiguration.processingTime / propertiesConfiguration.threads
        val workload: List<Deferred<Unit>> = (1..propertiesConfiguration.threads).map {
            GlobalScope.async {
                busyThread(threadDuration, currentStartTime)
            }
        }

        workload.awaitAll()

        return HelloWorld(propertiesConfiguration.returnMessage)
    }

    private fun busyThread(duration: Long, startTime: LocalDateTime) {
        while (LocalDateTime.now() < startTime.plusNanos(duration * 1000000)) {
            var dummy = 212121
            val dummy2 = dummy * dummy
            dummy += dummy2
            Thread.sleep(50)
        }
    }
}