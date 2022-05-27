import java.io.File
//伴生对象
open class ConfigMap{

    companion object{
        private const val PATH= "xxxx"

        fun load() = File(PATH).readBytes()
    }

}

fun main() {
    //相当java static
    //只有调用load 才会初始化对象
    //无论ConfigMap类实例化多少次 object只存在一份
    ConfigMap.load()
}