interface Movable {
    var maxSpeed: Int
    var wheels: Int

    fun move(movable: Movable): String
}


class Car(_name: String, override var wheels: Int = 4) : Movable {

    override var maxSpeed: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun move(movable: Movable): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
