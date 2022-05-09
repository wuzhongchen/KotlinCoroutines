class Student(

    //1.主构造函数里声明的属性
    _name:String,
    val age: Int
){
    //2.类级别的属性赋值
    var name = _name
    var score = 10
    private val hobby = "music"
    val subject:String

    //3.init初始化快里的属性赋值和函数调用
    init {
        println("initializing student...")
        subject = "math"
    }

    //4.次构造函数 还是要调用主构造函数的 把name和age传进去
    constructor(_name: String):this(_name,10){
        score = 20
    }
}

//public Student(@NotNull String _name, int age) {
//    Intrinsics.checkNotNullParameter(_name, "_name");
//    super();
//    this.age = age;
//    this.name = _name;
//    this.score = 10;
//    this.hobby = "music";
//    String var3 = "initializing student...";
//    System.out.println(var3);
//    this.subject = "math";
//}
//
//public Student(@NotNull String _name) {
//    Intrinsics.checkNotNullParameter(_name, "_name");
//    this(_name, 10);
//    this.score = 20;
//}

fun main() {
    Student("Jack")
}