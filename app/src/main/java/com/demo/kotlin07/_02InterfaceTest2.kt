interface Movable2 {
    var maxSpeed: Int
        get() = (1..500).shuffled().last()
        set(value) {}
    var wheels: Int

    fun move(movable2: Movable2): String
}


class Car2(_name: String, override var wheels: Int = 4) : Movable2 {

    override var maxSpeed: Int
        get() = super.maxSpeed
        set(value) {}

    override fun move(movable2: Movable2): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
