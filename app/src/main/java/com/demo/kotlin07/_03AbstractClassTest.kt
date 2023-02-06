abstract class Gun(val range: Int) {

    protected fun doSomething(){
        println("doSomething")
    }

    abstract fun pullTrigger(): String
}

//多重继承

class AK47(val price: Int, override var wheels: Int = 4) : Gun(range = 500), Movable2{
    override fun pullTrigger(): String {
        TODO("not implemented")
    }

    override fun move(movable2: Movable2): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}