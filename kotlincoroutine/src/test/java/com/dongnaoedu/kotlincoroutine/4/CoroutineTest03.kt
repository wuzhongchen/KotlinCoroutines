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
class CoroutineTest03 {

    fun simpleFlow() = flow<Int> {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i)
        }
    }

    @Test
    fun `test flow exception`() = runBlocking<Unit> {
        try {
            simpleFlow().collect { value ->
                println(value)
                check(value <= 1) { "Collected $value" }
            }
        } catch (e: Throwable) {
            println("Caught $e")
        }
    }


    @Test
    fun `test flow exception2`() = runBlocking<Unit> {
        flow {
            emit(1)
            throw ArithmeticException("Div 0") //上流的异常会影响到下
        }.catch { e: Throwable -> println("Caught $e") }
                .flowOn(Dispatchers.IO)
                .collect { println(it)
                }

        flow {
            throw ArithmeticException("Div 0")
            emit(1)
        }.catch { e: Throwable ->
            println("Caught $e")
            emit(10) //获取到异常 再补一个元素
        }.flowOn(Dispatchers.IO).collect { println(it) }

    }

    @Test
    fun `test flow exception3`() = runBlocking<Unit> {
        flow<Int> {
            for (i in 1..3) {
                delay(1000) //假装在一些重要的事情
                emit(i) //发射，产生一个元素
            }
        }.map { request -> "res $request".substring(20)}
            .catch { e: Throwable -> println("Caught $e") }
            .flowOn(Dispatchers.IO)
            .collect { println(it)
            }
    }

    fun simpleFlow2() = (1..3).asFlow()

    fun simpleFlow3() = flow<Int> {
        emit(1)
        throw RuntimeException()
    }


    @Test
    fun `test flow complete in finally`() = runBlocking<Unit> {
        try {
            simpleFlow2().collect { println(it) }
        } finally {
            println("Done")
        }

    }

    @Test
    fun `test flow complete in onCompletion`() = runBlocking<Unit> {
//        simpleFlow2()
//                .onCompletion { println("Done") }
//                .collect { println(it) }

//        simpleFlow3()
//                .onCompletion { exception ->
//                    if (exception != null) println("Flow completed exceptionally")
//                }
//                .catch { exception -> println("Caught $exception") }
//                .collect { println(it) }

        simpleFlow2()
                .onCompletion { exception ->
                    if (exception != null) println("Flow completed exceptionally")
                }
                .collect { value ->
                    println(value)
                    check(value <= 1) { "Collected $value" }
                }
    }

}