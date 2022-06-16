abstract class Gun(val range: Int) {

    protected fun doSomething(){
        println("doSomething")
    }

    abstract fun pullTrigger(): String
}

//多重继承

class AK47(val price: Int) : Gun(range = 500){
    override fun pullTrigger(): String {
        TODO("not implemented")
    }
}