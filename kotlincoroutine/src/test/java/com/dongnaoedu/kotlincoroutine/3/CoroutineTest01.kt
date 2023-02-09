package com.dongnaoedu.kotlincoroutine.`3`

import kotlinx.coroutines.*
import org.junit.Test
import java.io.IOException
import kotlin.AssertionError

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class CoroutineTest01 {

//    CoroutineContext是一组用于定义协程行为的元素。
//    Job：控制协程的生命周期
//    ·CoroutineDispatcher：向合适的线程分发任务
//    CoroutineName：协程的名称，调试的时候很有用
//    ·CoroutineExceptionHandler：处理未被捕捉的异常
    @Test
    fun `test CoroutineContext`() = runBlocking<Unit> {
    //为什么用+ 号？ 运算符重载了
        launch(Dispatchers.Default + CoroutineName("test")) {
            println("I'm working in thread ${Thread.currentThread().name}")
        }
    }

    @Test
    fun `test CoroutineContext extend`() = runBlocking<Unit> {
        val scope = CoroutineScope(Job() + Dispatchers.IO + CoroutineName("test"))
        //launch继承自coroutineContext
        val job = scope.launch {
            println("${coroutineContext[Job]}  ${Thread.currentThread().name}")
            //async是launch的子协程
            val result = async {
                println("${coroutineContext[Job]}  ${Thread.currentThread().name}")
                "OK"
            }.await()
        }
        job.join()
    }

    @Test
    fun `test CoroutineContext extend2`() = runBlocking<Unit> {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }
        val scope = CoroutineScope(
                Job() + Dispatchers.Main + coroutineExceptionHandler
        )

        val job = scope.launch(Dispatchers.IO) {
            //新协程
            //传入协程构建起的参数 的优先级 高于继承的上下文参数，因此会覆盖对应的参数值
        }
    }

    @Test
    fun `test exception propagation`() = runBlocking<Unit> {
        val job = GlobalScope.launch {
            try {
                throw IndexOutOfBoundsException()
            } catch (e: Exception) {
                println("Caught IndexOutOfBoundsException")
            }
        }
        job.join()

        val deferred = GlobalScope.async {
            println("async")
            throw ArithmeticException()
        }
        //根协程抛出异常 async需要在await的时候catch异常
        try {
            deferred.await()
        }catch (e:Exception){
            println("Caught ArithmeticException")
        }

        delay(1000)
    }

    //非根协程的异常传播
    @Test
    fun `test exception propagation2`() = runBlocking<Unit> {
        val scope = CoroutineScope(Job())
        val job = scope.launch {
            async {
                throw IllegalArgumentException()
                //如果async抛出异常 launch就会立即抛出异常，而不会调用.await()
            }
        }
        job.join()
    }

    @Test
    fun `test SupervisorJob`() = runBlocking<Unit> {
//        当一个协程由于一个异常而运行失败时，它会传播这个异常并传递给它的父级。接下来，父级会进行下面几步操作：
//        取消它自己的子级
//        取消它自己
//        将异常传播并传递给它的父级
        val supervisor = CoroutineScope(SupervisorJob())
        val job1 = supervisor.launch {
            delay(100)
            println("child 1")
            throw IllegalArgumentException()
        }

        val job2 = supervisor.launch {
            try {
//                delay(Long.MAX_VALUE)
                delay(3000)
            } finally {
                println("child 2 finished.")
            }
        }

//        delay(200)
//        如果job1 job2都要取消掉
//        supervisor.cancel()
        joinAll(job1, job2)
    }

    @Test
    fun `test supervisorScope`() = runBlocking<Unit> {
        supervisorScope {
            launch {
                delay(100)
                println("child 1")
                throw IllegalArgumentException()
            }

            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("child 2 finished.")
            }
        }
    }

    @Test
    fun `test supervisorScope2`() = runBlocking<Unit> {
        supervisorScope {
            val child = launch {
                try {
                    println("The child is sleeping")
                    delay(Long.MAX_VALUE)
                } finally {
                    println("The child is cancelled")
                }
            }
            yield()
            println("Throwing an exception from the scope")
            //在协程作用域里面发生异常 所有的子协程都会被取消
            throw AssertionError()
        }
    }

    @Test
    fun `test CoroutineExceptionHandler`() = runBlocking<Unit> {
        //使用CoroutineExceptionHandler对协程的异常进行捕获。
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }

        //时机：异常是被自动抛出异常的协程所抛出的（使用launch 会被catch到，而不是async时）；
        //位置：在CoroutineScope的CoroutineContext中或在一个根协程(CoroutineScope或者supervisorScope的直接子协程)中
        val job = GlobalScope.launch(handler) {
            throw AssertionError()
        }

        val deferred = GlobalScope.async(handler) {
            throw ArithmeticException()
        }

        job.join()
        deferred.await()
    }

    @Test
    fun `test CoroutineExceptionHandler2`() = runBlocking<Unit> {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }
        val scope = CoroutineScope(Job())
        val job = scope.launch(handler) {
            launch {
                throw IllegalArgumentException()
            }
        }
        job.join()
    }

    @Test
    fun `test CoroutineExceptionHandler3`() = runBlocking<Unit> {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }
        val scope = CoroutineScope(Job())
        val job = scope.launch {
            launch(handler) { //放在里面不会捕获到
                throw IllegalArgumentException()
            }
        }
        job.join()
    }


    @Test
    fun `test cancel and exception`() = runBlocking<Unit> {
        val job = launch {
            val child = launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    println("Child is cancelled.")
                }
            }
            yield()
            println("Cancelling child")
            child.cancelAndJoin()
            yield()
            println("Parent is not cancelled")
        }
        job.join()
    }


    @Test
    fun `test cancel and exception2`() = runBlocking<Unit> {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }

        val job = GlobalScope.launch(handler) {
            launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    withContext(NonCancellable) {
                        println("Children are cancelled, but exception is not handled until all children terminate")
                        delay(100)
                        println("The first child finished its non cancellable block")
                    }
                }
            }

            launch {
                delay(10)
                println("Second child throws an exception")
                throw ArithmeticException()
            }
        }
        job.join()
    }


    @Test
    fun `test exception aggregation`() = runBlocking<Unit> {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception  ${exception.suppressed.contentToString()}")
        }

        val job = GlobalScope.launch(handler) {
            launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    throw ArithmeticException()  //2
                }
            }

            launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    throw IndexOutOfBoundsException()  //3
                }
            }

            launch {
                delay(100)
                throw IOException()  //1
            }
        }

        job.join()
    }

}