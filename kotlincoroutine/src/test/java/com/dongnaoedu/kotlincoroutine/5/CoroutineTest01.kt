package com.dongnaoedu.kotlincoroutine.`5`


import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import org.junit.Test

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class CoroutineTest01 {

    //channel实际上就是一个队列
    @Test
    fun `test know channel`() = runBlocking<Unit> {
        val channel = Channel<Int>()
        //生产者
        val producer = GlobalScope.launch {
            var i = 0
            while (true) {
                delay(1000)
                channel.send(++i)
                println("send $i")
            }
        }

        //消费者
        val consumer = GlobalScope.launch {
            while (true) {
                val element = channel.receive()
                println("receive $element")
            }
        }
        joinAll(producer, consumer)

    }


    //容量
    @Test
    fun `test know channel2`() = runBlocking<Unit> {
        val channel = Channel<Int>()
        //生产者
        val producer = GlobalScope.launch {
            var i = 0
            while (true) {
                delay(1000)
                channel.send(++i)
                println("send $i")
            }
        }

        //消费者
        val consumer = GlobalScope.launch {
            while (true) {
                delay(2000)
                val element = channel.receive()
                println("receive $element")
            }
        }
        joinAll(producer, consumer)

    }


    @Test
    fun `test iterate channel`() = runBlocking<Unit> {
        val channel = Channel<Int>(Channel.UNLIMITED)
        //生产者
        val producer = GlobalScope.launch {
            for (x in 1..5) {
                channel.send(x * x)
                println("send ${x * x}")
            }
        }

        //消费者 场景: 数据来源很快 但是响应交互要延迟
        val consumer = GlobalScope.launch {
            val iterator = channel.iterator()
//            while (iterator.hasNext()){
//                val element = iterator.next()
//                println("receive $element")
//                delay(2000)
//            }
            for (element in channel) {
                println("receive $element")
                delay(2000)
            }
        }

        joinAll(producer, consumer)

    }


    //生产者协程
    @Test
    fun `test fast producer channel`() = runBlocking<Unit> {
        val receiveChannel: ReceiveChannel<Int> = GlobalScope.produce<Int> {
            repeat(100) {
                delay(1000)
                send(it)
            }
        }

        val consumer = GlobalScope.launch {
            for (i in receiveChannel) {
                println("received: $i")
            }
        }
        consumer.join()
    }


    @Test
    fun `test fast consumer channel`() = runBlocking<Unit> {
        val sendChannel: SendChannel<Int> = GlobalScope.actor<Int> {
            while (true) {
                val element = receive()
                println(element)
            }
        }

        val producer = GlobalScope.launch {
            for (i in 0..3) {
                sendChannel.send(i)
            }
        }

        producer.join()
    }

    @Test
    fun `test close channel`() = runBlocking<Unit> {
        val channel = Channel<Int>(3)
        //生产者
        val producer = GlobalScope.launch {
            List(3) {
                channel.send(it)
                println("send $it")
            }

            channel.close()
            println("""close channel. 
                |  - ClosedForSend: ${channel.isClosedForSend}
                |  - ClosedForReceive: ${channel.isClosedForReceive}""".trimMargin())
        }

        //消费者
        val consumer = GlobalScope.launch {
            for (element in channel){
                println("receive $element")
                delay(1000)
            }
            println("""After Consuming. 
                |   - ClosedForSend: ${channel.isClosedForSend} 
                |   - ClosedForReceive: ${channel.isClosedForReceive}""".trimMargin())
        }
        //channel最好是由主导的一方实现关闭
        joinAll(producer, consumer)
    }


    @Test
    fun `test broadcast`() = runBlocking<Unit> {
//        val broadcastChannel = BroadcastChannel<Int>(Channel.BUFFERED)
        //unsupported UNLIMITED or 0
        val channel = Channel<Int>()
        val broadcastChannel = channel.broadcast(3)
        val producer = GlobalScope.launch {
            List(3){
                delay(100)
                broadcastChannel.send(it)
            }
            broadcastChannel.close()
        }

        List(3){ index ->
            GlobalScope.launch {
                val receiveChannel = broadcastChannel.openSubscription()
                for (i in receiveChannel){
                    println("[#$index] received: $i")
                }
            }
        }.joinAll()
    }
}