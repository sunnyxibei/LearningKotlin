open class Person constructor() {

    /**
     * 如果一个类有首要构造器，那么每个次要构造器都要代理到首要构造器，可以通过this关键字实现
     */
    constructor(name: String) : this() {
    }

    /**
     * 中缀表达式
     * 1、必须是成员函数或扩展函数；
     * 2、必须只有一个参数；
     * 3、参数不得接受可变数量的参数且不能有默认值；
     *
     * 看上去这应该是为了DSL专门创建的一个语法？
     */
    infix fun greeting(str: String) {
        println(str)
    }

}