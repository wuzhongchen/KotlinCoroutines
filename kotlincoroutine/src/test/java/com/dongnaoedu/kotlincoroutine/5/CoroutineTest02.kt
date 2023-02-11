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
import org.junit.Test
import java.io.File


private val cachePath = "C://Users//isabe//AndroidStudioProjects//kotlincoroutine//txt//usercache.txt"
private val gson = Gson()

//定义返回类型
data class Response<T>(val value: T, val isLocal: Boolean)

//不推荐使用GlobalScope 而是变成扩展函数
fun CoroutineScope.getUserFromLocal(name: String) = async(Dispatchers.IO) {
    delay(3000) //故意的延迟
    File(cachePath).readText().let { gson.fromJson(it, User::class.java) }
}

fun CoroutineScope.getUserFromRemote(name: String) = async(Dispatchers.IO) {
    userServiceApi.getUser(name)
}

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class CoroutineTest02 {

    @Test
    fun `test select await`() = runBlocking<Unit> {
        GlobalScope.launch {
            val localRequest = getUserFromLocal("xxx")
            val remoteRequest = getUserFromRemote("13859")

            //只会返回一个最快的对象
            val userResponse = select<Response<User>> {
                localRequest.onAwait { Response(it, true) } //对应事件有返回值
                remoteRequest.onAwait { Response(it, false) }
            }

            userResponse.value?.let { println(it) }
        }.join()
    }


    @Test
    fun `test select channel`() = runBlocking<Unit> {
        val channels = listOf(Channel<Int>(), Channel<Int>())
        GlobalScope.launch {
            delay(100)
            channels[0].send(200)
        }

        GlobalScope.launch {
            delay(50)
            channels[1].send(100)
        }

        val result = select<Int?> {
            channels.forEach { channel ->
                channel.onReceive { it }
            }
        }
        println(result)
    }

    @Test
    fun `test SelectClause0`() = runBlocking<Unit> {
        val job1 = GlobalScope.launch {
            delay(100)
            println("job 1")
        }

        val job2 = GlobalScope.launch {
            delay(10)
            println("job 2")
        }

        select<Unit> {
            job1.onJoin { println("job 1 onJoin") } //无返回值 无参函数
            job2.onJoin { println("job 2 onJoin") }
        }

        delay(1000)
    }


    @Test
    fun `test SelectClause2`() = runBlocking<Unit> {
        val channels = listOf(Channel<Int>(), Channel<Int>())
        println(channels)

        launch(Dispatchers.IO) {
            select<Unit?> {
                launch {
                    delay(10)
                    channels[1].onSend(200) { sentChannel ->
                        println("sent on $sentChannel") //对应事件有返回值，还需要一个额外的参数，200是要发送的值，sentchannel回调的函数
                    }
                }

                launch {
                    delay(100)
                    channels[0].onSend(100) { sentChannel ->
                        println("sent on $sentChannel")
                    }
                }
            }
        }

        GlobalScope.launch {
            println(channels[0].receive())
        }

        GlobalScope.launch {
            println(channels[1].receive())
        }

        delay(1000)
    }


    @Test
    fun `test select flow`() = runBlocking<Unit> {
        // 函数 -> 协程 -> Flow -> Flow合并
        val name = "13859"
        coroutineScope {
            listOf(::getUserFromLocal, ::getUserFromRemote) //双冒号表示函数引用
                    .map { function ->
                        function.call(name) //传参 通过Kotlin反射
                    }.map { deferred ->
                        flow { emit(deferred.await()) }
                    }.merge().collect { user -> println(user) }

        }
    }

}