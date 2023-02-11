package com.dongnaoedu.kotlincoroutine.`5`

import com.dongnaoedu.kotlincoroutine.model.User
import com.dongnaoedu.kotlincoroutine.api.userServiceApi
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import org.junit.Test
import java.io.File
import java.util.concurrent.atomic.AtomicInteger


/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class CoroutineTest03 {

    @Test
    fun `test not safe concurrent`() = runBlocking<Unit> {
        var count = 0
        List(1000) {
            GlobalScope.launch { count++ } //count++不是原子性的操作
        }.joinAll()
        println(count)
    }

    @Test
    fun `test safe concurrent`() = runBlocking<Unit> {
        var count = AtomicInteger(0)
        List(1000) {
            GlobalScope.launch { count.incrementAndGet() }
        }.joinAll()
        println(count.get())
    }

//    Channel：并发安全的消息通道。
//    Mutex：轻量级锁，它的lock和unlock从语义上与线程锁比较类似，之所以轻量是因为它在获取不到锁时不会阻塞线程，而是挂起等待锁的释放。
//    Semaphore：轻量级信号量，信号量可以有多个，协程在获取到信号量后即可执行并发操作。
//    当Semaphore的参数为1时，效果等价于Mutex。
    //kill -9 pid
    @Test
    fun `test safe concurrent tools`() = runBlocking<Unit> {
        var count = 0
        val mutex = Mutex()
        List(1000) {
            GlobalScope.launch {
                mutex.withLock {
                    count++
                }
            }
        }.joinAll()
        println(count)
    }

    @Test
    fun `test safe concurrent tools2`() = runBlocking<Unit> {
        var count = 0
        val semaphore = Semaphore(1)
        List(1000) {
            GlobalScope.launch {
                semaphore.withPermit {
                    count++
                }
            }
        }.joinAll()
        println(count)
    }

    @Test
    fun `test avoid access outer variable`() = runBlocking<Unit> {
        var count = 0
        val result = count + List(1000){
            GlobalScope.async { 1 }
        }.map { it.await() }.sum()
        //避免访问外部可变状态
        //通过返回值提供运算结果
        println(result)
    }

}