package com.dongnaoedu.kotlincoroutine

import kotlinx.coroutines.*
import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class CoroutineTest04 {

    //在 finally 中释放资源
    @Test
    fun `test release resources`() = runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                println("job: I'm running finally")
            }
        }
        delay(1300L) // 延迟⼀段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消该作业并且等待它结束
        println("main: Now I can quit.")
    }


    //use函数：该函数只能被实现了Closeable的对象使用，程序结束的时候会自动调用close方法，适合文件对象
    @Test
    fun `test use function`() = runBlocking {
        //读取文件方式一
        val br = BufferedReader(FileReader("C://Users//isabe//AndroidStudioProjects//Kt_Demo//txt//i have a dream.txt")) //打开文件读取
        with(br) { //对br中的属性和方法直接进行操作
            var line: String?

            while (true) {
                line = readLine() ?: break //读取一行数据，若为空则退出循环
                println(line) //打印读取的数据
            }
            close() //关闭文件读取
        }

        //读取文件方式二
        BufferedReader(FileReader("C://Users//isabe//AndroidStudioProjects//Kt_Demo//txt//i have a dream.txt")).use {
            var line: String?

            while (true) {
                line = it.readLine() ?: break //读取一行数据，若为空则退出循环
                println(line) //打印读取的数据
            }
        }

        //读取文件方式三
        println(BufferedReader(FileReader("D:\\I have a dream.txt")).readText()) //最简单的读取文件的方法
    }


    //处于取消中状态的协程不能够挂起（运行不能取消的代码）
    //当协程被取消后需要调用挂起函数，我们需要将清理任务的代码放置于 NonCancellable CoroutineContext 中。
    //这样会挂起运行中的代码，并保持协程的取消中状态直到任务处理完成。
    @Test
    fun `test cancel with NonCancellable`() = runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
//                println("job: I'm running finally")
//                delay(1000L)
//                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                withContext(NonCancellable) {
                    println("job: I'm running finally")
                    delay(1000L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
        }
        delay(1300L) // 延迟⼀段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消该作业并等待它结束
        println("main: Now I can quit.")
    }
}