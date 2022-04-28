import java.io.File

fun main() {
    /**
     * also返回接受者对象 而let返回lambda结果
     * also适用 同一原始对象
     * 基于原始接受者对象执行额外的链式调用
     */
    //kotlin 可以先声明它 紧接着初始化
    var fileContents:List<String>
    val file = File("/Users/hs/AndroidStudioProjects/Kt_Demo/txt/i have a dream.txt")
        .also {
            //先打印 文件名
            println(it.name)
        }.also {
            //打印 List集合 没有换行
            fileContents = it.readLines()
        }

    println(fileContents)
}