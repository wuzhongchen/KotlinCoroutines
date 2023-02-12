package com.dongnaoedu.flowpractice.common

import kotlinx.coroutines.flow.MutableSharedFlow

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
object LocalEventBus {

    val events = MutableSharedFlow<Event>()

    suspend fun postEvent(event: Event) {
        events.emit(event)
    }

}

data class Event(val timestamp: Long)