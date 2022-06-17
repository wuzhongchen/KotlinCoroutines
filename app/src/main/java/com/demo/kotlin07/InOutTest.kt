//out 协变
// 只将泛型类型作为函数的返回（输出）
//可以称之为 生产接口 因为它主要是用来生产(product)指定的泛型对象
interface Production<out T> {
    fun product(): T
}

//in 逆变
// 只将泛型类型作为函数的入参（输入）
//可以称之为 消费者接口 因为它主要是用来消费(consume)
interface Consumer<in T> {
    fun consume(item: T)
}

//不变
// 既将泛型类型作为函数参数 又将函数类型作为函数的返回（输出）
interface ProductionConsumer<T> {
    fun product(): T
    fun consume(item: T)
}

open class Food
open class FastFood : Food()
class Burger : FastFood()

//生产者
//食品商店
class FoodStore : Production<Food>{
    override fun product(): Food {
        println("Produce food.")
        return Food()
    }
}
//快餐商店
class FastFoodStore : Production<FastFood>{
    override fun product(): FastFood {
        println("Produce FastFood.")
        return FastFood()
    }
}

//汉堡商店
class BurgerStore : Production<Burger>{
    override fun product(): Burger {
        println("Produce Burger.")
        return Burger()
    }
}

//消费者
class Everybody : Consumer<Food>{
    override fun consume(item: Food) {
        println("Eat food.")
    }
}

class ModernPeople : Consumer<FastFood>{
    override fun consume(item: FastFood) {
        println("Eat fastFood.")
    }
}

class American : Consumer<Burger>{
    override fun consume(item: Burger) {
        println("Eat burger.")
    }
}


fun main() {
    //赋值
    //子类泛型对象可以赋值给父类泛型对象，用 out。
    val production1: Production<Food> = FoodStore()

    //在Java里写法
    val production2: Production<FastFood> = FastFoodStore()
    //在Java里不行
    val production3: Production<Food> = FastFoodStore()
    //子类泛型对象Burger可以赋值给父类泛型对象Food，用 out。
    val production4: Production<Food> = BurgerStore()

    //父类泛型对象可以赋值给子类泛型对象，用 in。
    val consumer1: Consumer<Burger> = Everybody()

//    val consumer2: Consumer<Food> = ModernPeople()
    val consumer3: Consumer<Burger> = ModernPeople()
    consumer3.consume(Burger())
    val consumer4: Consumer<Burger> = American()
}