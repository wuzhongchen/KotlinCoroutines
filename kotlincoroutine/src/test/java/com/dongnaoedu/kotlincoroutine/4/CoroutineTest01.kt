package com.dongnaoedu.kotlincoroutineflow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Test
import kotlin.system.measureTimeMillis

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class CoroutineTest01 {

    //返回了多个值，但不是异步
    fun simpleList(): List<Int> = listOf<Int>(1, 2, 3)

    //返回了多个值，是同步
    //SequenceScope限制挂起
    fun simpleSequence(): Sequence<Int> = sequence {
        for (i in 1..3) {
            //Thread.sleep(1000)  //阻塞，假装在计算
//            delay(1000) //挂起函数
            yield(i) //只能调用它已有的挂起函数
        }
    }

    //返回了多个值，是异步，一次性返回了多个值
    suspend fun simpleList2(): List<Int> {
        delay(1000)
        return listOf<Int>(1, 2, 3)
    }

    //返回多个值，是异步的
//    flow..…}构建块中的代码可以挂起。
//函数simpleFlow不再标有suspend修饰符。
//流使用emit函数发射值。
//流使用collect函数收集值。
    fun simpleFlow() = flow<Int> {
        for (i in 1..3) {
            delay(1000) //假装在一些重要的事情
            emit(i) //发射，产生一个元素
        }
    }


    @Test
    fun `test multiple values`() {
        //simpleList().forEach { value -> println(value) }

        simpleSequence().forEach { value -> println(value) }
    }

    @Test
    fun `test multiple values2`() = runBlocking<Unit> { //需要在协程内部 才能调用挂起函数
        simpleList2().forEach { value -> println(value) }
    }

    @Test
    fun `test multiple values3`() = runBlocking<Unit> {
        launch {
            for (k in 1..3) {
                println("I'm not blocked $k")
                delay(1500)
            }
        }

        simpleFlow().collect { value -> println(value) }
    }


    fun simpleFlow2() = flow<Int> {
        println("Flow started")
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }

    @Test
    fun `test flow is cold`() = runBlocking<Unit> {
        val flow = simpleFlow2()
        println("Calling collect...")
        flow.collect { value -> println(value) }
        //Flow是一种类似于序列的冷流，flow构建器中的代码直到流被收集的时候才运行。
        println("Calling collect again...")
        flow.collect { value -> println(value) }
    }


    @Test
    fun `test flow continuation`() = runBlocking<Unit> {
        //操作符 过滤
        (1..5).asFlow().filter {
            it % 2 == 0
        }.map {  //变换
            "string $it"
        }.collect {
            println("Collect $it")
        }
    }


    @Test
    fun `test flow builder`() = runBlocking<Unit> {
        flowOf("one","two","three")
                .onEach { delay(1000) } //每隔1秒钟 发射1个元素
                .collect { value ->
                    println(value)
                }

        (1..3).asFlow().collect { value ->
            println(value)
        }
    }


    fun simpleFlow3() = flow<Int> {
        println("Flow started ${Thread.currentThread().name}")
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }

    //流的搜集总是在协程的上下文中发生
    fun simpleFlow4() = flow<Int> {
        withContext(Dispatchers.IO) {
            println("Flow started ${Thread.currentThread().name}")
            for (i in 1..3) {
                delay(1000)
                emit(i)
            }
        }
    }


    @Test
    fun `test flow context`() = runBlocking<Unit> {
        simpleFlow4()
                .collect { value -> println("Collected $value ${Thread.currentThread().name}") }
    }

    fun simpleFlow5() = flow<Int> {
        println("Flow started ${Thread.currentThread().name}")
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.Default) //操作符 用于更改flow发射的是上下文

    @Test
    fun `test flow on`() = runBlocking<Unit> {
        simpleFlow5()
                .collect { value -> println("Collected $value ${Thread.currentThread().name}") }
    }


    //事件源
    fun events() = (1..3)
            .asFlow()
            .onEach { delay(100) }
            .flowOn(Dispatchers.Default)


    @Test
    fun `test flow launch`() = runBlocking<Unit> {
        val job = events()
                .onEach { event -> println("Event: $event ${Thread.currentThread().name}") }
                //.collect {  }
                //.launchIn(CoroutineScope(Dispatchers.IO))
                .launchIn(this)

        //delay(200)
        //job.cancelAndJoin() //取消流
    }


    fun simpleFlow6() = flow<Int> {
        for (i in 1..3) {
            delay(1000)
            emit(i)
            println("Emitting $i")
        }
    }


    @Test
    fun `test cancel flow`() = runBlocking<Unit> {
        withTimeoutOrNull(2500) {
            simpleFlow6().collect { value -> println(value) }
        }
        println("Done")
    }


    fun simpleFlow7() = flow<Int> {
        for (i in 1..5) {
            emit(i)
            println("Emitting $i")
        }
    }

    @Test
    fun `test cancel flow check`() = runBlocking<Unit> {
        simpleFlow7().collect { value ->
            println(value)
            if (value == 3) cancel()
        }

        (1..5).asFlow().cancellable().collect { value -> //能够取消
            println(value)
            if (value == 3) cancel()
        }
    }


    fun simpleFlow8() = flow<Int> {
        for (i in 1..10) {
            delay(100)
            emit(i)
            println("Emitting $i ${Thread.currentThread().name}")
        }
    }

    //rxJava 也有背压
    @Test
    fun `test flow back pressure`() = runBlocking<Unit> {
        val time = measureTimeMillis {
//            simpleFlow8().collect { value ->
//                    delay(300)   //处理这个元素消耗300ms
//                    println("Collected $value ${Thread.currentThread().name}")
//             }
            simpleFlow8()
                    //.flowOn(Dispatchers.Default) //第2种：让flow处于后台线程 并行发送了3个元素
                    // .buffer(50) //第1种：使用缓冲区
//                    .conflate() //不处理每1个值，
                    .collectLatest { value -> //只接受最后一个值
//                    .collect { value ->
                delay(300)   //处理这个元素消耗300ms
                println("Collected $value ${Thread.currentThread().name}")
            }
        }

        println("Collected in $time ms")
    }

}