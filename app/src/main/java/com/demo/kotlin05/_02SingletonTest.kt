//object对象声明
//《91-对象声明》
object ApplicationConfig{
    init {
        println("ApplicationConfig loading...")
    }

    fun doSomething(){
        println("doSomething")
    }
}

fun main() {
    //既是类名，又是实例名
    //同一个 实例
    ApplicationConfig.doSomething()
    println(ApplicationConfig)//ApplicationConfig@57829d67
    println(ApplicationConfig)//    ApplicationConfig@57829d67

}