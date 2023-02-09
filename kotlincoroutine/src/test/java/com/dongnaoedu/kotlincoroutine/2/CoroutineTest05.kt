package com.dongnaoedu.kotlincoroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Test

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class CoroutineTest05 {

    //超时任务
    //很多情况下取消一个协程的理由是它有可能超时。
    @Test
    fun `test deal with timeout`() = runBlocking {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }

    }

    //withTimeoutOrNull 通过返回 null 来进行超时操作，从而替代抛出一个异常
    @Test
    fun `test deal with timeout return null`() = runBlocking {
        val result = withTimeoutOrNull(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done" // 在它运行得到结果之前取消它
        } ?: "默认值"
        println("Result is $result")
    }

}