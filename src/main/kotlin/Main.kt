import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URL

suspend fun main(args: Array<String>) {

    println("Hello from Kotlin!")

    val launch = GlobalScope.launch {
        delay(1000)
        println("Hello from Kotlin Coroutines!")
        val xiaoming = Student(cheater = Cheater())
        xiaoming.study()

        val teacher = Teacher()
        teacher.printName()
        teacher.name = "Miss Chen"
        teacher.printName()

        val apiHelper = ApiHelper.instance
        apiHelper.path = "/tickids/new"
        apiHelper.debug = false
    }
    launch.join()
}