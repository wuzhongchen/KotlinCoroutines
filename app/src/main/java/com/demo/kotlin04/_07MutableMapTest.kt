fun main() {
    val mutableMap = mutableMapOf("Jack" to 20, "Jason" to 18, "Jack" to 30)

    mutableMap += "Jimmy" to 30

    println(mutableMap)

    mutableMap.put("Jimmy", 31)

    mutableMap.getOrPut("Rose"){18}

    println(mutableMap)

}