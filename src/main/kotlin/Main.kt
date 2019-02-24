import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import single.ApiHelper

suspend fun main(args: Array<String>) {

    println("Hello from Kotlin!")
    val launch = GlobalScope.launch {
        delay(1000)
        println("Hello from Kotlin Coroutines!")
        testDelegate()
        testInfixFunction()
        testDeclarationSiteVariance()
    }
    launch.join()
}

private fun testDelegate() {
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

fun testInfixFunction() {
    val teacher = Teacher()
    teacher greeting "Just Do IT!"
}

/**
 * 声明点型变（Declaration-site variance）
 * 型变注释in out，逆变和协变
 * 作用类似 Java中的 ?extend E & ?super E
 *
 * Out (协变)，如果你的类是将泛型作为内部方法的返回，那么可以用 out
 * In(逆变)，如果你的类是将泛型对象作为函数的参数，那么可以用 in
 * Invariant(不变)，如果既将泛型作为函数参数，又将泛型作为函数的输出，那就既不用 in 或 out。
 */
fun testDeclarationSiteVariance() {
    val persons = ArrayList<Person>()
    persons.add(Person())
    persons.add(Student(Cheater()))
    persons.add(Teacher())
    handlePersonInfo(persons)

    val students = ArrayList<Student>()
    students.add(Student(Cheater()))
    handlePersonInfo(students)

    consumePersonInfo(persons)
    //consumePersonInfo(students) error，和Java ?super E同理
}

/**
 * 这里的List是Kotlin的集合类
 * 默认处理了类似于Java的?extend E的场景
 * public interface List<out E> : Collection<E>
 * 也就是说，Kotlin的List是只读的
 */
fun handlePersonInfo(persons: List<Person>) {
    val person = persons.get(0)
}

/**
 * 和上面的方法等同
 */
fun handlePersonInfo(persons: ArrayList<out Person>) {
    val person = persons.get(0)
}

/**
 * 类似于Java的?super E
 */
fun consumePersonInfo(persons: ArrayList<in Person>) {
    persons.add(Teacher())
    persons.add(Student(Cheater()))
    //persons.add(Any()) error，和Java ?super E同理
}
