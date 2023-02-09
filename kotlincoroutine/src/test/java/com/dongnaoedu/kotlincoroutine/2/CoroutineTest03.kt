package com.dongnaoedu.kotlincoroutine

import kotlinx.coroutines.*
import org.junit.Test

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class CoroutineTest03 {

    //取消作用域会取消它的子协程
    @Test
    fun `test scope cancel`() = runBlocking<Unit> {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            delay(1000)
            println("job 1")
        }

        scope.launch {
            delay(1000)
            println("job 2")
        }
        delay(100)
        scope.cancel()
        delay(1000)
    }

    //被取消的子协程并不会影响其余兄弟协程
    @Test
    fun `test brother job`() = runBlocking<Unit> {
        val scope = CoroutineScope(Dispatchers.Default)
        val job1 = scope.launch {
            delay(1000)
            println("job 1")
        }

        val job2 = scope.launch {
            delay(1000)
            println("job 2")
        }
        delay(100)
        job1.cancel()
        delay(1000)
    }

    //取消异常
    //协程通过抛出一个特殊的异常 CancellationException 来处理取消操作。
    //所有kotlinx.coroutines中的挂起函数（withContext、delay等）都是可取消的。
    @Test
    fun `test CancellationException`() = runBlocking<Unit> {
        val job1 = GlobalScope.launch {
            try {
                delay(1000)
                println("job 1")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        delay(100)
        //在调用 .cancel 时您可以传入一个 CancellationException 实例来提供更多关于本次取消的详细信息
        //如果您不构建新的 CancellationException 实例将其作为参数传入的话，会创建一个默认的 CancellationException
        job1.cancel(CancellationException("取消"))
        job1.join()
        //job1.cancelAndJoin()
    }

    //CPU密集型任务取消
    //isActive是一个可以被使用在CoroutineScope中的扩展属性，检查Job是否处于活跃状态。
    @Test
    fun `test cancel cpu task by isActive`() = runBlocking {
        val startTime = System.currentTimeMillis()
        //CPU密集型任务在Default调度器中运行，在主线程中通过isActive取消不了
        val job = launch(Dispatchers.Default){
            var nextPrintTime = startTime
            var i = 0
            while(i < 5 && isActive){
                //每秒打印消息两次
                if(System.currentTimeMillis() >= nextPrintTime){
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消一个作业并且等待它结束
        println("main: Now I can quit.")
    }


    //ensureActive()，如果job处于非活跃状态，这个方法会立即抛出异常。
    @Test
    fun `test cancel cpu task by ensureActive`() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default){
            var nextPrintTime = startTime
            var i = 0
            while(i < 5){
                ensureActive()
                if(System.currentTimeMillis() >= nextPrintTime){
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit.")
    }


    //yield函数会检查所在协程的状态，如果已经取消，则抛出CancellationException予以响应。
    //此外，它还会尝试出让线程的执行权，给其他协程提供执行机会。
    //如果要处理的任务属于：
    //1) CPU 密集型，2) 可能会耗尽线程池资源，3) 需要在不向线程池中添加更多线程的前提下允许线程处理其他任务，那么请使用 yield()。
    @Test
    fun `test cancel cpu task by yield`() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default){
            var nextPrintTime = startTime
            var i = 0
            while(i < 5){
                yield()
                if(System.currentTimeMillis() >= nextPrintTime){
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit.")
    }
}