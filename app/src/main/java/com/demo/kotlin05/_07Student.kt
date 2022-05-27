data class Student(var name: String, val age: Int) {
    private val hobby = "music"
    val subject: String
    //var score = 0

    init {
        println("initializing student")
        subject = "math"
    }

    constructor(_name: String) : this(_name, 10){
        //score = 10
    }

    //toString默认 打印主构造函数里写的属性
    //可以重写

    override fun toString(): String {
        return "Student(name='$name', age=$age, hobby='$hobby', subject='$subject')"
    }


}

fun main() {
    val  s = Student("Jack")
    val copy = s.copy("Rose")
    println(s)
    println(copy)
}