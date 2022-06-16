class PlayerScore(val experience: Int, val level: Int) {
    //解构声明 就是声明 component1 component2若干个组件函数
    //让每个函数负责管理返回一个属性数据
    operator fun component1() = experience
    operator fun component2() = level
}

//public final class PlayerScore {
//    private final int experience;
//    private final int level;
//
//    public final int component1() {
//        return this.experience;
//    }
//
//    public final int component2() {
//        return this.level;
//    }
//
//    public final int getExperience() {
//        return this.experience;
//    }
//
//    public final int getLevel() {
//        return this.level;
//    }
//
//    public PlayerScore(int experience, int level) {
//        this.experience = experience;
//        this.level = level;
//    }
//}

fun main() {
    val (x, y) = PlayerScore(10, 20)

    println("$x, $y")
}