//密封类
sealed class LicenseStatus2 {

    object UnQualified : LicenseStatus2()
    object Learning : LicenseStatus2()
    //密封类可以有若干子类 要继承密封类 ，这些自雷必须和它定义在同一个文件里
    class Qualified(val licenseId: String) : LicenseStatus2()

}

class Driver2(var status: LicenseStatus2) {
    fun checkLicense(): String {
        return when(status){
            is LicenseStatus2.UnQualified -> "没资格"
            is LicenseStatus2.Learning -> "在学"
            is LicenseStatus2.Qualified -> "有资格，驾驶证编号：${(this.status as LicenseStatus2.Qualified).licenseId}"
            else -> throw IllegalStateException("Some error, forgot clause!")
        }
    }
}

fun main() {
    val status = LicenseStatus2.Qualified("238239329")
    val driver = Driver2(status)
    println(driver.checkLicense())
}