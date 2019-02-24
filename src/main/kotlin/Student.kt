interface Study {
    fun study()
}

class Student(cheater: Cheater) : Person(), Study by cheater

class Cheater() : Study {
    override fun study() {
        println("Good good study, day day up!")
        println("Delegated by Cheater")
    }
}