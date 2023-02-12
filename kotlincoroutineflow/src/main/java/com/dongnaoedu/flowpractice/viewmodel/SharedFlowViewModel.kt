package com.dongnaoedu.flowpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongnaoedu.flowpractice.common.Event
import com.dongnaoedu.flowpractice.common.LocalEventBus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class SharedFlowViewModel : ViewModel() {

    private lateinit var job: Job

    fun startRefresh() {
        job = viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                LocalEventBus.postEvent(Event((System.currentTimeMillis())))
            }
        }
    }

    fun stopRefresh() {
        job.cancel()
    }

}