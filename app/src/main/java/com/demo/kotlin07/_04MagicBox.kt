class MagicBox1<T>(item: T) {
    private var subject: T = item
}

class Boy1(val name: String, val age: Int)

class Dog1(val weight: Int)

fun main() {
    //指定泛型的参数由放在一对<>里的字母T表示
    //T是个代表Item类型的占位符
    //接收任何类型的item作为主构造函数值（item: T）
    //并将item值赋给同样是T类型的subject私有属性
    val box1:MagicBox1<Boy1> = MagicBox1(Boy1("Jack",20))
    val box2:MagicBox1<Dog1> = MagicBox1(Dog1(20))
}
