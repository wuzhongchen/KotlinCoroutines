import java.io.File

fun main() {
    val file1 = File("/Users/hs/AndroidStudioProjects/Kt_Demo/txt/i have a dream.txt")
    file1.setReadable(true)
    file1.setWritable(true)
    file1.setExecutable(false)

    val file2 = File("/Users/hs/AndroidStudioProjects/Kt_Demo/txt/i have a dream.txt").apply {
        setReadable(true)
        setWritable(true)
        setExecutable(false)
    }
}