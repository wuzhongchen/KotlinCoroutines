
//条件语句
fun main() {
    val age = 4
    if(age in 0..3){
        println("婴幼儿")
    }else if(age in 3..12){
        println("少儿")
    }else{
        println("未知")
    }

    if(age !in 1..3){
        println("不在1~3岁")
    }

    val school = "0小学"
    val level = when(school){
        "学前班"  -> "幼儿"
        "小学" -> "少儿"
        "中学" -> "青少年"
        else -> {
            println("未知")
        }
    }
    //函数类型
    println(level)
}
